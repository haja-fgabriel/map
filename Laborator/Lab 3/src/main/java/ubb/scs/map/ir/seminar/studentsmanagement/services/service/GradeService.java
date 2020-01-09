package ubb.scs.map.ir.seminar.studentsmanagement.services.service;

import com.sun.tools.javac.util.Pair;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.*;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.ValidationException;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.FileRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.Subjects;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.Weeks;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.events.ChangeEventType;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.events.GradeChangeEvent;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.observer.Observable;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.observer.Observer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


// TODO - add observable to GradeService and connect it to GradeController

public class GradeService implements Observable<GradeChangeEvent> {
    FileRepository<Pair<Long, Long>, Grade> repository;
    FileRepository<Long, Homework> repoHomework;
    FileRepository<Long, Student> repoStudent;
    FileRepository<Pair<Long, Long>, GradeDTO> repoGradeDTO;
    List<Observer<GradeChangeEvent>> observers;

    public GradeService(
            FileRepository<Pair<Long, Long>, Grade> repository,
            FileRepository<Long, Homework> repoHomework,
            FileRepository<Long, Student> repoStudent,
            FileRepository<Pair<Long, Long>, GradeDTO> repoGradeDTO) {
        this.repository = repository;
        this.repoHomework = repoHomework;
        this.repoStudent = repoStudent;
        this.repoGradeDTO = repoGradeDTO;
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer<GradeChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<GradeChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(GradeChangeEvent t) {
        observers.stream().forEach(observer -> observer.update(t));
    }

    private void validateIDs(Long homeworkID, Long studentID) {
        String errors = "";

        Homework homework = repoHomework.findOne(homeworkID);
        if (homework == null)
            errors += "homework ID is invalid";

        Student student = repoStudent.findOne(studentID);
        if (student == null)
            errors += "; student ID is invalid";

        if (errors.length() > 0)
            throw new IllegalArgumentException(errors);
    }

    private float getTotalPenalty(int delay) {
        // TODO implement actual time-off
        int timeOff = 0;
        return Math.max(0, (delay - timeOff) * Subjects.penalty);
    }

    private float getFinalGrade(int delay, float number) {

        // TODO implement actual time-off
        int timeOff = 0;

        if (delay - timeOff > Subjects.maxDelay) {
            return 1;
        }

        return Math.max(1, number - (delay - timeOff) * Subjects.penalty);
    }

    public Iterable<Grade> getAllGrades() {
        return repository.findAll();
    }

    public Grade findGrade(Long homeworkID, Long studentID) {
        return repository.findOne(Pair.of(homeworkID, studentID));
    }


    public Grade addGrade(Long homeworkID, Long studentID, float number, LocalDateTime localDate, String feedback, String professor, int timeOff) throws ValidationException {
        validateIDs(homeworkID, studentID);

        Date date = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
        if (Weeks.getWeekInSemester(date) <= repoHomework.findOne(homeworkID).getStartWeek())
            throw new IllegalArgumentException("The student cannot present the assignment this soon");

        Homework homework = repoHomework.findOne(homeworkID);
        //System.out.println(Weeks.getCurrentWeek() + " " + homework.getDeadlineWeek());
        int delay = Math.max(Weeks.getWeekInSemester(date) - homework.getDeadlineWeek(), 0);
        float finalGrade = getFinalGrade(delay, number);
        //System.out.println();
        if (localDate == null)
            localDate = LocalDateTime.now();

        Grade g = new Grade(homeworkID, studentID, localDate, finalGrade, feedback, professor);
        g.setDelay(delay);

        // save in GradeDTO repository too, because we need the info when
        // mailing the student
        GradeDTO gradeDTO = new GradeDTO(
                homeworkID,
                studentID,
                finalGrade,
                Weeks.getWeekInSemester(date),
                homework.getDeadlineWeek(),
                feedback);
        repoGradeDTO.save(gradeDTO);

        Grade o = repository.save(g);
        notifyObservers(new GradeChangeEvent(ChangeEventType.ADD, g));
        return o;
    }

    public Grade deleteGrade(Long homeworkID, Long studentID) {
        Grade g = repository.delete(new Pair<>(homeworkID, studentID));
        notifyObservers(new GradeChangeEvent(ChangeEventType.DELETE, g));
        return g;
    }

    public Grade updateGrade(Long homeworkID, Long studentID, LocalDateTime localDate, float number, String professor, String feedback) throws RuntimeException {
        validateIDs(homeworkID, studentID);

        if (localDate == null)
            localDate = LocalDateTime.now();
        Date date = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());

        if (Weeks.getWeekInSemester(date) <= repoHomework.findOne(homeworkID).getStartWeek())
            throw new IllegalArgumentException("The student cannot present the assignment this soon");

        Homework homework = repoHomework.findOne(homeworkID);
        int delay = Math.max(Weeks.getWeekInSemester(date) - homework.getDeadlineWeek(), 0);
        Grade toUpdate = repository.findOne(new Pair<>(homeworkID, studentID));
        if (toUpdate == null)
            return null;
        toUpdate.setNumber(number);
        toUpdate.setProfessor(professor);
        toUpdate.setFeedback(feedback);
        toUpdate.setDate(localDate);
        Grade old = repository.update(toUpdate);
        notifyObservers(new GradeChangeEvent(ChangeEventType.UPDATE, toUpdate, old));
        return old;
    }

    public Iterable<Student> getStudentsByHomework(Long homeworkID) {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .filter(grade -> grade.getHomeworkID() == homeworkID)
                .map(grade -> repoStudent.findOne(grade.getStudentID()))
                .collect(Collectors.toList());

    }

    public Iterable<Student> getStudentsByHomeworkProfessor(Long homeworkID, String professor) {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .filter(grade -> grade.getHomeworkID() == homeworkID && grade.getProfessor().equals(professor))
                .map(grade -> repoStudent.findOne(grade.getStudentID()))
                .collect(Collectors.toList());
    }

    public Iterable<Grade> getGradesByHomeworkWeek(Long homeworkID, int week) {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .filter(grade -> grade.getHomeworkID() == homeworkID && Weeks.getWeekInSemester(Date.from(grade.getDate().atZone(ZoneId.systemDefault()).toInstant())) == week)
                //.map(grade -> repoStudent.findOne(grade.getStudentID()))
                .collect(Collectors.toList());
    }

    public Iterable<Student> getAllStudents() {
        return repoStudent.findAll();
    }

    public Iterable<Homework> getAllHomework() {
        return repoHomework.findAll();
    }

    // TODO implement functions
    public Iterable<StudentGradeReport> getAverageForStudents() {
        Map<Long, StudentGradeReport> toReturn = new HashMap<>();

        repoStudent.findAll().forEach(student -> {
            Long sid = student.getId();
            StudentGradeReport studentGradeReport = new StudentGradeReport(sid);

            float average[] = {0};
            int weight[] = {0};

            List<Grade> grades = StreamSupport.stream(repository.findAll().spliterator(), false)
                    .filter(grade -> grade.getStudentID() == sid)
                    .collect(Collectors.toList());



            grades.forEach(grade -> {
                Date checkedDate = Date.from(grade.getDate().atZone(ZoneId.systemDefault()).toInstant());
                Long hid = grade.getHomeworkID();
                int allocated = Weeks.getWeekInSemester(checkedDate) -
                        repoHomework.findOne(hid).getStartWeek();
                average[0] += grade.getNumber() * allocated;
                weight[0] += allocated;
            });

            average[0] /= weight[0];

            studentGradeReport.setName(student.getName());
            studentGradeReport.setAverage(average[0]);
            toReturn.put(sid, studentGradeReport);
        });

        return toReturn.values();
    }

    public HomeworkGradeReport getMostDifficultHomework() {
        HomeworkGradeReport[] result = {new HomeworkGradeReport(-1l)};

        repoHomework.findAll().forEach(homework -> {
            Long hid = homework.getId();
            HomeworkGradeReport homeworkGradeReport = new HomeworkGradeReport(hid);

            float[] average = {0};
            int count = 0;

            List<Grade> grades = StreamSupport.stream(repository.findAll().spliterator(), false)
                    .filter(grade -> grade.getHomeworkID() == hid)
                    .collect(Collectors.toList());

            count = grades.size();

            grades.forEach(grade -> average[0] += grade.getNumber());
            average[0] /= StreamSupport.stream(repoHomework.findAll().spliterator(), false)
                    .collect(Collectors.toList())
                    .size();


            homeworkGradeReport.setDescription(homework.getDescription());
            homeworkGradeReport.setAverage(average[0]);

            if (result[0].getHomeworkID() == -1 || average[0] < result[0].getAverage())
                result[0] = homeworkGradeReport;
        });

        return result[0];
    }

    public Iterable<StudentGradeReport> getPassedStudents() {
        return StreamSupport.stream(getAverageForStudents().spliterator(), false)
                .filter(studentGradeReport -> studentGradeReport.getAverage() >= 4.0f)
                .collect(Collectors.toList());
    }

    public Iterable<Student> getStudentsWithoutDelays() {
        return StreamSupport.stream(repoStudent.findAll().spliterator(), false)
                .filter(student ->
                        StreamSupport.stream(repository.findAll().spliterator(), false)
                                .noneMatch(grade -> grade.getStudentID().equals(student.getId())
                                        && grade.getDelay() != 0))
                .collect(Collectors.toList());
    }
}

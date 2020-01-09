package ubb.scs.map.ir.seminar.studentsmanagement.services.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Homework;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.HomeworkGradeReport;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Student;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.GradeDTOValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.GradeValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.HomeworkValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.StudentValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml.GradeDTOXMLRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml.GradeXMLRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml.HomeworkXMLRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml.StudentXMLRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

class GradeServiceTest {

    private GradeDTOXMLRepository gradeDTORepo;
    private GradeXMLRepository gradeRepo;
    private HomeworkXMLRepository homeworkRepo;
    private StudentXMLRepository studentRepo;
    private GradeService gradeService;

    @BeforeEach
    void setUp() {
        gradeDTORepo = new GradeDTOXMLRepository(new GradeDTOValidator(), "test_studgrades.txt");
        gradeRepo = new GradeXMLRepository(new GradeValidator(), "test_grades.txt");
        studentRepo = new StudentXMLRepository(new StudentValidator(), "test_students.txt");
        homeworkRepo = new HomeworkXMLRepository(new HomeworkValidator(), "test_homework.txt");
        gradeService = new GradeService(gradeRepo, homeworkRepo, studentRepo, gradeDTORepo);
    }

    @AfterEach
    void tearDown() {
        try {
            if (Files.exists(Path.of("test_studgrades.txt")))
                Files.delete(Path.of("test_studgrades.txt"));
            if (Files.exists(Path.of("test_students.txt")))
                Files.delete(Path.of("test_students.txt"));
            if (Files.exists(Path.of("test_homework.txt")))
                Files.delete(Path.of("test_homework.txt"));
            if (Files.exists(Path.of("test_grades.txt")))
                Files.delete(Path.of("test_grades.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllGrades() {
    }

    @Test
    void addGrade() {
        populate();
    }

    private void populate() {
        Homework h1 = new Homework("lab 5 logica", 6, 7);
        h1.setId(1l);
        Homework h2 = new Homework("lab 6 logica", 8, 10);
        h2.setId(2l);

        Student s1 = new Student("Camelia", 10f, "213");
        s1.setId(1l);
        Student s2 = new Student("Manole", 9.7f, "224");
        s2.setId(2l);
        Student s3 = new Student("Mihoc", 9.4f, "224");
        s3.setId(3l);

        homeworkRepo.save(h1);
        homeworkRepo.save(h2);
        studentRepo.save(s1);
        studentRepo.save(s2);
        studentRepo.save(s3);
        LocalDateTime date = LocalDateTime.of(2019, 11, 10, 12, 35);
        LocalDateTime date2 = LocalDateTime.of(2019, 12, 19, 12, 35);
        gradeService.addGrade(1l, 1l, 1, date, "intre fnc sau fnd e diferenta f mare", "mihis", 0);
        gradeService.addGrade(1l, 2l, 6, date, "se putea si mai bine", "mihis", 0);
        gradeService.addGrade(1l, 3l, 7, date, "indenteaza-l frumos", "mihis", 0);
        gradeService.addGrade(2l, 3l, 4, date2, "m-ai dezamagit", "mihis", 0);
    }

    @Test
    void deleteGrade() {
    }

    @Test
    void updateGrade() {
    }

    @Test
    void getStudentsByHomework() {
        populate();

        gradeService.getStudentsByHomework(1l).forEach(student -> System.out.println("getStudentsByHomework: " + student));
    }

    @Test
    void getStudentsByHomeworkProfessor() {
        populate();

        gradeService.getStudentsByHomeworkProfessor(1l, "mihis")
                .forEach(student -> System.out.println("getStudentsByHomeworkProfessor: " + student));
    }

    @Test
    void getHomeworkGradesByWeek() {

    }

    @Test
    void getAverageForStudents() {
        populate();
        gradeService.getAverageForStudents()
                .forEach(gradeReport -> System.out.println("getAverageForStudents: " + gradeReport));
    }

    @Test
    void getMostDifficultHomework() {
        populate();
        HomeworkGradeReport homeworkGradeReport = gradeService.getMostDifficultHomework();
        assert(homeworkGradeReport.getAverage() == 2f);
    }

    @Test
    void getPassedStudents() {
        populate();
        gradeService.getPassedStudents().forEach(gradeReport -> System.out.println("getPassedStudents: " + gradeReport));
    }

    @Test
    void getStudentsWithoutDelays() {
        populate();
        System.out.println("[DEBUG] intra?");
        gradeService.getStudentsWithoutDelays().forEach(student -> System.out.println("getStudentsWithoutDelays: " + student));
    }
}
package ubb.scs.map.ir.seminar.studentsmanagement;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.GradeDTOValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.GradeValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.HomeworkValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.StudentValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.csv.GradeFileRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.csv.HomeworkFileRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.csv.StudentFileRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml.GradeDTOXMLRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.services.config.ApplicationContext;
import ubb.scs.map.ir.seminar.studentsmanagement.services.service.GradeService;
import ubb.scs.map.ir.seminar.studentsmanagement.services.service.HomeworkService;
import ubb.scs.map.ir.seminar.studentsmanagement.services.service.StudentService;
import ubb.scs.map.ir.seminar.studentsmanagement.ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {
        HomeworkFileRepository homeworkFileRepository = new HomeworkFileRepository(new HomeworkValidator(),
                getProperty("database.catalog.teme"));
        HomeworkService homeworkService = new HomeworkService(homeworkFileRepository);

        StudentFileRepository studentFileRepository = new StudentFileRepository(new StudentValidator(),
                getProperty("database.catalog.students"));
        StudentService studentService = new StudentService(studentFileRepository);

        GradeDTOValidator gradeDTOValidator = new GradeDTOValidator();
        GradeDTOXMLRepository gradeDTOXMLRepository = new GradeDTOXMLRepository(gradeDTOValidator,
                getProperty("database.catalog.notedto"));

        GradeFileRepository gradeFileRepository = new GradeFileRepository(new GradeValidator(),
                getProperty("database.catalog.note"));
        GradeService gradeService = new GradeService(gradeFileRepository, homeworkFileRepository, studentFileRepository, gradeDTOXMLRepository);

        ConsoleUI ui = new ConsoleUI(homeworkService, studentService, gradeService);
        ui.run();
    }

    private static String getProperty(String s) {
        return ApplicationContext.getPROPERTIES().getProperty(s);
    }
}

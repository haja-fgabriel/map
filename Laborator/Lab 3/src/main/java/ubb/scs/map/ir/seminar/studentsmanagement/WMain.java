package ubb.scs.map.ir.seminar.studentsmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ubb.scs.map.ir.seminar.studentsmanagement.controllers.MainWindowController;
import ubb.scs.map.ir.seminar.studentsmanagement.controllers.domain.GradeController;
import ubb.scs.map.ir.seminar.studentsmanagement.controllers.domain.HomeworkController;
import ubb.scs.map.ir.seminar.studentsmanagement.controllers.domain.StudentController;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Grade;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.GradeDTOValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.GradeValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.HomeworkValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.StudentValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml.GradeDTOXMLRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml.GradeXMLRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml.HomeworkXMLRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml.StudentXMLRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.services.config.ApplicationContext;
import ubb.scs.map.ir.seminar.studentsmanagement.services.service.GradeService;
import ubb.scs.map.ir.seminar.studentsmanagement.services.service.HomeworkService;
import ubb.scs.map.ir.seminar.studentsmanagement.services.service.StudentService;

import java.io.IOException;

public class WMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        StudentXMLRepository studentRepository = new StudentXMLRepository(new StudentValidator(),
                ApplicationContext.getPROPERTIES().getProperty("database.catalog.students"));
        StudentService studentService = new StudentService(studentRepository);

        HomeworkXMLRepository homeworkRepository = new HomeworkXMLRepository(new HomeworkValidator(),
                ApplicationContext.getPROPERTIES().getProperty("database.catalog.teme"));
        HomeworkService homeworkService = new HomeworkService(homeworkRepository);

        GradeXMLRepository gradeRepository = new GradeXMLRepository(new GradeValidator(),
                ApplicationContext.getPROPERTIES().getProperty("database.catalog.note"));
        GradeDTOXMLRepository gradeDTORepository = new GradeDTOXMLRepository(new GradeDTOValidator(),
                ApplicationContext.getPROPERTIES().getProperty("database.catalog.folderFeedback"));
        GradeService gradeService = new GradeService(gradeRepository, homeworkRepository, studentRepository, gradeDTORepository);


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/mainWindow.fxml"));
        TabPane layout = loader.load();

        primaryStage.setTitle("Student Management");
        primaryStage.setScene(new Scene(layout));


        MainWindowController mainController = loader.getController();
        StudentController studentController = mainController.getStudentController();
        studentController.setService(studentService);

        HomeworkController homeworkController = mainController.getHomeworkController();
        homeworkController.setService(homeworkService);

        GradeController gradeController = mainController.getGradeController();
        gradeController.setService(gradeService);

        primaryStage.show();
    }
}

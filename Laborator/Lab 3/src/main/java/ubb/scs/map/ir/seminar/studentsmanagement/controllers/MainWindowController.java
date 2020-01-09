package ubb.scs.map.ir.seminar.studentsmanagement.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ubb.scs.map.ir.seminar.studentsmanagement.controllers.domain.GradeController;
import ubb.scs.map.ir.seminar.studentsmanagement.controllers.domain.HomeworkController;
import ubb.scs.map.ir.seminar.studentsmanagement.controllers.domain.StudentController;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.events.ChangeEventType;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.events.GradeChangeEvent;

public class MainWindowController {

    @FXML
    public TabPane tabs;
    @FXML
    private StudentController studentViewController;
    @FXML
    private HomeworkController homeworkViewController;
    @FXML
    private GradeController gradeViewController;

    public HomeworkController getHomeworkController() {
        return homeworkViewController;
    }

    public GradeController getGradeController() {
        return gradeViewController;
    }

    public StudentController getStudentController() {
        return studentViewController;
    }

    @FXML
    public void initialize() {
        MainWindowController controller = this;

        // Nu avea sens sa refactorizez foarte mult cod, sa am un singur service pentru toate 3 controllerele.
        // Evident, testele ar fi trebuit refactorizate si ele si numai incurca.
        tabs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> value, Tab old, Tab now) {
                if (now.getText().equals("Grades")) {
                    controller.getGradeController().update(null);
                }
            }
        });
    }
}

package ubb.scs.map.ir.seminar.studentsmanagement.controllers.domain;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Optional;
import java.util.Stack;

public class GradeConfirmationDialog {

    public static Optional<Integer> confirmGrade(Long homeworkID, Long studentID, Float value, String professor, String feedback) {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);


        dialog.setTitle("Student Confirmation");

        int timeOff = 0;

        GridPane grid = new GridPane();
        dialog.setHeaderText("Confirm new student?");

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,30,10,30));

        TextField textfieldHID = new TextField();
        textfieldHID.setEditable(false);
        textfieldHID.setText(Long.toString(homeworkID));

        TextField textfieldSID = new TextField();
        textfieldSID.setEditable(false);
        textfieldSID.setText(Long.toString(studentID));

        TextField textfieldValue = new TextField();
        textfieldValue.setEditable(false);
        textfieldValue.setText(Float.toString(value));

        TextField textfieldProfessor = new TextField();
        textfieldProfessor.setEditable(false);
        textfieldProfessor.setText(professor);

        TextArea textfieldFeedback = new TextArea();
        textfieldFeedback.setText(feedback);
        textfieldFeedback.setEditable(false);
        textfieldFeedback.setPrefRowCount(2);
        textfieldFeedback.setPrefColumnCount(13);

        grid.add(textfieldHID, 1, 0);
        grid.add(textfieldSID, 1, 1);
        grid.add(textfieldValue, 1, 2);
        grid.add(textfieldProfessor, 1, 3);
        grid.add(textfieldFeedback, 1, 4);

        grid.add(new Label("Homework ID"), 0, 0);
        grid.add(new Label("Student ID"), 0, 1);
        grid.add(new Label("Value"), 0, 2);
        grid.add(new Label("Professor"), 0, 3);
        grid.add(new Label("Feedback"), 0, 4);

        GridPane timeOffPane = new GridPane();
        timeOffPane.setHgap(10);

        CheckBox timeOffCheck = new CheckBox("Time-off");
        TextField textfieldTimeOff = new TextField();
        timeOffPane.setPadding(new Insets(0,0,0,0));
        timeOffPane.add(timeOffCheck,0,0);
        timeOffPane.add(textfieldTimeOff, 1, 0);
        Label labelWeeks = new Label("weeks");

        textfieldTimeOff.setVisible(false);
        textfieldTimeOff.setPrefColumnCount(6);
        labelWeeks.setVisible(false);

        timeOffPane.add(labelWeeks, 2, 0);
        grid.add(timeOffPane, 1,5);
        timeOffCheck.setOnAction(actionEvent -> {
            boolean checked = timeOffCheck.isSelected();
            textfieldTimeOff.setVisible(checked);
            labelWeeks.setVisible(checked);
        });


        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.YES) {
                if (timeOffCheck.isSelected())
                    return 0;
                return Integer.parseInt(textfieldTimeOff.getText());
            }
            return null;
        });

        return dialog.showAndWait();
    }
}

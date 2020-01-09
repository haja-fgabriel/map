package ubb.scs.map.ir.seminar.studentsmanagement.controllers.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ubb.scs.map.ir.seminar.studentsmanagement.controllers.MessageAlert;
import ubb.scs.map.ir.seminar.studentsmanagement.controllers.ReportDialog;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.*;
import ubb.scs.map.ir.seminar.studentsmanagement.services.service.GradeService;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.events.GradeChangeEvent;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.observer.Observer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class GradeController implements Observer<GradeChangeEvent> {
    private GradeService service;

    @FXML
    public ComboBox<Long> comboboxSID;
    @FXML
    public ComboBox<Long> comboboxHID;
    @FXML
    public TableColumn<Grade, Long> hidColumn;
    @FXML
    public TableColumn<Grade, Long> sidColumn;
    @FXML
    public TableColumn<Grade, Float> valueColumn;
    @FXML
    public TableColumn<Grade, String> professorColumn;
    @FXML
    public TableColumn<Grade, String> feedbackColumn;
    @FXML
    public TextField textfieldHID;
    @FXML
    public TextField textfieldSID;
    @FXML
    public TextField textfieldProfessor;
    @FXML
    public TextArea textfieldFeedback;
    @FXML
    public TextField textfieldValue;

    @FXML
    public ObservableList<Grade> model = FXCollections.observableArrayList();
    @FXML
    public ObservableList<Long> hids = FXCollections.observableArrayList();
    @FXML
    public ObservableList<Long> sids = FXCollections.observableArrayList();

    @FXML
    public TableView view;

    public void setService(GradeService service) {
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    private void initModel() {
        Stream<Homework> homework = StreamSupport
                .stream(service.getAllHomework().spliterator(), false);
        Stream<Student> students = StreamSupport
                .stream(service.getAllStudents().spliterator(), false);
        Stream<Grade> grades = StreamSupport
                .stream(service.getAllGrades().spliterator(), false);

        hids.setAll(homework.map(Entity::getId).collect(Collectors.toList()));
        sids.setAll(students.map(Entity::getId).collect(Collectors.toList()));
        model.setAll(grades.collect(Collectors.toList()));
//
//        List<Homework> hs = homework.collect(Collectors.toList());
//        int toSelect = -1;
//        int minimum = 0;
//
//        for (int i = 0; i < hs.size(); i++)
//            if (toSelect < 0 || Math.abs(Weeks.getCurrentWeek() - hs.get(i).getDeadlineWeek()) < minimum) {
//                minimum = Math.abs(Weeks.getCurrentWeek() - hs.get(i).getDeadlineWeek());
//                toSelect = i;
//            }
//      comboboxHID.getSelectionModel().select(toSelect);
    }

    @Override
    public void update(GradeChangeEvent gradeChangeEvent) {
        initModel();
    }

    @FXML
    public void initialize() {
        hidColumn.setCellValueFactory(new PropertyValueFactory<>("homeworkID"));
        sidColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        professorColumn.setCellValueFactory(new PropertyValueFactory<>("professor"));
        feedbackColumn.setCellValueFactory(new PropertyValueFactory<>("feedback"));
        view.setItems(model);
        comboboxHID.setItems(hids);
        comboboxSID.setItems(sids);

        view.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            Grade grade = (Grade) newValue;
//            textfieldHID.setText(Long.toString(grade.getHomeworkID()));
//            textfieldSID.setText(Long.toString(grade.getStudentID()));
            comboboxHID.getSelectionModel().select(grade.getHomeworkID());
            comboboxSID.getSelectionModel().select(grade.getStudentID());
            textfieldFeedback.setText(grade.getFeedback());
            textfieldProfessor.setText(grade.getProfessor());
            textfieldValue.setText(Float.toString(grade.getNumber()));
        });
    }

    @FXML
    public void handleAdd(ActionEvent actionEvent) {
        Long hid = comboboxHID.getSelectionModel().getSelectedItem();
        Long sid = comboboxSID.getSelectionModel().getSelectedItem();

        String feedback = textfieldFeedback.getText();
        String professor = textfieldProfessor.getText();
        Float value = Float.parseFloat(textfieldValue.getText());

        try {
            if (service.findGrade(hid, sid) != null)
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Add", "Nota deja exista");
            else {
                Optional<Integer> confirmAdd = GradeConfirmationDialog.confirmGrade(hid, sid, value, professor, feedback);
                confirmAdd.ifPresent(result -> {
                    service.addGrade(hid, sid, value, null, feedback, professor, result);
                });
            }
        } catch (RuntimeException e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
    }

    @FXML
    public void handleUpdate(ActionEvent actionEvent) {
        Long hid = comboboxHID.getSelectionModel().getSelectedItem();
        Long sid = comboboxSID.getSelectionModel().getSelectedItem();
        Float value = Float.parseFloat(textfieldValue.getText());
        String professor = textfieldProfessor.getText();
        String feedback = textfieldFeedback.getText();
        try {
            Grade g = service.updateGrade(hid, sid, null, value, professor, feedback);
            if (g != null)
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Update", "Nota nu exista");
        } catch (RuntimeException e) {
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
    }

    @FXML
    public void handleRemove(ActionEvent actionEvent) {
        Grade s = (Grade) view.getSelectionModel().getSelectedItem();
        if (s == null)
            return;
        try {
            Grade n = service.deleteGrade(s.getHomeworkID(), s.getStudentID());
            if (n == null)
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Update", "Nota nu exista");
        } catch (RuntimeException ex) {
            MessageAlert.showErrorMessage(null, ex.getCause().toString() + ": " + ex.getMessage());
        }
    }

    public void handleStudentDelays(ActionEvent actionEvent) {
        System.out.println("[DEBUG] intra");
        Iterable<Student> report = service.getStudentsWithoutDelays();
        Collection<GenericModel> students = StreamSupport.stream(report.spliterator(), false)
                .map(student -> new GenericModel(Long.toString(student.getId()), student.getName()))
                .collect(Collectors.toList());
        ReportDialog.showReport(null, "Sample", students, "Student ID", "Name");
    }

    public void handlePassedStudents(ActionEvent actionEvent) {
        Iterable<StudentGradeReport> report = service.getPassedStudents();
    }

    public void handleAverageStudents(ActionEvent actionEvent) {
        Iterable<StudentGradeReport> report = service.getAverageForStudents();
    }

    public void handleMostDifficultHomework(ActionEvent actionEvent) {
        HomeworkGradeReport report = service.getMostDifficultHomework();
    }
}

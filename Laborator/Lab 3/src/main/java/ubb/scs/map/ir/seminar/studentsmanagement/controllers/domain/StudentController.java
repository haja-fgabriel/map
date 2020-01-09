package ubb.scs.map.ir.seminar.studentsmanagement.controllers.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ubb.scs.map.ir.seminar.studentsmanagement.controllers.MessageAlert;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Student;
import ubb.scs.map.ir.seminar.studentsmanagement.services.service.StudentService;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.events.StudentChangeEvent;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.observer.Observer;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StudentController implements Observer<StudentChangeEvent> {

    ObservableList<Student> model = FXCollections.observableArrayList();

    private StudentService service;

    public void setService(StudentService service) {
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    @FXML
    public TextField textfieldGroup;
    @FXML
    public TextField textfieldMedia;
    @FXML
    public TextField textfieldName;
    @FXML
    public TextField textfieldID;
    @FXML
    public Button addButton;
    @FXML
    public Button updateButton;
    @FXML
    public Button removeButton;
    @FXML
    public TableColumn<Student, Long> idColumn;
    @FXML
    public TableColumn<Student, String> nameColumn;
    @FXML
    public TableColumn<Student, Float> mediaColumn;
    @FXML
    public TableColumn<Student, String> groupColumn;
    @FXML
    public TableView<Student> view;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Long>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("group"));
        mediaColumn.setCellValueFactory(new PropertyValueFactory<Student, Float>("media"));
        view.setItems(model);
        view.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            Student entity = (Student) newValue;
            textfieldID.setText(Long.toString(entity.getId()));
            textfieldGroup.setText(entity.getGroup());
            textfieldMedia.setText(Float.toString(entity.getMedia()));
            textfieldName.setText(entity.getName());
        });
    }

    private void initModel() {
        model.setAll(StreamSupport
                .stream(service.getAllStudents().spliterator(), false)
                .collect(Collectors.toList()));
    }

    @FXML
    public void handleAdd(ActionEvent e) {
        Long id = Long.parseLong(textfieldID.getText());
        String name = textfieldName.getText();
        Float media = Float.parseFloat(textfieldMedia.getText());
        String group = textfieldGroup.getText();
        //System.out.println("Add button triggered: id=" + textfieldID.getText() +", name="+textfieldName.getText());

        try {
            Student s = service.addStudent(id, name, media, group);
            if (s != null)
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Add", "Studentul deja exista");
        } catch (RuntimeException ex) {
            MessageAlert.showErrorMessage(null, ex.getCause().toString() + ": " + ex.getMessage());
        }
    }

    @FXML
    public void handleUpdate(ActionEvent actionEvent) {
        Long id = Long.parseLong(textfieldID.getText());
        String name = textfieldName.getText();
        Float media = Float.parseFloat(textfieldMedia.getText());
        String group = textfieldGroup.getText();
        try {
            Student s = service.updateStudent(id, name, media, group);
            if (s != null)
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Update", "Studentul nu exista");
        } catch (RuntimeException ex) {
            MessageAlert.showErrorMessage(null, ex.getCause().toString() + ": " + ex.getMessage());
        }
    }

    public void handleRemove(ActionEvent actionEvent) {
        Student s = (Student) view.getSelectionModel().getSelectedItem();
        if (s == null)
            return;
        try {
            Student n = service.deleteStudent(s.getId());
            if (n == null)
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Update", "Studentul nu exista");
        } catch (RuntimeException ex) {
            MessageAlert.showErrorMessage(null, ex.getCause().toString() + ": " + ex.getMessage());
        }
    }

    @Override
    public void update(StudentChangeEvent studentChangeEvent) {
        initModel();
    }
}

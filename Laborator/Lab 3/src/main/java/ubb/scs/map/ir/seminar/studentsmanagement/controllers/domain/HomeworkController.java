package ubb.scs.map.ir.seminar.studentsmanagement.controllers.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ubb.scs.map.ir.seminar.studentsmanagement.controllers.MessageAlert;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Homework;
import ubb.scs.map.ir.seminar.studentsmanagement.services.service.HomeworkService;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.events.HomeworkChangeEvent;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.observer.Observer;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HomeworkController implements Observer<HomeworkChangeEvent> {
    ObservableList<Homework> model = FXCollections.observableArrayList();

    @FXML
    public TableView view;
    @FXML
    public TableColumn<Homework, Long> idColumn;
    @FXML
    public TableColumn<Homework, String> descriptionColumn;
    @FXML
    public TableColumn<Homework, Integer> startWeekColumn;
    @FXML
    public TableColumn<Homework, Integer> deadlineWeekColumn;
    @FXML
    public TextField textfieldID;
    @FXML
    public TextField textfieldDescription;
    @FXML
    public TextField textfieldStartWeek;
    @FXML
    public TextField textfieldDeadlineWeek;

    private HomeworkService service;

    @Override
    public void update(HomeworkChangeEvent homeworkChangeEvent) {
        initModel();
    }

    public void setService(HomeworkService service) {
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startWeekColumn.setCellValueFactory(new PropertyValueFactory<>("startWeek"));
        deadlineWeekColumn.setCellValueFactory(new PropertyValueFactory<>("deadlineWeek"));
        view.setItems(model);

        view.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
            Homework entity = (Homework) newValue;
            textfieldID.setText( Long.toString(entity.getId()));
            textfieldDescription.setText(entity.getDescription());
            textfieldStartWeek.setText(Integer.toString(entity.getStartWeek()));
            textfieldDeadlineWeek.setText(Integer.toString(entity.getDeadlineWeek()));
        });
    }

    private void initModel() {
        model.setAll(StreamSupport
                .stream(service.getAllHomework().spliterator(), false)
                .collect(Collectors.toList()));

    }

    @FXML
    public void handleAdd(ActionEvent e) {
        Long id = Long.parseLong(textfieldID.getText());
        int startWeek = Integer.parseInt(textfieldStartWeek.getText());
        int deadlineWeek = Integer.parseInt(textfieldDeadlineWeek.getText());
        String description = textfieldDescription.getText();
        //System.out.println("Add button triggered: id=" + textfieldID.getText() +", name="+textfieldName.getText());

        try {
            Homework s = service.addHomework(id, description, deadlineWeek);
            if (s != null)
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Add", "Tema deja exista");
        } catch (RuntimeException ex) {
            MessageAlert.showErrorMessage(null, ex.getCause().toString() + ": " + ex.getMessage());
        }
    }

    @FXML
    public void handleUpdate(ActionEvent actionEvent) {
        Long id = Long.parseLong(textfieldID.getText());
        int startWeek = Integer.parseInt(textfieldStartWeek.getText());
        int deadlineWeek = Integer.parseInt(textfieldDeadlineWeek.getText());
        String description = textfieldDescription.getText();
        try {
            Homework s = service.updateHomework(id, description, deadlineWeek);
            if (s != null)
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Update", "Tema nu exista");
        } catch (RuntimeException ex) {
            MessageAlert.showErrorMessage(null, ex.getCause().toString() + ": " + ex.getMessage());
        }
    }

    @FXML
    public void handleRemove(ActionEvent actionEvent) {
        Homework s = (Homework) view.getSelectionModel().getSelectedItem();
        if (s == null)
            return;
        try {
            Homework n = service.deleteHomework(s.getId());
            if (n == null)
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Update", "Tema nu exista");
        } catch (RuntimeException ex) {
            MessageAlert.showErrorMessage(null, ex.getCause().toString() + ": " + ex.getMessage());
        }
    }

}

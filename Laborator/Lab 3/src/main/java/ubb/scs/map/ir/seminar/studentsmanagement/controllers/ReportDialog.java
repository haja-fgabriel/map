package ubb.scs.map.ir.seminar.studentsmanagement.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.GenericModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ReportDialog {
    public static void showReport(Stage owner, String title, Collection<GenericModel> data, String... titles) {


        Dialog<Integer> dialog = new Dialog<>();
        dialog.initOwner(owner);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
        ArrayList<String> columnTitles = new ArrayList<>(Arrays.asList(titles));

        System.out.println("[DEBUG] column titles: " + columnTitles);
        int numberArguments = columnTitles.size();

        ObservableList<GenericModel> model = FXCollections.observableArrayList();
        model.setAll(data);

        TableView<GenericModel> view = new TableView<>();
        view.setItems(model);


        for (int i = 0; i < numberArguments; i++) {
            TableColumn<GenericModel, String> currentColumn = new TableColumn<>();
            currentColumn.setCellValueFactory(new PropertyValueFactory<>(Integer.toString(i)));
            currentColumn.setText(columnTitles.get(i));
            view.getColumns().add(i, currentColumn);
        }

        dialog.setTitle(title);
        GridPane grid = new GridPane();
        grid.add(view, 0, 0);

        dialog.getDialogPane().setContent(grid);
        dialog.showAndWait();
    }
}

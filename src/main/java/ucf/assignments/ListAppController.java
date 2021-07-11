package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Bao Kastan
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ListAppController implements Initializable {
    public TableView<Item> listOfItems;

    public TableColumn<Item, CheckBox> colCompleted;
    public TableColumn<Item, String> colDueDate;
    public TableColumn<Item, String> colDescription;

    private ObservableList<Item> list = FXCollections.observableArrayList();

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private TextField itemDescriptionField;

    @FXML
    void clearAllButtonClicked(ActionEvent event) {
        listOfItems.getItems().clear();
    }

    @FXML
    void createNewItemButtonClicked(ActionEvent event) {
        if (itemDescriptionField.getText().trim().length() >= 1 && itemDescriptionField.getText().trim().length() <= 256) {
            Item item = new Item(dueDatePicker.getValue().toString(), itemDescriptionField.getText());
            listOfItems.getItems().add(item);
            list.add(item);
        }
        refresh();
    }

    public void DeleteButtonClicked(ActionEvent actionEvent) {
        listOfItems.getItems().removeAll(listOfItems.getSelectionModel().getSelectedItems());
    }

    @FXML
    void helpButtonClicked(ActionEvent event) {

    }

    @FXML
    void openButtonClicked(ActionEvent event) {

    }

    @FXML
    void saveAsButtonClicked(ActionEvent event) {

    }

    @FXML
    void showAllButtonClicked(ActionEvent event) {
        FilteredList<Item> filteredData = new FilteredList<>(list, p -> true);
        filteredData.setPredicate(null);
        listOfItems.setItems(filteredData);
    }

    @FXML
    void showOnlyCompletedButtonClicked(ActionEvent event) {
        FilteredList<Item> filteredData = new FilteredList<>(list, p -> true);

        filteredData.setPredicate(null);

        filteredData.setPredicate(Item -> {
            if (Item.isCompleted().isSelected()) {
                return true;
            }
            return false;
        });

        listOfItems.setItems(filteredData);
    }

    @FXML
    void showOnlyIncompleteButtonClicked(ActionEvent event) {
        FilteredList<Item> filteredData = new FilteredList<>(list, p -> true);

        filteredData.setPredicate(null);

        filteredData.setPredicate(Item -> {
            if (!Item.isCompleted().isSelected()) {
                return true;
            }
            return false;
        });

        listOfItems.setItems(filteredData);
    }

    private void refresh(){
        dueDatePicker.setValue(LocalDate.now());
        itemDescriptionField.setText(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dueDatePicker.setValue(LocalDate.now());

        colDueDate.setCellFactory(TextFieldTableCell.forTableColumn());
        colDescription.setCellFactory(TextFieldTableCell.forTableColumn());

        colCompleted.setCellValueFactory(new PropertyValueFactory<>("Completed"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
    }
}


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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.lang.Boolean.parseBoolean;

public class ListAppController implements Initializable {
    FileChooser fileChooser = new FileChooser();

    public TableView<Item> listOfItems;

    public TableColumn<Item, CheckBox> colCompleted;
    public TableColumn<Item, String> colDueDate;
    public TableColumn<Item, String> colDescription;

    private ObservableList<Item> list = FXCollections.observableArrayList();
    FilteredList<Item> filteredData = new FilteredList<>(list, p -> true);

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private TextField itemDescriptionField;

    @FXML
    void clearAllButtonClicked(ActionEvent event) {
        list.clear();
        listOfItems.setItems(filteredData);
    }

    @FXML
    void createNewItemButtonClicked(ActionEvent event) {
        if (itemDescriptionField.getText().trim().length() >= 1 && itemDescriptionField.getText().trim().length() <= 256) {
            Item item = new Item(dueDatePicker.getValue().toString(), itemDescriptionField.getText());
            list.add(item);
            listOfItems.setItems(filteredData);
        }
        refresh();
    }

    public void DeleteButtonClicked(ActionEvent actionEvent) {
        int visibleIndex = listOfItems.getSelectionModel().getSelectedIndex();
        list.remove(visibleIndex);
        listOfItems.setItems(filteredData);

    }

    @FXML
    void helpButtonClicked(ActionEvent event) {

    }

    @FXML
    void openButtonClicked(ActionEvent event) throws FileNotFoundException {
        File file = fileChooser.showOpenDialog(new Stage());

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            String[] parts = string.split(",");
            Item item = new Item(parts[1],parts[2]);
            CheckBox checkBox = new CheckBox();
            checkBox.setSelected(parseBoolean(parts[0]));
            item.setCompleted(checkBox);
            list.add(item);
            listOfItems.setItems(filteredData);
        }
    }

    @FXML
    void saveAsButtonClicked(ActionEvent event) throws IOException {
        File file = fileChooser.showSaveDialog(new Stage());
        FileWriter writer = new FileWriter(file + ".txt");
        for (Item item : list) {
            writer.write(Boolean.toString(item.isCompleted().isSelected()) + "," + item.getDueDate() + "," + item.getDescription()+"\n");
        }
        writer.close();
    }

    @FXML
    void showAllButtonClicked(ActionEvent event) {
        filteredData.setPredicate(null);
        listOfItems.setItems(filteredData);
    }

    @FXML
    void showOnlyCompletedButtonClicked(ActionEvent event) {
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
        fileChooser.setInitialDirectory(new File("C:"));

        dueDatePicker.setValue(LocalDate.now());

        colDueDate.setCellFactory(TextFieldTableCell.forTableColumn());
        colDescription.setCellFactory(TextFieldTableCell.forTableColumn());

        colCompleted.setCellValueFactory(new PropertyValueFactory<>("Completed"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
    }
}


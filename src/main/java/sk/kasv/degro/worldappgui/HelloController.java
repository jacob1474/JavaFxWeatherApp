package sk.kasv.degro.worldappgui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import sk.kasv.degro.worldappgui.database.Service;

import java.io.IOException;

public class HelloController {
    @FXML
    private ChoiceBox<String> choiceBox;

    public void initialize() {
        ObservableList<String> options = Service.getAllCountries();
        choiceBox.setItems(options);
        // Add options to the ChoiceBox

        // Set default selection
        choiceBox.getSelectionModel().selectFirst();

        // Add event listener if needed
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Handle selection change
            System.out.println("Selected: " + newValue);
        });
    }
}
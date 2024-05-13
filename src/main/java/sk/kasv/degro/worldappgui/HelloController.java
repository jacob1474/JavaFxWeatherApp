package sk.kasv.degro.worldappgui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import sk.kasv.degro.worldappgui.Util.ListConvertor;
import sk.kasv.degro.worldappgui.Util.PrettifyLargeNumber;
import sk.kasv.degro.worldappgui.Util.Rounder;
import sk.kasv.degro.worldappgui.database.Service;
import sk.kasv.degro.worldappgui.model.Country;
import sk.kasv.degro.worldappgui.Api.ApiCaller;

import java.io.IOException;
import java.util.List;

public class HelloController {
    private double temperatureCelsius;
    private double temperatureFahrenheit;
    private double temperatureKelvin;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private ChoiceBox<String> choiceBoxTwo;
    @FXML
    private RadioButton radioCelsius;
    @FXML
    private RadioButton radioFahrenheit;
    @FXML
    private RadioButton radioKelvin;

    @FXML
    private Label cityTemperature;

    @FXML
    private Label cityPopulation;

    private ToggleGroup temperatureToogleGroup;

    public void initialize() {
        List<Country> countries = Service.getAllCountries();
        ObservableList<String> options = ListConvertor.convertListCountry(countries);
        choiceBox.setItems(options);

        temperatureToogleGroup = new ToggleGroup();
        radioCelsius.setToggleGroup(temperatureToogleGroup);
        radioFahrenheit.setToggleGroup(temperatureToogleGroup);
        radioKelvin.setToggleGroup(temperatureToogleGroup);

        // Set default selection
        choiceBox.getSelectionModel();

        // Add event listener if needed
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Handle selection change
            choiceBoxTwo.setItems(ListConvertor.convertListCity(Service.getAllCitiesByCountry(countries.get(choiceBox.getSelectionModel().getSelectedIndex()).getCode3())));
        });

        choiceBoxTwo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Handle selection change
            temperatureCelsius = ApiCaller.getTemperature(newValue, countries.get(choiceBox.getSelectionModel().getSelectedIndex()).getCode3());
            temperatureFahrenheit = temperatureCelsius * 9 / 5 + 32;
            temperatureKelvin = temperatureCelsius + 273.15;
            cityTemperature.setText("Temperature: " + temperatureCelsius+"°C");
            cityPopulation.setText("Population: " + PrettifyLargeNumber.prettifyNumber(Service.getCityPopulation(newValue)));
        });

    }



    public void onCheckerBox(ActionEvent actionEvent) {
        cityPopulation.setVisible(!cityPopulation.isVisible());
    }



    public void onRadioCelsius(ActionEvent actionEvent) {
        cityTemperature.setText("Temperature: " + Rounder.round(temperatureCelsius, 2)+"°C");
    }

    public void onRadioFahrenheit(ActionEvent actionEvent) {
        cityTemperature.setText("Temperature: " + Rounder.round(temperatureFahrenheit, 2)+"°F");
    }

    public void onRadioKelvin(ActionEvent actionEvent) {
        cityTemperature.setText("Temperature: " + Rounder.round(temperatureKelvin, 2)+"K");
    }

}
package sk.kasv.degro.worldappgui;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import sk.kasv.degro.worldappgui.Util.ListConvertor;
import sk.kasv.degro.worldappgui.Util.PrettifyLargeNumber;
import sk.kasv.degro.worldappgui.Util.Rounder;
import sk.kasv.degro.worldappgui.database.Service;
import sk.kasv.degro.worldappgui.model.Country;
import sk.kasv.degro.worldappgui.Api.ApiCaller;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class HelloController {
    private double temperatureCelsius;
    private double temperatureFeelCelsius;
    private double longitude = 0;
    private double latitude = 0;
    private String imageMapUrl = "https://api.mapbox.com/styles/v1/mapbox/satellite-v9/static/-122.3774,37.7799,8.0,0/540x170@2x?access_token=pk.eyJ1IjoiYnM2NTZuYiIsImEiOiJjbHc2MjJ1dWIxajJ3MmpsODZsdW9ud3lpIn0.Cq9ihtg70hNbX5dTuj7RKA";
    private String imageUrl = "https://openweathermap.org/img/wn/01d@2x.png";
    private String imageFlagUrl = "https://flagsapi.com/SK/flat/64.png";
    private ToggleGroup temperatureToogleGroup;
    private boolean isPopulationVisible = true;
    private short choice = 0;

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
    @FXML
    private Label cityTemperatureFeel;
    @FXML
    private Label cityWind;
    @FXML
    private Label cityHumidity;
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView imageViewMap;
    @FXML
    private Label bigText;
    @FXML
    private ImageView imageViewFlag;
    @FXML
    private Label flag;
    @FXML
    private Label language;
    @FXML
    private TextField textField;
    @FXML
    private Polyline compass;
    @FXML
    private ProgressBar humidityProgressBar;

    public void initialize() {
        List<Country> countries = Service.getAllCountries();
        ObservableList<String> options = ListConvertor.convertListCountry(countries);
        AtomicReference<Country> selectedCountry = new AtomicReference<>(countries.get(0));

        choiceBox.setItems(options);

        temperatureToogleGroup = new ToggleGroup();
        radioCelsius.setToggleGroup(temperatureToogleGroup);
        radioFahrenheit.setToggleGroup(temperatureToogleGroup);
        radioKelvin.setToggleGroup(temperatureToogleGroup);

        // Set default selection
        choiceBox.getSelectionModel();

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
            choiceBox.setItems(ListConvertor.convertListCountry(Service.getCountriesByFirstLetter(newValue)));
            choiceBoxTwo.getItems().clear();
            } else {
                choiceBox.setItems(options);
                choiceBoxTwo.getItems().clear();
                
            selectedCountry.set(countries.get(0));
            }
        });

        // Add event listener if needed
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int a = options.indexOf(newValue);
            selectedCountry.set(countries.get(a));
            choiceBoxTwo.setItems(ListConvertor.convertListCity(Service.getAllCitiesByCountry(selectedCountry.get().getCode3())));
            language.setText("Language: " + Service.getCountryOfficialLanguage(selectedCountry.get().getCode3()));
            changeImage(null, 2, selectedCountry.get());
        });

        choiceBoxTwo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Handle selection change
            if(newValue == null){
                return;
            }
            temperatureCelsius = ApiCaller.getTemperature(newValue, countries.get(choiceBox.getSelectionModel().getSelectedIndex()).getCode3());
            temperatureFeelCelsius = ApiCaller.getTemperatureFeel(newValue, countries.get(choiceBox.getSelectionModel().getSelectedIndex()).getCode3());
            longitude = ApiCaller.getLongitude(newValue, countries.get(choiceBox.getSelectionModel().getSelectedIndex()).getCode3());
            latitude = ApiCaller.getLatitude(newValue, countries.get(choiceBox.getSelectionModel().getSelectedIndex()).getCode3());
            if(choice == 0){
                cityTemperature.setText("Temperature: " + temperatureCelsius+"°C");
                cityTemperatureFeel.setText("Temperature feels like: " + Rounder.round(temperatureFeelCelsius, 2)+"°C");  
            }
            else if(choice == 2){
                cityTemperature.setText("Temperature: " + Rounder.round(temperatureCelsius * 9 / 5 + 32, 2)+"°F");
                cityTemperatureFeel.setText("Temperature feels like: " + Rounder.round(temperatureFeelCelsius * 9 / 5 + 32, 2)+"°F");
            }
            else if(choice == 3){
                cityTemperature.setText("Temperature: " + Rounder.round(temperatureCelsius + 273.15, 2)+"K");
                cityTemperatureFeel.setText("Temperature feels like: " + Rounder.round(temperatureFeelCelsius + 273.15, 2)+"K"); 
            }
            cityPopulation.setText("City population: " + PrettifyLargeNumber.prettifyNumber(Service.getCityPopulation(newValue)));
            cityWind.setText("Wind speed: " + Rounder.round(ApiCaller.getWindSpeed(newValue, countries.get(choiceBox.getSelectionModel().getSelectedIndex()).getCode3()), 2)+"m/s");
            cityHumidity.setText("Humidity:                       " + Rounder.round(ApiCaller.getHumidity(newValue, countries.get(choiceBox.getSelectionModel().getSelectedIndex()).getCode3()), 2)+"%");
            humidityProgressBar.setProgress(ApiCaller.getHumidity(newValue, countries.get(choiceBox.getSelectionModel().getSelectedIndex()).getCode3())/100);
            changeImage(null, 1, selectedCountry.get()); 
            bigText.setText("Selected: " + selectedCountry.get().getName()+", " + newValue);
            compass.setRotate(ApiCaller.getWindDirection(newValue, countries.get(choiceBox.getSelectionModel().getSelectedIndex()).getCode3()));
        });

    }

    public void onCheckerBox(ActionEvent actionEvent) {
        if(isPopulationVisible){
            cityPopulation.setVisible(false);
            isPopulationVisible = false;
            language.setLayoutY(language.getLayoutY() - 20);
            flag.setLayoutY(flag.getLayoutY() - 20);
        } else {
            cityPopulation.setVisible(true);
            isPopulationVisible = true;
            language.setLayoutY(language.getLayoutY() + 20);
            flag.setLayoutY(flag.getLayoutY() + 20);
        }
    }

    public void onRadioCelsius(ActionEvent actionEvent) {
        cityTemperature.setText("Temperature: " + Rounder.round(temperatureCelsius, 2)+"°C");
        cityTemperatureFeel.setText("Temperature feels like: " + Rounder.round(temperatureFeelCelsius, 2)+"°C");
        choice =0;
    }

    public void onRadioFahrenheit(ActionEvent actionEvent) {
        cityTemperature.setText("Temperature: " + Rounder.round(temperatureCelsius * 9 / 5 + 32, 2)+"°F");
        cityTemperatureFeel.setText("Temperature feels like: " + Rounder.round(temperatureFeelCelsius * 9 / 5 + 32, 2)+"°F");
        choice = 2;
    }

    public void onRadioKelvin(ActionEvent actionEvent) {
        cityTemperature.setText("Temperature: " + Rounder.round(temperatureCelsius + 273.15, 2)+"K");
        cityTemperatureFeel.setText("Temperature feels like: " + Rounder.round(temperatureFeelCelsius + 273.15, 2)+"K");
        choice = 3;
    }

    public void changeImage(ActionEvent actionEvent, int num, Country selectedCountry) {
        imageUrl = "https://openweathermap.org/img/wn/"+ApiCaller.getWeatherIcon(choiceBoxTwo.getSelectionModel().getSelectedItem(), Service.getAllCountries().get(choiceBox.getSelectionModel().getSelectedIndex()).getCode3())+"@2x.png";
        imageMapUrl = "https://api.mapbox.com/styles/v1/mapbox/satellite-v9/static/"+longitude+","+latitude+",8.0,0/540x170@2x?access_token=pk.eyJ1IjoiYnM2NTZuYiIsImEiOiJjbHc2MjJ1dWIxajJ3MmpsODZsdW9ud3lpIn0.Cq9ihtg70hNbX5dTuj7RKA";
        imageFlagUrl = "https://flagsapi.com/"+selectedCountry.getCode2()+"/flat/64.png";
        
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                try {
                    // Create a URL object
                    URL url = new URL(imageUrl);

                    // Open a stream from the URL
                    try (InputStream stream = url.openStream()) {
                        // Create an image from the stream
                        Image image = new Image(stream);

                        // Update the UI on the JavaFX Application Thread
                        imageView.setImage(image);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        Task<Void> task2 = new Task<>() {
            @Override
            protected Void call() {
                try {
                    // Create a URL object
                    URL url = new URL(imageMapUrl);

                    // Open a stream from the URL
                    try (InputStream stream = url.openStream()) {
                        // Create an image from the stream
                        Image image = new Image(stream);

                        // Update the UI on the JavaFX Application Thread
                        imageViewMap.setImage(image);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        Task<Void> task3 = new Task<>(){
            @Override
            protected Void call() {
                try {
                    // Create a URL object
                    URL url = new URL(imageFlagUrl);

                    // Open a stream from the URL
                    try (InputStream stream = url.openStream()) {
                        // Create an image from the stream
                        Image image = new Image(stream);

                        // Update the UI on the JavaFX Application Thread
                        imageViewFlag.setImage(image);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        if(num ==1){
            if (task != null && task.isRunning()) {
                task.cancel();
            }
            new Thread(task).start();
            if (task2 != null && task2.isRunning()) {
                task2.cancel();
            }
            new Thread(task2).start();
        }
        if(num == 2){
            if (task3 != null && task3.isRunning()) {
                task3.cancel();
            }
            new Thread(task3).start();
        }
    }

}
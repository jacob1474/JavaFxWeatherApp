
# Java FX Weather App


Overview

WorldAppGUI is a JavaFX-based weather application that provides users with current weather information for cities around the world. The application utilizes the OpenWeatherMap API to fetch weather data and Mapbox API for maps, displaying temperature, humidity, wind speed, and other meteorological details.

## Features
City and Country Selection: Choose a country and then a city to get weather updates.
Temperature Display: Switch between Celsius, Fahrenheit, and Kelvin.

### Weather 
#### Details: 
View temperature, feels-like temperature, wind speed, humidity, and more.
#### Population Display: 
Toggle the visibility of the city's population.
#### Interactive UI: 
Real-time updates and dynamic content based on user input.
#### Map and Flag Display: 
View the city on a map and see the country flag.

### Requirements
Java 11 or higher, 
JavaFX SDK, 
MySQL Database
### Setup and Installation
#### Prerequisites
Java Development Kit (JDK): Ensure Java 11 or higher is installed.

JavaFX SDK: Download and set up the JavaFX SDK.

MySQL Database: Set up a MySQL database and ensure it contains the necessary tables and data for countries and cities.

#### Steps
Clone the Repository:

````
git clone <repository-url>
````
Database Configuration:

Configure your MySQL database credentials in the Service class located at:

src/sk/kasv/degro/worldappgui/database/Service.java


#### Copy code
````
private static final String URL = "jdbc:mysql://localhost:3306/world_x";
private static final String USER = "user_x";
private static final String PASSWORD = "password123";
````
#### Run the Application:

Navigate to the project directory.
Use the following command to compile and run the application:
````
mvn clean install
mvn javafx:run
````
#### Launch the Application:

Run the App class to launch the application.
The main window titled "Weather app!" will appear.

##### Select Country and City:

Use the choice boxes to select a country and then a city.
The application will display weather details for the selected city.
##### Switch Temperature Units:

Use the radio buttons to switch between Celsius, Fahrenheit, and Kelvin.
##### Toggle Population Visibility:

Click the checkbox to show or hide the population of the selected city.
#### Example Code
Here's a snippet of the main application entry point:

````
public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        stage.setTitle("Weather app!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
````
### Project Structure
#### Main application package.
##### controller: 
Contains the HelloController class which manages the UI logic.
#### database: 
Contains classes related to database operations (Service and Repository).
#### model: 
Contains data models (Country and City).
#### util: 
Utility classes for data processing and conversion.
api: Contains classes for API calls to fetch weather data.
License
This project is licensed under the MIT License.

#### Note: 
Ensure you have the necessary API keys for OpenWeatherMap, Mapbox, and FlagsAPI, and replace placeholders in the code with your actual keys.


## Acknowledgements

 - [Openweathermap](https://openweathermap.org/)
 - [Mapbox](https://www.mapbox.com/)
 - [Flagship](https://docs.developers.flagship.io/)


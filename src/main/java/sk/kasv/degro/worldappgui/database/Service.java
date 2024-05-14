package sk.kasv.degro.worldappgui.database;

import java.sql.*;
import java.util.List;

import sk.kasv.degro.worldappgui.model.Country;
import sk.kasv.degro.worldappgui.model.City;

public class Service {
    private static final String URL = "jdbc:mysql://localhost:3306/world_x";
    private static final String USER = "user_x";
    private static final String PASSWORD = "password123";

    public static List<Country> getAllCountries() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Repository repository = new Repository();

            return repository.getAllCountries(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<City> getAllCitiesByCountry(String countryCode) {
        System.out.println(countryCode);
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Repository repository = new Repository();

            return repository.getAllCitiesByCountry(connection, countryCode);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getCityPopulation(String cityName) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Repository repository = new Repository();

            return repository.getCityPopulation(connection, cityName);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getCountryOfficialLanguage(String countryCode) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Repository repository = new Repository();

            return repository.getCountryOfficialLanguage(connection, countryCode);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Country> getCountriesByFirstLetter(String firstLetter) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Repository repository = new Repository();

            return repository.getCountriesByFirstLetter(connection, firstLetter);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package sk.kasv.degro.worldappgui.database;

import sk.kasv.degro.worldappgui.model.Country;
import sk.kasv.degro.worldappgui.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


// Singelton class

public class Repository {
    private static final String SELECT_ALL_COUNTRIES = "SELECT * FROM country ORDER BY Name ASC;";
    private static final String SELECT_ALL_CITIES_BY_COUNTRY = "SELECT * FROM city WHERE CountryCode = ?;";
    private static final String SELECT_CITY_POPULATION = "SELECT JSON_VALUE(Info, '$.Population') AS Info FROM city WHERE Name = ?;";
    private static final String SELECT_COUNTRY_BY_FIRST_LETTER = "SELECT * FROM country WHERE Name LIKE ?;";
    private static final String SELECT_COUNTRY_OFFICIAL_LANGUAGE = "SELECT Language FROM countrylanguage WHERE CountryCode = ? AND IsOfficial = 'T';";
    private static Repository instance;

    private Repository() {
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }

        return instance;
    }

    public List<Country> getAllCountries(Connection connection) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COUNTRIES);
        ResultSet rs = preparedStatement.executeQuery();

        ArrayList<Country> countries = new ArrayList<Country>();

        while (rs.next()) {
            countries.add(new Country(rs.getString("Code"), rs.getString("Code2"), rs.getString("Name")));
        }

        return countries;
    }

    public List<City> getAllCitiesByCountry(Connection connection, String countryCode) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CITIES_BY_COUNTRY);
        preparedStatement.setString(1, countryCode);
        ResultSet rs = preparedStatement.executeQuery();

        ArrayList<City> cities = new ArrayList<City>();

        while (rs.next()) {
            cities.add(new City(rs.getString("Name"), rs.getString("District")));
        }

        return cities;
    }

    public int getCityPopulation(Connection connection, String cityName) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CITY_POPULATION);
        preparedStatement.setString(1, cityName);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return rs.getInt("Info");
        }

        return 0;
    }

    public String getCountryOfficialLanguage(Connection connection, String countryCode) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNTRY_OFFICIAL_LANGUAGE);
        preparedStatement.setString(1, countryCode);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return rs.getString("Language");
        }

        return "";
    }

    public List<Country> getCountriesByFirstLetter(Connection connection, String firstLetter) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNTRY_BY_FIRST_LETTER);
        preparedStatement.setString(1, firstLetter + "%");
        ResultSet rs = preparedStatement.executeQuery();

        ArrayList<Country> countries = new ArrayList<Country>();

        while (rs.next()) {
            countries.add(new Country(rs.getString("Code"), rs.getString("Code2"), rs.getString("Name")));
        }

        return countries;
    }
}

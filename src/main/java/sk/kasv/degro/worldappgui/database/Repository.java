package sk.kasv.degro.worldappgui.database;

import sk.kasv.degro.worldappgui.model.Country;
import sk.kasv.degro.worldappgui.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static final String SELECT_ALL_COUNTRIES = "SELECT * FROM country;";
    private static final String SELECT_ALL_CITIES_BY_COUNTRY = "SELECT * FROM city WHERE CountryCode = ?;";
    private static final String SELECT_CITY_POPULATION = "SELECT JSON_VALUE(Info, '$.Population') AS Info FROM city WHERE Name = ?;";

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
}

package sk.kasv.degro.worldappgui.database;

import sk.kasv.degro.worldappgui.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static final String SELECT_ALL_COUNTRIES = "SELECT city.Name, city.District, JSON_VALUE(Info, '$.Population') AS Info, country.Code, country.Code2, country.Name AS CountryName FROM city" +
            " INNER JOIN country ON city.CountryCode = country.Code" +
            " WHERE CountryCode LIKE ?";

    public List<Country> getAllCountries(Connection connection) throws Exception {
        // create new object of PreparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COUNTRIES);
        // execute query
        ResultSet rs = preparedStatement.executeQuery();
        // initialize ArrayList
        ArrayList<Country> countries = new ArrayList<Country>();
        // add country codes to ArrayList
        while (rs.next()) {
            countries.add(new Country(rs.getString("Code"), rs.getString("Code2"), rs.getString("CountryName")));
        }
        // return ArrayList
        return countries;
    }
}

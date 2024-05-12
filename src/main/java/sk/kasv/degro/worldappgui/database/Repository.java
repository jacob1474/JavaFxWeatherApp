package sk.kasv.degro.worldappgui.database;

import sk.kasv.degro.worldappgui.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static final String SELECT_ALL_COUNTRIES = "SELECT * FROM country;";


    public List<Country> getAllCountries(Connection connection) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COUNTRIES);
        ResultSet rs = preparedStatement.executeQuery();

        ArrayList<Country> countries = new ArrayList<Country>();

        while (rs.next()) {
            countries.add(new Country(rs.getString("Code"), rs.getString("Code2"), rs.getString("Name")));
        }

        return countries;
    }
}

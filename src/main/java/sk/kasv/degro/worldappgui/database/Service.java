package sk.kasv.degro.worldappgui.database;

import java.sql.*;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import sk.kasv.degro.worldappgui.Util.ListConvertor;
import sk.kasv.degro.worldappgui.model.Country;

public class Service {
    private static final String URL = "jdbc:mysql://localhost:3306/world_x";
    private static final String USER = "user_x";
    private static final String PASSWORD = "password123";

    public static ObservableList<String> getAllCountries() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Repository repository = new Repository();

            return ListConvertor.convertList(repository.getAllCountries(connection));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

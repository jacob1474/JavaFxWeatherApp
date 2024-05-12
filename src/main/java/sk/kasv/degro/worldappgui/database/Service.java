package sk.kasv.degro.worldappgui.database;

import java.sql.*;

public class Service {
    private static final String URL = "jdbc:mysql://localhost:3306/world_x";
    private static final String USER = "user_x";
    private static final String PASSWORD = "password123";

    public static void getAllCountries() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Repository repository = new Repository();
            repository.getAllCountries(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}

package ir.freeland.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateQuery {
    // JDBC URL, username and password of H2 server
    private static final String URL = "jdbc:h2:tcp://localhost:9092/~/testdb";
    private static final String USER = "sa"; // Default username
    private static final String PASSWORD = ""; // Default password (empty)

    public static void main(String[] args) {

        // Define the UPDATE query
        String updateQuery = "UPDATE COUNTRIES SET COUNTRY_ID = ? WHERE COUNTRY_ID = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            System.out.println("Connected to the H2 database successfully!");
            System.out.println("---------------------------------------------");

            // Set the parameters for the query
            preparedStatement.setString(1, "AG"); // New value
            preparedStatement.setString(2, "AR");  // Old value

            // Execute the update query
            int rowsUpdated = preparedStatement.executeUpdate();

            // Check if the update was successful
            if (rowsUpdated > 0) {
                System.out.println("Record(s) updated successfully! Total rows affected: " + rowsUpdated);
            } else {
                System.out.println("No records found with COUNTRY_ID = 'AR'.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

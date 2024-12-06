package ir.freeland.Task.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertQuery {
	// JDBC URL, username and password of H2 server
	private static final String URL = "jdbc:h2:tcp://localhost:9092/~/testdb";
	private static final String USER = "sa"; // Default username
	private static final String PASSWORD = ""; // Default password (empty)

	public static void main(String[] args) throws SQLException {

		try (Connection connection1 = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement preparedStatement = connection1.prepareStatement("INSERT INTO EMPLOYEES"
						+ "(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, MANAGER_ID, DEPARTMENT_ID) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");)

		{
			System.out.println("connected to the H2 database successfully!");
			System.out.println("---------------------------------------------");

			preparedStatement.setString(1, "183");
			preparedStatement.setString(2, "test2");
			preparedStatement.setString(3, "Test2");
			preparedStatement.setString(4, "test@yahoo.com2");
			preparedStatement.setString(5, "09123252298");
			preparedStatement.setString(6, "2020-01-04");
			preparedStatement.setString(7, "3");
			preparedStatement.setString(8, "6200.00");
			preparedStatement.setString(9, "100");
			preparedStatement.setString(10, "8");
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				System.out.println("Record inserted successfully!");
			} else {
				System.out.println("Failed to insert the record.");
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

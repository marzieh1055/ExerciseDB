package ir.freeland.Task.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectQuery {
	// JDBC URL, username and password of H2 server
	private static final String URL = "jdbc:h2:tcp://localhost:9092/~/testdb";
	private static final String USER = "sa"; // Default username
	private static final String PASSWORD = ""; // Default password (empty)

	public static void main(String[] args) throws SQLException {

		String depid = "1";
		String country = "US";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM EMPLOYEES WHERE  DEPARTMENT_ID = ?");
				PreparedStatement preparedStatement1 = connection
						.prepareStatement("SELECT * FROM DEPARTMENTS,LOCATIONS "
								+ "WHERE DEPARTMENTS.LOCATION_ID=LOCATIONS.LOCATION_ID \r\n"
								+ " AND COUNTRY_ID=?"
								);)

		{
			System.out.println("connected to the H2 database successfully!");
			System.out.println("---------------------------------------------");

			preparedStatement.setString(1, depid);
			preparedStatement1.setString(1, country);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {
					String EMPLOYEE_ID = resultSet.getString("EMPLOYEE_ID");
					String FIRST_NAME = resultSet.getString("FIRST_NAME");
					String LAST_NAME = resultSet.getString("LAST_NAME");
					String EMAIL = resultSet.getString("EMAIL");
					String DEPARTMENT_ID = resultSet.getString("DEPARTMENT_ID");
					// Print all user info in one line
					System.out.printf("DEPARTMENT_ID:%s, FIRST_NAME: %s, LAST_NAME: %s, EMAIL: %s,  EMPLOYEE_ID:  %s%n",
						DEPARTMENT_ID	, FIRST_NAME, LAST_NAME, EMAIL,EMPLOYEE_ID );
					
				}
			}
			System.out.println("---------------------------------------------");

			try (ResultSet resultSet = preparedStatement1.executeQuery()) {

				while (resultSet.next()) {
					String DEPARTMEANT_ID = resultSet.getString("DEPARTMENT_ID");
					String LOCATION_ID = resultSet.getString("LOCATION_ID");
					String COUNTRY_ID = resultSet.getString("COUNTRY_ID");
					String CITY = resultSet.getString("CITY");
					String STREET_ADDRESS = resultSet.getString("STREET_ADDRESS");

//					 Print all user info in one line
                    System.out.printf("DEPARTMEANT_ID: %s, LOCATION_ID: %s, COUNTRY_ID: %s, CITY: %s, STREET_ADREES: %s%n",
                    		DEPARTMEANT_ID, LOCATION_ID, COUNTRY_ID, CITY, STREET_ADDRESS);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
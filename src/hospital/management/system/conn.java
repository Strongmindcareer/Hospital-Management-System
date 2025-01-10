package hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {
    public Connection connection;
    public Statement statement;

    public conn() {
        try {
            String url = "jdbc:mysql://localhost:3306/hospital_management_system";
            String username = "root"; // Replace with your database username
            String password = "1234"; // Replace with your database password

            // Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(url, username, password);

            // Create a statement object to execute queries
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
}

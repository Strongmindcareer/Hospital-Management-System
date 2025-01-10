package hospital.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;

public class Employee_info extends JFrame {

    public Employee_info() {
        // Main panel setup
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(5, 5, 990, 590);
        mainPanel.setBackground(new Color(10, 151, 176));
        mainPanel.setLayout(null);
        add(mainPanel);

        // Table setup with default model
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        table.setModel(tableModel);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table.setRowHeight(30); // Set row height for better readability

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(10, 50, 970, 500);
        mainPanel.add(tableScrollPane);

        // Fetch data from database
        try {
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
            String query = "SELECT * FROM EMP_INFO";
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Add column names dynamically
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }

            // Add rows dynamically
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(rowData);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching data from the database:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        // Title label - Centered
        JLabel titleLabel = new JLabel("Employee Information");
        titleLabel.setBounds(0, 10, 1000, 30); // Set bounds to center the title
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the title
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18)); // Bold font for the title
        mainPanel.add(titleLabel);

        // Make column headers bold
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Tahoma", Font.BOLD, 14)); // Bold font for headers

        // Frame setup
        setTitle("Employee Info");
        setSize(1000, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame on the screen
        setLocationRelativeTo(null); // This will center the window on the screen

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Employee_info::new);

        // Add three more doctors directly to the database
        try {
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
            Statement statement = c.createStatement();

            // Inserting three new doctor records into the EMP_INFO table
            String insertQuery = "INSERT INTO EMP_INFO (Name, Age, Phone_Number, Salary, Gmail, Aadhar_Number) VALUES "
                    + "('Dr. Alice', '35', '9876543210', '70000', 'alice@gmail.com', '12345678902'), "
                    + "('Dr. Bob', '40', '9876543211', '75000', 'bob@gmail.com', '12345678903'), "
                    + "('Dr. Charlie', '45', '9876543212', '80000', 'charlie@gmail.com', '12345678904')";

            statement.executeUpdate(insertQuery);
            System.out.println("New doctors added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

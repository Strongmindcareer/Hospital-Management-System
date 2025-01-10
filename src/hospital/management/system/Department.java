package hospital.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Department extends JFrame {

    // Constructor
    public Department() {
        // Set up the frame
        setTitle("Department Management");
        setSize(800, 600);
        setLocation(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a JPanel for the main content
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(10, 151, 176));
        add(panel, BorderLayout.CENTER);

        // JLabel for title
        JLabel titleLabel = new JLabel("Department Information", JLabel.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        titleLabel.setBounds(200, 10, 400, 40);
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel);

        // JTable for displaying department data
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 70, 700, 400);
        panel.add(scrollPane);

        // Populate the table with department data
        populateTable(table);

        // Back button
        JButton backButton = new JButton("BACK");
        backButton.setBounds(550, 500, 150, 40);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        panel.add(backButton);

        // Back button action listener
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
            }
        });

        // Set frame visibility
        setVisible(true);
    }

    // Method to populate the JTable with data from the database
    private void populateTable(JTable table) {
        try {
            // Establish database connection
            conn c = new conn(); // Replace with your connection class implementation
            String query = "SELECT * FROM department";
            ResultSet resultSet = c.statement.executeQuery(query);

            // Extract metadata for column names
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Create table model
            DefaultTableModel model = new DefaultTableModel();

            // Add column names to the model
            for (int column = 1; column <= columnCount; column++) {
                model.addColumn(metaData.getColumnName(column));
            }

            // Add rows to the model
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    row[column - 1] = resultSet.getObject(column);
                }
                model.addRow(row);
            }

            // Set the model to the table
            table.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Department());
    }
}

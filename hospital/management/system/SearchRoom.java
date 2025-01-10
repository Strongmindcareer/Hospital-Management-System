package hospital.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class SearchRoom extends JFrame {
    private Choice choice;
    private JTable table;
    private DefaultTableModel tableModel;

    public SearchRoom() {
        // Panel setup
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 700, 500);
        panel.setBackground(new Color(10, 151, 176));
        panel.setLayout(null);

        // Title Label
        JLabel forLabel = new JLabel("Search For Room");
        forLabel.setBounds(250, 11, 250, 31);
        forLabel.setForeground(Color.white);
        forLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(forLabel);

        // Status Label
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(56, 73, 120, 20);
        statusLabel.setForeground(Color.white);
        statusLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(statusLabel);

        // Choice Dropdown
        choice = new Choice();
        choice.setBounds(170, 70, 260, 130);
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        // Table Setup
        String[] columnNames = {"Room ID", "Room Type", "Availability", "Capacity", "Price", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setBackground(new Color(230, 240, 250));
        table.setForeground(Color.black);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 120, 650, 250);
        panel.add(scrollPane);

        // Search Button
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(200, 420, 120, 30);
        searchButton.setBackground(Color.black);
        searchButton.setForeground(Color.white);
        searchButton.addActionListener(e -> fetchRooms());
        panel.add(searchButton);

        // JFrame settings
        setSize(700, 500);
        setLayout(null);
        setLocation(450, 250);
        setVisible(true);
        add(panel);
    }

    private void fetchRooms() {
        String status = choice.getSelectedItem();
        String query = "SELECT room_id, room_type, available, capacity, price, description FROM room_types WHERE available = "
                + (status.equals("Available") ? "TRUE" : "FALSE");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Clear existing data
            tableModel.setRowCount(0);

            // Populate table with data
            while (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                String roomType = resultSet.getString("room_type");
                boolean available = resultSet.getBoolean("available");
                int capacity = resultSet.getInt("capacity");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");

                tableModel.addRow(new Object[]{roomId, roomType, available ? "Available" : "Occupied", capacity, price, description});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}

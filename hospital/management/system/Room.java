package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Room extends JFrame {

    JTable table;

    Room() {
        // Set the title and size of the frame
        setTitle("Room Information");
        setSize(950, 650);
        setLocationRelativeTo(null);  // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create panel for layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        // Header label for the room table
        JLabel headerLabel = new JLabel("Room Types Information", JLabel.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));  // Modern font
        headerLabel.setForeground(new Color(10, 151, 176));
        panel.add(headerLabel, BorderLayout.NORTH);

        // Create table with scroll pane
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(10, 151, 176), 2));  // Stylish border for the table
        panel.add(scrollPane, BorderLayout.CENTER);

        // Fetch data from the database and set it to the table
        try {
            conn c = new conn();  // Create an instance of the conn class
            String query = "SELECT * FROM room_types";  // Query to fetch data from room_types table
            ResultSet resultSet = c.statement.executeQuery(query);  // Execute the query
            table.setModel(DbUtils.resultSetToTableModel(resultSet));  // Set the result in the table

            // Customizing the table header style
            table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));  // Modern header font
            table.getTableHeader().setBackground(new Color(10, 151, 176));
            table.getTableHeader().setForeground(Color.WHITE);
            table.setFont(new Font("Arial", Font.PLAIN, 14));
            table.setRowHeight(40);  // Increased row height for better readability

            // Alternate row coloring for better visual appeal
            table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
                @Override
                public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    java.awt.Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if (row % 2 == 0) {
                        cell.setBackground(new Color(240, 240, 240));  // Light grey for even rows
                    } else {
                        cell.setBackground(Color.WHITE);  // White for odd rows
                    }
                    return cell;
                }
            });

            // Adjust column width for better display (auto-resize)
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            // Enable word wrapping in the description column using JTextArea
            table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
                @Override
                public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    java.awt.Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if (column == 5) {  // Assuming the description is in the 6th column (index 5)
                        JTextArea textArea = new JTextArea(value.toString());
                        textArea.setWrapStyleWord(true);
                        textArea.setLineWrap(true);
                        textArea.setBackground(cell.getBackground());
                        textArea.setForeground(cell.getForeground());
                        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
                        textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));  // Padding for better spacing
                        textArea.setCaretPosition(0);
                        return textArea;
                    }
                    return cell;
                }
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage());
            e.printStackTrace();
        }

        // Add a footer panel with action buttons (optional)
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        closeButton.setBackground(new Color(98, 156, 163));
        closeButton.setForeground(Color.WHITE);
        closeButton.addActionListener(e -> System.exit(0));
        footerPanel.add(closeButton);

        panel.add(footerPanel, BorderLayout.SOUTH);  // Add footer panel at the bottom

        // Frame visibility settings
        setVisible(true);
    }

    public static void main(String[] args) {
        new Room();  // Create an instance of Room to display the window
    }
}

package hospital.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Ambulance {

    public Ambulance() {
        // Create the frame for Ambulance information
        JFrame frame = new JFrame("Ambulance Information");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1100, 750); // Increased size for more spacious layout
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245)); // Light gray background
        frame.add(mainPanel);

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(34, 139, 230)); // Blue for title panel
        titlePanel.setPreferredSize(new Dimension(1100, 100));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25)); // Center-align title
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Ambulance Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Create table and scroll pane
        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 16)); // Larger font size
        table.setRowHeight(35); // Increased row height for better readability
        table.setSelectionBackground(new Color(173, 216, 230)); // Light blue selection background
        table.setSelectionForeground(Color.BLACK);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
        table.getTableHeader().setBackground(new Color(34, 139, 230));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(211, 211, 211)); // Light gray grid lines

        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Added padding around table
        scrollPane.setBackground(Color.WHITE);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Populate the table
        try {
            conn c = new conn(); // Ensure 'conn' class exists in your project
            String q = "SELECT Name AS Name, Gender, Car_Name AS 'Car Name', Available, Location FROM Ambulance";
            PreparedStatement preparedStatement = c.connection.prepareStatement(q);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Add column names to the model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Add rows to the model
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            c.connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Unexpected error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Footer panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(34, 139, 230)); // Match footer with title panel
        footerPanel.setPreferredSize(new Dimension(1100, 80));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Center-align footer components
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Add BACK button
        JButton backButton = new JButton("BACK");
        backButton.setPreferredSize(new Dimension(200, 50)); // Larger button size
        backButton.setBackground(new Color(0, 0, 0)); // Black button
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        footerPanel.add(backButton);

        // Add action to BACK button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close current frame
            }
        });

        frame.setVisible(true);
    }
}

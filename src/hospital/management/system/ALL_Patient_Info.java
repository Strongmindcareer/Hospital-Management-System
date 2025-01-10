package hospital.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ALL_Patient_Info extends JFrame {
    ALL_Patient_Info() {
        // Set JFrame properties
        setTitle("All Patient Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null); // Open JFrame in the center of the screen
        setLayout(new BorderLayout());

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));
        add(mainPanel);

        // Create and style title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(10, 151, 176));
        titlePanel.setPreferredSize(new Dimension(1200, 80));
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("All Patient Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setPreferredSize(new Dimension(1000, 60));
        titlePanel.add(titleLabel);

        // Create table and scroll pane
        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setSelectionBackground(new Color(200, 230, 201));
        table.setSelectionForeground(Color.BLACK);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(10, 151, 176));
        table.getTableHeader().setForeground(Color.WHITE);

        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Populate the table
        try {
            conn c = new conn(); // Ensure 'conn' class exists in your project
            String q = "SELECT * FROM patients_info";
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
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unexpected error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Create footer panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(10, 151, 176));
        footerPanel.setPreferredSize(new Dimension(1200, 80));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Add BACK button
        JButton backButton = new JButton("BACK");
        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        footerPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ALL_Patient_Info();
    }
}

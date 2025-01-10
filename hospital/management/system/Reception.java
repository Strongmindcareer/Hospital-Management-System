package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reception extends JFrame {

    // Constructor
    Reception() {
        // Create a panel with a custom background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the image
                ImageIcon icon = new ImageIcon("C:\\Hospital Management System\\src\\icon\\R.png.png"); // Correct path to your image
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);
        panel.setBounds(5, 160, 1525, 678);
        add(panel);

        // Create the header panel
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(5, 5, 1525, 150);
        panel1.setBackground(new Color(10, 151, 176));
        add(panel1);

        // Add an image to the header with dynamic scaling
        ImageIcon originalIcon = new ImageIcon("C:\\Hospital Management System\\src\\icon\\o.png"); // Path to your image
        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH); // Adjust size
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel headerLabel = new JLabel(scaledIcon);
        headerLabel.setBounds(600, 35, 300, 100); // Centering the image horizontally
        panel1.add(headerLabel);

        // Add buttons to the main panel with manual positioning
        String[] buttonLabels = {
                "Add New Patient", "Room", "Department", "All Employee Info",
                "Patient Info", "Patient Discharge", "Update Details",
                "Hospital Ambulance", "Search Rooms", "Logout"
        };

        int[][] buttonPositions = {
                {50, 50}, {300, 50}, {550, 50}, {800, 50},
                {50, 150}, {300, 150}, {550, 150}, {800, 150},
                {50, 250}, {300, 250}
        };

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setBounds(buttonPositions[i][0], buttonPositions[i][1], 200, 50);
            button.setBackground(new Color(184, 0, 31));
            button.setForeground(Color.BLACK);
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            panel.add(button);

            // Add ActionListener for each button
            String label = buttonLabels[i]; // Capture the label for use in the listener
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (label.equals("Patient Info")) {
                        new ALL_Patient_Info(); // Open AllPatientInfo dialog
                    } else if (label.equals("Add New Patient")) {
                        new NEW_PATIENT(); // Open the NEW_PATIENT window when clicked
                    } else if (label.equals("Room")) {
                        new Room(); // Open the Room window when clicked
                    } else if (label.equals("Department")) {
                        new Department(); // Open the Department window when clicked
                    } else if (label.equals("All Employee Info")) {
                        new Employee_info(); // Open the Employee Info window when clicked
                    } else if (label.equals("Patient Discharge")) {
                        new patient_discharge(); // Open the Patient Discharge page
                    } else if (label.equals("Update Details")) {
                        new update_patient_details();
                    } else if (label.equals("Search Rooms")) {
                        new SearchRoom();
                    } else if (label.equals("Hospital Ambulance")) {
                        // Open Ambulance page in EDT
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                new Ambulance(); // Open the Ambulance page when clicked
                            }
                        });
                    } else if (label.equals("Logout")) {
                        System.exit(0); // Close the application on logout
                    }
                }
            });
        }

        setLayout(null);
        setSize(1550, 900); // Adjusted size
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Reception(); // Launch the Reception screen
    }
}

package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class update_patient_details extends JFrame {
    private JTextField nameField, roomNumberField, inTimeField, paidAmountField, pendingAmountField;
    private JButton updateButton, backButton, checkButton;

    public update_patient_details() {
        setTitle("Update Patient Details");
        setSize(651, 698);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Background Image Panel
        JLabel background = new JLabel(new ImageIcon("C:/Hospital Management System/src/icon/p.jpg")); // Replace with your image file
        background.setLayout(new GridBagLayout());
        setContentPane(background);

        // Layout and constraints for positioning components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Update Patient Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        background.add(titleLabel, gbc);

        // Patient Name
        JLabel nameLabel = new JLabel("Patient Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        background.add(nameLabel, gbc);

        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        background.add(nameField, gbc);

        // Room Number
        JLabel roomLabel = new JLabel("Room Number:");
        roomLabel.setFont(new Font("Arial", Font.BOLD, 16));
        roomLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2;
        background.add(roomLabel, gbc);

        roomNumberField = new JTextField();
        roomNumberField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        background.add(roomNumberField, gbc);

        // In Time
        JLabel inTimeLabel = new JLabel("In Time:");
        inTimeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inTimeLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 3;
        background.add(inTimeLabel, gbc);

        inTimeField = new JTextField();
        inTimeField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        background.add(inTimeField, gbc);

        // Amount Paid
        JLabel paidAmountLabel = new JLabel("Amount Paid (₹):");
        paidAmountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        paidAmountLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 4;
        background.add(paidAmountLabel, gbc);

        paidAmountField = new JTextField();
        paidAmountField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        background.add(paidAmountField, gbc);

        // Pending Amount
        JLabel pendingAmountLabel = new JLabel("Pending Amount (₹):");
        pendingAmountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pendingAmountLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 5;
        background.add(pendingAmountLabel, gbc);

        pendingAmountField = new JTextField();
        pendingAmountField.setFont(new Font("Arial", Font.PLAIN, 16));
        pendingAmountField.setEditable(false);
        gbc.gridx = 1;
        background.add(pendingAmountField, gbc);

        // Buttons
        updateButton = new JButton("Update");
        checkButton = new JButton("Check");
        backButton = new JButton("Back");

        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        checkButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFont(new Font("Arial", Font.BOLD, 14));

        gbc.gridx = 0;
        gbc.gridy = 6;
        background.add(updateButton, gbc);

        gbc.gridx = 1;
        background.add(checkButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        background.add(backButton, gbc);

        // Button Actions
        updateButton.addActionListener(new UpdateAction());
        checkButton.addActionListener(new CheckAction());
        backButton.addActionListener(e -> goBack());

        setVisible(true);
    }

    private class UpdateAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String roomNumber = roomNumberField.getText();
            String inTime = inTimeField.getText();
            String paidAmount = paidAmountField.getText();

            if (name.isEmpty() || roomNumber.isEmpty() || inTime.isEmpty() || paidAmount.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int paid = Integer.parseInt(paidAmount);
                int totalAmount = 5000; // Example: Assuming total amount for each patient is ₹5000
                int pendingAmount = totalAmount - paid;

                pendingAmountField.setText(String.valueOf(pendingAmount));
                JOptionPane.showMessageDialog(null, "Details Updated Successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid amount entered!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class CheckAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String roomNumber = roomNumberField.getText();
            String inTime = inTimeField.getText();
            String paidAmount = paidAmountField.getText();
            String pendingAmount = pendingAmountField.getText();

            if (name.isEmpty() || roomNumber.isEmpty() || inTime.isEmpty() || paidAmount.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled out before checking!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Display entered details in a readable format
            JOptionPane.showMessageDialog(
                    null,
                    "Patient Details:\n" +
                            "Name: " + name + "\n" +
                            "Room Number: " + roomNumber + "\n" +
                            "In Time: " + inTime + "\n" +
                            "Amount Paid (₹): " + paidAmount + "\n" +
                            "Pending Amount (₹): " + pendingAmount,
                    "Patient Details",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void goBack() {
        // Define the action when the "Back" button is pressed
        // You can navigate to another screen or close the current one
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(update_patient_details::new);
    }
}

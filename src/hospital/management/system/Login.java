package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public Login() {
        // Frame settings
        setTitle("Login Page");
        setSize(700, 500);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Background image
        JLabel background = new JLabel(new ImageIcon("C:\\Hospital Management System\\src\\icon\\g.jpg"));
        background.setBounds(0, 0, getWidth(), getHeight()); // Cover the entire frame
        add(background);

        // Login box panel (remove background color)
        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(0, 0, 400, 500);
        loginPanel.setLayout(null); // No background color
        loginPanel.setOpaque(false); // Make the panel transparent
        background.add(loginPanel); // Add login panel to background label

        // Centered "Login" heading label
        JLabel loginHeading = new JLabel("User Login..");
        loginHeading.setBounds(200, 50, 300, 40); // Adjust position
        loginHeading.setFont(new Font("Arial", Font.BOLD, 28));
        loginHeading.setForeground(new Color(0, 0, 0));
        loginPanel.add(loginHeading);

        // Centered welcome text
        JLabel welcomeLabel = new JLabel("Welcome to Hospital Management System !");
        welcomeLabel.setBounds(50, 120, 350, 40);  // Adjusted bounds
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 17)); // Font size reduced for better visibility
        welcomeLabel.setForeground(new Color(0, 0, 0)); // Ensuring distinct color from background
        loginPanel.add(welcomeLabel); // Make sure this is added to the panel

        // Centered username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 170, 300, 20);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setForeground(new Color(0, 0, 0));
        loginPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(50, 195, 300, 40);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(77, 161, 169), 2));
        loginPanel.add(usernameField);

        // Centered password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 250, 300, 20);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(new Color(0, 0, 0));
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 275, 300, 40);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(77, 161, 169), 2));
        loginPanel.add(passwordField);

        // Centered Show Password checkbox
        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(50, 320, 150, 20);
        showPassword.setBackground(new Color(241, 211, 206));
        showPassword.setForeground(new Color(0, 0, 0));
        showPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });
        loginPanel.add(showPassword);

        // Centered login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 380, 130, 40);
        loginButton.setBackground(new Color(0, 0, 0));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!username.isEmpty() && !password.isEmpty()) {
                try {
                    conn c = new conn();  // Create database connection instance
                    String query = "SELECT * FROM login WHERE ID = ? AND PW = ?"; // Correct column names
                    PreparedStatement pstmt = c.connection.prepareStatement(query);
                    pstmt.setString(1, username);  // Set username in query
                    pstmt.setString(2, password);  // Set password in query

                    ResultSet rs = pstmt.executeQuery();  // Execute the query
                    if (rs.next()) {
                        // Login success, open the Reception page
                        JOptionPane.showMessageDialog(null, "Welcome, " + username + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                        new Reception();  // Open the Reception page
                        dispose();  // Close the Login frame
                    } else {
                        // Invalid credentials
                        JOptionPane.showMessageDialog(null, "Invalid credentials, please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter both username and password.", "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        });
        loginPanel.add(loginButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}

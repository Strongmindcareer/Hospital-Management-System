package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class NEW_PATIENT extends JFrame implements ActionListener {
    // Declare components
    private JComboBox<String> comboBoxRoom;
    private JComboBox<String> comboBoxID;
    private JTextField textFieldID, textFieldName, textFieldDisease, textFieldTime, textFieldDeposit;
    private JRadioButton rMale, rFemale, rOther;
    private JButton bAdd, bBack, bClear;
    private ButtonGroup genderGroup;

    // Constructor to initialize the frame
    public NEW_PATIENT() {
        // Set frame properties
        setTitle("New Patient Registration");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addExitConfirmation();
        setLayout(null);  // Using null layout to position elements manually

        // Background image
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icon/patient.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(900, 700, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 1000, 700);  // Positioning the background
        add(backgroundLabel);

        // Create the form panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(10, 151, 176, 200));
        panel.setBounds(1, 1, 1000, 1000); // Positioning the panel inside the frame
        backgroundLabel.add(panel);  // Adding the panel on top of the background

        // Title
        JLabel title = new JLabel("New Patient Registration");
        title.setFont(new Font("Serif", Font.BOLD, 32));
        title.setForeground(Color.BLACK);
        title.setBounds(250, 20, 400, 50);
        panel.add(title);

        // ID Type
        JLabel labelIDType = createLabel("ID Type:", 50, 100, 150, 25);
        panel.add(labelIDType);
        comboBoxID = createComboBox(new String[] {"", "Aadhar Card", "Voter ID", "Driving License"}, 250, 100, 200, 30);
        panel.add(comboBoxID);

        // ID Number
        JLabel labelIDNumber = createLabel("Enter ID Number:", 50, 150, 150, 25);
        panel.add(labelIDNumber);
        textFieldID = createTextField(250, 150, 200, 30);
        panel.add(textFieldID);

        // Name
        JLabel labelName = createLabel("Name:", 50, 200, 150, 25);
        panel.add(labelName);
        textFieldName = createTextField(250, 200, 200, 30);
        panel.add(textFieldName);

        // Gender
        JLabel labelGender = createLabel("Gender:", 50, 250, 150, 25);
        panel.add(labelGender);
        rMale = createRadioButton("Male", 250, 250, 70, 30);
        rFemale = createRadioButton("Female", 320, 250, 90, 30);
        rOther = createRadioButton("Other", 410, 250, 80, 30);
        genderGroup = new ButtonGroup();
        genderGroup.add(rMale);
        genderGroup.add(rFemale);
        genderGroup.add(rOther);
        panel.add(rMale);
        panel.add(rFemale);
        panel.add(rOther);

        // Disease
        JLabel labelDisease = createLabel("Disease:", 50, 300, 150, 25);
        panel.add(labelDisease);
        textFieldDisease = createTextField(250, 300, 200, 30);
        panel.add(textFieldDisease);

        // Room Type
        JLabel labelRoomType = createLabel("Room Type:", 50, 350, 150, 25);
        panel.add(labelRoomType);
        comboBoxRoom = createComboBox(new String[] {"", "Standard", "Comfort", "Economy", "Luxury Suite", "Executive", "VIP Suite", "Presidential Suite", "ICU", "Orthopedic", "Maternity", "Pediatric", "Rehabilitation", "Burn Unit"}, 250, 350, 200, 30);
        panel.add(comboBoxRoom);

        // Admission Time
        JLabel labelAdmissionTime = createLabel("Admission Time:", 50, 400, 150, 25);
        panel.add(labelAdmissionTime);
        textFieldTime = createTextField(250, 400, 200, 30);
        panel.add(textFieldTime);

        // Deposit
        JLabel labelDeposit = createLabel("Deposit:", 50, 450, 150, 25);
        panel.add(labelDeposit);
        textFieldDeposit = createTextField(250, 450, 200, 30);
        panel.add(textFieldDeposit);

        // Buttons
        bAdd = createButton("Add", 250, 530, 100, 40, new Color(217, 234, 253));
        bAdd.addActionListener(this);  // Listen for button clicks
        panel.add(bAdd);

        bClear = createButton("Clear", 370, 530, 100, 40, new Color(217, 234, 253));
        bClear.addActionListener(this);
        panel.add(bClear);

        bBack = createButton("Back", 490, 530, 100, 40, new Color(217, 234, 253));
        bBack.addActionListener(this);
        panel.add(bBack);

        setVisible(true);
    }

    // Utility methods for creating components
    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        return textField;
    }

    private JComboBox<String> createComboBox(String[] items, int x, int y, int width, int height) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setBounds(x, y, width, height);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        return comboBox;
    }

    private JRadioButton createRadioButton(String text, int x, int y, int width, int height) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setBounds(x, y, width, height);
        radioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        return radioButton;
    }

    private JButton createButton(String text, int x, int y, int width, int height, Color color) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Tahoma", Font.PLAIN, 16));
        button.setBackground(color);
        return button;
    }

    // ActionListener method to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bAdd) {
            handleAddAction();  // Call method to add patient to the database
        } else if (e.getSource() == bClear) {
            clearFields();  // Call method to clear form fields
        } else if (e.getSource() == bBack) {
            dispose();  // Close the current window (back to previous window)
        }
    }

    // Action to add patient to database
    private void handleAddAction() {
        // Get form data
        String idType = (String) comboBoxID.getSelectedItem();
        String idNumber = textFieldID.getText();
        String name = textFieldName.getText();
        String gender = rMale.isSelected() ? "Male" : rFemale.isSelected() ? "Female" : "Other";
        String disease = textFieldDisease.getText();
        String roomType = (String) comboBoxRoom.getSelectedItem();
        String admissionTime = textFieldTime.getText();
        double deposit = 0;
        try {
            deposit = Double.parseDouble(textFieldDeposit.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid deposit amount.");
            return;
        }

        // Validate required fields
        if (idType.isEmpty() || idNumber.isEmpty() || name.isEmpty() || disease.isEmpty() || roomType.isEmpty() || admissionTime.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        // Get the room type ID from the room_types table
        int roomTypeId = getRoomTypeId(roomType);
        if (roomTypeId == -1) {
            JOptionPane.showMessageDialog(this, "Invalid room type selected.");
            return;
        }

        // Database insertion
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
             PreparedStatement stmt = con.prepareStatement("INSERT INTO patients_info (id_type, id_number, name, gender, disease, admission_time, deposit, room_type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, idType);
            stmt.setString(2, idNumber);
            stmt.setString(3, name);
            stmt.setString(4, gender);
            stmt.setString(5, disease);
            stmt.setString(6, admissionTime);
            stmt.setDouble(7, deposit);
            stmt.setInt(8, roomTypeId);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Patient added successfully!");
            clearFields();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // Helper method to get the room type ID based on the selected room type
    private int getRoomTypeId(String roomType) {
        int roomTypeId = -1;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "1234");
             PreparedStatement stmt = con.prepareStatement("SELECT room_id FROM room_types WHERE room_type = ?")) {

            stmt.setString(1, roomType);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                roomTypeId = rs.getInt("room_id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching room type ID: " + e.getMessage());
        }
        return roomTypeId;
    }

    // Clear all input fields
    private void clearFields() {
        comboBoxID.setSelectedIndex(0);
        textFieldID.setText("");
        textFieldName.setText("");
        textFieldDisease.setText("");
        textFieldTime.setText("");
        textFieldDeposit.setText("");
        comboBoxRoom.setSelectedIndex(0);
        genderGroup.clearSelection();
    }

    // Add exit confirmation when the window is closed
    private void addExitConfirmation() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int confirm = JOptionPane.showConfirmDialog(NEW_PATIENT.this,
                        "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    public static void main(String[] args) {
        new NEW_PATIENT();
    }
}

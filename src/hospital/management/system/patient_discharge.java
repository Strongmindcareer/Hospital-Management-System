package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class patient_discharge extends JFrame {

    private final HashMap<String, String[]> patientsInfo;

    public patient_discharge() {
        setTitle("Patient Discharge");

        // Simulated Database
        patientsInfo = new HashMap<>();
        patientsInfo.put("01", new String[]{"101", "10:00 AM", "Standard"});
        patientsInfo.put("02", new String[]{"102", "11:00 AM", "Comfort"});
        patientsInfo.put("03", new String[]{"103", "12:00 PM", "Luxury Suite"});
        patientsInfo.put("04", new String[]{"104", "1:00 PM", "Standard"});
        patientsInfo.put("05", new String[]{"105", "2:00 PM", "Comfort"});
        patientsInfo.put("06", new String[]{"106", "3:00 PM", "Luxury Suite"});
        patientsInfo.put("07", new String[]{"107", "4:00 PM", "Standard"});
        patientsInfo.put("08", new String[]{"108", "5:00 PM", "Comfort"});
        patientsInfo.put("09", new String[]{"109", "6:00 PM", "Luxury Suite"});
        patientsInfo.put("10", new String[]{"110", "7:00 PM", "Standard"});

        // Main panel with background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("C:/Hospital Management System/src/icon/Discharge.png"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    g.setColor(new Color(240, 248, 255)); // Light blue fallback
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        panel.setLayout(null);
        add(panel);

        // Title
        JLabel titleLabel = new JLabel("Patient Discharge");
        titleLabel.setBounds(200, 20, 400, 50);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 40));
        titleLabel.setForeground(new Color(0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel);

        // Customer ID Section
        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdLabel.setBounds(50, 100, 200, 30);
        customerIdLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(customerIdLabel);

        Choice customerIdChoice = new Choice();
        customerIdChoice.setBounds(250, 100, 250, 30);
        for (String id : patientsInfo.keySet()) {
            customerIdChoice.add(id);
        }
        panel.add(customerIdChoice);

        // Room Number Section
        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setBounds(50, 150, 200, 30);
        roomNumberLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(roomNumberLabel);

        JTextField roomNumberField = new JTextField();
        roomNumberField.setBounds(250, 150, 250, 30);
        roomNumberField.setEditable(false);
        roomNumberField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(roomNumberField);

        // In Time Section
        JLabel inTimeLabel = new JLabel("In Time:");
        inTimeLabel.setBounds(50, 200, 200, 30);
        inTimeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(inTimeLabel);

        JTextField inTimeField = new JTextField();
        inTimeField.setBounds(250, 200, 250, 30);
        inTimeField.setEditable(false);
        inTimeField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(inTimeField);

        // Room Type Section
        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeLabel.setBounds(50, 250, 200, 30);
        roomTypeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(roomTypeLabel);

        JTextField roomTypeField = new JTextField();
        roomTypeField.setBounds(250, 250, 250, 30);
        roomTypeField.setEditable(false);
        roomTypeField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(roomTypeField);

        // Out Time Section
        JLabel outTimeLabel = new JLabel("Out Time:");
        outTimeLabel.setBounds(50, 300, 200, 30);
        outTimeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(outTimeLabel);

        JTextField outTimeField = new JTextField();
        outTimeField.setBounds(250, 300, 250, 30);
        outTimeField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(outTimeField);

        // Remarks Section
        JLabel remarksLabel = new JLabel("Remarks:");
        remarksLabel.setBounds(50, 350, 200, 30);
        remarksLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(remarksLabel);

        JTextArea remarksArea = new JTextArea();
        remarksArea.setBounds(250, 350, 400, 120);
        remarksArea.setLineWrap(true);
        remarksArea.setWrapStyleWord(true);
        remarksArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(remarksArea);

        // Buttons
        JButton checkButton = new JButton("Check");
        checkButton.setBounds(550, 100, 150, 40);
        checkButton.setBackground(new Color(0, 153, 76));
        checkButton.setForeground(Color.WHITE);
        checkButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(checkButton);

        JButton dischargeButton = new JButton("Discharge");
        dischargeButton.setBounds(250, 500, 200, 50);
        dischargeButton.setBackground(new Color(204, 0, 0));
        dischargeButton.setForeground(Color.WHITE);
        dischargeButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(dischargeButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(500, 500, 150, 50);
        backButton.setBackground(new Color(0, 51, 153));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(backButton);

        // Action Listeners
        checkButton.addActionListener(e -> {
            String selectedId = customerIdChoice.getSelectedItem();
            if (patientsInfo.containsKey(selectedId)) {
                String[] details = patientsInfo.get(selectedId);
                roomNumberField.setText(details[0]);
                inTimeField.setText(details[1]);
                roomTypeField.setText(details[2]);
                remarksArea.setText("Remarks: Patient had a " + details[2].toLowerCase() + " stay.");
            }
        });

        dischargeButton.addActionListener(e -> {
            String outTime = outTimeField.getText().trim();
            if (outTime.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the Out Time.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String selectedId = customerIdChoice.getSelectedItem();
            patientsInfo.remove(selectedId);
            JOptionPane.showMessageDialog(this, "Discharge successful for ID: " + selectedId);
        });

        backButton.addActionListener(e -> dispose());

        // JFrame Properties
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new patient_discharge();
    }
}

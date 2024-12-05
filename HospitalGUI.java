import java.awt.*;
import javax.swing.*;

public class HospitalGUI {
    private HospitalManagementSystem hms;
    private JFrame frame;
    private JTextArea displayArea;

    public HospitalGUI() {
        hms = new HospitalManagementSystem();

        // Main frame setup
        frame = new JFrame("Grey Sloan Memorial Hospital");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());

        // Patient input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField medicalReasonField = new JTextField();
        JTextField priorityField = new JTextField();

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Medical Reason:"));
        inputPanel.add(medicalReasonField);
        inputPanel.add(new JLabel("Priority (1-10):"));
        inputPanel.add(priorityField);

        JButton addPatientButton = new JButton("Add Patient");
        addPatientButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String reason = medicalReasonField.getText();
                int priority = Integer.parseInt(priorityField.getText());

                if (priority < 1 || priority > 10) {
                    displayArea.append("Priority must be between 1 and 10.\n");
                    return;
                }

                Patient patient = new Patient(name, age, reason, priority);
                hms.addPatient(patient);

                String department = hms.getPatientDepartment(patient.getId());
                displayArea.append("Patient Added: " + patient + "\n");
                displayArea.append("Assigned Department: " + department + "\n");

                nameField.setText("");
                ageField.setText("");
                medicalReasonField.setText("");
                priorityField.setText("");
            } catch (NumberFormatException ex) {
                displayArea.append("Invalid input. Please try again.\n");
            }
        });

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Buttons for additional actions
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));
        JButton serveAppointmentButton = new JButton("Serve Next Appointment");
        serveAppointmentButton.addActionListener(e -> {
            String result = hms.serveNextAppointment();
            displayArea.append(result + "\n");
        });

        JButton handleEmergencyButton = new JButton("Handle Emergency");
        handleEmergencyButton.addActionListener(e -> {
            String result = hms.handleEmergency();
            displayArea.append(result + "\n");
        });

        JButton dischargePatientButton = new JButton("Discharge Patient");
        dischargePatientButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(frame, "Enter patient name to discharge:");
            if (name != null && !name.isEmpty()) {
                for (Patient patient : hms.retrieveAllPatients()) {
                    if (patient.getName().equalsIgnoreCase(name)) {
                        hms.dischargePatient(patient);
                        displayArea.append("Patient Discharged: " + patient + "\n");
                        return;
                    }
                }
                displayArea.append("Patient not found.\n");
            }
        });

        JButton undoDischargeButton = new JButton("Undo Discharge");
        undoDischargeButton.addActionListener(e -> {
            String result = hms.undoDischarge();
            displayArea.append(result + "\n");
        });

        JButton retrievePatientButton = new JButton("Retrieve Patient by ID");
        retrievePatientButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(frame, "Enter patient ID:");
            if (id != null && !id.isEmpty()) {
                Patient patient = hms.retrievePatientById(id);
                if (patient != null) {
                    displayArea.append("Patient Found: " + patient + "\n");
                } else {
                    displayArea.append("Patient with ID " + id + " not found.\n");
                }
            }
        });

        JButton listDepartmentsButton = new JButton("List Departments");
        listDepartmentsButton.addActionListener(e -> {
            displayArea.append("Departments: " + hms.listDepartments() + "\n");
        });

        // Add buttons to button panel
        buttonPanel.add(serveAppointmentButton);
        buttonPanel.add(handleEmergencyButton);
        buttonPanel.add(dischargePatientButton);
        buttonPanel.add(undoDischargeButton);
        buttonPanel.add(retrievePatientButton);
        buttonPanel.add(listDepartmentsButton);

        // Adding components to the frame
        frame.add(new JLabel("Welcome to Grey Sloan Memorial Hospital!", JLabel.CENTER), BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.WEST);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(addPatientButton, BorderLayout.EAST);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HospitalGUI::new);
    }
}


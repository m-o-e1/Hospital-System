import java.util.*;

public class HospitalManagementSystem {
    private ArrayList<Patient> allPatients = new ArrayList<>();
    private HashMap<String, String> patientDepartments = new HashMap<>(); // Map patient ID to department
    private AppointmentQueue appointmentQueue = new AppointmentQueue();
    private EmergencyQueue emergencyQueue = new EmergencyQueue();
    private DischargeStack dischargeStack = new DischargeStack();
    private HashMap<String, Patient> patientRecords = new HashMap<>(); // Hash table for quick patient retrieval

    /**
     * Adds a patient to the system.
     *
     * @param patient The patient to add
     */
    public void addPatient(Patient patient) {
        allPatients.add(patient); // Add to list of all patients
        appointmentQueue.addPatient(patient); // Add to the FIFO appointment queue
        patientRecords.put(patient.getId(), patient); // Add to hash table for quick lookup

        if (patient.getPriority() <= 2) {
            emergencyQueue.addEmergencyPatient(patient); // Add to emergency queue if priority is high
        }

        // Assign the patient to a department
        String department = assignDepartment(patient.getMedicalReason());
        patientDepartments.put(patient.getId(), department);
    }

    /**
     * Assigns a department based on the medical reason.
     *
     * @param medicalReason The reason for the visit
     * @return The department name
     */
    private String assignDepartment(String medicalReason) {
        if (medicalReason.toLowerCase().contains("heart") || medicalReason.toLowerCase().contains("cardiac")) {
            return "Cardiology";
        } else if (medicalReason.toLowerCase().contains("fracture") || medicalReason.toLowerCase().contains("bone")) {
            return "Orthopedics";
        } else if (medicalReason.toLowerCase().contains("child") || medicalReason.toLowerCase().contains("pediatric")) {
            return "Pediatrics";
        } else if (medicalReason.toLowerCase().contains("emergency")) {
            return "ER";
        }else if (medicalReason.toLowerCase().contains("brain")|| medicalReason.toLowerCase().contains("skull")){
           return "Neurology";
        }else if(medicalReason.toLowerCase().contains("pregnant") || medicalReason.toLowerCase().contains("birth")){
            return "Gynecology";
        }
         else {
            return "General Medicine"; // Default department
        }
    }

    /**
     * Retrieves a patient's department based on their ID.
     *
     * @param patientId The ID of the patient
     * @return The department name
     */
    public String getPatientDepartment(String patientId) {
        return patientDepartments.getOrDefault(patientId, "Unknown");
    }

    /**
     * Retrieves all patients currently in the system.
     *
     * @return A list of all patients
     */
    public ArrayList<Patient> retrieveAllPatients() {
        return new ArrayList<>(allPatients); // Return a copy of the list to prevent external modification
    }

    /**
     * Retrieves a patient by their unique ID.
     *
     * @param id The ID of the patient to retrieve
     * @return The patient with the specified ID, or null if not found
     */
    public Patient retrievePatientById(String id) {
        return patientRecords.get(id); // Retrieve the patient by ID
    }

    /**
     * Serves the next patient in the appointment queue.
     *
     * @return A message about the served patient
     */
    public String serveNextAppointment() {
        Patient patient = appointmentQueue.serveNextPatient();
        return patient == null ? "No appointments in the queue." : "Serving: " + patient;
    }

    /**
     * Handles the next emergency case in the emergency queue.
     *
     * @return A message about the handled emergency
     */
    public String handleEmergency() {
        Patient patient = emergencyQueue.handleEmergency();
        return patient == null ? "No emergency cases." : "Emergency handled for: " + patient;
    }

    /**
     * Discharges a patient and removes them from the system.
     *
     * @param patient The patient to discharge
     */
    public void dischargePatient(Patient patient) {
        dischargeStack.dischargePatient(patient); // Add to discharge stack
        allPatients.remove(patient); // Remove from the list of all patients
        patientRecords.remove(patient.getId()); // Remove from hash table
        patientDepartments.remove(patient.getId()); // Remove from department mapping
    }

    /**
     * Undoes the last patient discharge.
     *
     * @return A message about the undone discharge
     */
    public String undoDischarge() {
        Patient patient = dischargeStack.undoDischarge();
        if (patient != null) {
            allPatients.add(patient); // Re-add the patient to the list
            patientRecords.put(patient.getId(), patient); // Re-add to the hash table
            String department = assignDepartment(patient.getMedicalReason());
            patientDepartments.put(patient.getId(), department); // Reassign department
            return "Undo discharge for: " + patient;
        }
        return "No discharge to undo.";
    }

    /**
     * Lists all departments in the hospital.
     *
     * @return A comma-separated list of departments
     */
    public String listDepartments() {
        Department department = new Department();
        return department.listDepartments();
    }
}


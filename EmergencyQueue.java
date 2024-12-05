import java.util.PriorityQueue;
import java.util.Comparator;

public class EmergencyQueue {
    private PriorityQueue<Patient> emergencyQueue;

    public EmergencyQueue() {
        this.emergencyQueue = new PriorityQueue<>(Comparator.comparingInt(Patient::getPriority));
    }

    public void addEmergencyPatient(Patient patient) {
        emergencyQueue.add(patient);
    }

    public Patient handleEmergency() {
        return emergencyQueue.poll(); // Handle the highest priority
    }

    public String listEmergencyCases() {
        return emergencyQueue.isEmpty() ? "No emergency cases." : emergencyQueue.toString();
    }
}

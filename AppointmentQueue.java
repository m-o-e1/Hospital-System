import java.util.LinkedList;
import java.util.Queue;

public class AppointmentQueue {
    private Queue<Patient> appointmentQueue;

    public AppointmentQueue() {
        this.appointmentQueue = new LinkedList<>();
    }

    public void addPatient(Patient patient) {
        appointmentQueue.add(patient);
    }

    public Patient serveNextPatient() {
        return appointmentQueue.poll(); // Serve the next patient
    }

    public String listAppointments() {
        return appointmentQueue.isEmpty() ? "No appointments." : appointmentQueue.toString();
    }
}

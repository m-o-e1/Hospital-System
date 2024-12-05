import java.util.Stack;

public class DischargeStack {
    private Stack<Patient> dischargedPatients;

    public DischargeStack() {
        this.dischargedPatients = new Stack<>();
    }

    public void dischargePatient(Patient patient) {
        dischargedPatients.push(patient);
    }

    public Patient undoDischarge() {
        return dischargedPatients.isEmpty() ? null : dischargedPatients.pop();
    }

    public String listDischargedPatients() {
        return dischargedPatients.isEmpty() ? "No discharged patients." : dischargedPatients.toString();
    }
}


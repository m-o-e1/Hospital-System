public class Patient {
    private static int idCounter = 1; // Static counter for unique IDs
    private String id; // Unique ID
    private String name;
    private int age;
    private String medicalReason;
    private int priority; // Lower number = higher priority

    public Patient(String name, int age, String medicalReason, int priority) {
        this.id = "P" + (idCounter++); // Auto-generate unique ID (e.g., P1, P2)
        this.name = name;
        this.age = age;
        this.medicalReason = medicalReason;
        this.priority = priority;
    }

    public String getId() { //getter function
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMedicalReason() {
        return medicalReason;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + " (" + age + ", " + medicalReason + ", Priority: " + priority + ")";
    }
}

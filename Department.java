import java.util.ArrayList;

public class Department {
    private ArrayList<String> departments;

    public Department() {
        this.departments = new ArrayList<>();
        // Initialize departments
        departments.add("ER");
        departments.add("Pediatrics");
        departments.add("Neurology");
        departments.add("Orthology");
        departments.add("Gynecology");
        departments.add("Cardiology");
        departments.add("General Surgery");
    }

    public ArrayList<String> getDepartments() {
        return departments;
    }

    public String listDepartments() {
        return String.join(", ", departments);
    }
}



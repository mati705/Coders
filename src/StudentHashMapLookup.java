import java.util.HashMap;

public class StudentHashMapLookup {

    // HashMap - Fast Lookup for Students using Student ID
    private HashMap<String, Student> studentMap = new HashMap<>();

    // Add student using student ID as key
    public void addStudent(Student s) {
        studentMap.put(s.getStudentId(), s);
        System.out.println("Student added in lookup system: " + s.getName());
    }

    public void searchStudent(String id) {
        if (studentMap.containsKey(id)) {
            System.out.println("Student Found -> " + studentMap.get(id));
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // Display all student IDs
    public void display() {
        System.out.println("\n--- Fast Lookup using HashMap ---");
        System.out.println("Student IDs: " + studentMap.keySet());
    }
}
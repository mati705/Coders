import java.util.HashMap;

public class Fast_Lookup {
    //HashMap - Fast Lookup for Students and Courses (O(1) access)

    private HashMap<Integer, Student> studentMap = new HashMap<>();

    // Add a student using their ID as the unique key
    public void addStudent(Student s) {
        studentMap.put(s.getStudentId(), s);
        System.out.println("Registered student " + s.getName() + " in lookup system.");
    }

    public void searchStudent(int id) {
        if (studentMap.containsKey(id)) {
            System.out.println("Student Found -> " + studentMap.get(id));
        } else {
            System.out.println("Error: Student with ID " + id + " not found.");
        }
    }

    // Simple display to show all registered IDs
    public void display() {
        System.out.println("\n--- Fast Lookup (HashMap) ---");
        System.out.println("Registered Student IDs: " + studentMap.keySet());
    }
}
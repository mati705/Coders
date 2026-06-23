import java.util.HashMap;

public class StudentHashMapLookup {

    private HashMap<String, Student> studentMap = new HashMap<>();

    public void addStudent(Student s) {
        studentMap.put(s.getStudentId(), s);
        System.out.println("Student added in lookup system: " + s.getName());
    }

    public Student getStudent(String id) {
        return studentMap.get(id);
    }

    public boolean containsStudent(String id) {
        return studentMap.containsKey(id);
    }

    public void searchStudent(String id) {
        if (studentMap.containsKey(id)) {
            System.out.println("Student Found -> " + studentMap.get(id));
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public void display() {
        System.out.println("\n--- Fast Lookup using HashMap ---");
        System.out.println("Student IDs: " + studentMap.keySet());
    }
}
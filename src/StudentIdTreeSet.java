import java.util.TreeSet;

public class StudentIdTreeSet {

    private TreeSet<String> studentIds = new TreeSet<>();

    public void addStudentId(String studentId) {
        studentIds.add(studentId);
    }

    public void displayStudentIds() {
        System.out.println("\n--- TreeSet Sorted Student IDs ---");

        for (String id : studentIds) {
            System.out.println(id);
        }
    }
}
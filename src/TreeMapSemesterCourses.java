import java.util.TreeMap;

public class TreeMapSemesterCourses {

    private TreeMap<Integer, String> semesterCourses = new TreeMap<>();

    public void addSemesterCourses(int semester, String courses) {
        semesterCourses.put(semester, courses);
    }

    public void displaySemesterCourses() {
        System.out.println("\n--- TreeMap Semester Wise Courses ---");

        for (Integer semester : semesterCourses.keySet()) {
            System.out.println("Semester " + semester + ": " + semesterCourses.get(semester));
        }
    }
}
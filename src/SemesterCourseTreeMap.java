import java.util.TreeMap;

public class SemesterCourseTreeMap {

    private TreeMap<Integer, String> SemesterCourseTreeMap = new TreeMap<>();

    public void addSemesterCourses(int semester, String courses) {
        SemesterCourseTreeMap.put(semester, courses);
    }

    public void displaySemesterCourses() {
        System.out.println("\n--- TreeMap Semester Wise Courses ---");

        for (Integer semester : SemesterCourseTreeMap.keySet()) {
            System.out.println("Semester " + semester + ": " + SemesterCourseTreeMap.get(semester));
        }
    }
}
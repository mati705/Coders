import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PrerequisiteGraph {

    private HashMap<String, ArrayList<String>> prerequisites;

    public PrerequisiteGraph() {
        prerequisites = new HashMap<>();
    }

    public void addCourse(String courseId) {
        if (!prerequisites.containsKey(courseId)) {
            prerequisites.put(courseId, new ArrayList<>());
        }
    }

    public void addPrerequisite(String courseId, String prerequisiteCourse) {
        addCourse(courseId);
        addCourse(prerequisiteCourse);

        prerequisites.get(courseId).add(prerequisiteCourse);
    }

    public boolean canEnroll(String courseId, HashSet<String> completedCourses) {
        HashSet<String> checkedCourses = new HashSet<>();

        boolean result = checkPrerequisites(courseId, completedCourses, checkedCourses);

        if (result) {
            System.out.println("Student can enroll in " + courseId);
        } else {
            System.out.println("Student cannot enroll in " + courseId);
        }

        return result;
    }

    private boolean checkPrerequisites(String courseId,
                                       HashSet<String> completedCourses,
                                       HashSet<String> checkedCourses) {

        if (checkedCourses.contains(courseId)) {
            return true;
        }

        checkedCourses.add(courseId);

        ArrayList<String> requiredList = prerequisites.get(courseId);

        if (requiredList == null || requiredList.size() == 0) {
            return true;
        }

        for (int i = 0; i < requiredList.size(); i++) {
            String requiredCourse = requiredList.get(i);

            if (!completedCourses.contains(requiredCourse)) {
                System.out.println("Missing prerequisite: " + requiredCourse);
                return false;
            }

            boolean previousRequirementsCompleted =
                    checkPrerequisites(requiredCourse, completedCourses, checkedCourses);

            if (!previousRequirementsCompleted) {
                return false;
            }
        }

        return true;
    }

    public void displayGraph() {
        System.out.println("\n--- Course Prerequisite Graph ---");

        for (String course : prerequisites.keySet()) {
            System.out.println(course + " requires " + prerequisites.get(course));
        }
    }
}
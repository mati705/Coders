import java.util.ArrayList;
import java.util.HashMap;

public class StudentEnrollmentHashMap {

    private HashMap<String, ArrayList<Course>> enrollmentMap = new HashMap<>();

    public void enrollStudent(String studentId, Course course) {
        enrollmentMap.putIfAbsent(studentId, new ArrayList<Course>());
        enrollmentMap.get(studentId).add(course);
    }

    public boolean dropCourse(String studentId, String courseId) {
        ArrayList<Course> courses = enrollmentMap.get(studentId);

        if (courses == null) {
            return false;
        }

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equalsIgnoreCase(courseId)) {
                courses.remove(i);
                return true;
            }
        }

        return false;
    }

    public boolean isAlreadyEnrolled(String studentId, String courseId) {
        ArrayList<Course> courses = enrollmentMap.get(studentId);

        if (courses == null) {
            return false;
        }

        for (Course course : courses) {
            if (course.getCourseId().equalsIgnoreCase(courseId)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Course> getStudentCourses(String studentId) {
        enrollmentMap.putIfAbsent(studentId, new ArrayList<Course>());
        return enrollmentMap.get(studentId);
    }

    public ArrayList<String> getAllEnrollmentLines() {
        ArrayList<String> lines = new ArrayList<>();

        for (String studentId : enrollmentMap.keySet()) {
            ArrayList<Course> courses = enrollmentMap.get(studentId);

            for (Course course : courses) {
                lines.add(studentId + "|" + course.getCourseId());
            }
        }

        return lines;
    }

    public void displayStudentCourses(String studentId) {
        ArrayList<Course> courses = enrollmentMap.get(studentId);

        System.out.println("\n--- Enrolled Courses for " + studentId + " ---");

        if (courses == null || courses.size() == 0) {
            System.out.println("No enrolled courses.");
            return;
        }

        for (Course course : courses) {
            System.out.println(course);
        }
    }
}
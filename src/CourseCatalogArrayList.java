import java.util.ArrayList;

public class CourseCatalogArrayList {

    private ArrayList<Course> courses;

    public CourseCatalogArrayList() {
        courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        if (findById(course.getCourseId()) == null) {
            courses.add(course);
        }
    }

    public Course getCourse(int index) {
        return courses.get(index);
    }

    public Course findById(String courseId) {
        for (Course c : courses) {
            if (c.getCourseId().equalsIgnoreCase(courseId)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Course> getAll() {
        return courses;
    }

    public void displayCourses() {
        System.out.println("\n--- Course Catalog using ArrayList ---");
        for (Course c : courses) {
            System.out.println(c);
        }
    }
}
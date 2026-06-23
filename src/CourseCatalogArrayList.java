import java.util.ArrayList;
// Stores all available courses. Easy add / remove / iterate.

public class CourseCatalogArrayList {
    private ArrayList<Course> courses;

    public CourseCatalogArrayList() {
        courses = new ArrayList<>();
    }

    public void addCourse(Course course) {


        courses.add(course);
    }

    public Course getCourse(int index) {
        return courses.get(index);
    }

    public Course findById(String courseId) {
        for (Course c : courses) {
            if (c.getCourseId().equals(courseId))
                return c;
        }
        return null;
    }

    public ArrayList<Course> getAll() {
        return courses;
    }

    public void displayCourses() {
        for(Course c : courses) {
            System.out.println(c);
        }
    }
}
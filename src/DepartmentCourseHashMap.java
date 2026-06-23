import java.util.ArrayList;
import java.util.HashMap;

public class DepartmentCourseHashMap {

    private HashMap<String, ArrayList<Course>> DepartmentCourseHashMap = new HashMap<>();

    public void addCourseToDepartment(String departmentName, Course course) {
        DepartmentCourseHashMap.putIfAbsent(departmentName, new ArrayList<Course>());
        DepartmentCourseHashMap.get(departmentName).add(course);
    }

    public ArrayList<Course> getCoursesByDepartment(String departmentName) {
        return DepartmentCourseHashMap.get(departmentName);
    }

    public void displayDepartments() {
        System.out.println("\n--- Department Course Records using HashMap ---");

        for (String department : DepartmentCourseHashMap.keySet()) {
            System.out.println("\nDepartment: " + department);

            ArrayList<Course> courses = DepartmentCourseHashMap.get(department);
            for (Course course : courses) {
                System.out.println(course);
            }
        }
    }
}
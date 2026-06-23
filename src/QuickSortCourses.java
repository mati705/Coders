import java.util.ArrayList;

public class QuickSortCourses {

    public static void sortAndPrint(CourseCatalogArrayList storage) {
        ArrayList<Course> list = storage.getAll();

        Course[] courses = new Course[list.size()];

        for (int i = 0; i < list.size(); i++) {
            courses[i] = list.get(i);
        }

        if (courses.length == 0) {
            System.out.println("No courses available for quick sort.");
            return;
        }

        quickSort(courses, 0, courses.length - 1);
        printCourses(courses);
    }

    public static void quickSort(Course[] courses, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(courses, low, high);

            quickSort(courses, low, pivotIndex - 1);
            quickSort(courses, pivotIndex + 1, high);
        }
    }

    private static int partition(Course[] courses, int low, int high) {
        String pivot = courses[high].getCourseName();
        int smallIndex = low - 1;

        for (int i = low; i < high; i++) {
            String currentCourseName = courses[i].getCourseName();

            if (currentCourseName.compareToIgnoreCase(pivot) < 0) {
                smallIndex++;
                swap(courses, smallIndex, i);
            }
        }

        swap(courses, smallIndex + 1, high);

        return smallIndex + 1;
    }

    private static void swap(Course[] courses, int first, int second) {
        Course temp = courses[first];
        courses[first] = courses[second];
        courses[second] = temp;
    }

    public static void printCourses(Course[] courses) {
        for (int i = 0; i < courses.length; i++) {
            System.out.println(courses[i]);
        }
    }
}
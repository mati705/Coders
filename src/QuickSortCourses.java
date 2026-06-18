public class QuickSortCourses {

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
            if (courses[i].getCourseName().compareToIgnoreCase(pivot) < 0) {
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
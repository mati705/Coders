public class HeapSortCourses {

    public static void heapSort(Course[] courses) {
        int size = courses.length;

        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(courses, size, i);
        }

        for (int i = size - 1; i > 0; i--) {
            swap(courses, 0, i);
            heapify(courses, i, 0);
        }
    }

    private static void heapify(Course[] courses, int size, int rootIndex) {
        int largest = rootIndex;

        int left = 2 * rootIndex + 1;
        int right = 2 * rootIndex + 2;

        if (left < size && courses[left].getCourseId().compareToIgnoreCase(courses[largest].getCourseId()) > 0) {
            largest = left;
        }

        if (right < size && courses[right].getCourseId().compareToIgnoreCase(courses[largest].getCourseId()) > 0) {
            largest = right;
        }

        if (largest != rootIndex) {
            swap(courses, rootIndex, largest);
            heapify(courses, size, largest);
        }
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
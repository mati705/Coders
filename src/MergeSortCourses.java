public class MergeSortCourses {

    public static void sort(Course[] courses, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;

            sort(courses, start, mid);
            sort(courses, mid + 1, end);

            merge(courses, start, mid, end);
        }
    }

    private static void merge(Course[] courses, int start, int mid, int end) {
        int leftSize = mid - start + 1;
        int rightSize = end - mid;

        Course[] left = new Course[leftSize];
        Course[] right = new Course[rightSize];

        for (int i = 0; i < leftSize; i++) {
            left[i] = courses[start + i];
        }

        for (int i = 0; i < rightSize; i++) {
            right[i] = courses[mid + 1 + i];
        }

        int i = 0;
        int j = 0;
        int k = start;

        while (i < leftSize && j < rightSize) {
            if (left[i].getCourseId().compareToIgnoreCase(right[j].getCourseId()) <= 0) {
                courses[k] = left[i];
                i++;
            } else {
                courses[k] = right[j];
                j++;
            }

            k++;
        }

        while (i < leftSize) {
            courses[k] = left[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            courses[k] = right[j];
            j++;
            k++;
        }
    }

    public static void printCourses(Course[] courses) {
        for (int i = 0; i < courses.length; i++) {
            System.out.println(courses[i]);
        }
    }
}
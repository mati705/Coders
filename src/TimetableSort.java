import java.util.ArrayList;

// 9) Sorting Algorithms - Timetable Organization
public class TimetableSort {

    // ---------- Merge Sort (by courseName ascending) ----------
    public static void mergeSort(ArrayList<Course> list, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(list, l, m);
            mergeSort(list, m + 1, r);
            merge(list, l, m, r);
        }
    }

    private static void merge(ArrayList<Course> list, int l, int m, int r) {
        // Create temporary sublists using your explicit Course class types
        ArrayList<Course> left = new ArrayList<>(list.subList(l, m + 1));
        ArrayList<Course> right = new ArrayList<>(list.subList(m + 1, r + 1));

        int i = 0, j = 0, k = l;
        while (i < left.size() && j < right.size()) {
            // Use the getter method getCourseName() and compare strings alphabetically
            if (left.get(i).getCourseName().compareTo(right.get(j).getCourseName()) <= 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) list.set(k++, left.get(i++));
        while (j < right.size()) list.set(k++, right.get(j++));
    }

    // ---------- Quick Sort (by courseId ascending) ----------
    public static void quickSort(ArrayList<Course> list, int low, int high) {
        if (low < high) {
            int p = partition(list, low, high);
            quickSort(list, low, p - 1);
            quickSort(list, p + 1, high);
        }
    }

    private static int partition(ArrayList<Course> list, int low, int high) {
        // Use the getter method getCourseId() to fetch the pivot string
        String pivot = list.get(high).getCourseId();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // Compare current item's ID with the pivot ID
            if (list.get(j).getCourseId().compareTo(pivot) <= 0) {
                i++;
                Course tmp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, tmp);
            }
        }
        Course tmp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, tmp);
        return i + 1;
    }

    public static void display(String title, ArrayList<Course> list) {
        System.out.println("\n--- " + title + " ---");
        for (Course c : list) System.out.println(c); // This automatically triggers your custom toString()
    }
}
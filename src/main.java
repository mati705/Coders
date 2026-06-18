import java.util.HashSet;

public class main {

    public static void main(String[] args) {

        System.out.println("SMART UNIVERSITY COURSE MANAGEMENT SYSTEM");
        System.out.println("------------------------------------------");

        Course c1 = new Course("CS101", "Programming Fundamentals", "Monday 9-11");
        Course c2 = new Course("CS201", "Object Oriented Programming", "Monday 11-1");
        Course c3 = new Course("CS301", "Data Structures", "Tuesday 9-11");
        Course c4 = new Course("CS401", "Database Systems", "Wednesday 10-12");
        Course c5 = new Course("CS405", "Software Engineering", "Tuesday 9-11");

        Student s1 = new Student("SE-101", "Ali Khan", 3.60);
        Student s2 = new Student("SE-102", "Sara Ahmed", 3.85);
        Student s3 = new Student("SE-103", "Bilal Hussain", 3.25);

        Course[] courses = {c1, c2, c3, c4, c5};

        System.out.println("\n--- All Courses ---");
        for (int i = 0; i < courses.length; i++) {
            System.out.println(courses[i]);
        }

        System.out.println("\n--- Merge Sort Courses By Course ID ---");
        Course[] mergeArray = {c1, c2, c3, c4, c5};
        MergeSortCourses.sort(mergeArray, 0, mergeArray.length - 1);
        MergeSortCourses.printCourses(mergeArray);

        System.out.println("\n--- Quick Sort Courses By Course Name ---");
        Course[] quickArray = {c1, c2, c3, c4, c5};
        QuickSortCourses.quickSort(quickArray, 0, quickArray.length - 1);
        QuickSortCourses.printCourses(quickArray);

        System.out.println("\n--- Heap Sort Courses By Course ID ---");
        Course[] heapArray = {c1, c2, c3, c4, c5};
        HeapSortCourses.heapSort(heapArray);
        HeapSortCourses.printCourses(heapArray);

        System.out.println("\n--- Prerequisite Graph Testing ---");

        PrerequisiteGraph graph = new PrerequisiteGraph();

        graph.addCourse("CS101");
        graph.addCourse("CS201");
        graph.addCourse("CS301");
        graph.addCourse("CS401");

        graph.addPrerequisite("CS201", "CS101");
        graph.addPrerequisite("CS301", "CS201");
        graph.addPrerequisite("CS401", "CS101");

        graph.displayGraph();

        HashSet<String> completedCourses = new HashSet<>();
        completedCourses.add("CS101");
        completedCourses.add("CS201");

        if (graph.canEnroll("CS301", completedCourses)) {
            System.out.println("Student can enroll in CS301.");
        } else {
            System.out.println("Student cannot enroll in CS301.");
        }

        if (graph.canEnroll("CS401", completedCourses)) {
            System.out.println("Student can enroll in CS401.");
        } else {
            System.out.println("Student cannot enroll in CS401.");
        }

        System.out.println("\n--- Undo Stack Testing ---");

        UndoActionImplementation undoStack = new UndoActionImplementation(10);

        UndoAction a1 = new UndoAction("REGISTER", "SE-101", "CS301", "Registration done");
        UndoAction a2 = new UndoAction("DROP", "SE-102", "CS201", "Course dropped");
        UndoAction a3 = new UndoAction("ADD_COURSE", "N/A", "CS405", "Course added");

        undoStack.push(a1);
        undoStack.push(a2);
        undoStack.push(a3);

        undoStack.displayStack();

        System.out.println("\nUndo Last Action:");
        undoStack.undoLastAction();

        System.out.println("\nAfter Undo:");
        undoStack.displayStack();

        System.out.println("\nPROJECT TESTING COMPLETED.");


        TreeSetStudentRecords set = new TreeSetStudentRecords();

        set.addStudentId("SE-103");
        set.addStudentId("SE-101");
        set.addStudentId("SE-102");

        set.displayStudentIds();

        TreeMapSemesterCourses map = new TreeMapSemesterCourses();

        map.addSemesterCourses(4, "DSA, Database Systems, Software Engineering");
        map.addSemesterCourses(1, "Programming Fundamentals, English");
        map.addSemesterCourses(2, "OOP, Discrete Structures");

        map.displaySemesterCourses();
    }
}
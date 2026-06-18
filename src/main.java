import java.util.ArrayList;
import java.util.HashSet;

public class main {

    public static void main(String[] args) {

        System.out.println("SMART UNIVERSITY COURSE MANAGEMENT SYSTEM");

        // ---------------- FILE HANDLING OBJECT ----------------
        FileHandling fileHandler = new FileHandling();
        fileHandler.createFiles();

        // ---------------- DATA STRUCTURE OBJECTS ----------------
        ArrayListStorage arrayListStorage = new ArrayListStorage();
        Fast_Lookup lookup = new Fast_Lookup();
        AccedemicRecordBST bst = new AccedemicRecordBST();
        Enrollment_History history = new Enrollment_History();
        RegistrationQueue registrationQueue = new RegistrationQueue();
        Continuous_Registration circularQueue = new Continuous_Registration(5);
        PrerequisiteGraph graph = new PrerequisiteGraph();
        DoublyLinkedListNavigation navigation = new DoublyLinkedListNavigation();
        PriorityQueueManager priorityQueue = new PriorityQueueManager();
        UndoActionImplementation undoStack = new UndoActionImplementation(10);

        // ---------------- LOAD DATA FROM FILES ----------------
        fileHandler.loadCourses(arrayListStorage, graph);
        fileHandler.loadStudents(lookup, bst);
        fileHandler.loadEnrollments(history);
        fileHandler.loadPrerequisites(graph);
        fileHandler.loadRegistrationRequests(registrationQueue, circularQueue);

        // ---------------- SHOW LOADED DATA ----------------
        System.out.println("\n--- Courses Loaded From File ---");
        arrayListStorage.displayCourses();

        System.out.println("\n--- Students Loaded in HashMap ---");
        lookup.display();

        System.out.println("\n--- Students Loaded in BST ---");
        bst.displayInOrder();

        System.out.println("\n--- Enrollment History Loaded ---");
        history.displayHistory();

        System.out.println("\n--- Prerequisite Graph Loaded ---");
        graph.displayGraph();

        System.out.println("\n--- Registration Queue Loaded ---");
        registrationQueue.display();

        System.out.println("\n--- Circular Queue Loaded ---");
        circularQueue.display();

        // ---------------- ADD SAMPLE DATA IF COURSE FILE IS EMPTY ----------------
        if (arrayListStorage.getAll().size() == 0) {

            System.out.println("\nNo courses found in file, adding sample courses for testing.");

            Course c1 = new Course("CS101", "Programming Fundamentals", "Monday 9-11");
            Course c2 = new Course("CS201", "Object Oriented Programming", "Monday 11-1");
            Course c3 = new Course("CS301", "Data Structures", "Tuesday 9-11");
            Course c4 = new Course("CS401", "Database Systems", "Wednesday 10-12");
            Course c5 = new Course("CS405", "Software Engineering", "Tuesday 9-11");

            arrayListStorage.addCourse(c1);
            arrayListStorage.addCourse(c2);
            arrayListStorage.addCourse(c3);
            arrayListStorage.addCourse(c4);
            arrayListStorage.addCourse(c5);

            graph.addCourse("CS101");
            graph.addCourse("CS201");
            graph.addCourse("CS301");
            graph.addCourse("CS401");
            graph.addCourse("CS405");
        }

        // ---------------- SAMPLE STUDENTS FOR TESTING PRIORITY QUEUE ----------------
        Student s1 = new Student("SE-101", "Ali Khan", 3.60);
        Student s2 = new Student("SE-102", "Sara Ahmed", 3.85);
        Student s3 = new Student("SE-103", "Bilal Hussain", 3.25);

        lookup.addStudent(s1);
        lookup.addStudent(s2);
        lookup.addStudent(s3);

        bst.insert(s1);
        bst.insert(s2);
        bst.insert(s3);

        // ---------------- ARRAYLIST TESTING ----------------
        System.out.println("\n--- ArrayList Storage Testing ---");
        arrayListStorage.displayCourses();

        System.out.println("\nSearch Course CS301:");
        Course foundCourse = arrayListStorage.findById("CS301");

        if (foundCourse != null) {
            System.out.println("Course Found: " + foundCourse);
        } else {
            System.out.println("Course not found.");
        }

        // ---------------- SINGLY LINKED LIST TESTING ----------------
        System.out.println("\n--- Enrollment History Singly Linked List Testing ---");

        history.addRecord("CS101");
        history.addRecord("CS201");
        history.addRecord("CS301");

        System.out.println("Enrollment History:");
        history.displayHistory();

        System.out.println("Total Records: " + history.countRecords());

        if (history.searchCourse("CS201")) {
            System.out.println("CS201 found in enrollment history.");
        } else {
            System.out.println("CS201 not found in enrollment history.");
        }

        history.deleteRecord("CS201");

        System.out.println("\nAfter deleting CS201:");
        history.displayHistory();

        System.out.println("Total Records: " + history.countRecords());

        // ---------------- DOUBLY LINKED LIST TESTING ----------------
        System.out.println("\n--- Doubly Linked List Course Navigation Testing ---");

        ArrayList<Course> courseList = arrayListStorage.getAll();

        for (int i = 0; i < courseList.size(); i++) {
            navigation.addCourse(courseList.get(i).toString());
        }

        navigation.displayForward();
        navigation.displayBackward();

        System.out.println("Total Courses in Navigation: " + navigation.countCourses());

        if (navigation.searchCourse("CS301 - Data Structures - Tuesday 9-11")) {
            System.out.println("Course found in navigation list.");
        } else {
            System.out.println("Course not found in navigation list.");
        }

        navigation.deleteCourse("CS201 - Object Oriented Programming - Monday 11-1");

        System.out.println("\nAfter deleting CS201:");
        navigation.displayForward();
        navigation.displayBackward();

        System.out.println("Total Courses in Navigation: " + navigation.countCourses());

        // ---------------- MAKE COURSE ARRAY FROM ARRAYLIST ----------------
        Course[] courses = makeCourseArray(arrayListStorage);

        System.out.println("\n--- All Courses From ArrayList ---");
        for (int i = 0; i < courses.length; i++) {
            System.out.println(courses[i]);
        }

        // ---------------- MERGE SORT ----------------
        System.out.println("\n--- Merge Sort Courses By Course ID ---");
        Course[] mergeArray = makeCourseArray(arrayListStorage);

        if (mergeArray.length > 0) {
            MergeSortCourses.sort(mergeArray, 0, mergeArray.length - 1);
            MergeSortCourses.printCourses(mergeArray);
        } else {
            System.out.println("No courses available for merge sort.");
        }

        // ---------------- QUICK SORT ----------------
        System.out.println("\n--- Quick Sort Courses By Course Name ---");
        Course[] quickArray = makeCourseArray(arrayListStorage);

        if (quickArray.length > 0) {
            QuickSortCourses.quickSort(quickArray, 0, quickArray.length - 1);
            QuickSortCourses.printCourses(quickArray);
        } else {
            System.out.println("No courses available for quick sort.");
        }

        // ---------------- HEAP SORT ----------------
        System.out.println("\n--- Heap Sort Courses By Course ID ---");
        Course[] heapArray = makeCourseArray(arrayListStorage);

        if (heapArray.length > 0) {
            HeapSortCourses.heapSort(heapArray);
            HeapSortCourses.printCourses(heapArray);
        } else {
            System.out.println("No courses available for heap sort.");
        }

        // ---------------- PREREQUISITE GRAPH TESTING ----------------
        System.out.println("\n--- Prerequisite Graph Testing ---");

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

        // ---------------- PRIORITY QUEUE TESTING ----------------
        System.out.println("\n--- Priority Queue Waitlist Testing ---");

        priorityQueue.addStudent(String.valueOf(s1.getCgpa()), s1.getName());
        priorityQueue.addStudent(String.valueOf(s2.getCgpa()), s2.getName());
        priorityQueue.addStudent(String.valueOf(s3.getCgpa()), s3.getName());

        priorityQueue.peekNext();

        priorityQueue.assignSeat();
        priorityQueue.assignSeat();
        priorityQueue.assignSeat();

        priorityQueue.peekNext();

        // ---------------- NORMAL QUEUE TESTING ----------------
        System.out.println("\n--- Normal Registration Queue Testing ---");

        registrationQueue.addRequest("SE-101 requested CS101");
        registrationQueue.addRequest("SE-102 requested CS301");
        registrationQueue.addRequest("SE-103 requested CS401");

        registrationQueue.display();
        registrationQueue.peekNext();
        registrationQueue.totalRequests();

        registrationQueue.processNext();

        System.out.println("\nAfter Processing One Request:");
        registrationQueue.display();
        registrationQueue.peekNext();
        registrationQueue.totalRequests();

        // ---------------- CIRCULAR QUEUE TESTING ----------------
        System.out.println("\n--- Circular Queue Continuous Registration Testing ---");

        circularQueue.enqueue("SE-104 requested CS201");

        circularQueue.display();

        System.out.println("Next Request: " + circularQueue.peek());

        System.out.println("Processed Request: " + circularQueue.dequeue());

        System.out.println("\nAfter Processing One Request:");
        circularQueue.display();

        circularQueue.enqueue("SE-105 requested CS405");

        System.out.println("\nAfter Adding One More Request:");
        circularQueue.display();

        // ---------------- UNDO STACK TESTING ----------------
        System.out.println("\n--- Undo Stack Testing ---");

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

        // ---------------- TREESET TESTING ----------------
        System.out.println("\n--- TreeSet Testing ---");

        TreeSetStudentRecords set = new TreeSetStudentRecords();

        set.addStudentId("SE-103");
        set.addStudentId("SE-101");
        set.addStudentId("SE-102");

        set.displayStudentIds();

        // ---------------- TREEMAP TESTING ----------------
        System.out.println("\n--- TreeMap Testing ---");

        TreeMapSemesterCourses map = new TreeMapSemesterCourses();

        map.addSemesterCourses(4, "DSA, Database Systems, Software Engineering");
        map.addSemesterCourses(1, "Programming Fundamentals, English");
        map.addSemesterCourses(2, "OOP, Discrete Structures");

        map.displaySemesterCourses();

        System.out.println("\nPROJECT TESTING COMPLETED.");
    }

    private static Course[] makeCourseArray(ArrayListStorage storage) {

        ArrayList<Course> list = storage.getAll();

        Course[] courses = new Course[list.size()];

        for (int i = 0; i < list.size(); i++) {
            courses[i] = list.get(i);
        }

        return courses;
    }
}
import java.util.HashSet;

public class main {

    public static void main(String[] args) {

        UniversityFileManager fileManager = new UniversityFileManager();
        fileManager.createFiles();

        CourseCatalogArrayList courseCatalog = new CourseCatalogArrayList();
        StudentHashMapLookup studentLookup = new StudentHashMapLookup();
        AccedemicRecordBST bst = new AccedemicRecordBST();
        Enrollment_History history = new Enrollment_History();
        StudentEnrollmentHashMap enrollmentMap = new StudentEnrollmentHashMap();
        PrerequisiteGraph graph = new PrerequisiteGraph();
        WaitlistPriorityQueue waitlist = new WaitlistPriorityQueue();
        UndoActionImplementation undoStack = new UndoActionImplementation(20);

        fileManager.loadCourses(courseCatalog, graph);
        fileManager.loadStudents(studentLookup, bst);
        fileManager.loadPrerequisites(graph);
        fileManager.loadEnrollments(history, enrollmentMap, courseCatalog);

        if (courseCatalog.getAll().size() == 0) {
            Course c1 = new Course("CS101", "Programming Fundamentals", "Monday 9-11");
            Course c2 = new Course("CS201", "Object Oriented Programming", "Monday 11-1");
            Course c3 = new Course("CS301", "Data Structures", "Tuesday 9-11");

            courseCatalog.addCourse(c1);
            courseCatalog.addCourse(c2);
            courseCatalog.addCourse(c3);

            fileManager.saveCourse(c1);
            fileManager.saveCourse(c2);
            fileManager.saveCourse(c3);
        }

        if (!studentLookup.containsStudent("SE-101")) {
            Student s1 = new Student("SE-101", "Ali Khan", 3.60);
            Student s2 = new Student("SE-102", "Sara Ahmed", 3.85);

            studentLookup.addStudent(s1);
            studentLookup.addStudent(s2);
            bst.insert(s1);
            bst.insert(s2);

            fileManager.saveStudent(s1);
            fileManager.saveStudent(s2);
        }

        graph.addPrerequisite("CS201", "CS101");
        graph.addPrerequisite("CS301", "CS201");

        DepartmentCourseHashMap departmentMap = new DepartmentCourseHashMap();
        departmentMap.addCourseToDepartment("Software Engineering", courseCatalog.findById("CS101"));
        departmentMap.addCourseToDepartment("Software Engineering", courseCatalog.findById("CS201"));
        departmentMap.addCourseToDepartment("Software Engineering", courseCatalog.findById("CS301"));

        RegistrationService registrationService = new RegistrationService(
                courseCatalog,
                studentLookup,
                enrollmentMap,
                graph,
                waitlist,
                fileManager,
                history,
                undoStack
        );

        HashSet<String> completedCourses = new HashSet<String>();
        completedCourses.add("CS101");
        completedCourses.add("CS201");

        System.out.println("\n========== SMART UNIVERSITY COURSE MANAGEMENT SYSTEM ==========");

        System.out.println("\n1. Courses");
        courseCatalog.displayCourses();

        System.out.println("\n2. Students using HashMap");
        studentLookup.display();

        System.out.println("\n3. Academic Records using BST");
        bst.displayInOrder();

        System.out.println("\n4. Department Management using HashMap");
        departmentMap.displayDepartments();

        System.out.println("\n5. Prerequisite Graph");
        graph.displayGraph();

        System.out.println("\n6. Registration Validation");
        registrationService.registerStudent("SE-101", "CS301", completedCourses);

        System.out.println("\n7. Enrolled Courses using HashMap");
        enrollmentMap.displayStudentCourses("SE-101");

        System.out.println("\n8. Merge Sort by Course ID");
        Course[] mergeArray = courseCatalog.getAll().toArray(new Course[0]);
        MergeSortCourses.sort(mergeArray, 0, mergeArray.length - 1);
        MergeSortCourses.printCourses(mergeArray);

        System.out.println("\n9. Quick Sort by Course Name");
        QuickSortCourses.sortAndPrint(courseCatalog);

        System.out.println("\n10. Heap Sort by Course ID");
        Course[] heapArray = courseCatalog.getAll().toArray(new Course[0]);
        HeapSortCourses.heapSort(heapArray);
        HeapSortCourses.printCourses(heapArray);

        System.out.println("\n11. Greedy Schedule");
        GreedyScheduleOptimizer optimizer = new GreedyScheduleOptimizer();
        Course[] selected = optimizer.makeSchedule(courseCatalog.getAll().toArray(new Course[0]));
        optimizer.printSchedule(selected);

        System.out.println("\n12. Backtracking / Graph Coloring");
        GraphColoringTimetable graphColoring = new GraphColoringTimetable(courseCatalog.getAll());
        graphColoring.displayRoomAssignment();

        System.out.println("\n13. Undo Stack");
        undoStack.undoLastAction();

        System.out.println("\n========== PROGRAM FINISHED ==========");
    }
}
import java.util.ArrayList;
import java.util.HashSet;

public class RegistrationService {

    private CourseCatalogArrayList courseCatalog;
    private StudentHashMapLookup studentLookup;
    private StudentEnrollmentHashMap enrollmentMap;
    private PrerequisiteGraph prerequisiteGraph;
    private TimetableConflictChecker conflictChecker;
    private WaitlistPriorityQueue waitlist;
    private UniversityFileManager fileManager;
    private Enrollment_History history;
    private UndoActionImplementation undoStack;

    public RegistrationService(CourseCatalogArrayList courseCatalog,
                               StudentHashMapLookup studentLookup,
                               StudentEnrollmentHashMap enrollmentMap,
                               PrerequisiteGraph prerequisiteGraph,
                               WaitlistPriorityQueue waitlist,
                               UniversityFileManager fileManager,
                               Enrollment_History history,
                               UndoActionImplementation undoStack) {

        this.courseCatalog = courseCatalog;
        this.studentLookup = studentLookup;
        this.enrollmentMap = enrollmentMap;
        this.prerequisiteGraph = prerequisiteGraph;
        this.conflictChecker = new TimetableConflictChecker();
        this.waitlist = waitlist;
        this.fileManager = fileManager;
        this.history = history;
        this.undoStack = undoStack;
    }

    public boolean registerStudent(String studentId, String courseId, HashSet<String> completedCourses) {
        Student student = studentLookup.getStudent(studentId);

        if (student == null) {
            System.out.println("Registration failed: Student not found.");
            return false;
        }

        Course course = courseCatalog.findById(courseId);

        if (course == null) {
            System.out.println("Registration failed: Course not found.");
            return false;
        }

        if (enrollmentMap.isAlreadyEnrolled(studentId, courseId)) {
            System.out.println("Registration failed: Student already enrolled in this course.");
            return false;
        }

        if (!prerequisiteGraph.canEnroll(courseId, completedCourses)) {
            System.out.println("Registration failed: Prerequisite not completed.");
            return false;
        }

        ArrayList<Course> enrolledCourses = enrollmentMap.getStudentCourses(studentId);

        if (conflictChecker.hasConflict(enrolledCourses, course)) {
            System.out.println("Registration failed: Timetable conflict found.");
            return false;
        }

        if (!course.hasAvailableSeat()) {
            waitlist.addStudent(student);
            System.out.println("Course is full. Student added to waitlist.");
            return false;
        }

        enrollmentMap.enrollStudent(studentId, course);
        course.enrollOneSeat();
        history.addRecord(courseId);
        fileManager.saveEnrollment(studentId, courseId);
        undoStack.push(new UndoAction("REGISTER", studentId, courseId, "Student registered successfully."));

        System.out.println("Registration successful: " + studentId + " enrolled in " + courseId);
        return true;
    }

    public boolean dropCourse(String studentId, String courseId) {
        Course course = courseCatalog.findById(courseId);

        if (course == null) {
            System.out.println("Drop failed: Course not found.");
            return false;
        }

        boolean removed = enrollmentMap.dropCourse(studentId, courseId);

        if (!removed) {
            System.out.println("Drop failed: Enrollment record not found.");
            return false;
        }

        course.dropOneSeat();
        fileManager.rewriteEnrollments(enrollmentMap);
        undoStack.push(new UndoAction("DROP", studentId, courseId, "Student dropped course."));

        System.out.println("Course dropped successfully.");
        return true;
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UniversityFileManager{

    private String studentFile = "students.txt";
    private String courseFile = "courses.txt";
    private String enrollmentFile = "enrollments.txt";
    private String prerequisiteFile = "prerequisites.txt";
    private String registrationFile = "registrations.txt";

    public void createFiles() {
        createOneFile(studentFile);
        createOneFile(courseFile);
        createOneFile(enrollmentFile);
        createOneFile(prerequisiteFile);
        createOneFile(registrationFile);
    }

    private void createOneFile(String fileName) {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException e) {
            System.out.println("File creation error: " + fileName);
        }
    }

    // -------------------- STUDENTS --------------------

    public void saveStudent(Student student) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(studentFile, true));

            writer.write(student.getStudentId() + "|" + student.getName() + "|" + student.getCgpa());
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            System.out.println("Student save error.");
        }
    }

    public void loadStudents(StudentHashMapLookup lookup, AccedemicRecordBST bst) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(studentFile));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length == 3) {
                    String id = data[0];
                    String name = data[1];
                    double cgpa = Double.parseDouble(data[2]);

                    Student student = new Student(id, name, cgpa);

                    lookup.addStudent(student);
                    bst.insert(student);
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Student load error.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid CGPA in students.txt.");
        }
    }

    // -------------------- COURSES --------------------

    public void saveCourse(Course course) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(courseFile, true));

            writer.write(course.getCourseId() + "|" + course.getCourseName() + "|" + course.getTimeSlot());
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            System.out.println("Course save error.");
        }
    }

    public void loadCourses(CourseCatalogArrayList storage, PrerequisiteGraph graph) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(courseFile));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length == 3) {
                    String id = data[0];
                    String name = data[1];
                    String time = data[2];

                    Course course = new Course(id, name, time);

                    storage.addCourse(course);
                    graph.addCourse(id);
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Course load error.");
        }
    }

    // -------------------- ENROLLMENTS --------------------

    public void saveEnrollment(String studentId, String courseId) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(enrollmentFile, true));

            writer.write(studentId + "|" + courseId);
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            System.out.println("Enrollment save error.");
        }
    }

    public void loadEnrollments(Enrollment_History history) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(enrollmentFile));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length == 2) {
                    String courseId = data[1];
                    history.addRecord(courseId);
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Enrollment load error.");
        }
    }

    // -------------------- PREREQUISITES --------------------

    public void savePrerequisite(String courseId, String requiredCourse) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(prerequisiteFile, true));

            writer.write(courseId + "|" + requiredCourse);
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            System.out.println("Prerequisite save error.");
        }
    }

    public void loadPrerequisites(PrerequisiteGraph graph) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(prerequisiteFile));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length == 2) {
                    String courseId = data[0];
                    String requiredCourse = data[1];

                    graph.addPrerequisite(courseId, requiredCourse);
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Prerequisite load error.");
        }
    }

    // -------------------- REGISTRATION REQUESTS --------------------

    public void saveRegistrationRequest(String request) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(registrationFile, true));

            writer.write(request);
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            System.out.println("Registration request save error.");
        }
    }

    public void loadRegistrationRequests(RegistrationQueue queue, ContinuousRegistrationCircularQueue circularQueue) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(registrationFile));

            String line;

            while ((line = reader.readLine()) != null) {
                queue.addRequest(line);
                circularQueue.enqueue(line);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Registration request load error.");
        }
    }
}
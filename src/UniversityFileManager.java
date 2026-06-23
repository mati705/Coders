import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UniversityFileManager {

    private final String studentFile = "students.txt";
    private final String courseFile = "courses.txt";
    private final String enrollmentFile = "enrollments.txt";
    private final String prerequisiteFile = "prerequisites.txt";
    private final String registrationFile = "registrations.txt";

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

    public void saveStudent(Student student) {
        appendLine(studentFile, student.getStudentId() + "|" + student.getName() + "|" + student.getCgpa());
    }

    public void loadStudents(StudentHashMapLookup lookup, AccedemicRecordBST bst) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(studentFile));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length == 3) {
                    Student student = new Student(data[0], data[1], Double.parseDouble(data[2]));
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

    public void saveCourse(Course course) {
        appendLine(courseFile, course.toFileString());
    }

    public void loadCourses(CourseCatalogArrayList storage, PrerequisiteGraph graph) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(courseFile));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }

                Course course = parseCourse(line);

                if (course != null) {
                    storage.addCourse(course);
                    graph.addCourse(course.getCourseId());
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Course load error.");
        }
    }

    private Course parseCourse(String line) {
        try {
            String[] data = line.split("\\|");

            if (data.length == 3) {
                return new Course(data[0], data[1], data[2]);
            }

            if (data.length >= 6) {
                return new Course(
                        data[0],
                        data[1],
                        data[2]
                );
            }
        } catch (Exception e) {
            System.out.println("Invalid course line skipped: " + line);
        }

        return null;
    }

    public void rewriteCourses(CourseCatalogArrayList storage) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(courseFile, false));
            ArrayList<Course> courses = storage.getAll();

            for (Course course : courses) {
                writer.write(course.toFileString());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Course rewrite error.");
        }
    }

    public void saveEnrollment(String studentId, String courseId) {
        appendLine(enrollmentFile, studentId + "|" + courseId);
    }

    public void loadEnrollments(Enrollment_History history) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(enrollmentFile));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length == 2) {
                    history.addRecord(data[1]);
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Enrollment load error.");
        }
    }

    public void loadEnrollments(Enrollment_History history,
                                StudentEnrollmentHashMap enrollmentMap,
                                CourseCatalogArrayList storage) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(enrollmentFile));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length == 2) {
                    String studentId = data[0];
                    String courseId = data[1];
                    Course course = storage.findById(courseId);

                    history.addRecord(courseId);

                    if (course != null && !enrollmentMap.isAlreadyEnrolled(studentId, courseId)) {
                        enrollmentMap.enrollStudent(studentId, course);
                        course.enrollOneSeat();
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Enrollment load error.");
        }
    }

    public void rewriteEnrollments(StudentEnrollmentHashMap enrollmentMap) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(enrollmentFile, false));
            ArrayList<String> lines = enrollmentMap.getAllEnrollmentLines();

            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Enrollment rewrite error.");
        }
    }

    public void savePrerequisite(String courseId, String requiredCourse) {
        appendLine(prerequisiteFile, courseId + "|" + requiredCourse);
    }

    public void loadPrerequisites(PrerequisiteGraph graph) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(prerequisiteFile));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length == 2) {
                    graph.addPrerequisite(data[0], data[1]);
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Prerequisite load error.");
        }
    }

    public void saveRegistrationRequest(String request) {
        appendLine(registrationFile, request);
    }

    public void loadRegistrationRequests(RegistrationQueue queue,
                                         ContinuousRegistrationCircularQueue circularQueue) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(registrationFile));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }

                queue.addRequest(line);
                circularQueue.enqueue(line);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Registration request load error.");
        }
    }

    private void appendLine(String fileName, String line) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(line);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("File save error: " + fileName);
        }
    }
}
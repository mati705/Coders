import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

public class SimpleUniversityDashboard extends JFrame {

    private ArrayListStorage courseStorage;
    private Fast_Lookup lookup;
    private AccedemicRecordBST bst;
    private Enrollment_History history;
    private RegistrationQueue registrationQueue;
    private Continuous_Registration circularQueue;
    private PrerequisiteGraph graph;
    private PriorityQueueManager priorityQueue;
    private UndoActionImplementation undoStack;
    private ScheduleOptimizer optimizer;
    private FileHandling fileHandler;

    private JTextArea outputArea;

    public SimpleUniversityDashboard() {

        fileHandler = new FileHandling();
        fileHandler.createFiles();

        courseStorage = new ArrayListStorage();
        lookup = new Fast_Lookup();
        bst = new AccedemicRecordBST();
        history = new Enrollment_History();
        registrationQueue = new RegistrationQueue();
        circularQueue = new Continuous_Registration(5);
        graph = new PrerequisiteGraph();
        priorityQueue = new PriorityQueueManager();
        undoStack = new UndoActionImplementation(10);
        optimizer = new ScheduleOptimizer();

        setTitle("Smart University Course Management System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createGUI();
        loadDataFromFiles();
    }

    private void createGUI() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        JLabel title = new JLabel("Smart University Course Management System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setOpaque(true);
        title.setBackground(new Color(30, 70, 120));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(12, 5, 12, 5));

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Courses", createCoursePanel());
        tabs.addTab("Students", createStudentPanel());
        tabs.addTab("Registration", createRegistrationPanel());
        tabs.addTab("Prerequisites", createPrerequisitePanel());
        tabs.addTab("Algorithms", createAlgorithmPanel());

        outputArea = new JTextArea(8, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(tabs, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void loadDataFromFiles() {

        fileHandler.loadCourses(courseStorage, graph);
        fileHandler.loadStudents(lookup, bst);
        fileHandler.loadEnrollments(history);
        fileHandler.loadPrerequisites(graph);
        fileHandler.loadRegistrationRequests(registrationQueue, circularQueue);

        show("Data loaded from text files.");
    }

    private JPanel createCoursePanel() {

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel idLabel = new JLabel("Course ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Course Name:");
        JTextField nameField = new JTextField();

        JLabel timeLabel = new JLabel("Time Slot:");
        JTextField timeField = new JTextField();

        JButton addButton = new JButton("Add Course");
        JButton showButton = new JButton("Show Courses");
        JButton searchButton = new JButton("Search Course");

        panel.add(idLabel);
        panel.add(idField);

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(timeLabel);
        panel.add(timeField);

        panel.add(addButton);
        panel.add(showButton);

        panel.add(searchButton);
        panel.add(new JLabel(""));

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String time = timeField.getText().trim();

                if (id.equals("") || name.equals("") || time.equals("")) {
                    show("Please fill all course fields.");
                    return;
                }

                Course course = new Course(id, name, time);

                courseStorage.addCourse(course);
                graph.addCourse(id);
                fileHandler.saveCourse(course);

                show("Course added and saved: " + course);

                idField.setText("");
                nameField.setText("");
                timeField.setText("");
            }
        });

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                outputArea.setText("--- Courses Stored in ArrayList ---\n");

                ArrayList<Course> list = courseStorage.getAll();

                for (int i = 0; i < list.size(); i++) {
                    outputArea.append(list.get(i) + "\n");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String id = idField.getText().trim();

                if (id.equals("")) {
                    show("Enter course ID to search.");
                    return;
                }

                Course found = courseStorage.findById(id);

                if (found != null) {
                    show("Course found: " + found);
                } else {
                    show("Course not found.");
                }
            }
        });

        return panel;
    }

    private JPanel createStudentPanel() {

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Student Name:");
        JTextField nameField = new JTextField();

        JLabel cgpaLabel = new JLabel("CGPA:");
        JTextField cgpaField = new JTextField();

        JButton addButton = new JButton("Add Student");
        JButton searchButton = new JButton("Search Student");
        JButton showButton = new JButton("Show BST Records");

        panel.add(idLabel);
        panel.add(idField);

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(cgpaLabel);
        panel.add(cgpaField);

        panel.add(addButton);
        panel.add(searchButton);

        panel.add(showButton);
        panel.add(new JLabel(""));

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String id = idField.getText().trim();
                    String name = nameField.getText().trim();
                    String cgpaText = cgpaField.getText().trim();

                    if (id.equals("") || name.equals("") || cgpaText.equals("")) {
                        show("Please fill all student fields.");
                        return;
                    }

                    double cgpa = Double.parseDouble(cgpaText);

                    Student student = new Student(id, name, cgpa);

                    lookup.addStudent(student);
                    bst.insert(student);
                    fileHandler.saveStudent(student);

                    show("Student added in HashMap, BST and file: " + student);

                    idField.setText("");
                    nameField.setText("");
                    cgpaField.setText("");

                } catch (NumberFormatException ex) {
                    show("CGPA must be a number.");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String id = idField.getText().trim();

                if (id.equals("")) {
                    show("Enter student ID to search.");
                    return;
                }

                lookup.searchStudent(id);
                show("Student search done using HashMap. Check console for result.");
            }
        });

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                bst.displayInOrder();
                show("BST records displayed in console.");
            }
        });

        return panel;
    }

    private JPanel createRegistrationPanel() {

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel studentLabel = new JLabel("Student ID:");
        JTextField studentField = new JTextField();

        JLabel courseLabel = new JLabel("Course ID:");
        JTextField courseField = new JTextField();

        JButton addRequestButton = new JButton("Add Request");
        JButton processButton = new JButton("Process Request");
        JButton saveHistoryButton = new JButton("Save Enrollment");
        JButton undoButton = new JButton("Undo Last Action");

        panel.add(studentLabel);
        panel.add(studentField);

        panel.add(courseLabel);
        panel.add(courseField);

        panel.add(addRequestButton);
        panel.add(processButton);

        panel.add(saveHistoryButton);
        panel.add(undoButton);

        addRequestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String studentId = studentField.getText().trim();
                String courseId = courseField.getText().trim();

                if (studentId.equals("") || courseId.equals("")) {
                    show("Enter student ID and course ID.");
                    return;
                }

                String request = studentId + " requested " + courseId;

                registrationQueue.addRequest(request);
                circularQueue.enqueue(request);
                fileHandler.saveRegistrationRequest(request);

                show("Request added in Queue, Circular Queue and file: " + request);
            }
        });

        processButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String request = registrationQueue.processNext();

                if (request == null) {
                    show("No request available.");
                } else {
                    show("Processed request: " + request);
                }
            }
        });

        saveHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String studentId = studentField.getText().trim();
                String courseId = courseField.getText().trim();

                if (studentId.equals("") || courseId.equals("")) {
                    show("Enter student ID and course ID.");
                    return;
                }

                history.addRecord(courseId);
                fileHandler.saveEnrollment(studentId, courseId);

                UndoAction action = new UndoAction("REGISTER", studentId, courseId, "Registration done");
                undoStack.push(action);

                show("Enrollment saved in Linked List and file: " + studentId + " -> " + courseId);
            }
        });

        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                undoStack.undoLastAction();
                show("Undo operation performed. Check console.");
            }
        });

        return panel;
    }

    private JPanel createPrerequisitePanel() {

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel courseLabel = new JLabel("Course ID:");
        JTextField courseField = new JTextField();

        JLabel requiredLabel = new JLabel("Required Course:");
        JTextField requiredField = new JTextField();

        JLabel completedLabel = new JLabel("Completed Courses:");
        JTextField completedField = new JTextField();

        JButton addButton = new JButton("Add Prerequisite");
        JButton checkButton = new JButton("Check Enrollment");
        JButton showButton = new JButton("Show Graph");

        panel.add(courseLabel);
        panel.add(courseField);

        panel.add(requiredLabel);
        panel.add(requiredField);

        panel.add(completedLabel);
        panel.add(completedField);

        panel.add(new JLabel("Example:"));
        panel.add(new JLabel("CS101,CS201"));

        panel.add(addButton);
        panel.add(checkButton);

        panel.add(showButton);
        panel.add(new JLabel(""));

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String courseId = courseField.getText().trim();
                String requiredCourse = requiredField.getText().trim();

                if (courseId.equals("") || requiredCourse.equals("")) {
                    show("Enter course ID and required course.");
                    return;
                }

                graph.addPrerequisite(courseId, requiredCourse);
                fileHandler.savePrerequisite(courseId, requiredCourse);

                show(requiredCourse + " added as prerequisite of " + courseId);
            }
        });

        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String courseId = courseField.getText().trim();
                String completedText = completedField.getText().trim();

                if (courseId.equals("")) {
                    show("Enter course ID.");
                    return;
                }

                HashSet<String> completedCourses = new HashSet<String>();

                if (!completedText.equals("")) {
                    String[] parts = completedText.split(",");

                    for (int i = 0; i < parts.length; i++) {
                        completedCourses.add(parts[i].trim());
                    }
                }

                boolean result = graph.canEnroll(courseId, completedCourses);

                if (result) {
                    show("Student can enroll in " + courseId);
                } else {
                    show("Student cannot enroll in " + courseId);
                }
            }
        });

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                graph.displayGraph();
                show("Graph displayed in console.");
            }
        });

        return panel;
    }

    private JPanel createAlgorithmPanel() {

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JButton mergeButton = new JButton("Merge Sort");
        JButton quickButton = new JButton("Quick Sort");
        JButton heapButton = new JButton("Heap Sort");
        JButton greedyButton = new JButton("Greedy Schedule");

        JLabel waitNameLabel = new JLabel("Waitlist Student Name:");
        JTextField waitNameField = new JTextField();

        JLabel waitCgpaLabel = new JLabel("Waitlist CGPA:");
        JTextField waitCgpaField = new JTextField();

        JButton waitlistButton = new JButton("Add to Waitlist");
        JButton assignButton = new JButton("Assign Seat");

        panel.add(mergeButton);
        panel.add(quickButton);

        panel.add(heapButton);
        panel.add(greedyButton);

        panel.add(waitNameLabel);
        panel.add(waitNameField);

        panel.add(waitCgpaLabel);
        panel.add(waitCgpaField);

        panel.add(waitlistButton);
        panel.add(assignButton);

        mergeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Course[] courses = getCourseArray();

                if (courses.length == 0) {
                    show("No courses available for merge sort.");
                    return;
                }

                MergeSortCourses.sort(courses, 0, courses.length - 1);
                showCourses("Merge Sort Result", courses);
            }
        });

        quickButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Course[] courses = getCourseArray();

                if (courses.length == 0) {
                    show("No courses available for quick sort.");
                    return;
                }

                QuickSortCourses.quickSort(courses, 0, courses.length - 1);
                showCourses("Quick Sort Result", courses);
            }
        });

        heapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Course[] courses = getCourseArray();

                if (courses.length == 0) {
                    show("No courses available for heap sort.");
                    return;
                }

                HeapSortCourses.heapSort(courses);
                showCourses("Heap Sort Result", courses);
            }
        });

        greedyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Course[] courses = getCourseArray();

                if (courses.length == 0) {
                    show("No courses available for schedule.");
                    return;
                }

                Course[] selectedSchedule = optimizer.makeSchedule(courses);
                showCourses("Greedy Schedule Result", selectedSchedule);
            }
        });

        waitlistButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = waitNameField.getText().trim();
                String cgpa = waitCgpaField.getText().trim();

                if (name.equals("") || cgpa.equals("")) {
                    show("Enter student name and CGPA.");
                    return;
                }

                priorityQueue.addStudent(cgpa, name);
                show("Student added to Priority Queue waitlist: " + name);

                waitNameField.setText("");
                waitCgpaField.setText("");
            }
        });

        assignButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                priorityQueue.assignSeat();
                show("Seat assigned using Priority Queue. Check console for result.");
            }
        });

        return panel;
    }

    private Course[] getCourseArray() {

        ArrayList<Course> list = courseStorage.getAll();

        Course[] courses = new Course[list.size()];

        for (int i = 0; i < list.size(); i++) {
            courses[i] = list.get(i);
        }

        return courses;
    }

    private void showCourses(String title, Course[] courses) {

        outputArea.setText("--- " + title + " ---\n");

        for (int i = 0; i < courses.length; i++) {
            outputArea.append(courses[i] + "\n");
        }
    }

    private void show(String message) {

        outputArea.append(message + "\n");
    }
}
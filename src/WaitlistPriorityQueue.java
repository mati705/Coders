import java.util.Comparator;
import java.util.PriorityQueue;

public class WaitlistPriorityQueue {

    private PriorityQueue<Student> waitList = new PriorityQueue<>(
            new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    return Double.compare(s2.getCgpa(), s1.getCgpa());
                }
            }
    );

    public void addStudent(Student student) {
        waitList.offer(student);
        System.out.println(student.getName() + " added to waitlist. CGPA: " + student.getCgpa());
    }

    // Kept for your GUI buttons
    public void addStudent(String cgpa, String studentName) {
        double studentCgpa = Double.parseDouble(cgpa);
        Student student = new Student("WAIT-" + studentName.replace(" ", ""), studentName, studentCgpa);
        addStudent(student);
    }

    public Student assignSeatStudent() {
        if (waitList.isEmpty()) {
            System.out.println("Waitlist is empty!");
            return null;
        }

        Student student = waitList.poll();
        System.out.println("Assigned Seat to: " + student);
        return student;
    }

    // Kept for your GUI buttons
    public void assignSeat() {
        assignSeatStudent();
    }

    public void peekNext() {
        if (waitList.isEmpty()) {
            System.out.println("Waitlist is empty!");
        } else {
            System.out.println("Next in line: " + waitList.peek());
        }
    }
}
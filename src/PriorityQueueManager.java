import java.util.PriorityQueue;
import java.util.Collections;
// Priority Queue - Waitlist System

public class PriorityQueueManager {

    // Adding Collections.reverseOrder() makes the highest CGPA come out first

    private PriorityQueue<String> waitList = new PriorityQueue<>(Collections.reverseOrder());


    public void addStudent(String cgpa, String studentName) {
        String entry = cgpa + " - " + studentName;
        waitList.offer(entry);
        System.out.println(studentName + " (" + cgpa + ") added to waitlist.");
    }

    public void assignSeat() {
        if (waitList.isEmpty()) {
            System.out.println("Waitlist is empty!");
            return;
        }

        System.out.println("Assigned Seat to: " + waitList.poll());
    }

    // A simple check method to see who is next without removing them
    public void peekNext() {
        if (waitList.isEmpty()) {
            System.out.println("Waitlist is empty!");
        } else {
            System.out.println("Next in line: " + waitList.peek());
        }
    }
}
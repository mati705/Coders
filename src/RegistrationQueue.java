import java.util.LinkedList;

public class RegistrationQueue {

    private LinkedList<String> queue = new LinkedList<>();

    // Add Registration Request
    public void addRequest(String studentId) {

        queue.addLast(studentId);

        System.out.println("Request Added: " + studentId);
    }

    // Process Next Student
    public String processNext() {

        if(queue.isEmpty()) {

            System.out.println("No pending requests.");
            return null;
        }

        String student = queue.removeFirst();

        System.out.println("Processing: " + student);

        return student;
    }

    // View First Student
    public void peekNext() {

        if(queue.isEmpty()) {

            System.out.println("Queue is Empty");
            return;
        }

        System.out.println("Next Student: " + queue.getFirst());
    }

    // Count Requests
    public void totalRequests() {

        System.out.println("Total Requests: " + queue.size());
    }

    // Check Empty
    public boolean isEmpty() {

        return queue.isEmpty();
    }

    // Display Queue
    public void display() {

        System.out.println("\n--- Registration Queue ---");

        System.out.println(queue);
    }
}

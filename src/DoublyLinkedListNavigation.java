// Student can move forward and backward through courses.
public class DoublyLinkedListNavigation {

    class Node {

        String course;
        Node next;
        Node prev;

        Node(String course) {
            this.course = course;
        }
    }

    private Node head;

    // Add course
    public void addCourse(String course) {

        Node newNode = new Node(course);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
        newNode.prev = temp;
    }

    // Display from first to last
    public void displayForward() {

        Node temp = head;

        System.out.println("Forward Navigation:");

        while (temp != null) {
            System.out.print(temp.course + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    // Display from last to first
    public void displayBackward() {

        if (head == null) {
            return;
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        System.out.println("Backward Navigation:");

        while (temp != null) {
            System.out.print(temp.course + " ");
            temp = temp.prev;
        }

        System.out.println();
    }

    // Search course
    public boolean searchCourse(String course) {

        Node temp = head;

        while (temp != null) {

            if (temp.course.equalsIgnoreCase(course)) {
                return true;
            }

            temp = temp.next;
        }

        return false;
    }

    // Delete course
    public void deleteCourse(String course) {

        if (head == null) {
            return;
        }

        // delete first node
        if (head.course.equalsIgnoreCase(course)) {

            head = head.next;

            if (head != null) {
                head.prev = null;
            }

            return;
        }

        Node temp = head;

        while (temp != null &&
                !temp.course.equalsIgnoreCase(course)) {

            temp = temp.next;
        }

        if (temp == null) {
            return;
        }

        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }

        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }
    }

    // Count total courses
    public int countCourses() {

        int count = 0;

        Node temp = head;

        while (temp != null) {

            count++;
            temp = temp.next;
        }

        return count;
    }
}

// Each node holds one enrollment record (course ID) and points to the next.
public class Enrollment_History {

    class Node {

        String courseId;
        Node next;

        Node(String courseId) {
            this.courseId = courseId;
        }
    }

    Node head;

    public void addRecord(String courseId) {

        Node newNode = new Node(courseId);

        if(head == null) {
            head = newNode;
            return;
        }

        Node temp = head;

        while(temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
    }
    public boolean searchCourse(String courseId) {

        Node temp = head;

        while(temp != null) {

            if(temp.courseId.equals(courseId)) {
                return true;
            }

            temp = temp.next;
        }

        return false;
    }
    public void deleteRecord(String courseId) {

        if(head == null)
            return;

        if(head.courseId.equals(courseId)) {

            head = head.next;
            return;
        }

        Node temp = head;

        while(temp.next != null &&
                !temp.next.courseId.equals(courseId)) {

            temp = temp.next;
        }

        if(temp.next != null) {
            temp.next = temp.next.next;
        }
    }
    public int countRecords() {

        int count = 0;

        Node temp = head;

        while(temp != null) {

            count++;
            temp = temp.next;
        }

        return count;
    }

    public void displayHistory() {

        Node temp = head;

        while(temp != null) {
            System.out.println(temp.courseId+" ->");
            temp = temp.next;
        }
    }
}
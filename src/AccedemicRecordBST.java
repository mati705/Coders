public class AccedemicRecordBST {

    static class Node {
        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node root;

    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private Node insertRec(Node current, Student student) {
        if (current == null) {
            return new Node(student);
        }

        int result = student.getStudentId().compareToIgnoreCase(current.student.getStudentId());

        if (result < 0) {
            current.left = insertRec(current.left, student);
        } else if (result > 0) {
            current.right = insertRec(current.right, student);
        }

        return current;
    }

    public Student search(String studentId) {
        return searchRec(root, studentId);
    }

    private Student searchRec(Node current, String studentId) {
        if (current == null) {
            return null;
        }

        int result = studentId.compareToIgnoreCase(current.student.getStudentId());

        if (result == 0) {
            return current.student;
        } else if (result < 0) {
            return searchRec(current.left, studentId);
        } else {
            return searchRec(current.right, studentId);
        }
    }

    public void displayInOrder() {
        System.out.println("\n--- BST Academic Records In Sorted Order ---");
        inorder(root);
    }

    private void inorder(Node current) {
        if (current == null) {
            return;
        }

        inorder(current.left);
        System.out.println(current.student);
        inorder(current.right);
    }

    public String getInOrderText() {
        StringBuilder text = new StringBuilder();
        makeInOrderText(root, text);
        return text.toString();
    }

    private void makeInOrderText(Node current, StringBuilder text) {
        if (current == null) {
            return;
        }

        makeInOrderText(current.left, text);
        text.append(current.student).append("\n");
        makeInOrderText(current.right, text);
    }
}
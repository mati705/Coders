// Binary Search Tree (BST) - Academic Records

public class AcemicRecordBST {

    public static class Node {

        public Student student;
        public Node left, right;

        public Node(Student s) {
            this.student = s;
        }
    }

    public Node root;

    public void insert(Student s) {
        root = insertRec(root, s);
    }
    public Node insertRec(Node node, Student s) {
        if (node == null)
            return new Node(s);

        // Directly accessing studentId instead of using a getter
        if (s.studentId < node.student.studentId)
            node.left = insertRec(node.left, s);
        else if (s.studentId > node.student.studentId)
            node.right = insertRec(node.right, s);

        return node;
    }

    public Student search(int studentId) {
        return searchRec(root, studentId);
    }

    public Student searchRec(Node node, int id) {
        if (node == null)
            return null;


        if (id == node.student.studentId)
            return node.student;

        if (id < node.student.studentId)
            return searchRec(node.left, id);

        return searchRec(node.right, id);
    }

    public void inorder(Node node) {

        if(node == null)
            return;

        inorder(node.left);
        System.out.println(node.student);
        inorder(node.right);
    }

    public void preorder(Node node) {

        if(node == null)
            return;

        System.out.println(node.student);

        preorder(node.left);
        preorder(node.right);
    }

    public void postorder(Node node) {

        if(node == null)
            return;

        postorder(node.left);
        postorder(node.right);

        System.out.println(node.student);
    }
}
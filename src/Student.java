public class Student {

    private int studentId;
    private String name;
    private double cgpa;

    public Student(int studentId, String name, double cgpa) {
        this.studentId = studentId;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    @Override
    public String toString() {
        return studentId + " - " + name + " CGPA: " + cgpa;
    }
}
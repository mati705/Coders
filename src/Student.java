public class Student implements Comparable<Student> {

    public String studentId;
    public String name;
    public double cgpa;

    public Student(String studentId, String name, double cgpa) {
        this.studentId = studentId;
        this.name = name;
        this.cgpa = cgpa;
    }
    @Override
    public int compareTo(Student other) {
        return Double.compare(other.cgpa, this.cgpa);
    }

    public String getStudentId() {
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
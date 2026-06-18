public class Course {

    private String courseId;
    private String courseName;
    private String timeSlot;

    public Course(String courseId, String courseName, String timeSlot) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.timeSlot = timeSlot;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return courseId + " - " + courseName + " - " + timeSlot;
    }
}
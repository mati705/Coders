public class Course {

    private String courseId;
    private String courseName;
    private String timeSlot;

    private int credits;
    private int capacity;
    private int enrolledSeats;
    private String instructor;



    // New constructor: used when full course data is available
    public Course(String courseId, String courseName, String timeSlot) {

        this.courseId = courseId;
        this.courseName = courseName;
        this.timeSlot = timeSlot;
        this.credits = credits;
        this.capacity = capacity;
        this.enrolledSeats = 0;
        this.instructor = instructor;
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

    public int getCredits() {
        return credits;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledSeats() {
        return enrolledSeats;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public boolean hasAvailableSeat() {
        return enrolledSeats < capacity;
    }

    public boolean enrollOneSeat() {
        if (hasAvailableSeat()) {
            enrolledSeats++;
            return true;
        }
        return false;
    }

    public boolean dropOneSeat() {
        if (enrolledSeats > 0) {
            enrolledSeats--;
            return true;
        }
        return false;
    }

    public String toFileString() {
        return courseId + "|" + courseName + "|" + timeSlot + "|" + credits + "|" + capacity + "|" + instructor;
    }

    @Override
    public String toString() {
        return courseId + " - " + courseName +
                " - " + timeSlot +
                " - Credits: " + credits +
                " - Seats: " + enrolledSeats + "/" + capacity +
                " - Instructor: " + instructor;
    }
}
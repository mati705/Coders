
public class UndoAction {
    private String actionType;
    private String studentId;
    private String courseCode;
    private String details;

    public UndoAction(String actionType, String studentId, String courseCode, String details) {
        this.actionType = actionType;
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.details = details;
    }

    public String getActionType() {
        return actionType;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getDetails() {
        return details;
    }

    public String getUndoMessage() {
        if (actionType.equalsIgnoreCase("REGISTER")) {
            return "Registration undone for " + studentId + " in " + courseCode;
        }

        if (actionType.equalsIgnoreCase("DROP")) {
            return "Drop undone for " + studentId + " in " + courseCode;
        }

        if (actionType.equalsIgnoreCase("TIMETABLE_UPDATE")) {
            return "Timetable undo for " + courseCode;
        }

        if (actionType.equalsIgnoreCase("ADD_COURSE")) {
            return "Course undo for " + courseCode;
        }
        return details;
    }
    @Override
    public String toString() {
        return actionType +
                " | Student: " + studentId +
                " | Course: " + courseCode +
                " | Details: " + details;
    }
}
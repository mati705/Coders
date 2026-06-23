import java.util.ArrayList;

public class TimetableConflictChecker {

    public boolean hasConflict(ArrayList<Course> enrolledCourses, Course newCourse) {
        for (Course enrolledCourse : enrolledCourses) {
            if (isSameDay(enrolledCourse.getTimeSlot(), newCourse.getTimeSlot())
                    && isTimeOverlap(enrolledCourse.getTimeSlot(), newCourse.getTimeSlot())) {
                return true;
            }
        }

        return false;
    }

    private boolean isSameDay(String firstSlot, String secondSlot) {
        String firstDay = getDay(firstSlot);
        String secondDay = getDay(secondSlot);

        return firstDay.equalsIgnoreCase(secondDay);
    }

    private String getDay(String timeSlot) {
        String[] parts = timeSlot.trim().split(" ");
        return parts[0];
    }

    private boolean isTimeOverlap(String firstSlot, String secondSlot) {
        int firstStart = getStartTime(firstSlot);
        int firstEnd = getEndTime(firstSlot);

        int secondStart = getStartTime(secondSlot);
        int secondEnd = getEndTime(secondSlot);

        return firstStart < secondEnd && secondStart < firstEnd;
    }

    private int getStartTime(String timeSlot) {
        String timePart = timeSlot.substring(timeSlot.indexOf(" ") + 1);
        String[] times = timePart.split("-");
        return convertTo24Hour(times[0].trim());
    }

    private int getEndTime(String timeSlot) {
        String timePart = timeSlot.substring(timeSlot.indexOf(" ") + 1);
        String[] times = timePart.split("-");
        return convertTo24Hour(times[1].trim());
    }

    private int convertTo24Hour(String time) {
        int hour = Integer.parseInt(time);

        // Simple university timing support:
        // 1, 2, 3, 4, 5 are treated as afternoon class timings.
        if (hour >= 1 && hour <= 5) {
            hour += 12;
        }

        return hour;
    }
}
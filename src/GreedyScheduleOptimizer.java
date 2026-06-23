public class GreedyScheduleOptimizer {

    public Course[] makeSchedule(Course[] courses) {

        Course[] selectedCourses = new Course[courses.length];
        int selectedCount = 0;

        for (int i = 0; i < courses.length; i++) {

            boolean clashFound = false;

            for (int j = 0; j < selectedCount; j++) {

                if (courses[i].getTimeSlot().equalsIgnoreCase(selectedCourses[j].getTimeSlot())) {
                    clashFound = true;
                    break;
                }
            }

            if (!clashFound) {
                selectedCourses[selectedCount] = courses[i];
                selectedCount++;
            }
        }

        Course[] finalSchedule = new Course[selectedCount];

        for (int i = 0; i < selectedCount; i++) {
            finalSchedule[i] = selectedCourses[i];
        }

        return finalSchedule;
    }

    public void printSchedule(Course[] courses) {
        System.out.println("\n--- Greedy Selected Schedule ---");

        if (courses.length == 0) {
            System.out.println("No course selected.");
            return;
        }

        for (int i = 0; i < courses.length; i++) {
            System.out.println(courses[i]);
        }
    }
}
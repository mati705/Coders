import java.util.ArrayList;

public class GraphColoringTimetable {

    private ArrayList<Course> courses;
    private int[][] conflictGraph;
    private int[] colors;
    private String[] rooms;

    public GraphColoringTimetable(ArrayList<Course> courses) {
        this.courses = courses;
        this.conflictGraph = new int[courses.size()][courses.size()];
        this.colors = new int[courses.size()];
        this.rooms = new String[]{"Room-1", "Room-2", "Room-3"};

        buildConflictGraph();
    }

    private void buildConflictGraph() {
        TimetableConflictChecker checker = new TimetableConflictChecker();

        for (int i = 0; i < courses.size(); i++) {
            for (int j = i + 1; j < courses.size(); j++) {
                ArrayList<Course> temp = new ArrayList<Course>();
                temp.add(courses.get(i));

                if (checker.hasConflict(temp, courses.get(j))) {
                    conflictGraph[i][j] = 1;
                    conflictGraph[j][i] = 1;
                }
            }
        }
    }

    public boolean assignRooms() {
        return colorCourse(0);
    }

    private boolean colorCourse(int courseIndex) {
        if (courseIndex == courses.size()) {
            return true;
        }

        for (int roomNumber = 1; roomNumber <= rooms.length; roomNumber++) {
            if (isSafe(courseIndex, roomNumber)) {
                colors[courseIndex] = roomNumber;

                if (colorCourse(courseIndex + 1)) {
                    return true;
                }

                colors[courseIndex] = 0;
            }
        }

        return false;
    }

    private boolean isSafe(int courseIndex, int roomNumber) {
        for (int i = 0; i < courses.size(); i++) {
            if (conflictGraph[courseIndex][i] == 1 && colors[i] == roomNumber) {
                return false;
            }
        }

        return true;
    }

    public void displayRoomAssignment() {
        System.out.println("\n--- Backtracking / Graph Coloring Timetable ---");

        if (!assignRooms()) {
            System.out.println("No valid room assignment possible.");
            return;
        }

        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getCourseId() + " -> " + rooms[colors[i] - 1]);
        }
    }
}
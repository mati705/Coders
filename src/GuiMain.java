import javax.swing.SwingUtilities;

public class GuiMain {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SimpleUniversityDashboard dashboard = new SimpleUniversityDashboard();
                dashboard.setVisible(true);
            }
        });
    }
}
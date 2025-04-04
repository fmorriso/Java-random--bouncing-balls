import javax.swing.*;
import java.awt.*;

public class Driver
{

    public static void main(String[] args)
    {
        String title = String.format("Random Moving Balls using Java version %s", getJavaVersion());
        System.out.println(title);
        JFrame frame = new MainFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // use a percentage of the available device screen size, making sure it is an even value and square
        Dimension gameSize = SwingScreenUtilities.getScaledSize(0.45, 10, true);
        System.out.format("gameSize=%s%n", gameSize);

        // note the use of setPreferredSize instead of setSize, coupled with
        // frame.pack() below:
        frame.setPreferredSize(gameSize);
        frame.setSize(gameSize);

        GameController controller = new GameController(frame);

        MainPanel pnl = new MainPanel(controller);
        pnl.setSize(gameSize);
        pnl.setPreferredSize(gameSize);

        // IMPORTANT: without this, the JPanel is not the exact same size as its parent
        // JFrame
        // Border zeroBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        // pnl.setBorder(zeroBorder);

        frame.setLayout(null);
        frame.getContentPane().add(pnl);

        frame.pack();

        Insets insets = frame.getInsets();
        System.out.format("Frame insets: %s%n", insets);

        // put the JFrame in the middle of the physical screen
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        //System.out.format("Driver: frame: w = %d, h = %d%n", frame.getWidth(), frame.getHeight());

    }

    /** get the java version that is running the current program
     * @return string containing the java version running the current program
     */
    private static String getJavaVersion()
    {
        Runtime.Version runTimeVersion = Runtime.version();
        return String.format("%s.%s.%s.%s", runTimeVersion.feature(), runTimeVersion.interim(), runTimeVersion.update(), runTimeVersion.patch());
    }

}

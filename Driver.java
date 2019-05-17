import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
//
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Driver {

    public static void main(String[] args) {
	// capture size of screen we're using
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	// Define the size of the JFrame as a square that is a percentage of the
	// available screen area and a multiple of 100
	final int frameHeight = (int) (screenSize.height * 75.00 / 100) / 100 * 100;
	final int frameWidth = frameHeight;

	Dimension gameSize = new Dimension(frameWidth, frameHeight);
	System.out.format("frameSize=%s%n", gameSize);

	JFrame frame = new JFrame("Random Moving Balls");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

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
	Border zeroBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);	
	//pnl.setBorder(zeroBorder);
	
	frame.setLayout(null);
	frame.getContentPane().add(pnl);

	frame.pack();
	
	Insets insets = frame.getInsets();
	System.out.format("Frame insets: %s%n", insets);

	// put the JFrame in the middle of the physical screen
	frame.setLocationRelativeTo(null);
	frame.setResizable(false);
	frame.setVisible(true);

	System.out.format("Driver: frame: w = %d, h = %d%n", frame.getWidth(), frame.getHeight());

    }

}

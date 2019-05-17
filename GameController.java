import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
//
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameController {

    private JFrame parentComponent;
    private Dimension gameSize;
    private ArrayList<RandomBall> balls;

    public GameController(JFrame frame) {
	this.parentComponent = frame;
	this.gameSize = parentComponent.getSize();

	balls = generateBalls();
	startBallTimers();
    }

    private void startBallTimers() {
	for (RandomBall b : balls) {
	    b.getTimer().start();
	}
    }

    private ArrayList<RandomBall> generateBalls() {
	ArrayList<RandomBall> list = new ArrayList<RandomBall>();
	int n = getRandomNumberOfBalls();
	System.out.format("Number of balls to create is %d%n", n);
	for (int i = 0; i < n; i++) {
	    Color c = getUniqueColor(list);
	    RandomBall b = new RandomBall(getRandomX(), getRandomY(), getRandomDiameter(), c, gameSize);
	    int delay = getRandomDelay();
	    Timer t = new Timer(delay, (ae -> {
		b.move(this.gameSize.width, this.gameSize.height);
	    }));
	    b.setTimer(t);

	    list.add(b);
	}
	return list;
    }

    private Color getUniqueColor(ArrayList<RandomBall> list) {

	if (list.size() <= 1) {
	    return ColorExtensions.getRandomDarkColor();
	}
	boolean keepLooking = true;
	int byTotalRGB = (int) (64.0 / list.size());
	//System.out.format("uniqueColor: list size = %d, diff = %d%n", list.size(), byTotalRGB);
	Color c = null;
	int count = 0;
	int attempts = 0;
	int maxAttempts = (int) (1024.0 * 2 / list.size());
	do {
	    attempts++;
	    c = ColorExtensions.getRandomDarkColor();
	    int i = 0;
	    for (RandomBall b : list) {
		i++;
		// System.out.format("Color difference %d = %d%n", i,
		// ColorExtensions.getColorDifference(c, b.getColor()));
		if (ColorExtensions.areSignificantlyDifferentColors(c, b.getColor(), byTotalRGB)) {
		    count++;
		}
	    }
	    // stop if we have unique colors or exceeded max number of attempts
	    if (count == list.size()) {
		keepLooking = false;
	    } else if (attempts == maxAttempts) {
		System.out.format("gave up looking for unique color in list of size = %d after %d attempts%n", list.size(), attempts);
		keepLooking = false;
	    }

	} while (keepLooking);

	return c;

    }

    private int getRandomNumberOfBalls() {
	final int MAX = 9;
	final int MIN = 3;
	final int RANGE = MAX - MIN + 1;
	return (int) (Math.random() * RANGE + MIN);
    }

    private int getRandomDelay() {
	final int MAX = 500;
	final int MIN = (int) (MAX / 2.0);
	final int RANGE = MAX - MIN + 1;
	return (int) (Math.random() * RANGE + MIN);
    }

    private double getRandomX() {
	double MAX = gameSize.width;
	double MIN = 0;
	double RANGE = MAX - MIN + 1;

	return Math.random() * RANGE + MIN;
    }

    private double getRandomY() {
	double MAX = gameSize.height;
	double MIN = 0;
	double RANGE = MAX - MIN + 1;

	return Math.random() * RANGE + MIN;
    }

    private double getRandomDiameter() {
	double MAX = gameSize.height * 7.0 / 100.0;
	double MIN = MAX * 60.0 / 100;
	double RANGE = MAX - MIN + 1;

	return Math.random() * RANGE + MIN;
    }

    public ArrayList<RandomBall> getBalls() {
	return balls;
    }

    public Dimension getPanelSize() {
	return this.gameSize;
    }
}

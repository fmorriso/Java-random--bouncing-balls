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
	    RandomBall b = new RandomBall(getRandomX(), getRandomY(), getRandomDiameter(),
		    ColorExtensions.getRandomDarkColor(), gameSize);
	    int delay = getRandomDelay();
	    Timer t = new Timer(delay, (ae -> {
		b.move(this.gameSize.width, this.gameSize.height);
	    }));
	    b.setTimer(t);

	    list.add(b);
	}
	return list;
    }

    private int getRandomNumberOfBalls() {
	final int MAX = 5;
	final int MIN = 2;
	final int RANGE = MAX - MIN + 1;
	return (int) (Math.random() * RANGE + MIN);
    }

    private int getRandomDelay() {
	final int MAX = 1500;
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
	double MAX = gameSize.height * 6.0 / 100.0;
	double MIN = MAX * 66.667 / 100;
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

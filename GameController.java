import java.awt.Dimension;
import java.util.ArrayList;
//
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameController {
    private JFrame parentComponent;
    private Dimension panelSize;
    private ArrayList<RandomBall> balls;

    public GameController(JFrame frame) {
	this.parentComponent = frame;
	this.panelSize = parentComponent.getSize();
    }

    private ArrayList<RandomBall> generateBalls() {
	ArrayList<RandomBall> list = new ArrayList<RandomBall>();
	int n = getRandomNumberOfBalls();
	System.out.format("Number of balls to create is %d%n", n);
	for (int i = 0; i < n; i++) {
	    RandomBall b = new RandomBall();
	    int delay = getRandomDelay();
	    Timer t = new Timer(delay, (ae -> {
		b.move(this.panelSize.width, this.panelSize.height);
	    }));
	    b.setTimer(t);
	    b.setX(getRandomX());
	    list.add(b);
	}
	return list;
    }

    private int getRandomNumberOfBalls() {
	int MAX = 5;
	int MIN = 2;
	int RANGE = MAX - MIN + 1;
	return (int) (Math.random() * RANGE + MIN);
    }

    private int getRandomDelay() {
	int MAX = 1500;
	int MIN = (int) (MAX / 2.0);
	int RANGE = MAX - MIN + 1;
	return (int) (Math.random() * RANGE + MIN);
    }

    private double getRandomX() {
	double MAX = 400;
	double MIN = 0;
	double RANGE = MIN - MAX + 1;

	return Math.random() * RANGE + MIN;
    }
}

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel {

    private Dimension panelSize;
    ArrayList<RandomBall> balls;

    public MainPanel(Dimension frameSize) {
	panelSize = frameSize;
	balls = generateBalls();

	for (Ball b : balls) {
	    System.out.format("b.x = %.2f%n", b.getX());
	}
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

    private int getRandomDelay() {
	// TODO Auto-generated method stub
	return 0;
    }

    private double getRandomX() {
	double MAX = 400;
	double MIN = 0;
	double RANGE = MIN - MAX + 1;

	return Math.random() * RANGE + MIN;
    }

    private int getRandomNumberOfBalls() {
	int MAX = 5;
	int MIN = 2;
	int RANGE = MAX - MIN + 1;
	return (int) (Math.random() * RANGE + MIN);
    }

}

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

    private Dimension panelSize;
    ArrayList<Ball> balls;

    public MainPanel(Dimension frameSize) {
	panelSize = frameSize;
	balls = generateBalls();

	for (Ball b : balls) {
	    System.out.format("b.x = %.2f%n", b.getX());
	}
    }

    private ArrayList<Ball> generateBalls() {
	ArrayList<Ball> list = new ArrayList<Ball>();
	int n = getRandomNumberOfBalls();
	System.out.format("Number of balls to create is %d%n", n);
	for (int i = 0; i < n; i++) {
	    Ball b = new Ball();
	    b.setX(getRandomX());
	    list.add(b);
	}
	return list;
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

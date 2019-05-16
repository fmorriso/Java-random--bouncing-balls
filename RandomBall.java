import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Timer;

public class RandomBall extends RunnableBall {

    private Timer t;
    private boolean shouldMove;
    private Dimension gameSize;

    public RandomBall(double x, double y, double diameter, Color c, Dimension gameSize) {
	super(x, y, diameter, c);
	this.gameSize = gameSize;
	this.shouldMove = true;
    }

    @Override
    public boolean shouldMove() {
	return shouldMove;
    }

    @Override
    public void move() {
	if (shouldMove()) {
	    super.move(gameSize.width, gameSize.height);
	}
    }

    public void setTimer(Timer t) {
	this.t = t;
    }

    public Timer getTimer() {
	return this.t;
    }

}

import java.awt.Color;

public abstract class RunnableBall extends Ball implements Runnable {

    public abstract boolean shouldMove();

    public abstract void move();

    public RunnableBall(double x, double y, double diameter, Color c) {
	super(x, y, diameter, c);
    }

    @Override
    public void run() {
	if (shouldMove()) {
	    move();
	}

    }
}

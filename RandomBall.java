import javax.swing.Timer;

public class RandomBall extends RunnableBall {

    private Timer t;

    @Override
    public boolean shouldMove() {
	return false;
    }

    @Override
    public void move() {
	// TODO Auto-generated method stub

    }

    public void setTimer(Timer t) {
	this.t = t;
    }

}

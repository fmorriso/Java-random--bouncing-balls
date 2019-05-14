
public abstract class RunnableBall extends Ball implements Runnable {

    public abstract boolean shouldMove();
    public abstract void move();
    
    @Override
    public void run() {
	if(shouldMove()) {
	    move();
	}
	

    }
}

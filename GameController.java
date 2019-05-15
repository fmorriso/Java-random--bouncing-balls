import java.util.ArrayList;

public class GameController {
	private MainPanel parentComponent;
	private ArrayList<RandomBall> balls;
	public GameController(MainPanel parentComponent) {
		this.parentComponent = parentComponent;
	}

	public void generateRandomBalls() {
		balls = new ArrayList<RandomBall>();
	}
}

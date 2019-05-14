// Computer Math - Unit 2 - Lab 11 - Bouncing Pinball
import java.awt.*;

import javax.swing.Timer;

public class Ball extends Polkadot
{

	private static final double DEFAULT_DIAMETER = 50;
	
	private static int MAX_MOVE = 12;
	private static int MIN_MOVE = MAX_MOVE / 2;
	private static int MOVE_RANGE = MAX_MOVE - MIN_MOVE + 1;

	private double dx; // pixels to move each time move() is called.
	private double dy;

	// constructors
	public Ball() // default constructor
	{
		this(200, 200, DEFAULT_DIAMETER, Color.BLACK);
	}

	public Ball(double x, double y, double dia, Color c)
	{
		super(x, y, dia, c);
		initialize();
	}

	private void initialize()
	{
		MAX_MOVE = (int) (getDiameter() * 15.0 / 100);
		MIN_MOVE = (int) (getDiameter() * 05.0 / 100);
		MOVE_RANGE = MAX_MOVE - MIN_MOVE + 1;
		System.out.format("diameter = %.1f, min move = %d, max move = %d %n", getDiameter(), MIN_MOVE, MAX_MOVE);

		// make sure x movement is faster/farther than y movement
		dy = getRandomDelta();
		dx = dy * 1.3333;

	}

	private double getRandomDelta()
	{
		double retval = Math.random() * MOVE_RANGE + MIN_MOVE;
		System.out.format("random delta=%.1f%n", retval);
		return retval;
	}

	// modifier methods
	public void setdx(double x)
	{
		dx = x;
	}

	public void setdy(double y)
	{
		dy = y;
	}

	// accessor methods
	public double getdx()
	{
		return dx;
	}

	public double getdy()
	{
		return dy;
	}

	// instance methods
	public void move(double rightEdge, double bottomEdge)
	{
		final double leftEdge = 0;
		final double topEdge = 0;

		setX(getX() + dx); // move horizontally
		setY(getY() + dy); // move vertically

		if (getX() >= rightEdge - getRadius()) // hit the right edge
		{
			setX(rightEdge - getRadius());
			dx = dx * -1;
		} else if (getX() <= leftEdge + getRadius()) // hit the left edge
		{
			setX(leftEdge + getRadius());
			dx = dx * -1;
		} else if (getY() >= bottomEdge - getRadius()) // hit the bottom edge
		{
			setY(bottomEdge - getRadius());
			dy = dy * -1;
		} else if (getY() <= topEdge + getRadius()) // hit the top edge
		{
			setY(topEdge + getRadius());
			dy = dy * -1;
		}

	}

	public void draw(Graphics myBuffer)
	{
		myBuffer.setColor(getColor());
		myBuffer.fillOval((int) (getX() - getRadius()), (int) (getY() - getRadius()), (int) getDiameter(), (int) getDiameter());
	}


}
import javax.swing.*;
import java.awt.*;

public class RandomBall extends Ball implements Runnable
{

    private static int totalBalls = 0;
    private static int totalMoves = 0;
    private Timer t;
    private boolean shouldMove;
    private Dimension gameSize;
    private Insets insets;

    public RandomBall(double x, double y, double diameter, Color c, Dimension gameSize, Insets insets)
    {
        super(x, y, diameter, c);
        this.gameSize = gameSize;
        this.insets = insets;
        this.shouldMove = true;
        totalBalls++;
        if (totalBalls == 1) {
            System.out.format("RandomBall (constructor): gameSize = %s, insets=%s%n", gameSize, insets);
        }
    }

    @Override
    public boolean move(double rightEdge, double bottomEdge)
    {
        double adjustedBottomEdge = bottomEdge - this.getRadius() - insets.bottom;
        double adjustedRightEdge = rightEdge - insets.right;
        if (totalMoves <= 0) {
            System.out.format("RandomBall: move, bottomEdge = %.2f, adjustedBottom = %.2f, adjustedRight = %.2f %n", bottomEdge,
                    adjustedBottomEdge, adjustedRightEdge);
        }
        totalMoves++;

        // if we hit an edge, change the color randomly
        boolean hitAnEdge = super.move(adjustedRightEdge, adjustedBottomEdge);
        if (hitAnEdge) setColor(ColorExtensions.getRandomDarkColor());
        return hitAnEdge;
    }

    @Override
    public void run()
    {
        System.out.println("RunnableBall: run()");
        moveRunnable();
    }

    public boolean shouldMove()
    {
        return shouldMove;
    }

    public void moveRunnable()
    {
        if (totalBalls <= 1) {
            System.out.println("RandomBall: moveRunnable");
        }
        if (shouldMove()) {
            if (totalBalls == 1) {
                System.out.format("RandomBall (moveRunnable): gameSize = %s%n", gameSize);
            }
            super.move(gameSize.width, gameSize.height);
        }
    }

    public Timer getTimer()
    {
        return this.t;
    }

    public void setTimer(Timer t)
    {
        this.t = t;
        // System.out.format("RandomBall: timer delay = %d%n", t.getDelay());
    }

}

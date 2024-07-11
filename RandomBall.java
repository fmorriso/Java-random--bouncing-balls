import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.Timer;

public class RandomBall extends Ball implements Runnable {

   private Timer t;
   private boolean shouldMove;
   private Dimension gameSize;
   private Insets insets;
   private static int totalBalls = 0;
   private static int totalMoves = 0;

   public RandomBall(double x, double y, double diameter, Color c, Dimension gameSize, Insets insets) {
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
    public void move(double rightEdge, double bottomEdge) {
   
      double adjustedBottomEdge = bottomEdge - this.getRadius() - insets.bottom;
      double adjustedRightEdge = rightEdge - insets.right;
      if (totalMoves <= 0) {
         System.out.format("RandomBall: move, bottomEdge = %.2f, adjustedBottom = %.2f, adjustedRight = %.2f %n", bottomEdge,
            adjustedBottomEdge, adjustedRightEdge);
      }
      super.move(adjustedRightEdge, adjustedBottomEdge);
      totalMoves++;
   }

   @Override
    public void run() {
      System.out.println("RunnableBall: run()");
      moveRunnable();
   }

   public boolean shouldMove() {
      return shouldMove;
   }

   public void moveRunnable() {
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

   public void setTimer(Timer t) {
      this.t = t;
   // System.out.format("RandomBall: timer delay = %d%n", t.getDelay());
   }

   public Timer getTimer() {
      return this.t;
   }

}

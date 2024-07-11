import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;
//
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameController {

   private JFrame parentComponent;
   private Dimension gameSize;
   private Insets insets;
   private ArrayList<RandomBall> balls;

   public GameController(JFrame frame) {
      this.parentComponent = frame;
      this.gameSize = parentComponent.getSize();
      this.insets = parentComponent.getInsets();
   
      balls = generateBalls();
      startBallTimers();
   }

   private void startBallTimers() {
      for (RandomBall b : balls) {
         b.getTimer().start();
      }
   }

   private ArrayList<RandomBall> generateBalls() {
      ArrayList<RandomBall> list = new ArrayList<RandomBall>();
      int n = getRandomNumberOfBalls();
      System.out.format("Number of balls to create is %d%n", n);
      for (int i = 0; i < n; i++) {
         Color c = getUniqueColor(list);
         RandomBall b = new RandomBall(getRandomX(), getRandomY(), getRandomDiameter(), c, gameSize, insets);
         int delay = getRandomDelay();
         Timer t = new Timer(delay, (
            ae -> {
               b.move(this.gameSize.width, this.gameSize.height);
            }));
         b.setTimer(t);
      
         list.add(b);
      }
      return list;
   }

   private Color getUniqueColor(ArrayList<RandomBall> list) {
   
   // avoid unnecessary overhead when the list is small/empty
      if (list.size() <= 1) {
         return ColorExtensions.getRandomDarkColor();
      }
   
      boolean keepLooking = true;
   // vary the minimum separation between colors of each ball colors based on the size of the list.
   // The more items in the list, the less unique the colors need to be.
      int byTotalRGB = (int) (64.0 / list.size());
   //System.out.format("uniqueColor: list size = %d, minimum difference = %d%n", list.size(), byTotalRGB);
      Color c = null;
      int count = 0;
      int attempts = 0;
   // vary the maximum number of attempts to insure unique colors based on the size of the list.
   // The more items in the list, the less unique the colors need to be.
      int maxAttempts = (int) (2048.0 / list.size());
      do {
         attempts++;
         c = ColorExtensions.getRandomDarkColor();
       // check the other balls to see if this new ball's color is unique enough
         for (RandomBall b : list) {		
         // System.out.format("Color difference %d = %d%n", i,
         // ColorExtensions.getColorDifference(c, b.getColor()));
            if (ColorExtensions.areSignificantlyDifferentColors(c, b.getColor(), byTotalRGB)) {
               count++;
            }
         }
       // stop if we have unique colors or have exceeded the maximum number of attempts to find a unique color
         if (count == list.size()) {
            keepLooking = false;
         } else if (attempts == maxAttempts) {
            System.out.format("gave up looking for unique color in list of size = %d after %d attempts%n", list.size(), attempts);
            keepLooking = false;
         }
      
      } while (keepLooking);
   
      return c;
   
   }

   private int getRandomNumberOfBalls() {
      final int MAX = 9;
      final int MIN = 3;
      final int RANGE = MAX - MIN + 1;
      return (int) (Math.random() * RANGE + MIN);
   }

   private int getRandomDelay() {
      final int MAX = 500;
      final int MIN = (int) (MAX / 2.0);
      final int RANGE = MAX - MIN + 1;
      return (int) (Math.random() * RANGE + MIN);
   }

   private double getRandomX() {
      double MAX = gameSize.width;
      double MIN = 0;
      double RANGE = MAX - MIN + 1;
   
      return Math.random() * RANGE + MIN;
   }

   private double getRandomY() {
      double MAX = gameSize.height;
      double MIN = 0;
      double RANGE = MAX - MIN + 1;
   
      return Math.random() * RANGE + MIN;
   }

   private double getRandomDiameter() {
      double MAX = gameSize.height * 7.0 / 100.0;
      double MIN = MAX * 60.0 / 100;
      double RANGE = MAX - MIN + 1;
   
      return Math.random() * RANGE + MIN;
   }

   public ArrayList<RandomBall> getBalls() {
      return balls;
   }

   public Dimension getPanelSize() {
      return this.gameSize;
   }
}

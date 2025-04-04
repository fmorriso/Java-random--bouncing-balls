import java.awt.Insets;
//
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

   public MainFrame(String title) {
      super(title);   
   }

    // eliminate top, bottom, right and left insets in order to keep JFrame and JPanel the same size.
   @Override
   public Insets getInsets() {
      final int top = 29;
      final int bottom = 6;
      Insets insets = new Insets(top, 0, bottom, 0);
      return insets;
   }
}

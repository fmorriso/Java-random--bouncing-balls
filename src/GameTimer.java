import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer implements ActionListener {

   MainPanel panel;
	
   public GameTimer(MainPanel panel) {
      this.panel = panel;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
       //System.out.println("actionPerformed panel.drawScreen() called");
      panel.drawScreen();
   }

}

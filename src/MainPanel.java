import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class MainPanel extends JPanel
{

    private Dimension gameSize;
    private BufferedImage myImage;
    private Graphics myBuffer;

    private Timer game;
    private GameController controller;

    public MainPanel(GameController controller)
    {
        this.controller = controller;
        this.gameSize = controller.getPanelSize();
        Border zeroBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        this.setBorder(zeroBorder);
        System.out.format("Constructor: game size: w = %d, h = %d%n", gameSize.width, gameSize.height);
        this.setSize(gameSize);
        this.setPreferredSize(gameSize);
        System.out.format("MainPanel size: w = %d, h = %d%n", getWidth(), getHeight());

        myImage = new BufferedImage(gameSize.width, gameSize.height, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();

        final int delay = 5;
        this.game = new Timer(delay, new GameTimer(this));
        this.game.start();

    }

    // @Override
    @Override
    public void addNotify()
    {
        super.addNotify();
        System.out.println("MainPanel has been added");
        System.out.format("addNotify: game size: w = %d, h = %d%n", getWidth(), getHeight());
        this.setSize(gameSize);
        this.setPreferredSize(gameSize);
    }


    public void paintComponent(Graphics g)
    {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    private void clearBackground()
    {
        myBuffer.setColor(getBackground());
        myBuffer.fillRect(0, 0, getWidth(), getHeight());
    }

    public void drawScreen()
    {
        if (controller == null)
            return;
        if (controller.getBalls() == null)
            return;
        if (controller.getBalls().size() == 0)
            return;
        //System.out.format("rb.draw called for %d balls%n", controller.getBalls().size());
        clearBackground();
        for (RandomBall rb : controller.getBalls())
        {
            rb.draw(myBuffer);
        }
        repaint();
    }

}

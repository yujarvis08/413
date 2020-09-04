package tankrotationexample.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DmgPowerUp extends PowerUps{
    public DmgPowerUp(int x, int y, BufferedImage powerUp) {
        super(x, y, powerUp);
    }
    public Rectangle getBounds() {
        return new Rectangle(x,y,powerUp.getWidth(),powerUp.getHeight());
    }

    public void drawImage(Graphics g){
        Graphics2D g2 =(Graphics2D)g;
        g2.drawImage(this.powerUp,x,y,null);
    }
}

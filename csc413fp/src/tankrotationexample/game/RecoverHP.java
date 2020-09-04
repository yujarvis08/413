package tankrotationexample.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RecoverHP extends PowerUps{
    public RecoverHP(int x, int y, BufferedImage powerUp) {
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

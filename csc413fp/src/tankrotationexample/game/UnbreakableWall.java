package tankrotationexample.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UnbreakableWall extends Wall{
    int x,y;
    int state = 1;
    BufferedImage wallImage;

    public UnbreakableWall(int x, int y, BufferedImage wallImage) {
        this.x = x;
        this.y = y;
        this.wallImage = wallImage;
    }

    public void drawImage(Graphics g) {
            Graphics g2 = (Graphics2D) g;
            g2.drawImage(this.wallImage, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,wallImage.getWidth(),wallImage.getHeight());
    }
}

package tankrotationexample.game;



import tankrotationexample.GameConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author anthony-pc
 */
public class Tank extends GameObject{


    private int x;
    private int y;
    private int vx;
    private int vy;
    private float angle;
    private Rectangle hitBox;
    private int hp;
    private int lives;
    private int dmg;

    private final int R = 2;
    private final float ROTATIONSPEED = 3.0f;



    private BufferedImage img;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;
    private static ArrayList<Bullet> ammo;


    Tank(int x, int y, int vx, int vy, int angle, BufferedImage img, int hp, int lives, int dmg) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.ammo = new ArrayList<>();
        this.hitBox = new Rectangle(x,y,this.img.getWidth(),this.img.getHeight());
        this.hp = hp;
        this.lives = lives;
        this.dmg = dmg;

    }

    void setX(int x){ this.x = x; }

    void setY(int y) { this. y = y;}

    void setHP(int hp) { this.hp = hp; }

    void setDmg(int dmg) { this.dmg = dmg; }

    void setLives(int lives) { this.lives = lives; }

    int getX(){ return x; }

    int getY(){ return y; }

    public int getVy() { return vy; }

    public int getVx() { return vx; }

    int getHP() { return hp; }

    int getLives() { return lives; }

    int getDmg() { return dmg; }


    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleShootPressed() { this.ShootPressed = true;}

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShootPressed() { this.ShootPressed = false;}

    public boolean isDownPressed() {
        return DownPressed;
    }
    public boolean isUpPressed() {
        return UpPressed;
    }

    void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if (this.ShootPressed && TRE.tick % 20 == 0) {
            Bullet b = new Bullet(x,y, (int) angle,TRE.bulletImage,this);
            this.ammo.add(b);
        }
        this.ammo.forEach(bullet -> bullet.update());
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
        this.hitBox.setLocation(x,y);
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
        this.hitBox.setLocation(x,y);
    }

    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= GameConstants.GAME_SCREEN_WIDTH - 88) {
            x = GameConstants.GAME_SCREEN_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameConstants.GAME_SCREEN_HEIGHT - 80) {
            y = GameConstants.GAME_SCREEN_HEIGHT - 80;
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,img.getWidth(),img.getHeight());
    }

    public static ArrayList<Bullet> getBullet(){
        return ammo;
    }


    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        this.ammo.forEach(bullet -> bullet.drawImage(g));
        g2d.drawImage(this.img, rotation, null);

        if(this.getHP() <= 25) {
            g2d.setColor(Color.red);
            g2d.fillRect(this.getX(), this.getY() - 20, this.img.getWidth(), 5);
        } else if(this.getHP() < 75 && this.getHP() >= 50) {
            g2d.setColor(Color.yellow);
            g2d.fillRect(this.getX(), this.getY() - 20, this.img.getWidth(), 5);
        } else {
            g2d.setColor(Color.green);
            g2d.fillRect(this.getX(), this.getY() - 20, this.img.getWidth(), 5);
        }

    }



}

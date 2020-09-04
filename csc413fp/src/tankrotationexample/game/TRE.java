/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankrotationexample.game;


import tankrotationexample.GameConstants;
import tankrotationexample.Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;


import static javax.imageio.ImageIO.read;

/**
 *
 * @author anthony-pc
 */
public class TRE extends JPanel implements Runnable {

    private BufferedImage world;
    private Tank t1;
    private Tank t2;
    private Launcher lf;
    static long tick = 0;
    public static ArrayList<Wall> walls;
    public static ArrayList<DmgPowerUp> dmgPowerUp;
    public static ArrayList<RecoverHP> healPowerUp;
    public static ArrayList<AddLife> lifePowerUp;
    public static BufferedImage bulletImage;
    private static Collision c;

    public TRE(Launcher lf){
        this.lf = lf;
    }

    @Override
    public void run(){
       try {
           this.resetGame();
           while (true) {
                this.tick++;
                this.t1.update(); // update tank
                this.t2.update();
                this.repaint();   // redraw game
               c.PlayerCollision(this.t1,this.t2);
               c.WallCollision(this.t1, this.getWalls());
               c.WallCollision(this.t2, this.getWalls());
               c.bulletWalls(this.t1,this.getWalls());
               c.bulletWalls(this.t2,this.getWalls());
               c.DmgPowerUp(this.t1,this.getDmgPowerUp());
               c.DmgPowerUp(this.t2,this.getDmgPowerUp());
               c.HpPowerUp(this.t1,this.getHeal());
               c.HpPowerUp(this.t2,this.getHeal());
               c.bulletTank(this.t1,this.t2);
               c.bulletTank(this.t2,this.t1);
               c.LivesPowerUp(this.t1,this.getLivesPowerUp());
               c.LivesPowerUp(this.t2,this.getLivesPowerUp());
                Thread.sleep(1000 / 144); //sleep for a few milliseconds
                /*
                 * simulate an end game event
                 * we will do this with by ending the game when drawn 2000 frames have been drawn
                 */
                if(t1.getLives() <= 0 || t2.getLives() <= 0){
                    this.lf.setFrame("end");
                    return;
                }
            }
       } catch (InterruptedException ignored) {
           System.out.println(ignored);
       }
    }

    /**
     * Reset game to its initial state.
     */
    public void resetGame(){
        this.tick = 0;
        this.t1.setX(100);
        this.t1.setY(100);
        this.t2.setX(800);
        this.t2.setY(800);
        this.t1.setHP(100);
        this.t2.setHP(100);
        this.t1.setLives(3);
        this.t2.setLives(3);
        this.t1.setDmg(25);
        this.t2.setDmg(25);
    }


    /**
     * Load all resources for Tank Wars Game. Set all Game Objects to their
     * initial state as well.
     */
    public void gameInitialize() {
        this.world = new BufferedImage(GameConstants.GAME_SCREEN_WIDTH,
                                       GameConstants.GAME_SCREEN_HEIGHT,
                                       BufferedImage.TYPE_INT_RGB);

        BufferedImage t1img = null;
        BufferedImage t2img = null;
        BufferedImage breakWall;
        BufferedImage unbreakWall;
        BufferedImage recovery;
        BufferedImage dmgUp;
        BufferedImage life;
        walls = new ArrayList<>();
        dmgPowerUp = new ArrayList<>();
        healPowerUp = new ArrayList<>();
        lifePowerUp = new ArrayList<>();
        try {
            /*
             * note class loaders read files from the out folder (build folder in Netbeans) and not the
             * current working directory.
             */
            t1img = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("tank1.png")));
            t2img = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("tank1.png")));
            unbreakWall = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("Wall1.gif")));
            breakWall = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("Wall2.gif")));
            bulletImage = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("Shell.gif")));
            dmgUp = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("Pickup.gif")));
            recovery = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("hppack.png")));
            life = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("lifepack.png")));

            InputStreamReader isr = new InputStreamReader(TRE.class.getClassLoader().getResourceAsStream("maps/map1"));
            BufferedReader mapReader =new BufferedReader(isr);

            String row = mapReader.readLine();
            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for(int curRow=0;curRow<numRows;curRow++){
                row = mapReader.readLine();
                mapInfo = row.split("\t");
                for(int curCol=0;curCol<numCols;curCol++){
                    switch(mapInfo[curCol]){
                        case "2":
                            this.walls.add(new BreakableWall(curCol*30,curRow*30,breakWall));
                            break;
                        case "3":
                        case "9":
                            this.walls.add(new UnbreakableWall(curCol*30,curRow*30,unbreakWall));
                            break;
                        case "5":
                            this.healPowerUp.add(new RecoverHP(curCol*30, curRow*30, recovery));
                            break;
                        case "6":
                            this.dmgPowerUp.add(new DmgPowerUp(curCol*30,curRow*30, dmgUp));
                            break;
                        case "7":
                            this.lifePowerUp.add(new AddLife(curCol*30, curRow*30, life));
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        t1 = new Tank(100, 100, 0, 0, 0, t1img,100,3,25);
        t2 = new Tank(800, 800, 0, 0, 0, t2img,100,3,25);
        c = new Collision(t1,t2);
        TankControl tc1 = new TankControl(t1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);
        TankControl tc2 = new TankControl(t2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        this.setBackground(Color.BLACK);
        this.lf.getJf().addKeyListener(tc1);
        this.lf.getJf().addKeyListener(tc2);
    }

    private ArrayList<Wall> getWalls(){
        return walls;
    }

    private ArrayList<DmgPowerUp> getDmgPowerUp() {
        return dmgPowerUp;
    }

    private ArrayList<RecoverHP> getHeal() {
        return healPowerUp;
    }

    private ArrayList<AddLife> getLivesPowerUp() { return lifePowerUp;}

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D buffer = world.createGraphics();
        BufferedImage p1;
        BufferedImage p2;
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0,0,GameConstants.WORLD_WIDTH,GameConstants.WORLD_HEIGHT);
        this.walls.forEach(wall -> wall.drawImage(buffer));
        this.dmgPowerUp.forEach(DmgPowerUp -> DmgPowerUp.drawImage(buffer));
        this.healPowerUp.forEach(RecoverHP -> RecoverHP.drawImage(buffer));
        this.lifePowerUp.forEach(AddLife -> AddLife.drawImage(buffer));
        this.t1.drawImage(buffer);
        this.t2.drawImage(buffer);


        if (t1.getY() <= GameConstants.GAME_SCREEN_WIDTH / 4) {
            p1 = world.getSubimage(0, 0, 1000, 500);
        } else if (t1.getY() >= 500) {
            p1 = world.getSubimage(0, 500, 1000, 500);
        } else {
            p1 = world.getSubimage(0, t1.getY(), 1000, 500);
        }

        if (t2.getY() <= GameConstants.GAME_SCREEN_WIDTH / 4) {
            p2 = world.getSubimage(0, 0, 1000, 500);
        } else if (t2.getY() >= 500) {
            p2 = world.getSubimage(0, 500, 1000, 500);
        } else {
            p2 = world.getSubimage(0, t2.getY(), 1000, 500);
        }

        g2.drawImage(p1,0, 0, null);
        g2.drawImage(p2, 0, 502, null);

        g2.setColor(Color.cyan);
        g2.setFont(new Font("Arial", Font.BOLD, 25));
        g2.drawString("Lives: " + t1.getLives(), 10, 30);
        g2.drawString("Lives: " + t2.getLives(), 880, 550);

        BufferedImage minimap = world.getSubimage(0, 0, 900, 900);
        g2.scale(.15, .15);
        g2.drawImage(minimap, 5650, 0, null);
    }
}

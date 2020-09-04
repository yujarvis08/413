package tankrotationexample.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Collision {
    Tank t1,t2;
    public Collision(Tank t1,Tank t2){
        this.t1=t1;
        this.t2=t2;
    }

    public void PlayerCollision(Tank t1, Tank t2){
        Rectangle hitBox1 = new Rectangle(t1.getBounds());
        Rectangle hitBox2 = new Rectangle(t2.getBounds());

        if(hitBox1.intersects(hitBox2)){
            if(t1.isDownPressed()){
                t1.setX(t1.getX() + t1.getVx());
                t1.setY(t1.getY() + t1.getVy());
            }
            if(t1.isUpPressed()){
                t1.setX(t1.getX() - t1.getVx());
                t1.setY(t1.getY() - t1.getVy());
            }
            if(t2.isDownPressed()){
                t2.setX(t2.getX() + t2.getVx());
                t2.setY(t2.getY() + t2.getVy());
            }
            if(t2.isUpPressed()){
                t2.setX(t2.getX() - t2.getVx());
                t2.setY(t2.getY() - t2.getVy());
            }
        }
    }

    public void WallCollision(Tank t, ArrayList<Wall> walls){
        Rectangle hitBox = new Rectangle(t.getBounds());

        for(int i = 0; i < walls.size(); i++){
            if(hitBox.intersects(walls.get(i).getBounds())){
                if(t.isUpPressed() ){
                    t.setX(t.getX() - t.getVx());
                    t.setY(t.getY() - t.getVy());
                }
                else if(t.isDownPressed()){
                    t.setX(t.getX() + t.getVx());
                    t.setY(t.getY() + t.getVy());
                }
            }
        }
    }

    public void bulletWalls(Tank t, ArrayList<Wall> wall){
        Bullet b;
        Wall walls;
        ArrayList <Bullet> bullets = t.getBullet();

        for(int i = 0; i < bullets.size(); i++){
            b = bullets.get(i);
            Rectangle hitBox1  = new Rectangle(b.getBounds());

            for(int j = 0; j < wall.size(); j++){
                walls = wall.get(j);
                Rectangle hitBox2 = new Rectangle(walls.getBounds());

                if(hitBox1.intersects(hitBox2)){
                    bullets.remove(b);

                    if(walls instanceof BreakableWall){
                        wall.remove(walls);
                    }
                }
            }
        }
    }

    public void HpPowerUp(Tank tank, ArrayList<RecoverHP> hp){
        RecoverHP hpBoost;
        Rectangle hitBox =new Rectangle(tank.getBounds());

        for(int i = 0; i < hp.size(); i++){
            hpBoost = hp.get(i);
            if(hitBox.intersects(hpBoost.getBounds())){
                hp.remove(hpBoost);
                if(tank.getHP() <= 75) {
                    tank.setHP(tank.getHP() + 25);
                }
                else {
                    tank.setHP(100);
                }
            }
        }
    }

    public void LivesPowerUp(Tank tank, ArrayList<AddLife> life){
        AddLife plusLife;
        Rectangle hitBox =new Rectangle(tank.getBounds());

        for(int i = 0; i < life.size(); i++){
            plusLife = life.get(i);
            if(hitBox.intersects(plusLife.getBounds())){
                life.remove(plusLife);
                tank.setLives(tank.getLives() + 1);
            }
        }
    }

    public void DmgPowerUp(Tank tank, ArrayList<DmgPowerUp> dmg){
        DmgPowerUp dmgBoost;
        Rectangle hitBox = new Rectangle(tank.getBounds());

        for(int i = 0; i < dmg.size(); i++){
            dmgBoost = dmg.get(i);
            if(hitBox.intersects(dmgBoost.getBounds())){
                dmg.remove(dmgBoost);
                if(tank.getDmg() < 50) {
                    tank.setDmg(tank.getDmg() + 25);
                } else {
                    tank.setDmg(tank.getDmg() + 10);
                }
            }
        }
    }

    public void bulletTank(Tank t1,Tank t2){
        Bullet b;
        ArrayList<Bullet> bullet = t1.getBullet();

        ArrayList<Integer> xSpawnPoints = new ArrayList<Integer>();
        xSpawnPoints.add(100);
        xSpawnPoints.add(800);

        Random rand = new Random();

        int xr = xSpawnPoints.get(rand.nextInt(xSpawnPoints.size()));
        int yr = xSpawnPoints.get(rand.nextInt(xSpawnPoints.size()));

        for(int i = 0; i < bullet.size(); i++){
            b = bullet.get(i);
            if(b.getTank() == t1){
                if(b.getBounds().intersects(t2.getBounds())){
                    bullet.remove(b);
                    t2.setHP(t2.getHP()-t1.getDmg());
                }
                if(t2.getHP() == 0){
                    t2.setLives(t2.getLives() - 1);
                    t2.setX(xr);
                    t2.setY(yr);
                    t2.setHP(100);
                    t2.setDmg(25);
                }
            }
        }
    }
}

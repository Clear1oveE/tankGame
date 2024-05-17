package src.com.hspedu.tankGame;

import java.util.Vector;

/**
 * @author Clearlove
 * @version 1.0
 * 敌人的坦克
 */
public class EnemyTank extends Tank implements Runnable{
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;
    public EnemyTank(int x, int y, Direction dir) {
        super(x, y, dir);
        Shot shot = new Shot(dir, x, y);
        shots.add(shot);
        Thread thread = new Thread(shot);
        thread.start();

    }
    int count = 0;
    int random = 0;
    public void move(){
        if(count == 0) {
            random = (int) (Math.random() * 4);
        }
        count++;
        if(count==20){
            count = 0;
        }
        //System.out.println(random);
        switch (random){
            case 0:
                this.moveUp();setDir(Direction.FRONT);break;
            case 1:
                this.moveDown();setDir(Direction.BACK);break;
            case 2:
                this.moveLeft();setDir(Direction.LEFT);break;
            case 3:
                this.moveRight();setDir(Direction.RIGHT);break;
        }
    }

    @Override
    public void run() {
        while (true){
            if(isLive && shots.size() < 1){
                Shot shot = new Shot(getDir(), getX(), getY());
                shots.add(shot);
                Thread thread = new Thread(shot);
                thread.start();
            }
            move();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(!isLive){
                break;
            }
        }
    }
}

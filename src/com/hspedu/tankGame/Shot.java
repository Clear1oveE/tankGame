package src.com.hspedu.tankGame;

/**
 * @author Clearlove
 * @version 1.0
 * 射击子弹
 */
public class Shot implements Runnable{

    private Direction dir = Direction.FRONT;
    private int x;
    private int y;
    private int speed = 15;
    private boolean isAlive = true;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Shot(Direction dir, int x, int y) {
        this.dir = dir;
        switch (dir){
            case FRONT:
                this.x = x + 20;
                this.y = y;
                break;
            case RIGHT:
                this.x = x + 60;
                this.y = y + 20;
                break;
            case BACK:
                this.x = x + 20;
                this.y = y + 60;
                break;
            case LEFT:
                this.x = x;
                this.y = y + 20;
                break;
        }

    }

    @Override
    public void run() {
        while(isAlive){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (dir){
                case LEFT :
                    x -= speed;break;
                case RIGHT:
                    x += speed;break;
                case BACK:
                    y += speed;break;
                case FRONT:
                    y -= speed;break;
            }

            //test
            //System.out.println("子弹 x=" + x + "y=" + y);
            if(!(x >= 0 && x <= 1000 && y>=0 && y<= 750 && isAlive)){
                isAlive = false;
                System.out.println("触碰边界，子弹销毁");
                break;
            }
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

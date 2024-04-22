package src.com.hspedu.tankGame;

/**
 * @author Clearlove
 * @version 1.0
 */
public class Tank {
    private int x;
    private int y;
    private Direction dir;
    private int speed = 1;

    public Tank(int x, int y,Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    public void moveUp(){
        y-=speed;
    }
    public void moveDown(){
        y+=speed;
    }

    public void moveLeft(){
        x-=speed;
    }
    public void moveRight(){
        x+=speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
}

package src.com.hspedu.tankGame;

import java.util.Vector;

/**
 * @author Clearlove
 * @version 1.0
 */
public class MyTank extends Tank{
    private Shot shot = null;
    Vector<Shot> shots = new Vector<>();
    public MyTank(int x, int y, Direction dir) {
        super(x, y, dir);
    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }

    public void shotEnemyTank(){
//        switch (getDir()){
//            case FRONT:
//                shot = new Shot(getDir(),getX()+20,getY());
//                break;
//            case RIGHT:
//                shot = new Shot(getDir(),getX()+60,getY()+20);
//                break;
//            case BACK:
//                shot = new Shot(getDir(),getX()+20,getY()+60);
//                break;
//            case LEFT:
//                shot = new Shot(getDir(),getX(),getY()+20);
//                break;
//        }
        shot = new Shot(getDir(),getX(),getY());
        shots.add(shot);
        Thread thread = new Thread(shot);
        thread.start();
    }


}

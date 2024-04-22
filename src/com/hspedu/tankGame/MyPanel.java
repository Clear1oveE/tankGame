package src.com.hspedu.tankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author Clearlove
 * @version 1.0
 * 坦克大战的绘图区域
 */
public class MyPanel extends JPanel implements KeyListener {
    //我的坦克
    MyTank myTank = null;
    //敌人的坦克，多线程用Vector储存
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //敌人的数量
    int enemySize = 3;

    public MyPanel(){
        //初始化玩家坦克的位置
        myTank = new MyTank(100,100,Direction.FRONT);
        myTank.setSpeed(5);
        //初始化敌人的坦克
        for (int i = 0; i < 3; i++) {
            enemyTanks.add(new EnemyTank(100*(i + 1),0,Direction.BACK));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //
        g.fillRect(0,0,1000,750);
        drawTank(myTank.getX(), myTank.getY(),g,myTank.getDir(),TankType.MYTANK);
        //drawTank(myTank.getX()+60, myTank.getY(),g,Direction.RIGHT,TankType.ENEMYTANK);
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDir(),TankType.ENEMYTANK);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            myTank.setDir(Direction.FRONT);
            myTank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            myTank.setDir(Direction.BACK);
            myTank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            myTank.setDir(Direction.LEFT);
            myTank.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            myTank.setDir(Direction.RIGHT);
            myTank.moveRight();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     *
     * @param x 坦克的左上角x坐标
     * @param y 坦克的左上角y坐标
     * @param g 画笔
     * @param direct 坦克的方向
     * @param type 坦克类型
     */
    public void drawTank(int x,int y,Graphics g,Direction direct,TankType type){

        //根据坦克类型设置颜色
        switch (type){
            case MYTANK:
                g.setColor(Color.cyan);break;
            case ENEMYTANK:
                g.setColor(Color.yellow);
        }

        switch (direct){
            case FRONT:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+10, y+10, 20,40,false);
                g.fill3DRect(x+30, y, 10,60,false);
                g.fillOval(x+10, y+20,20,20 );
                g.drawLine(x+20,y,x+20,y+30);
                break;
            case BACK:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+10, y+10, 20,40,false);
                g.fill3DRect(x+30, y, 10,60,false);
                g.fillOval(x+10, y+20,20,20 );
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case RIGHT:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+10, y+10, 40,20,false);
                g.fill3DRect(x, y+30, 60,10,false);
                g.fillOval(x+20, y+10,20,20 );
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case LEFT:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+10, y+10, 40,20,false);
                g.fill3DRect(x, y+30, 60,10,false);
                g.fillOval(x+20, y+10,20,20 );
                g.drawLine(x+30,y+20,x,y+20);
                break;

        }

    }

}

enum TankType{
    MYTANK(0),ENEMYTANK(1);

    int type;

    TankType(int type){
        this.type = type;
    }
}
enum Direction{
    FRONT(0),BACK(1),LEFT(2),RIGHT(3);

    int dir;

    Direction(int dir) {
        this.dir = dir;
    }
}

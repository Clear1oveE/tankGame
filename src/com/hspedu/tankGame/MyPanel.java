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
public class MyPanel extends JPanel implements KeyListener ,Runnable{
    //我的坦克
    MyTank myTank = null;
    //敌人的坦克，多线程用Vector储存
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //敌人的数量
    int enemySize = 3;
    //存放炸弹
    Vector<Boom> booms = new Vector<>();
    //定义三张爆炸图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    public MyPanel(){
        //初始化玩家坦克的位置
        myTank = new MyTank(500,100,Direction.FRONT);
        myTank.setSpeed(15);
        //初始化敌人的坦克
        for (int i = 0; i < enemySize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0, Direction.BACK);
            enemyTanks.add(enemyTank);
            enemyTank.setSpeed(5);
            new Thread((enemyTank)).start();

        }
        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("./bomb_1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("./bomb_2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("./bomb_3.png"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //
        g.fillRect(0,0,1000,750);
        if(myTank != null && myTank.isLive) {
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDir(), TankType.MYTANK);
        }//drawTank(myTank.getX()+60, myTank.getY(),g,Direction.RIGHT,TankType.ENEMYTANK);
//        if(myTank.getShot()!=null && myTank.getShot().isAlive()){
//            g.draw3DRect(myTank.getShot().getX(),myTank.getShot().getY(),1,1,false);
//        }
        for (int i = 0; i < myTank.shots.size(); i++) {
            //System.out.println(myTank.shots.size());
            Shot shot = myTank.shots.get(i);
            if(shot != null && shot.isAlive()){
                g.draw3DRect(shot.getX(), shot.getY(), 1,1,false);
            }else {
                myTank.shots.remove(shot);
            }
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < booms.size(); i++) {
            Boom boom = booms.get(i);
            if(boom.life> 6){
                g.drawImage(image1,boom.x,boom.y,60,60,this);
            }else if(boom.life>3){
                g.drawImage(image2,boom.x,boom.y,60,60,this);
            }else{
                g.drawImage(image3,boom.x,boom.y,60,60,this);
            }
            boom.lifeDown();
            if(boom.life == 0){
                booms.remove(boom);
            }
        }
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if(enemyTank.isLive){
                drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDir(),TankType.ENEMYTANK);
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if(shot.isAlive()){
                        g.draw3DRect(shot.getX(),shot.getY(),1,1,false);

                    }else {
                        enemyTank.shots.remove(shot);
                    }
                }
            }

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
        } else if (e.getKeyCode() == KeyEvent.VK_J) {
//            if(myTank.getShot() == null || !myTank.getShot().isAlive()){
//                myTank.shotEnemyTank();
//            }
            myTank.shotEnemyTank();

        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    //编写方法，判断我方的子弹是否击中敌人坦克
    public void hitEnemy(){
        for (int i = 0; i < myTank.shots.size(); i++) {
            Shot shot = myTank.shots.get(i);
            if(shot != null && shot.isAlive()){
                for (int j = 0; j < enemyTanks.size(); j++) {
                    EnemyTank enemyTank = enemyTanks.get(j);
                    hitTank(shot,enemyTank);
                }
            }
        }
    }
    public void hitMyTank(){
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                if(myTank.isLive && shot.isAlive()){
                    hitTank(shot,myTank);
                }

            }
        }
    }
    public  void hitTank(Shot s ,Tank enemyTank){
        switch (enemyTank.getDir()){
            case BACK :
            case FRONT:
                if(s.getX() > enemyTank.getX() && s.getX() < enemyTank.getX() + 40
                        && s.getY() > enemyTank.getY() && s.getY() < enemyTank.getY() + 60 ){
                    s.setAlive(false);
                    enemyTank.isLive = false;
                    Boom boom = new Boom(enemyTank.getX(), enemyTank.getY());
                    booms.add(boom);
                    enemyTanks.remove(enemyTank);
                }
                break;
            case LEFT:
            case RIGHT:
                if(s.getX() > enemyTank.getX() && s.getX() < enemyTank.getX() + 60
                && s.getY() > enemyTank.getY() && s.getY() < enemyTank.getY() + 40){
                    s.setAlive(false);
                    enemyTank.isLive = false;
                    Boom boom = new Boom(enemyTank.getX(), enemyTank.getY());
                    booms.add(boom);
                    enemyTanks.remove(enemyTank);
                }
        }
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

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hitEnemy();
            hitMyTank();
            //判断是否击中敌人坦克

//            for (int i = 0; i < enemyTanks.size(); i++) {
//                //System.out.println(111);
//                EnemyTank enemyTank = enemyTanks.get(i);
//                enemyTank.move();
//            }
            this.repaint();
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

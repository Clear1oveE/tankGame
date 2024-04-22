package src.com.hspedu.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Clearlove
 * @version 1.0
 * 练习小球通过键盘控制移动
 */
public class BallMove extends JFrame  {
    MyPanel mp = null;
    public static void main(String[] args) {
        BallMove ballMove = new BallMove();
    }


    public BallMove(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400,300);
        //可以监听键盘事件
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}

class MyPanel extends JPanel implements KeyListener{

    //为了让小球可以移动把坐标设置成变量

    int x = 10;
    int y = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,20,20);
    }

    //有字符输出时，该方法就会触发
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //某个键按下触发
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println((char)e.getKeyCode() + "被按下");

        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            y++;
        }
        this.repaint();

    }

    //某个键松开触发
    @Override
    public void keyReleased(KeyEvent e) {

    }
}

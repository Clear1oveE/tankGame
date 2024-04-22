package src.com.hspedu.draw;

import javax.swing.*;
import java.awt.*;

/**
 * @author Clearlove
 * @version 1.0
 * 练习如何在面板上画出圆形
 */
public class DrawCircle extends JFrame{
    //JFrame对应窗口，理解成是一个画框，画板要放到画框里

    //定义画板
    private MyPanel mp = null;
    public static void main(String[] args) {
        DrawCircle drawCircle = new DrawCircle();
    }

    public DrawCircle(){
        //初始化画板
        mp = new MyPanel();
        //把画板放入画框
        this.add(mp);
        //设置窗口大小
        this.setSize(400,300);
        //点击小叉退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MyPanel extends JPanel{

    //MyPanel对象是一个画板
    //Graphics g 把g理解成画笔

    @Override
    public void paint(Graphics g) {//绘图方法
        //在窗口初始化/大小变化/repaint调用
        super.paint(g);//调用父类方法完成初始化
        g.drawOval(10,10,100,100);


    }
}

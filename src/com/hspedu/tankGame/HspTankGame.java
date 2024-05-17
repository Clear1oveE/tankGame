package src.com.hspedu.tankGame;

import javax.swing.*;

/**
 * @author Clearlove
 * @version 1.0
 */
public class HspTankGame extends JFrame {

    MyPanel mp = null;
    public static void main(String[] args) {
        HspTankGame hspTankGame = new HspTankGame();
    }
    public HspTankGame(){

        mp = new MyPanel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

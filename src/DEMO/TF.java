package DEMO;
//

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;


public class TF extends JFrame {
    public static void main(String[] args) {

        TF tf = new TF();

    }

    MyPanle mp = null;

    public TF() {
        mp = new MyPanle();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        Thread t2 = new Thread(mp);
        t2.start();
    }
}


//定义一个MY PANEL 用于绘图
class MyPanle extends JPanel implements KeyListener, Runnable {
    //定义一个我的坦克
    Me me = null;
    Vector<Enemy> ETS = null;
    Enemy et = null;
    int EnSize = 3;


    public MyPanle() {
        me = new Me(10, 200);
        ETS = new Vector<Enemy>();
        for (int i = 0; i < EnSize; i++) {
            et = new Enemy((i + 1) * 50, 0);
            ETS.add(et);
            et.setColor(0);
            et.setDirect(2);
        }

    }

    public void paint(Graphics g) {
        super.paint(g);
        int x = getX();
        int y = getY();
        me.setColor(1);
        me.setSpeed(5);
        me.bullet.setSpeed(10);
        this.drawTank(me.getX(), me.getY(), g, me.getDirect(), me.getColor());
        for (int i = 0; i < me.bullets.size(); i++) {
            Bullet mybullet = this.me.bullets.get(i);
            if (mybullet != null && mybullet.isLive ) {
                g.draw3DRect(mybullet.x, mybullet.y, 1, 1, false);
            }
            if (!mybullet.isLive ) {
                me.bullets.remove(mybullet);
            }
        }
        for (int i = 0; i < ETS.size(); i++) {
            et = ETS.get(i);
            if (et.isLive) {
                this.drawTank(et.x, et.y, g, et.getDirect(), et.getColor());
            }
        }

    }

    private void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                //敌方
                break;
            case 1:
                g.setColor(Color.red);
                //自己
                break;
        }

        switch (direct) {
            case 0:
                //方向向上
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.drawOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 15, x + 10, y - 5);
                break;
            case 1:
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 15, y + 10, x + 35, y + 10);
                break;
            case 2:
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.drawOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 15, x + 10, y + 35);
                break;
            case 3:
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 15, y + 10, x - 5, y + 10);
                break;
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            this.me.setDirect(0);
            this.me.moveup();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            this.me.setDirect(1);
            this.me.moveright();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            this.me.setDirect(2);
            this.me.movedown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            this.me.setDirect(3);
            this.me.moveleft();
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            if (this.me.bullets.size() <= 5) {
                this.me.shot();
            }
        }
        repaint();
    }


    public void keyReleased(KeyEvent e) {

    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < me.bullets.size(); i++) {
                Bullet b = me.bullets.get(i);
                if (b.isLive) {
                    for (int j = 0; j < ETS.size(); j++) {
                        Enemy et = ETS.get(j);
                        if (et.isLive) {
                            et.hittank(b, et);
                        }
                    }
                }
            }
            repaint();
        }
    }
}


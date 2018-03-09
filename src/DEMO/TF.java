package DEMO;
//

import javax.swing.*;
import java.awt.*;

public class TF extends JFrame {
    public static void main(String[] args) {

        TF tf = new TF();

    }

    MyPanle mp = null;

    public TF() {
        mp = new MyPanle();
        this.add(mp);
        mp.setBackground(Color.BLACK);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

//定义一个MY PANEL 用于绘图
class MyPanle extends JPanel implements {
    //定义一个我的坦克
    Me me = null;
    public MyPanle() {
        me = new Me(10, 10);

    }

    public void paint(Graphics g) {
        super.paint(g);
        int x = getX();
        int y = getY();
        this.drawTank(me.getX(),me.getY(),g,0,1);

    }

    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                //敌方
            case 1:
                g.setColor(Color.red);
                //自己
                break;
        }

        switch (direct) {
            case 0:
                //方向向上
                g.fill3DRect(me.x, me.y, 5, 30, false);
                g.fill3DRect(me.x + 15, me.y, 5, 30, false);
                g.fill3DRect(me.x + 5, me.y + 5, 10, 20, false);
                g.drawOval(me.x + 5, me.y + 10, 10, 10);
                g.drawLine(me.x + 10, me.y + 15, me.x + 10, me.y + 5);
                break;
        }
    }
}

//坦克类
class Tank {

    int x = 0;

    int y = 0;

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

    public Tank(int x, int y) {
        this.x = x;

        this.y = y;
    }
}

//驾驶坦克
class Me extends Tank {

    public Me(int x, int y) {

        super(x, y);

    }
}
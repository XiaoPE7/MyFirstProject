package DEMO;

import java.util.Vector;

//坦克类
class Tank {

    int x = 0;
    int y = 0;
    int direct = 0;
    int speed = 1;
    int color = 1;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }


    //0表示向上，1右，2下，3左
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

//驾驶的坦克
class Me extends Tank {
    Bullet bullet = null;
    Vector<Bullet> bullets = null;

    public Me(int x, int y) {
        super(x, y);
        bullet = new Bullet(x, y, direct);
        bullets = new Vector<Bullet>();

    }


    public void moveup() {
        y -= speed;
    }

    public void movedown() {
        y += speed;
    }

    public void moveleft() {
        x -= speed;
    }

    public void moveright() {
        x += speed;
    }

    public void shot() {
        switch (this.direct) {
            case 0:
                bullet = new Bullet(x + 10, y - 5, 0);
                bullets.add(bullet);
                break;
            case 1:
                bullet = new Bullet(x + 35, y + 10, 1);
                bullets.add(bullet);
                break;
            case 2:
                bullet = new Bullet(x + 10, y + 35, 2);
                bullets.add(bullet);
                break;
            case 3:
                bullet = new Bullet(x - 5, y + 10, 3);
                bullets.add(bullet);
                break;
        }
        Thread t = new Thread(bullet);
        t.start();
    }
}


class Enemy extends Tank implements Runnable {
    boolean isLive = true;

    public Enemy(int x, int y) {
        super(x, y);
    }

    void hittank(Bullet b, Enemy e) {
        System.out.println("b.x;" + b.x + "b.y" + b.y + "e.x" + e.x + "e.y" + e.y);
        switch (e.direct) {

            case 0:
            case 2:
                if (b.x >=e.x && b.x <= e.x + 20 && b.y >= e.y && b.y <= e.y + 30) {
                    b.isLive = false;
                    e.isLive = false;
                }
                break;
            case 1:
            case 3:
                if (b.x >= e.x && b.x <= e.x + 30 && b.y >= e.y && b.y <= e.y + 20) {
                    b.isLive = false;
                    e.isLive = false;
                }
                break;
        }

    }

    @Override
    public void run() {
    }
}


class Bullet implements Runnable {
    int x;
    int y;
    int speed = 3;
    int direct;
    boolean isLive = true;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Bullet(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }


    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            if (x > 400 || x < 0 || y > 300 || y < 0) {
                isLive = false;
                break;
            }
        }
    }
}


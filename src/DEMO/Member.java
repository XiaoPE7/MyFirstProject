package DEMO;

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

    public Me(int x, int y) {
        super(x, y);
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
}

class Enemy extends Tank {
    public Enemy(int x,int y){
        super(x,y);
    }

}
package com.inno.ildmi;

/**
 * Created by mjazz on 02.08.2017.
 */
public class Wall {
    private int x1, y1, x2, y2;

    @Override
    public boolean equals(Object obj) {
        Wall temp = (Wall)obj;
        if(temp.getX1() == this.x1 && temp.getX2() == this.x2 && temp.getY1() == this.y1 && temp.getY2() == this.y2) return true;
        if(temp.getX1() == this.x2 && temp.getX2() == this.x1 && temp.getY1() == this.y2 && temp.getY2() == this.y1) return true;
        return false;
    }

    public Wall(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}

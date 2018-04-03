package com.assignment.utils;

import java.awt.*;

/**
 * Created by manisharathore on 29/03/18.
 */
public class BattlePoint extends Point {
    private int x, y;
    private String pos;

    public BattlePoint(String pos, int x, int y) {
        this.x = x;
        this.y = y;
        this.pos = pos;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    @Override
    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void printCordinates() {
        System.out.println("[x="+this.getX()+", y="+this.getY()+"]");
    }
}

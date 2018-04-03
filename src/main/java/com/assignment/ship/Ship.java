package com.assignment.ship;

import com.assignment.utils.BattlePoint;
import com.assignment.utils.Position;
import com.assignment.utils.Utils;


public class Ship {
    private String name;
    private String type;
    private int width;
    private int height;
    private BattlePoint startPoint;
    private BattlePoint endPoint;
    private int size;
    private int livesLeft;
    private boolean isSunk;
    private Position position;

    public Ship(String type, String name, int width, int height, String pos) {
        this.type = type.toLowerCase();
        this.name = name;
        this.width = width;
        this.height = height;

        this.startPoint = Utils.getBattlePointFromString(pos);
        int endPointX = (int) this.startPoint.getX() + (width - 1);
        int endPointY = (int) this.startPoint.getY() + (height-1);

        this.endPoint = new BattlePoint("", endPointX, endPointY);

        this.position = new Position(startPoint, endPoint);
        this.livesLeft = width * height;
        this.isSunk = false;
    }

    public String getName() {
        return name;
    }

    public int getShipStrengthValue() {
        return (this.type).equals("q") ? 2 : 1;
    }

    public int getSize() {
        return size;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public boolean isSunk() {
        return livesLeft == 0;
    }

    public void setSunk(boolean sunk) {
        isSunk = sunk;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void shipWasHit() {
        if(livesLeft == 0) {
            isSunk = true;
            //System.out.println("You sunk the " + name);
            return;
        }
        livesLeft--;
    }
}
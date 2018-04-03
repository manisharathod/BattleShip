package com.assignment.battleGround;

import com.assignment.constants.Constants;
import com.assignment.ship.Ship;
import com.assignment.utils.BattlePoint;
import com.assignment.utils.Position;
import com.assignment.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class BattleGround {
    private List<Ship> ships = new ArrayList<>();
    private char[][] battleGround;
    private int width;
    private int height;

    /**
     * Constructor
     */
    public BattleGround(int width, int height) {

        this.width = width;
        this.height = height;
        battleGround = new char[width][height];

        //init battle ground with default icons
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                battleGround[i][j] = Constants.BATTLEGROUND_ICON;
            }
        }
    }

    /**
     * target point to attack
     *
     * @param point the point
     * @return ship
     */
    public Ship targetShip(BattlePoint point) {
        boolean isHit = false;
        Ship hitShip = null;
        for(int i = 0; i < ships.size(); i++) {
            Ship ship = ships.get(i);
            if(ship.getPosition() != null) {
                //check if point lie between a ship coordinates
                if(Utils.isPointBetween(point, ship.getPosition())) {
                    isHit = true;
                    hitShip = ship;
                    break;
                }
            }
        }
        //check if it is alreday a hit or miss
        char charAtHitPoint = getIconOnPoint(point);
        if(charAtHitPoint == Constants.SHIP_IS_HIT_ICON || charAtHitPoint == Constants.SHOT_MISSED_ICON) {
            return null;
        }

        // if hit mark as hit or a miss
        final char result = isHit ? Constants.SHIP_IS_HIT_ICON : Constants.SHOT_MISSED_ICON;
        updateShipOnbattleGround(point, result);

        return (isHit) ? hitShip : null;
    }

    /**
     * Place ships on battleGround.
     */
    public boolean addShipOnBattleGround(Ship ship) {

        this.ships.add(ship);

        Position position = ship.getPosition();
        if(!isPositionOccupied(position)) {
            drawShipOnbattleGround(ship);
            ship.setPosition(position);
        } else {
            System.out.println("A ship in that position already exists - try again");
            return false;
        }
        return true;
    }

    /*
        Mark point as targeted
     */
    private void updateShipOnbattleGround(BattlePoint point, final char result) {
        int x = (int) point.getX();
        int y = (int) point.getY();

        if(result == Constants.SHIP_IS_HIT_ICON) {
            char charAtHitPoint = battleGround[x][y];
            int shipStrength = Character.getNumericValue(charAtHitPoint);
            shipStrength -= 1;

            if(!(shipStrength > 0)) {
                battleGround[x][y] = Constants.SHIP_IS_HIT_ICON;
            } else {
                battleGround[x][y] = (char) (shipStrength + '0');
            }
        } else {
            battleGround[x][y] = result;
        }
        //printbattleGround();
    }

    /*
        check if point is already visited
    */
    private char getIconOnPoint(BattlePoint point) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        return battleGround[x][y];
    }

    /**
     *
     * @param position
     * @return
     */
    private boolean isPositionOccupied(Position position) {
        boolean isOccupied = false;
        BattlePoint from = position.getFrom();
        BattlePoint to = position.getTo();

        outer:
        for(int i = (int) from.getY(); i < to.getY() && i < width; i++) {
            for(int j = (int) from.getX(); j < to.getX() && j < height; j++) {
                if(battleGround[i][j] != Constants.BATTLEGROUND_ICON) {
                    isOccupied = true;
                    break outer;
                }
            }
        }

        return isOccupied;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public char[][] getBattleGround() {
        return battleGround;
    }

    public void setBattleGround(char[][] battleGround) {
        this.battleGround = battleGround;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @param ship
     */
    private void drawShipOnbattleGround(Ship ship) {
        Position position = ship.getPosition();
        BattlePoint from = position.getFrom();
        BattlePoint to = position.getTo();

        char c = (char) (ship.getShipStrengthValue()  + '0' );
        for(int i = (int) from.getY(); i <= to.getY(); i++) {
            for(int j = (int) from.getX(); j <= to.getX(); j++) {
                battleGround[i][j] =  c;
            }
        }
        printbattleGround();
    }


    /**
     * Print battleGround.
     */
    public void printbattleGround() {
        System.out.print("\t");

        for(int i = 0; i < this.width; i++) {
            System.out.print(i+1 + "\t");
        }

        System.out.println();

        for(int i = 0; i < this.width; i++) {
            System.out.print(Constants.BATTLEGROUND_LETTERS[i] + "\t");
            for(int j = 0; j < this.height; j++) {
                System.out.print(battleGround[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
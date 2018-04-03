package com.assignment.player;

import com.assignment.battleGround.BattleGround;
import com.assignment.ship.Ship;
import com.assignment.utils.BattlePoint;
import com.assignment.utils.Utils;

import java.util.*;


public class Player {
    private int id;
    private int nOfShips;
    private BattleGround board;
    private List<BattlePoint> listOfMissiles;
    private Scanner scanner;

    /**
     * Instantiates a new Player.
     *
     * @param id the id
     */
    public Player(int id) {
        this.scanner = new Scanner(System.in);
        this.id = id;
        this.listOfMissiles = new ArrayList<>();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets nOfShips.
     *
     * @return the nOfShips
     */
    public int getnOfShips() {
        return nOfShips;
    }

    /**
     * Gets nOfMissiles left.
     *
     * @return the nOfShips
     */
    public int getnOfMissiles() {
        return listOfMissiles.size();
    }

    /**
     * Set nOfShips.
     *
     * @return the nOfShips
     */
    public void setnOfShips(int nOfShips) {
        this.nOfShips = nOfShips;
    }

    /**
     * Decrement no of ships by one.
     */
    public void decrementNOfShipsByOne() {
        nOfShips--;
    }

    public void setBattleGround(BattleGround battleGround) {
        this.board = battleGround;
    }
    /**
     * Turn to play.
     *
     * @param opponent the opponent
     */
    public boolean turnToPlay(Player opponent) {
        if(listOfMissiles.size() > 0) {
            BattlePoint  point = listOfMissiles.get(0);
            listOfMissiles.remove(0);

            return attack(point, opponent);
        }
        System.out.printf("Player-%d has no more missiles left to launch%n", id);
        return false;
    }

    public void addMissiles(String[] listOfMissiles) {
        for(int i=0; i < listOfMissiles.length; i++) {

            BattlePoint point = Utils.getBattlePointFromString(listOfMissiles[i]);
            this.listOfMissiles.add(point);
        }
    }

    /**
     * Attack
     *
     * @param point
     * @param opponent
     */
    private boolean attack(BattlePoint point, Player opponent) {
        Ship ship = opponent.board.targetShip(point);
        boolean isShipHit = (ship != null) ? true : false;

        if(isShipHit) {
            ship.shipWasHit();
            if(ship.isSunk() == true) {
                opponent.decrementNOfShipsByOne();
            }
        }
        System.out.printf("Player-%d fires a missile with target %s (%d, %d)", id, point.getPos(), (int) point.getX(), (int) point.getY());
        System.out.println(" which got " + ((isShipHit) ? "hit" : "miss")+ opponent.getnOfShips());
        return isShipHit;
    }
}
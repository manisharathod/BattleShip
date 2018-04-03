package com.assignment.battleShipGame;

import com.assignment.battleGround.BattleGround;
import com.assignment.player.Player;
import com.assignment.ship.Ship;

import java.security.InvalidParameterException;
import java.util.Scanner;


public class BattleShipGame {
    private Player[] players;

    /*
     Initialize game and players
     */
    public BattleShipGame() {
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        this.players = new Player[]{
                player1,
                player2
        };
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter battlship width and heights: ");
        String[] inputs = (sc.nextLine()).split(" ");
        int width  = Integer.parseInt(inputs[0]);
        int height = Integer.parseInt(inputs[1]);

        if(!(width > 0 && height > 0)) {
            throw new InvalidParameterException("width height cannot be zero!");
        }

        BattleGround b1 = new BattleGround(width, height);
        BattleGround b2 = new BattleGround(width, height);
        System.out.println("Enter nOf ships for each player: ");
        int nOfShips    = Integer.parseInt(sc.nextLine());
        if(!(nOfShips > 0)) {
            throw new InvalidParameterException("No of ships must be grt zero");
        }
        player1.setnOfShips(nOfShips);
        player2.setnOfShips(nOfShips);

        for (int i =0; i < nOfShips; i++) {
            System.out.println("Enter ship type, width, height, position: ");

            String[] shipInput =  (sc.nextLine()).split(" ");
            String battleshipType = (shipInput[0]).toLowerCase();
            if(!battleshipType.equals("p") && !battleshipType.equals("q")) {
                throw new InvalidParameterException("Ship only can be of type \"P\" or \"Q\"");
            }

            width = Integer.parseInt(shipInput[1]);
            height = Integer.parseInt(shipInput[2]);

            if(width > b1.getWidth() || height > b1.getHeight()) {
                throw new InvalidParameterException("Ship size can only be less the board size");
            }

            b1.addShipOnBattleGround(new Ship(battleshipType, "Ship"+i, width, height, shipInput[3]));
            b2.addShipOnBattleGround(new Ship(battleshipType, "Ship"+i, width, height, shipInput[4]));
        }
        player1.setBattleGround(b1);
        player2.setBattleGround(b2);

        System.out.println("Enter sequence of attacks for player 1");
        player1.addMissiles((sc.nextLine()).split(" "));
        System.out.println("Enter sequence  of attacks for player 2");
        player2.addMissiles((sc.nextLine()).split(" "));
    }

    /*
    Start the game
     */
    public void start() {
        int i = 0;
        int j = 1;
        int size = players.length;
        Player player = null;

        while(players[0].getnOfMissiles() > 0 || players[1].getnOfMissiles() > 0) {
            boolean isShipHit  = players[i % size].turnToPlay(players[j % size]);
            if(isShipHit == false) {
                i++;
                j++;
            }
            player = (players[0].getnOfShips() < players[1].getnOfShips()) ?
                    players[1] :
                    players[0];
        }

        System.out.printf("Player-%d won the battle",player.getId());
    }
}
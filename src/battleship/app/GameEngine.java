package battleship.app;

import battleship.exceptions.*;
import battleship.model.Player;
import battleship.ui.Display;

import java.util.Scanner;

class GameEngine {
    Scanner sc = new Scanner(System.in);
    Player player1;

    void startGame() {
        placeShip();
        System.out.println(Display.gameStart());
        while(checkGameState()) {
            fire();
        }
        sc.close();
    }

    private void placeShip() {
        player1 = new Player();
        System.out.println(player1.getBoardAsString());

        for(int i = 0; i < 5; i++) {
            boolean valid = false;
            while(!valid) {
                System.out.println(Display.placeShip(i));
                String shipPositions = sc.nextLine();
                try {
                    player1.createFleet(shipPositions, i);
                    System.out.println(player1.getLengthOfShipAsString(player1.getShip(i)));
                    System.out.println(player1.getPartsOfShipAsString(player1.getShip(i)));
                    System.out.println(player1.getBoardAsString());
                    valid = true;
                } catch (NotAlignedShipException | NotInsideTheBoardException | InvalidShipLengthException |
                         TooCloseToAnotherShipException | ShipOverlapException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void fire() {
        System.out.println(player1.getFogWarBoardAsString());

        boolean valid = false;
        while(!valid) {
            System.out.println(Display.takeAShot());
            String fireShot = sc.nextLine();
            try {
                boolean isFireOnShip = player1.fire(fireShot);
                System.out.println(player1.getFogWarBoardAsString());
                System.out.println(Display.isFireOnShip(isFireOnShip));
                valid = true;
            } catch (NotInsideTheBoardException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean checkGameState() {
        boolean isShipSank = player1.isShipSank();
        if(isShipSank) {
            boolean isAlive = player1.isAlive();
            if(isAlive) {
                System.out.println(Display.sankShip());
            } else {
                System.out.println(Display.win());
            }
            return isAlive;
        }
        return true;
    }
}

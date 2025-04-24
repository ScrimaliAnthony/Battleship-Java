package battleship.app;

import battleship.exceptions.*;
import battleship.model.Player;
import battleship.ui.Display;

import java.util.Scanner;

class GameEngine {
    Scanner sc = new Scanner(System.in);
    Player[] players = new Player[2];

    void startGame() {
        for(int i = 0; i < 2; i++) {
            System.out.println(Display.playerCall(i + 1));
            placeShip(i);
        }

        boolean isContinue = true;
        while(isContinue) {
            for(int i = 0; i < 2; i++) {
                System.out.println(Display.gameStart(players[i].getName()));
                fire(i);

                isContinue = checkGameState(i);
                if(!isContinue) {
                    break;
                }
            }
        }
        sc.close();
    }

    private void placeShip(int num) {
        players[num] = new Player(num);

        System.out.println(players[num].getBoardAsString());

        for(int i = 0; i < 5; i++) {
            boolean valid = false;
            while(!valid) {
                System.out.println(Display.placeShip(i));
                String shipPositions = sc.nextLine();
                try {
                    players[num].createFleet(shipPositions, i);
                    System.out.println(players[num].getLengthOfShipAsString(players[num].getShip(i)));
                    System.out.println(players[num].getPartsOfShipAsString(players[num].getShip(i)));
                    System.out.println(players[num].getBoardAsString());
                    valid = true;
                } catch (NotAlignedShipException | NotInsideTheBoardException | InvalidShipLengthException |
                         TooCloseToAnotherShipException | ShipOverlapException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void fire(int num) {
        System.out.println(players[num].getFogWarBoardAsString());

        boolean valid = false;
        while(!valid) {
            System.out.println(Display.takeAShot());
            String fireShot = sc.nextLine();
            try {
                boolean isFireOnShip = players[num].fire(fireShot);
                System.out.println(players[num].getFogWarBoardAsString());
                System.out.println(Display.isFireOnShip(isFireOnShip));
                valid = true;
            } catch (NotInsideTheBoardException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean checkGameState(int num) {
        boolean isShipSank = players[num].isShipSank();
        if(isShipSank) {
            boolean isAlive = players[num].isAlive();
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

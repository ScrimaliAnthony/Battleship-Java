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
            createPlayers(i);
            createGameBoards(i);

            System.out.println(Display.playerCall(i + 1));
            System.out.println(players[i].getBoardAsString());
            placeShip(i);
            System.out.println("Press Enter and pass the move to another player");
            sc.nextLine();
        }

        boolean isContinue = true;
        while(isContinue) {
            for(int i = 0; i < 2; i++) {
                int playerEnnemy = i == 0 ? 1 : 0;
                System.out.println(Display.gameStart(players[i].getName()));
                fire(i, playerEnnemy);

                isContinue = checkGameState(playerEnnemy);
                if(!isContinue) {
                    break;
                }
                System.out.println("Press Enter and pass the move to another player");
                sc.nextLine();
            }
        }
        sc.close();
    }

    private void createPlayers(int num) {
        players[num] = new Player(num);
    }

    private void createGameBoards(int num) {
        players[num].createGameBoards();
    }

    private void placeShip(int num) {
        for(int i = 0; i < 5; i++) {
            boolean valid = false;
            while(!valid) {
                System.out.println(Display.placeShip(i));
                String shipPositions = sc.nextLine();
                try {
                    players[num].createFleet(shipPositions, i);
                    System.out.println(players[num].getBoardAsString());
                    valid = true;
                } catch (NotAlignedShipException | NotInsideTheBoardException | InvalidShipLengthException |
                         TooCloseToAnotherShipException | ShipOverlapException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void fire(int num, int playerEnnemy) {
        System.out.print(players[num].getFogWarBoardAsString());
        System.out.println("---------------------");
        System.out.println(players[num].getBoardAsString());

        boolean valid = false;
        while(!valid) {
            System.out.println(Display.takeAShot());
            String fireShot = sc.nextLine();
            try {
                int[] fire = players[playerEnnemy].fire(fireShot);
                boolean isFireOnShip = players[playerEnnemy].isFireOnShip(fire);
                players[num].fireOnFogWarGameBoard(fire, isFireOnShip);
                System.out.println(Display.isFireOnShip(isFireOnShip));
                valid = true;
            } catch (NotInsideTheBoardException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean checkGameState(int playerEnnemy) {
        boolean isShipSank = players[playerEnnemy].isShipSank();
        if(isShipSank) {
            boolean isAlive = players[playerEnnemy].isAlive();
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

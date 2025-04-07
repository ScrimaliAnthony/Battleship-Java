package battleship.app;

import battleship.exceptions.*;
import battleship.model.Player;
import battleship.ui.Display;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Player player1 = new Player();
        System.out.println(player1.getBoardAsString());

        for(int i = 0; i < 5; i++) {
            boolean valid = false;
            System.out.println(Display.placeShip(i));

            while(!valid) {
                String shipPositions = sc.nextLine();
                try {
                    player1.createShips(shipPositions, i);
                    player1.placeShip(player1.getShip(i));

                    System.out.println(player1.getLengthOfShipAsString(player1.getShip(i)));
                    System.out.println(player1.getPartsOfShipAsString(player1.getShip(i)));
                    System.out.println(player1.getBoardAsString());

                    valid = true;
                } catch (InvalidShipLengthException | NotAlignedShipException | NotInsideTheBoardException |
                         ShipOverlapException | TooCloseToAnotherShipException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        sc.close();
    }
}

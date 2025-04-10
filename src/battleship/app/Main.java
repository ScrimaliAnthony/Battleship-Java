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

        System.out.println("Création des bateaux terminé");

        sc.close();
    }
}

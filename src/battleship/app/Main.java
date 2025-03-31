package battleship.app;

import battleship.model.Player;
import battleship.ui.Display;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Player player1 = new Player();
        System.out.println(player1.getBoardAsString());


        System.out.println(Display.placeShip());
        String shipPositions = sc.nextLine();

        player1.createShips(shipPositions, 5);
        System.out.println(player1.getLengthOfShipAsString(player1.getAircraftCarrier()));
        System.out.println(player1.getPartsOfShipAsString(player1.getAircraftCarrier()));

        sc.close();
    }
}

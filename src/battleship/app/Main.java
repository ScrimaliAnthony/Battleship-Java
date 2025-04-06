package battleship.app;

import battleship.model.Player;
import battleship.ui.Display;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Player player1 = new Player();
        System.out.println(player1.getBoardAsString());

        for(int i = 0; i < 5; i++) {
            System.out.println(Display.placeShip(i));
            String shipPositions = sc.nextLine();

            player1.createShips(shipPositions, i);
            player1.placeShip(player1.getShip(i));

            System.out.println(player1.getLengthOfShipAsString(player1.getShip(i)));
            System.out.println(player1.getPartsOfShipAsString(player1.getShip(i)));
            System.out.println(player1.getBoardAsString());
        }

        sc.close();
    }
}

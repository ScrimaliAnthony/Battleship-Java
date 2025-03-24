package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameBoard gameBoard = new GameBoard(10, 10);
        Player player1 = new Player();

        System.out.println(Display.board(gameBoard.getRow(), gameBoard.getCol(), gameBoard.getBoard()));

        System.out.println(Display.placeShip());
        String shipPositions = sc.nextLine();

        player1.convertInputToCoordinates(shipPositions);
        System.out.println(Display.lengthOfShip(player1.getLength()));

        sc.close();
    }
}

package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameBoard gameBoard = new GameBoard(10, 10);

        System.out.println(Display.board(gameBoard.getRow(), gameBoard.getCol(), gameBoard.getBoard()));
        System.out.println(Display.placeShip());
        String shipPositions = sc.nextLine();
        gameBoard.convertInputToCoordinates(shipPositions);

        sc.close();
    }
}

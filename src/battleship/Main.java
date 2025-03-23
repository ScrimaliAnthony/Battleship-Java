package battleship;

public class Main {

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(10, 10);

        System.out.println(Display.board(gameBoard.getRow(), gameBoard.getCol(), gameBoard.getBoard()));
    }
}

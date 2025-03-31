package battleship.ui;

public class Display {

    public static String board(int row, int col, char[][] board) {
        StringBuilder boardBuilder = new StringBuilder();
        boardBuilder.append(" ");

        for(int i = 1; i <= row; i++) {
            boardBuilder.append(" ").append(i);
        }
        boardBuilder.append(System.lineSeparator());

        for(int i = 0; i < row; i++) {
            char colChar = (char) ('A' + i);
            boardBuilder.append(colChar).append(" ");
            for(int j = 0; j < col; j++) {
                boardBuilder.append(board[i][j]).append(" ");
            }
            boardBuilder.append(System.lineSeparator());
        }

        return boardBuilder.toString();
    }

    public static String placeShip() {
        return "Enter the coordinates of the ship:";
    }

    public static String lengthOfShip(int length) {
        return "Length: " + length;
    }

    public static String partsOfShip(String parts) {
        return "Parts: " + parts;
    }
}
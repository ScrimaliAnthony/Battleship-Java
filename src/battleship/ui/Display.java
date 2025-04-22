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

    public static String placeShip(int i) {
        String[] names = {"Aircraft Carrier (5 cells)", "Battleship (4 cells)", "Submarine (3 cells)", "Cruiser (3 cells)", "Destroyer (2 cells)"};
        return "Enter the coordinates of the " + names[i] + ":";
    }

    public static String lengthOfShip(int length) {
        return "Length: " + length;
    }

    public static String partsOfShip(String parts) {
        return "Parts: " + parts;
    }

    public static String gameStart() {
        return "The game starts!\n";
    }

    public static String takeAShot() {
        return "Take a shot!\n";
    }

    public static String isFireOnShip(boolean isFireOnShip) {
        if(isFireOnShip) {
            return "You hit a ship!\n";
        } else {
            return "You missed!\n";
        }
    }

    public static String sankShip() {
        return "You sank a ship! Specify a new target:";
    }

    public static String win() {
        return "You sank the last ship. You won. Congratulations!";
    }
}
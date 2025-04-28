package battleship.utils;

import battleship.exceptions.*;

public class Validators {

    private Validators() {}

    public static void ShipValidate(int[] indexes, int shipMaxLength, int length, String shipName, char[][] gameBoard,
                                    int[] rowIndex, int[] colIndex, int boardRow, int boardCol)
            throws NotAlignedShipException, NotInsideTheBoardException, InvalidShipLengthException, ShipOverlapException,
            TooCloseToAnotherShipException {

        checkAlignment(indexes);

        for(int index : indexes) {
            checkInsideTheBoard(index);
        }

        checkLengthOfShip(shipMaxLength, length, shipName);

        isShipOverlap(gameBoard, rowIndex, colIndex, length);
        isTooClose(boardRow, boardCol, rowIndex, colIndex, gameBoard, length);
    }

    public static void fireValidate(int[] indexes) throws NotInsideTheBoardException {
        for(int index : indexes) {
            checkInsideTheBoard(index);
        }
    }

    private static void checkAlignment(int[] index) throws NotAlignedShipException {
        if(index[0] != index[2] && index[1] != index[3]) {
            throw new NotAlignedShipException("Error: Your ship must be aligned either horizontally or vertically. Try again:");
        }
    }

    private static void checkInsideTheBoard(int index) throws NotInsideTheBoardException {
        if(index < 0 || index > 9) {
            throw new NotInsideTheBoardException("Error! You entered the wrong coordinates! Try again: (from A1 to J10 inclusive). Try again:");
        }
    }

    private static void checkLengthOfShip(int shipMaxLength, int length, String shipName) throws InvalidShipLengthException {
        if(length != shipMaxLength) {
            throw new InvalidShipLengthException("Error: The length of the "+ shipName + " need to be " + shipMaxLength + " Try again:");
        }
    }

    private static void isShipOverlap(char[][] gameBoard, int[] row, int col[], int length) throws ShipOverlapException {
        for(int i = 0; i < length; i++) {
            if(gameBoard[row[i]][col[i]] == 'O') {
                throw new ShipOverlapException("Error: You can't place a ship on another one. Try again:");
            }
        }
    }

    private static void isTooClose(int boardRow, int boardCol, int rowIndex[], int colIndex[], char[][] gameBoard, int length)
            throws TooCloseToAnotherShipException {
        for(int i = 0; i < length; i++) {
            for (int j = rowIndex[i] - 1; j <= rowIndex[i] + 1; j++) {
                for (int k = colIndex[i] - 1; k <= colIndex[i] + 1; k++) {
                    if (j >= 0 && j < boardRow && k >= 0 && k < boardCol) {
                        if (gameBoard[j][k] == 'O') {
                            throw new TooCloseToAnotherShipException("Error! You placed it too close to another one. Try again:");
                        }
                    }
                }
            }
        }
    }
}

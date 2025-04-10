package battleship.model;

import battleship.exceptions.ShipOverlapException;
import battleship.exceptions.TooCloseToAnotherShipException;

class GameBoard {

    final private char[][] bord;
    final private int row;
    final private int col;

    GameBoard(int row, int col) {
        this.row = row;
        this.col = col;

        bord = new char[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                bord[i][j] = '~';
            }
        }
    }

    void placeShipOnBoard(Ship ship) throws ShipOverlapException, TooCloseToAnotherShipException {
        int[] row = ship.getRowIndex();
        int[] col = ship.getColIndex();

        for(int i = 0; i < ship.getLength(); i++) {
            if(bord[row[i]][col[i]] == 'O') {
                throw new ShipOverlapException("Error: You can't place a ship on another one. Try again:");
            }
            if(isTooClose(row[i], col[i])) {
                throw new TooCloseToAnotherShipException("Error! You placed it too close to another one. Try again:");
            }
        }

        for(int i = 0; i < ship.getLength(); i++) {
            bord[row[i]][col[i]] = 'O';
        }
    }

    private boolean isTooClose(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < this.row && j >= 0 && j < this.col) {
                    if (bord[i][j] == 'O') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    char[][] getBoard() {
        return bord;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }
}

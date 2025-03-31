package battleship.model;

class GameBoard {

    final private char[][] bord;
    final private int row;
    final private int col;

    public GameBoard(int row, int col) {
        this.row = row;
        this.col = col;

        bord = new char[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                bord[i][j] = '~';
            }
        }
    }

    public void placeShipOnBoard(Ship ship) {
        int[] row = ship.getRowIndex();
        int[] col = ship.getColIndex();

        for(int i = 0; i < ship.getLength(); i++) {
            bord[row[i]][col[i]] = 'O';
        }
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

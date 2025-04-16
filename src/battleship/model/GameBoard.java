package battleship.model;

class GameBoard {

    final private char[][] board;
    final private int row;
    final private int col;

    GameBoard(int row, int col) {
        this.row = row;
        this.col = col;

        board = new char[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                board[i][j] = '~';
            }
        }
    }

    void placeShipOnBoard(Ship ship) {
        int[] row = ship.getRowIndex();
        int[] col = ship.getColIndex();

        for(int i = 0; i < ship.getLength(); i++) {
            board[row[i]][col[i]] = 'O';
        }
    }

    boolean isFireOnShip(int[] indexes) {
        int row = indexes[0];
        int col = indexes[1];

        if(board[row][col] == 'O') {
            board[row][col] = 'X';
            return true;
        } else if(board[row][col] == '~') {
            board[row][col] = 'M';
            return false;
        }
        return false;
    }

    char[][] getBoard() {
        return board;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }
}

package battleship.model;

abstract class AbstractGameBoard {

    final private char[][] board;
    final private int row;
    final private int col;

    AbstractGameBoard(int row, int col) {
        this.row = row;
        this.col = col;

        board = new char[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                board[i][j] = '~';
            }
        }
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

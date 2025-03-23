package battleship;

class GameBoard {

    private char[][] bord;
    private int row;
    private int col;

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

    void convertInputToCoordinates(String shipPositions) {
        String[] shipPosition = shipPositions.split(" ");
        int[] rowPosition = new int[2];
        int[] colPosition = new int[2];

        int i = 0;
        do {
            int letter = shipPosition[i].charAt(0);
            rowPosition[i] = letter - 'A';
            i++;
        } while(i < 2);

        i = 0;
        do {
            int number = shipPosition[i].charAt(1);
            colPosition[i] = number - '0' - 1;
            i++;
        } while(i < 2);
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

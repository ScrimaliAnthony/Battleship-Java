package battleship.model;

public class PlayerGameBoard extends AbstractGameBoard{

    PlayerGameBoard(int row, int col) {
        super(row, col);
    }

    void placeShipOnBoard(Ship ship) {
        int[] row = ship.getRowIndex();
        int[] col = ship.getColIndex();

        for(int i = 0; i < ship.getLength(); i++) {
            this.getBoard()[row[i]][col[i]] = 'O';
        }
    }

    boolean isFireOnShip(int[] indexes) {
        int row = indexes[0];
        int col = indexes[1];

        if(this.getBoard()[row][col] == 'O') {
            this.getBoard()[row][col] = 'X';
            return true;
        } else if(this.getBoard()[row][col] == '~') {
            this.getBoard()[row][col] = 'M';
            return false;
        }
        return false;
    }
}

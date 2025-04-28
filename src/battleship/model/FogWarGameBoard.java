package battleship.model;

public class FogWarGameBoard extends AbstractGameBoard{

    FogWarGameBoard(int row, int col) {
        super(row, col);
    }

    void placeFireOnBoard(int[] indexes, boolean isFireOnShip) {
        int row = indexes[0];
        int col = indexes[1];

        if(isFireOnShip) {
            this.getBoard()[row][col] = 'X';
        } else {
            this.getBoard()[row][col] = 'M';
        }
    }
}

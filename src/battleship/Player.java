package battleship;

public class Player {

    private int length = 0;

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

        checkCoordinates(rowPosition, colPosition);
    }

    private void checkCoordinates(int[] rowPosition, int[] colPosition) {
        if(rowPosition[0] == rowPosition[1]) {
            lengthOfShip(colPosition);
        } else if(colPosition[0] == colPosition[1]) {
            lengthOfShip(rowPosition);
        } else {
            System.out.println("Error");
            System.exit(1);
        }
        if(rowPosition[0] > 10 || rowPosition[1] > 10 || colPosition[0] > 10 || colPosition[1] > 10 || rowPosition[0] < 10 || rowPosition[1] < 10 || colPosition[0] < 10 || colPosition[1] < 10) {
            System.out.println("Error");
            System.exit(1);
        }
    }

    private void lengthOfShip(int[] position) {
        for(int i = position[0]; i <= position[1]; i++) {
            length++;
        }
    }

    int getLength() {
        return length;
    }
}

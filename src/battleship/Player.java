package battleship;

public class Player {

    private int length = 0;
    private String parts = "";

    void createShip(String shipPositions) {
        String[] coordinates = convertInputToCoordinates(shipPositions);

        String[] startCoordinates = separateCoordinates(coordinates[0]);
        String[] endCoordinates = separateCoordinates(coordinates[1]);

        int startRowIndex = convertCoordinatesToIndex(startCoordinates[0], 'A');
        int startColIndex = convertCoordinatesToIndex(startCoordinates[1], '1');
        int endRowIndex = convertCoordinatesToIndex(endCoordinates[0], 'A');
        int endColIndex = convertCoordinatesToIndex(endCoordinates[1], '1');

        checkIndex(startRowIndex, startColIndex, endRowIndex, endColIndex);

        int[] dynamicIndex = checkDynamicIndex(startRowIndex, startColIndex, endRowIndex, endColIndex);
        int[] staticIndex = checkStaticIndex(startRowIndex, startColIndex, endRowIndex, endColIndex);
        boolean isStaticRow = isStaticRow(startRowIndex, endRowIndex);
        boolean isOrder = isOrder(dynamicIndex);

        lengthOfShip(dynamicIndex);

        partsOfShip(dynamicIndex, staticIndex, isStaticRow, isOrder);
    }

    private String[] convertInputToCoordinates(String shipPositions) {
        return shipPositions.split(" ");
    }

    private String[] separateCoordinates(String shipPosition) {
        return shipPosition.split("(?<=\\D)(?=\\d)");
    }

    private int convertCoordinatesToIndex(String coordinate, char charToAdd) {
        if(coordinate.length() > 1) {
            return 9;
        } else {
            char coordinateChar = coordinate.charAt(0);
            return (coordinateChar - charToAdd);
        }
    }

    private void checkIndex(int startRowIndex, int startColIndex, int endRowIndex, int endColIndex) {
        if(startRowIndex != endRowIndex && startColIndex != endColIndex) {
            System.out.println("Error");
            System.exit(0);
        }
        if(startRowIndex < 0 || startRowIndex > 9 || startColIndex < 0 || startColIndex > 9 || endRowIndex < 0 || endRowIndex > 9 || endColIndex < 0 || endColIndex > 9) {
            System.out.println("Error");
            System.exit(0);
        }
    }

    private int[] checkDynamicIndex(int startRowIndex, int startColIndex, int endRowIndex, int endColIndex) {
        if(startRowIndex != endRowIndex) {
            return new int[]{startRowIndex, endRowIndex};
        } else {
            return new int[]{startColIndex, endColIndex};
        }
    }

    private int[] checkStaticIndex(int startRowIndex, int startColIndex, int endRowIndex, int endColIndex) {
        if(startRowIndex == endRowIndex) {
            return new int[]{startRowIndex, endRowIndex};
        } else {
            return new int[]{startColIndex, endColIndex};
        }
    }

    private boolean isStaticRow(int startRowIndex, int endRowIndex) {
        if(startRowIndex == endRowIndex) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isOrder(int[] dynamicIndex) {
        if(dynamicIndex[0] < dynamicIndex[1]) {
            return true;
        } else {
            return false;
        }
    }

    private void lengthOfShip(int[] position) {
        length = Math.abs((position[0] - position[1])) +1;
    }

    private void partsOfShip(int[] dynamicIndex, int[] staticIndex, boolean isStaticRow, boolean isOrder) {
        StringBuilder partsOfShipBuilder = new StringBuilder(30);

        for(int i = 0; i < length; i++) {
            if(isOrder) {
                if(isStaticRow) {
                    partsOfShipBuilder.append((char) (staticIndex[0] + 'A')).append(dynamicIndex[0] + i + 1).append(" ");
                } else {
                    partsOfShipBuilder.append((char) (dynamicIndex[0] + 'A' + i)).append(staticIndex[0] + 1).append(" ");
                }
            } else {
                if(isStaticRow) {
                    partsOfShipBuilder.append((char) (staticIndex[0] + 'A')).append(dynamicIndex[0] - i + 1).append(" ");
                } else {
                    partsOfShipBuilder.append((char) (dynamicIndex[0] + 'A' - i)).append(staticIndex[0] + 1).append(" ");
                }
            }

        }

        parts = partsOfShipBuilder.toString();
    }

    int getLength() {
        return length;
    }

    String getParts() {
        return parts;
    }
}

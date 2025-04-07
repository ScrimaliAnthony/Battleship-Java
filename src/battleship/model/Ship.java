package battleship.model;

import battleship.exceptions.InvalidShipLengthException;
import battleship.exceptions.NotAlignedShipException;
import battleship.exceptions.NotInsideTheBoardException;

class Ship {

    private final int maxLength;
    private final String name;

    private int length = 0;
    private String parts = "";

    private final int startRowIndex;
    private final int startColIndex;
    private final int endRowIndex;
    private final int endColIndex;

    private final int[] dynamicIndex;
    private final int[] staticIndex;

    private final boolean isStaticRow;
    private final boolean isOrder;

    private final int[] rowIndex;
    private final int[] colIndex;

    public Ship(String shipPositions, int maxLength, String name) throws InvalidShipLengthException, NotAlignedShipException, NotInsideTheBoardException {
        this.maxLength = maxLength;
        this.name = name;

        String[] coordinates = convertInputToCoordinates(shipPositions);

        String[] startCoordinates = separateCoordinates(coordinates[0]);
        String[] endCoordinates = separateCoordinates(coordinates[1]);

        startRowIndex = convertCoordinatesToIndex(startCoordinates[0], 'A');
        startColIndex = convertCoordinatesToIndex(startCoordinates[1], '1');
        endRowIndex = convertCoordinatesToIndex(endCoordinates[0], 'A');
        endColIndex = convertCoordinatesToIndex(endCoordinates[1], '1');

        checkIndex(startRowIndex, startColIndex, endRowIndex, endColIndex);

        dynamicIndex = checkDynamicIndex(startRowIndex, startColIndex, endRowIndex, endColIndex);
        staticIndex = checkStaticIndex(startRowIndex, startColIndex, endRowIndex, endColIndex);

        isStaticRow = isStaticRow(startRowIndex, endRowIndex);
        isOrder = isOrder(dynamicIndex);

        lengthOfShip(dynamicIndex);

        checkLengthOfShip();

        partsOfShip(dynamicIndex, staticIndex, isStaticRow, isOrder);

        rowIndex = rowIndex();
        colIndex = colIndex();
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

    private void checkIndex(int startRowIndex, int startColIndex, int endRowIndex, int endColIndex) throws NotAlignedShipException, NotInsideTheBoardException {
        if(startRowIndex != endRowIndex && startColIndex != endColIndex) {
           throw new NotAlignedShipException("Error: Your ship must be aligned either horizontally or vertically. Try again:");
        }
        if(startRowIndex < 0 || startRowIndex > 9 || startColIndex < 0 || startColIndex > 9 || endRowIndex < 0 || endRowIndex > 9 || endColIndex < 0 || endColIndex > 9) {
            throw new NotInsideTheBoardException("Error: Your ship must be placed within the board (from A1 to J10 inclusive). Try again:");
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

    private void checkLengthOfShip() throws InvalidShipLengthException {
        if(length != maxLength) {
            throw new InvalidShipLengthException("Error: The length of the "+ this.name + " need to be " + this.maxLength + " Try again:");
        }
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

    private int[] rowIndex() {
        int[] rowIndex = new int[maxLength];
        int i = 0;

        if(isStaticRow) {
            while(i < maxLength) {
                rowIndex[i] = startRowIndex;
                i++;
            }
        } else {
            if(isOrder) {
                for(int j = startRowIndex; j <= endRowIndex; j++) {
                    rowIndex[i] = j;
                    i++;
                }
            } else {
                for(int j = endRowIndex; j <= startRowIndex; j++) {
                    rowIndex[i] = j;
                    i++;
                }
            }
        }
        return rowIndex;
    }

    private int[] colIndex() {
        int[] colIndex = new int[maxLength];
        int i = 0;

        if(!isStaticRow) {
            while(i < maxLength) {
                colIndex[i] = startColIndex;
                i++;
            }
        } else {
            if(isOrder) {
                for(int j = startColIndex; j <= endColIndex; j++) {
                    colIndex[i] = j;
                    i++;
                }
            } else {
                for(int j = endColIndex; j <= startColIndex; j++) {
                    colIndex[i] = j;
                    i++;
                }
            }
        }
        return colIndex;
    }

    int getLength() {
        return length;
    }

    String getParts() {
        return parts;
    }

    int[] getRowIndex() {
        return rowIndex;
    }

    int[] getColIndex() {
        return colIndex;
    }
}

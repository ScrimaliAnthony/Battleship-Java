package battleship.model;

import battleship.exceptions.*;
import battleship.utils.Converter;
import battleship.utils.Validators;

import java.util.Arrays;

public class ShipFactory {

    private ShipFactory() {}

    public static Ship createShip(String shipPosition, int shipMaxLength, String shipName, char[][] gameBoard,
            int boardRow, int boardCol)
            throws NotAlignedShipException, NotInsideTheBoardException, InvalidShipLengthException, ShipOverlapException,
            TooCloseToAnotherShipException {

        int[] indexes = Converter.shipConvert(shipPosition);

        int[] dynamicIndex = checkDynamicIndex(indexes[0], indexes[1], indexes[2], indexes[3]);
        int[] staticIndex = checkStaticIndex(indexes[0], indexes[1], indexes[2], indexes[3]);

        boolean isStaticRow = isStaticRow(indexes[0], indexes[2]);
        boolean isOrder = isOrder(dynamicIndex);

        int length = lengthOfShip(dynamicIndex);
        String partsOfShip = partsOfShip(dynamicIndex, staticIndex, isStaticRow, isOrder, length);

        int[] rowIndex = rowIndex(length, isStaticRow, isOrder, indexes[0], indexes[2]);
        int[] colIndex = colIndex(length, isStaticRow, isOrder, indexes[1], indexes[3]);

        Validators.ShipValidate(indexes, shipMaxLength, length, shipName, gameBoard, rowIndex, colIndex, boardRow, boardCol);

        return new Ship(length, shipName, partsOfShip, rowIndex, colIndex);
    }

    private static int[] checkDynamicIndex(int startRowIndex, int startColIndex, int endRowIndex, int endColIndex) {
        if(startRowIndex != endRowIndex) {
            return new int[]{startRowIndex, endRowIndex};
        } else {
            return new int[]{startColIndex, endColIndex};
        }
    }

    private static int[] checkStaticIndex(int startRowIndex, int startColIndex, int endRowIndex, int endColIndex) {
        if(startRowIndex == endRowIndex) {
            return new int[]{startRowIndex, endRowIndex};
        } else {
            return new int[]{startColIndex, endColIndex};
        }
    }

    private static boolean isStaticRow(int startRowIndex, int endRowIndex) {
        if(startRowIndex == endRowIndex) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isOrder(int[] dynamicIndex) {
        if(dynamicIndex[0] < dynamicIndex[1]) {
            return true;
        } else {
            return false;
        }
    }

    private static int lengthOfShip(int[] position) {
        return Math.abs((position[0] - position[1])) +1;
    }

    private static String partsOfShip(int[] dynamicIndex, int[] staticIndex, boolean isStaticRow, boolean isOrder, int length) {
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
        return partsOfShipBuilder.toString();
    }

    private static int[] rowIndex(int length, boolean isStaticRow, boolean isOrder, int startRowIndex, int endRowIndex) {
        int[] rowIndex = new int[length];

        if (isStaticRow) {
            for (int i = 0; i < length; i++) {
                rowIndex[i] = startRowIndex;
            }
        } else {
            if (isOrder) {
                for (int i = 0; i < length; i++) {
                    rowIndex[i] = startRowIndex + i;
                }
            } else {
                for (int i = 0; i < length; i++) {
                    rowIndex[i] = startRowIndex - i;
                }
            }
        }

        return rowIndex;
    }


    private static int[] colIndex(int length, boolean isStaticRow, boolean isOrder, int startColIndex, int endColIndex) {
        int[] colIndex = new int[length];

        if (!isStaticRow) {
            for (int i = 0; i < length; i++) {
                colIndex[i] = startColIndex;
            }
        } else {
            if (isOrder) {
                for (int i = 0; i < length; i++) {
                    colIndex[i] = startColIndex + i;
                }
            } else {
                for (int i = 0; i < length; i++) {
                    colIndex[i] = startColIndex - i;
                }
            }
        }

        return colIndex;
    }

}

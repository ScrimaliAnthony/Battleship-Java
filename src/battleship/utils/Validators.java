package battleship.utils;

import battleship.exceptions.InvalidShipLengthException;
import battleship.exceptions.NotAlignedShipException;
import battleship.exceptions.NotInsideTheBoardException;

public class Validators {

    private Validators() {}

    public static void ShipValidate(int[] indexes, int shipMaxLength, int length, String shipName)
            throws NotAlignedShipException, NotInsideTheBoardException, InvalidShipLengthException {
        checkAlignment(indexes);

        for(int index : indexes) {
            checkInsideTheBoard(index);
        }

        checkLengthOfShip(shipMaxLength, length, shipName);
    }

    private static void checkAlignment(int[] index) throws NotAlignedShipException {
        if(index[0] != index[2] && index[1] != index[3]) {
            throw new NotAlignedShipException("Error: Your ship must be aligned either horizontally or vertically. Try again:");
        }
    }

    private static void checkInsideTheBoard(int index) throws NotInsideTheBoardException {
        if(index < 0 || index > 9) {
            throw new NotInsideTheBoardException("Error: Your ship must be placed within the board (from A1 to J10 inclusive). Try again:");
        }
    }

    private static void checkLengthOfShip(int shipMaxLength, int length, String shipName) throws InvalidShipLengthException {
        if(length != shipMaxLength) {
            throw new InvalidShipLengthException("Error: The length of the "+ shipName + " need to be " + shipMaxLength + " Try again:");
        }
    }
}

package battleship.utils;

public class Converter {

    private Converter() {}

    public static int[] shipConvert(String input) {
        int[] index = new int[4];

        String[] coordinates = convertShipInputToCoordinates(input);

        String[] startCoordinates = separateShipCoordinates(coordinates[0]);
        String[] endCoordinates = separateShipCoordinates(coordinates[1]);

        index[0] = convertCoordinatesToIndex(startCoordinates[0], 'A');
        index[1] = convertCoordinatesToIndex(startCoordinates[1], '1');
        index[2] = convertCoordinatesToIndex(endCoordinates[0], 'A');
        index[3] = convertCoordinatesToIndex(endCoordinates[1], '1');

        return index;
    }

    private static String[] convertShipInputToCoordinates(String shipPositions) {
        return shipPositions.split(" ");
    }

    private static String[] separateShipCoordinates(String shipPosition) {
        return shipPosition.split("(?<=\\D)(?=\\d)");
    }

    private static int convertCoordinatesToIndex(String coordinate, char charToAdd) {
        if(coordinate.length() > 1) {
            return 9;
        } else {
            char coordinateChar = coordinate.charAt(0);
            return (coordinateChar - charToAdd);
        }
    }
}

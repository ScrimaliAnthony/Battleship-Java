package battleship.utils;

public class Converter {

    private Converter() {}

    public static int[] shipConvert(String input) {
        int[] index = new int[4];

        String[] coordinates = convertShipInputToCoordinates(input);

        String[] startCoordinates = separateCoordinates(coordinates[0]);
        String[] endCoordinates = separateCoordinates(coordinates[1]);

        index[0] = convertCoordinatesToIndex(startCoordinates[0], 'A');
        index[1] = convertCoordinatesToIndex(startCoordinates[1], '1');
        index[2] = convertCoordinatesToIndex(endCoordinates[0], 'A');
        index[3] = convertCoordinatesToIndex(endCoordinates[1], '1');

        return index;
    }

    public static int[] fireConvert(String input) {
        int[] indexes = new int[2];
        String[] coordinates = separateCoordinates(input);

        indexes[0] = convertCoordinatesToIndex(coordinates[0], 'A');
        indexes[1] = convertCoordinatesToIndex(coordinates[1], '1');

        return indexes;
    }

    private static String[] convertShipInputToCoordinates(String shipPositions) {
        return shipPositions.split(" ");
    }

    private static String[] separateCoordinates(String shipPosition) {
        return shipPosition.split("(?<=\\D)(?=\\d)");
    }

    private static int convertCoordinatesToIndex(String coordinate, char charToAdd) {
        if(coordinate.length() > 1) {
            return Integer.parseInt(coordinate) -1;
        } else {
            char coordinateChar = coordinate.charAt(0);
            return (coordinateChar - charToAdd);
        }
    }
}

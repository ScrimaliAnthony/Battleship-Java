package battleship.utils;

import battleship.exceptions.InvalidInputFormatException;

public class Converter {

    private Converter() {}

    public static int[] shipConvert(String input) throws InvalidInputFormatException {
        String[] coordinates = convertShipInputToCoordinates(input);
        if (coordinates.length != 2) {
            throw new InvalidInputFormatException("Error: Invalid input format. Use format: LetterNumber Space LetterNumber (e.g., A1 A5). Try again:");
        }

        for (String coordinate : coordinates) {
            if (!coordinate.matches("(?i)^[a-j](10|[1-9])$")) {
                throw new InvalidInputFormatException("Error: Invalid coordinate '" + coordinate + "'. Use a letter (A–J) followed by a number (1–10). Try again:");
            }
        }

        String[] startCoordinates = separateCoordinates(coordinates[0]);
        String[] endCoordinates = separateCoordinates(coordinates[1]);

        int[] index = new int[4];
        index[0] = convertCoordinatesToIndex(startCoordinates[0], 'A');
        index[1] = convertCoordinatesToIndex(startCoordinates[1], '1');
        index[2] = convertCoordinatesToIndex(endCoordinates[0], 'A');
        index[3] = convertCoordinatesToIndex(endCoordinates[1], '1');

        return index;
    }

    public static int[] fireConvert(String input) throws InvalidInputFormatException {
        if (!input.matches("(?i)^[a-j](10|[1-9])$")) {
            throw new InvalidInputFormatException("Error: Invalid input. Use a letter (A–J) followed by a number (1–10), e.g., B4. Try again:");
        }

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
            char coordinateChar = Character.toUpperCase(coordinate.charAt(0));
            return (coordinateChar - charToAdd);
        }
    }
}

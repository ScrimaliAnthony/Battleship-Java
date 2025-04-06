package battleship.model;

import battleship.ui.Display;

public class Player {
    private GameBoard gameBoard;

    private Ship[] ships = new Ship[5];

    public Player() {
        createGameBoard();
    }

    public void createShips(String shipPosition, int i) {
        int[] shipSize = { 5, 4, 3, 3, 2 };
        String[] shipsNames = { "aircraftCarrier", "battleShip", "submarine", "cruiser", "destroyer" };

        ships[i] = new Ship(shipPosition, shipSize[i], shipsNames[i]);
    }

    private void createGameBoard() {
        gameBoard = new GameBoard(10, 10);
    }

    public void placeShip(Ship ship) {
        gameBoard.placeShipOnBoard(ship);
    }

    public String getBoardAsString() {
        return Display.board(gameBoard.getRow(), gameBoard.getCol(), gameBoard.getBoard());
    }

    public String getLengthOfShipAsString(Ship ship) {
        return Display.lengthOfShip(ship.getLength());
    }

    public String getPartsOfShipAsString(Ship ship) {
        return Display.partsOfShip(ship.getParts());
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public Ship getShip(int i) {
        return ships[i];
    }
}

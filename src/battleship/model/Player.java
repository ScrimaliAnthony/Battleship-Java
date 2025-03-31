package battleship.model;

import battleship.ui.Display;

public class Player {
    private GameBoard gameBoard;
    private Ship aircraftCarrier;

    public Player() {
        createGameBoard();
    }

    private void createGameBoard() {
        gameBoard = new GameBoard(10, 10);
    }

    public void createShips(String shipPosition, int maxLength) {
        aircraftCarrier = new Ship(shipPosition, maxLength);
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

    public Ship getAircraftCarrier() {
        return aircraftCarrier;
    }
}

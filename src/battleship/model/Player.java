package battleship.model;

import battleship.exceptions.*;
import battleship.ui.Display;

public class Player {
    private GameBoard gameBoard;

    private Ship[] ships = new Ship[5];

    public Player() {
        createGameBoard();
    }

    private void createGameBoard() {
        gameBoard = new GameBoard(10, 10);
    }

    public void createFleet(String shipPosition, int i)
            throws NotAlignedShipException, NotInsideTheBoardException, InvalidShipLengthException,
            TooCloseToAnotherShipException, ShipOverlapException {

        int[] shipMaxLength = { 5, 4, 3, 3, 2 };
        String[] shipsNames = { "aircraftCarrier", "battleShip", "submarine", "cruiser", "destroyer" };

        ships[i] = ShipFactory.createShip(shipPosition, shipMaxLength[i], shipsNames[i], getBoard(), getRow(), getCol());
        placeShip(ships[i]);
    }

    public void placeShip(Ship ship) {
        gameBoard.placeShipOnBoard(ship);
    }

    public boolean fire(String fireShot) throws NotInsideTheBoardException {
        int[] fireIndex = Fire.shot(fireShot);
        return this.gameBoard.isFireOnShip(fireIndex);
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

    public char[][] getBoard() {
        return this.gameBoard.getBoard();
    }

    public int getRow() {
        return this.gameBoard.getRow();
    }

    public int getCol() {
        return this.gameBoard.getCol();
    }
}

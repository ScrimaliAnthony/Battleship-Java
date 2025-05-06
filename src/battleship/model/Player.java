package battleship.model;

import battleship.exceptions.*;
import battleship.ui.Display;

public class Player {
    private String name;
    private PlayerGameBoard playerGameBoard;
    private FogWarGameBoard fogWarGameBoard;

    private Ship[] ships = new Ship[5];

    public Player(int num) {
        this.name = "player " + (num + 1);
    }

    public void createGameBoards() {
        playerGameBoard = new PlayerGameBoard(10, 10);
        fogWarGameBoard = new FogWarGameBoard(10, 10);
    }

    public void createFleet(String shipPosition, int i)
            throws NotAlignedShipException, NotInsideTheBoardException, InvalidShipLengthException,
            TooCloseToAnotherShipException, ShipOverlapException, InvalidInputFormatException {

        int[] shipMaxLength = { 5, 4, 3, 3, 2 };
        String[] shipsNames = { "aircraftCarrier", "battleShip", "submarine", "cruiser", "destroyer" };

        ships[i] = ShipFactory.createShip(shipPosition, shipMaxLength[i], shipsNames[i], getBoard(), getRow(), getCol());
        placeShip(ships[i]);
    }

    public void placeShip(Ship ship) {
        playerGameBoard.placeShipOnBoard(ship);
    }

    public int[] fire(String fireShot) throws NotInsideTheBoardException, InvalidInputFormatException {
        return Fire.shot(fireShot);
    }

    public boolean isFireOnShip(int[] fireIndex) {
        boolean isFireOnShip = this.playerGameBoard.isFireOnShip(fireIndex);
        if(isFireOnShip) {
            checkShip(fireIndex);
        }
        return isFireOnShip;
    }

    private void checkShip(int[] fireIndex) {
        int row = fireIndex[0];
        int col = fireIndex[1];

        for(Ship ship : ships) {
            boolean isRowShip = false;
            boolean isColShip = false;
            for(int shipRow : ship.getRowIndex()) {
                if(shipRow == row) {
                    isRowShip = true;
                }
            }
            for(int shipCol : ship.getColIndex()) {
                if(shipCol == col) {
                    isColShip = true;
                }
            }
            if(isRowShip && isColShip) {
                ship.setPv();
            }
        }
    }

    public boolean isShipSank() {
        for(Ship ship : ships) {
            if(ship.getIsAlive() && ship.getPv() == 0) {
                ship.setIsAlive();
                return true;
            }
        }
        return false;
    }

    public void fireOnFogWarGameBoard(int[] indexes, boolean isFireOnShip) {
        this.fogWarGameBoard.placeFireOnBoard(indexes, isFireOnShip);
    }

    public boolean isAlive() {
        int deathCount = 0;
        for(Ship ship : ships) {
            if(!ship.getIsAlive()) {
                deathCount++;
            }
            if(deathCount == 5) {
                return false;
            }
        }
        return true;
    }

    public String getBoardAsString() {
        return Display.board(playerGameBoard.getRow(), playerGameBoard.getCol(), playerGameBoard.getBoard());
    }

    public String getFogWarBoardAsString() {
        return Display.board(fogWarGameBoard.getRow(), fogWarGameBoard.getCol(), fogWarGameBoard.getBoard());
    }

    public String getLengthOfShipAsString(Ship ship) {
        return Display.lengthOfShip(ship.getLength());
    }

    public String getPartsOfShipAsString(Ship ship) {
        return Display.partsOfShip(ship.getParts());
    }

    public String getName() {
        return this.name;
    }

    public PlayerGameBoard getPlayerGameBoard() {
        return playerGameBoard;
    }

    public Ship getShip(int i) {
        return ships[i];
    }

    public char[][] getBoard() {
        return this.playerGameBoard.getBoard();
    }

    public int getRow() {
        return this.playerGameBoard.getRow();
    }

    public int getCol() {
        return this.playerGameBoard.getCol();
    }
}

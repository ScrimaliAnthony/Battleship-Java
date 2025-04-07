package battleship.exceptions;

public class TooCloseToAnotherShipException extends GameException {
    public TooCloseToAnotherShipException(String message) {
        super(message);
    }
}

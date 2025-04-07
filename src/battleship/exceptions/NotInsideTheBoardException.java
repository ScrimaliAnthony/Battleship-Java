package battleship.exceptions;

public class NotInsideTheBoardException extends GameException {
    public NotInsideTheBoardException(String message) {
        super(message);
    }
}

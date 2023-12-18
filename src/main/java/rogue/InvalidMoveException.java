package rogue;
public class InvalidMoveException extends Exception {
    /**.
    *default
    */
    public InvalidMoveException() {
        super();
    }
    /**.
     *@param message
     */
    public InvalidMoveException(String message) {
        super(message);
    }
}


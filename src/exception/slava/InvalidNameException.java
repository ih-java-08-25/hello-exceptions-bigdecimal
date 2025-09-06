package exception.slava;

// CUSTOM EXCEPTION --> UNCHECKED EXCEPTION
public class InvalidNameException extends RuntimeException {
    public InvalidNameException(String message) {
        super(message);
    }
}

package exception.custom;

// CUSTOM EXCEPTION --> CHECKED EXCEPTION
public class InvalidAgeException extends Exception {
    public InvalidAgeException(String message){
        super(message);
    }

    public InvalidAgeException(){
        super("Error, invalid age");
    }
}

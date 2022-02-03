package CustomException;

public class ConfirmPasswordException extends Exception {
    public ConfirmPasswordException(){
        super("Password Does Not Match");
    }

    public ConfirmPasswordException(String message){
        super(message);
    }
}

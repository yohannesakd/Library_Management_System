package CustomException;

public class EmailException extends Exception {
    public EmailException(){
        super("Please Input Correct Email");
    }

    public EmailException(String message){
        super(message);
    }
}

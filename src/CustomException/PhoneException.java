package CustomException;

public class PhoneException extends Exception {
    public PhoneException(){
        super("Please Input Correct Phone");
    }

    public PhoneException(String message){
        super(message);
    }
}

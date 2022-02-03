package CustomException;

public class NullFieldException extends Exception {
    public NullFieldException(){
        super("Empty Fields");
    }

    public NullFieldException(String message){
        super(message);
    }
}

package model.exception;

public class SenhaInvalidaException extends IllegalArgumentException {
    public SenhaInvalidaException(String msg) {
        super(msg);
    }    
}

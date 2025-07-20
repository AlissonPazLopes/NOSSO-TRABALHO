package model.exception;

//pode ser chamada no setLogin do usuario
public class SenhaInvalidaException extends IllegalArgumentException {
    public SenhaInvalidaException(String msg) {
        super(msg);
    }    
}

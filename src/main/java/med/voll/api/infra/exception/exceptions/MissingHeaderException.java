package med.voll.api.infra.exception.exceptions;

public class MissingHeaderException extends RuntimeException{

    public MissingHeaderException(String message) {
        super(message);
    }
}

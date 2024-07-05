package med.voll.api.infra.exception.exceptions;

public class AppointNotFoundException extends RuntimeException{

    public AppointNotFoundException(String message) {
        super(message);
    }
}

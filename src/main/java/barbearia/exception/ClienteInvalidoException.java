package barbearia.exception;

public class ClienteInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClienteInvalidoException(String message) {
        super(message);
    }
}

package barbearia.exception;

public class ServicoInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServicoInvalidoException(String message) {
        super(message);
    }
}

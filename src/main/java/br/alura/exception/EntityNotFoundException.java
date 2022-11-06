package br.alura.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Não encontrado";
    }
}

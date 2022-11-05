package br.alura.exception;

public class VideoNotFoundException extends RuntimeException {

    public VideoNotFoundException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Não encontrado";
    }
}

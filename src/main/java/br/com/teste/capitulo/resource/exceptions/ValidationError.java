package br.com.teste.capitulo.resource.exceptions;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError implements Serializable {
    private static final long serialVersionUID = 1l;
    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addErros(String fieldName, String message){
        erros.add(new FieldMessage(fieldName, message));
    }
}

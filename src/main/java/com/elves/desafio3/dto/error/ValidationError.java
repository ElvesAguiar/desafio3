package com.elves.desafio3.dto.error;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

    private List<FieldMessage> erros = new ArrayList<>();


    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addError(String fieldname, String Message) {
        FieldMessage error = new FieldMessage(fieldname,Message);
        erros.add(error);
    }

    public List<FieldMessage> getErros(){
        return erros;
    }
}

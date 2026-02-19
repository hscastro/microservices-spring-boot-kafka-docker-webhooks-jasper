package com.hscastro.icompras.pedidos.exceptions;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

    private String campo;
    private String mensagem;

    public ValidationException(String campo, String mensagem) {
        super(mensagem);
        this.campo = campo;
        this.mensagem = mensagem;
    }
}

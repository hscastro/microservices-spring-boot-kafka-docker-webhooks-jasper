package com.hscastro.icompras.pedidos.exceptions;

public record ErroResposta(String erro, String campo, String mensagem) {
}

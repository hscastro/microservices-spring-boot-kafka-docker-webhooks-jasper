package com.hscastro.icompras.pedidos.client.representation;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public record ClienteRepresentation(
        Long codigo, String nome, String cpf, String logradouro, String numero, String bairro, String cidade,String email, String telefone) {
}

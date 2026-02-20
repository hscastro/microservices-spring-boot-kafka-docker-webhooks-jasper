package com.hscastro.icompras.pedidos.controller.dto;

public record RecebimentoCallbackPagamentoDTO(
        Long codigo, String chavePagamento,
        boolean status, String observacoes) {
}

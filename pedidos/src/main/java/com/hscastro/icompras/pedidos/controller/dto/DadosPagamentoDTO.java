package com.hscastro.icompras.pedidos.controller.dto;

import com.hscastro.icompras.pedidos.model.enums.TipoPagamento;

public record DadosPagamentoDTO(
        String dados,
        TipoPagamento tipoPagamento) {
}

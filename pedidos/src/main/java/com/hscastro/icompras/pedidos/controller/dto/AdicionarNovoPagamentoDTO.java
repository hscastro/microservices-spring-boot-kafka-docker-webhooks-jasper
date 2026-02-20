package com.hscastro.icompras.pedidos.controller.dto;

import com.hscastro.icompras.pedidos.model.enums.TipoPagamento;

public record AdicionarNovoPagamentoDTO(Long codigoPedido, String dadosCartao, TipoPagamento tipo) {
}

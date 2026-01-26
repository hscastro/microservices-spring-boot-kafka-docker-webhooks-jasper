package com.hscastro.icompras.pedidos.controller.dto;

import java.math.BigDecimal;

public record ItemPedidoDTO(
        Long codigo,
        Long codigoProduto,
        Integer quantidade,
        BigDecimal valorUnitario) {
}

package com.hscastro.icompras.pedidos.controller.mappers;

import com.hscastro.icompras.pedidos.controller.dto.ItemPedidoDTO;
import com.hscastro.icompras.pedidos.model.ItemPedido;
import org.mapstruct.Mapper;


@Mapper(componentModel="spring")
public interface ItemPedidoMapper {
    ItemPedido map(ItemPedidoDTO itemPedidoDTO);
}

package com.hscastro.icompras.pedidos.controller.mappers;

import com.hscastro.icompras.pedidos.controller.dto.ItemPedidoDTO;
import com.hscastro.icompras.pedidos.controller.dto.NovoPedidoDTO;
import com.hscastro.icompras.pedidos.model.ItemPedido;
import com.hscastro.icompras.pedidos.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;


@Mapper(componentModel="spring")
public interface PedidoMapper {
    ItemPedidoMapper ITEM_PEDIDO_MAPPER = Mappers.getMapper(ItemPedidoMapper.class);

    @Mapping(source = "itens", target = "itens", qualifiedByName = "mapItens")
    @Mapping(source = "dadosPagamento", target = "dadosPagamento")
    Pedido map(NovoPedidoDTO dto);

    @Named("mapItens")
    default List<ItemPedido> mapItens(List<ItemPedidoDTO> dtos){
        return dtos.stream().map(ITEM_PEDIDO_MAPPER::map).toList();
    }

    private static BigDecimal calcularTotal(Pedido pedido){
        return null;
    }
}

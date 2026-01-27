package com.hscastro.icompras.pedidos.controller.mappers;

import com.hscastro.icompras.pedidos.controller.dto.ItemPedidoDTO;
import com.hscastro.icompras.pedidos.controller.dto.NovoPedidoDTO;
import com.hscastro.icompras.pedidos.model.ItemPedido;
import com.hscastro.icompras.pedidos.model.Pedido;
import com.hscastro.icompras.pedidos.model.enums.StatusPedido;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @AfterMapping
    default void afterMapping(@MappingTarget Pedido pedido){
        pedido.setStatus(StatusPedido.REALIZADO);
        pedido.setDataPedido(LocalDateTime.now());
        var total = calcularValorTotal(pedido);
        pedido.setTotal(total);

        pedido.getItens().forEach(item -> item.setPedido(pedido));
    }
    private static BigDecimal calcularValorTotal(Pedido pedido) {
        return pedido.getItens().stream().map(item ->
            item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()))
        ).reduce(BigDecimal.ZERO, BigDecimal::add).abs();
    }
}

package com.hscastro.icompras.pedidos.service;

import com.hscastro.icompras.pedidos.model.Pedido;
import com.hscastro.icompras.pedidos.repositories.ItemPedidoRepository;
import com.hscastro.icompras.pedidos.repositories.PedidoRepository;
import com.hscastro.icompras.pedidos.validator.PedidoValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoValidator pedidoValidator;


    public PedidoService(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository,PedidoValidator pedidoValidator){
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoValidator = pedidoValidator;
    }

    public void salvar(Pedido pedido){
        pedidoRepository.save(pedido);
    }

    public Optional<Pedido> findPedidoById(Long codigo){
        return pedidoRepository.findById(codigo);
    }
}

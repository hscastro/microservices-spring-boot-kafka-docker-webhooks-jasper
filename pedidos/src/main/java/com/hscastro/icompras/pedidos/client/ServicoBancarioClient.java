package com.hscastro.icompras.pedidos.client;


import com.hscastro.icompras.pedidos.model.Pedido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@Component
public class ServicoBancarioClient {

    public String solicitarPagamento(Pedido pedido){
        log.info("Solicitando pagamento o pedido o codigo {}.", pedido.getCodigo());
        return UUID.randomUUID().toString();
    }
}

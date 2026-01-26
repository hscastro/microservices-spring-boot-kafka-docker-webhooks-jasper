package com.hscastro.icompras.pedidos.controller;


import com.hscastro.icompras.pedidos.controller.dto.NovoPedidoDTO;
import com.hscastro.icompras.pedidos.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    public ResponseEntity create(@RequestBody NovoPedidoDTO pedidoDTO){
        return null;
    }

}

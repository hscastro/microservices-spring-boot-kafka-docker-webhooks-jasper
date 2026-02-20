package com.hscastro.icompras.pedidos.controller;

import com.hscastro.icompras.pedidos.controller.dto.RecebimentoCallbackPagamentoDTO;
import com.hscastro.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedidos/callback-pagamentos")
@RequiredArgsConstructor
public class RecebimentoCallbackPagamentocontroller {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Object> atualizaStatusPagamento(@RequestBody RecebimentoCallbackPagamentoDTO body,
                                                  @RequestHeader(required = true, name = "apiKey") String apiKey){
        pedidoService.atualizaStatusPagamento(
                body.codigo(),
                body.chavePagamento(),
                body.status(),
                body.observacoes()
        );

        return ResponseEntity.ok().build();

    }

}

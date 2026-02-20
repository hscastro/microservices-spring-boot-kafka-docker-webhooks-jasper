package com.hscastro.icompras.pedidos.controller;


import com.hscastro.icompras.pedidos.controller.dto.AdicionarNovoPagamentoDTO;
import com.hscastro.icompras.pedidos.controller.dto.NovoPedidoDTO;
import com.hscastro.icompras.pedidos.controller.mappers.PedidoMapper;
import com.hscastro.icompras.pedidos.exceptions.ErroResposta;
import com.hscastro.icompras.pedidos.exceptions.ValidationException;
import com.hscastro.icompras.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoMapper mapper;


    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody NovoPedidoDTO pedidoDTO){
        try {
            var pedido = mapper.map(pedidoDTO);
            var novoPedido = pedidoService.criarPedido(pedido);
            return ResponseEntity.ok(novoPedido.getCodigo());
        }catch (ValidationException ex){
            var erro = new ErroResposta("Erro de validação", ex.getCampo(), ex.getMensagem());
            return ResponseEntity.badRequest().body(erro);
        }
    }

    @PostMapping("pagamentos")
    public ResponseEntity<Object> adicionaNovoPagamento(@RequestBody AdicionarNovoPagamentoDTO novoPagamentoDTO){
        try {
            pedidoService.adicionaNovoPagamento(novoPagamentoDTO.codigoPedido(), novoPagamentoDTO.dadosCartao(), novoPagamentoDTO.tipo());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            var erro = new ErroResposta("Item não encontrado", "codigoPedido", e.getMessage());
            return ResponseEntity.badRequest().body(erro);
        }

    }


}

package com.hscastro.icompras.pedidos.validator;


import com.hscastro.icompras.pedidos.client.ClienteClient;
import com.hscastro.icompras.pedidos.client.ProdutoClient;
import com.hscastro.icompras.pedidos.client.representation.ClienteRepresentation;
import com.hscastro.icompras.pedidos.client.representation.ProdutoRepresentation;
import com.hscastro.icompras.pedidos.exceptions.ValidationException;
import com.hscastro.icompras.pedidos.model.ItemPedido;
import com.hscastro.icompras.pedidos.model.Pedido;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PedidoValidator {

    private final ProdutoClient produtoClient;
    private final ClienteClient clienteClient;

    public PedidoValidator(ProdutoClient produtoClient, ClienteClient clienteClient) {
        this.produtoClient = produtoClient;
        this.clienteClient = clienteClient;
    }

    public void valida(Pedido pedido){
        Long codigoCliente = pedido.getCodigoCliente();
        validarCliente(codigoCliente);
        pedido.getItens().forEach(this::validarItem);
    }

    private void validarItem(ItemPedido item) {
        try {
            var response = produtoClient.obterDados(item.getCodigoProduto());
            ProdutoRepresentation produto = response.getBody();
            log.info("Produto de codigo {} encontrado {}", produto.codigo(), produto.nome());
        }catch (FeignException.NotFound e){
            log.error("Produto não encontrado!");
            var message = String.format("Produto de codigo d% não encontrado.", item.getCodigoProduto());
            throw new ValidationException("codigoProduto", message);
        }
    }

    private void validarCliente(Long codigoCliente) {
        try {
            var response = clienteClient.obterDados(codigoCliente);
            ClienteRepresentation cliente = response.getBody();
            log.info("Cliente de codigo {} encontrado {}", cliente.codigo(), cliente.nome());
        }catch (FeignException.NotFound e){
            log.error("Cliente não encontrado!");
            var message = String.format("Cliente de codigo d% não encontrado.", codigoCliente);
            throw new ValidationException("codigoCliente", message);
        }
    }
}

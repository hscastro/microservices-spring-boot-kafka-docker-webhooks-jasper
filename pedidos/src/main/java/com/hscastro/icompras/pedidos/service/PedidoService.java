package com.hscastro.icompras.pedidos.service;

import com.hscastro.icompras.pedidos.client.ServicoBancarioClient;
import com.hscastro.icompras.pedidos.model.Pedido;
import com.hscastro.icompras.pedidos.model.enums.StatusPedido;
import com.hscastro.icompras.pedidos.repositories.ItemPedidoRepository;
import com.hscastro.icompras.pedidos.repositories.PedidoRepository;
import com.hscastro.icompras.pedidos.validator.PedidoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoValidator pedidoValidator;
    private final ServicoBancarioClient servicoBancarioClient;


    public PedidoService(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository, PedidoValidator pedidoValidator, ServicoBancarioClient servicoBancarioClient){
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoValidator = pedidoValidator;
        this.servicoBancarioClient = servicoBancarioClient;
    }
    @Transactional
    public Pedido criarPedido(Pedido pedido){
        pedidoValidator.valida(pedido);
        realizarPersistencia(pedido);
        enviarSolicitacaoPagamento(pedido);
        return pedido;
    }

    private void enviarSolicitacaoPagamento(Pedido pedido) {
        var chavePagamento = servicoBancarioClient.solicitarPagamento(pedido);
        pedido.setChavePagamento(chavePagamento);
    }

    private void realizarPersistencia(Pedido pedido) {
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(pedido.getItens());
    }

    public Optional<Pedido> buscarPedidoPorId(Long codigo){
           return pedidoRepository.findById(codigo);
    }

    public void atualizaStatusPagamento(
            Long codigoPedido, String chavePagamento, boolean sucesso, String observacoes) {

        var pedidoEncontrado = pedidoRepository.findByCodigoAndChavePagamento(codigoPedido, chavePagamento);

        if (pedidoEncontrado.isEmpty()){
            var msg = String.format("Pedido não encontrado para o codigo %d e chave pgto %s", codigoPedido, chavePagamento);
            log.error(msg);
            return;
        }

        Pedido pedido = pedidoEncontrado.get();

        if(sucesso){
            pedido.setStatus(StatusPedido.PAGO);
        }else {
            pedido.setStatus(StatusPedido.ERRO_PAGAMENTO);
            pedido.setObservacoes(observacoes);
        }
        pedidoRepository.save(pedido);
    }
}

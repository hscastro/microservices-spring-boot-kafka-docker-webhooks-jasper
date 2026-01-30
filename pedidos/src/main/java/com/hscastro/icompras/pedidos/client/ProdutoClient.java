package com.hscastro.icompras.pedidos.client;


import com.hscastro.icompras.pedidos.client.representation.ProdutoRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produtos",url = "${icompras.pedidos.clients.produtos.url}")
public interface ProdutoClient {

    @GetMapping("{codigo}")
    ResponseEntity<ProdutoRepresentation> obterDados(@PathVariable("codigo") Long codigo);
}

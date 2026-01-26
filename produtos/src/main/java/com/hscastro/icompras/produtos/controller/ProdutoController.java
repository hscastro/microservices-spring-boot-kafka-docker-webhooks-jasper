package com.hscastro.icompras.produtos.controller;

import com.hscastro.icompras.produtos.model.Produto;
import com.hscastro.icompras.produtos.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto){
        service.salvar(produto);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<Produto> obterDados(@PathVariable("codigo") Long codigo){
        return service.findProdutoById(codigo)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}

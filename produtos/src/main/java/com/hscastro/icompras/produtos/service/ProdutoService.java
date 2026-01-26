package com.hscastro.icompras.produtos.service;


import com.hscastro.icompras.produtos.model.Produto;
import com.hscastro.icompras.produtos.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public void salvar(Produto produto){
        produtoRepository.save(produto);
    }

    public Optional<Produto> findProdutoById(Long codigo){
        return produtoRepository.findById(codigo);
    }

}

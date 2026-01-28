package com.hscastro.icompras.clientes.service;


import com.hscastro.icompras.clientes.model.Cliente;
import com.hscastro.icompras.clientes.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public void salvar(Cliente cliente){
        clienteRepository.save(cliente);
    }

    public Optional<Cliente> obterDados(Long codigo){
        return clienteRepository.findById(codigo);
    }

}

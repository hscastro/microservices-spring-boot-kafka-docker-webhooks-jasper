package com.hscastro.icompras.clientes.controller;

import com.hscastro.icompras.clientes.model.Cliente;
import com.hscastro.icompras.clientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente){
        service.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<Cliente> obterDados(@PathVariable("codigo") Long codigo){
        return service.obterDados(codigo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

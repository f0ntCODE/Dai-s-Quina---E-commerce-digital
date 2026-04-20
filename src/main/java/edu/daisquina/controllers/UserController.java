package edu.daisquina.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.daisquina.dominio.Cliente;
import edu.daisquina.service.ClienteService;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/cliente")
public class UserController {

    private final ClienteService clienteService;

    public UserController(ClienteService clienteService){
        this.clienteService = clienteService;

    }

    @PostMapping("/editar/{idCliente}")
    public ResponseEntity<String> editarDados(@PathVariable int idCliente,
        @RequestParam String novoNome,
        @RequestParam String novoEmail,
        @RequestParam String novaSenha
    ) {
        
        Cliente cliente = clienteService.editar(idCliente, novoNome, novoEmail, novaSenha);

        return ResponseEntity.ok(cliente.toString());
    }

    

}

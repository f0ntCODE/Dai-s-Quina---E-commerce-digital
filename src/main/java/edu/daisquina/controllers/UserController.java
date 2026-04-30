package edu.daisquina.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.daisquina.dtos.RequestClienteDTO;
import edu.daisquina.dtos.ResponseClienteDTO;
import edu.daisquina.service.ClienteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/cliente")
public class UserController {

    private final ClienteService clienteService;

    public UserController(ClienteService clienteService){
        this.clienteService = clienteService;

    }

    @PostMapping("/editar/{idCliente}")
    public ResponseEntity<String> editarDados(@PathVariable Long idCliente,
        @RequestParam String novoNome,
        @RequestParam String novoEmail,
        @RequestParam String novaSenha
    ) {

        RequestClienteDTO request = new RequestClienteDTO(novoNome, novoEmail, novaSenha);
        
        ResponseClienteDTO cliente = clienteService.editar(idCliente, request);

        return ResponseEntity.ok(cliente.toString());
    }

    @PostMapping("/excluir/{idCliente}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Long idCliente
    ) {
        clienteService.excluir(idCliente);
        
        return ResponseEntity.ok("Excluído");
    } 
}

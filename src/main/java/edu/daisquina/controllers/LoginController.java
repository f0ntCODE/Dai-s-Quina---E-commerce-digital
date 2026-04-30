package edu.daisquina.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.daisquina.dtos.RequestClienteDTO;
import edu.daisquina.dtos.ResponseClienteDTO;
import edu.daisquina.service.ClienteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/auth")
public class LoginController{

    private final ClienteService clienteService;

    public LoginController(ClienteService clienteService){
        this.clienteService = clienteService;

    }

    @GetMapping("/test")
    public ResponseEntity<?> testeAPI() {
        return ResponseEntity.ok("API de autenticação funcionando");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String nome,
        @RequestParam String email,
        @RequestParam String senha
    ) {

        RequestClienteDTO request = new RequestClienteDTO(nome, email, senha);
        ResponseClienteDTO clienteNovo = clienteService.criar(request);
        
        return ResponseEntity.ok(clienteNovo.toString());
    }
}

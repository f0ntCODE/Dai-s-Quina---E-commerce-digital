package edu.daisquina.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.daisquina.dominio.Carrinho;
import edu.daisquina.dominio.Cliente;
import edu.daisquina.dominio.Mercadoria;
import edu.daisquina.service.CarrinhoService;
import edu.daisquina.service.ClienteService;
import edu.daisquina.service.MercadoriaService;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {

    private final ClienteService clienteService;
    private final MercadoriaService mercadoriaService;
    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService, ClienteService clienteService, MercadoriaService mercadoriaService){

        this.clienteService = clienteService;
        this.mercadoriaService = mercadoriaService;
        this.carrinhoService = carrinhoService;

    }

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarAoCarrinho(@RequestParam int idCliente, 
        @RequestParam int idMercadoria,
        @RequestParam int quantidade
    ){

        Mercadoria mercadoria = buscarMercadoria(idMercadoria).get();

        Carrinho carrinho = carrinhoService.adicionar(idCliente, mercadoria, 2);

        return ResponseEntity.ok(carrinho.toString());
    }

    @PostMapping("/remover")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    private Optional<Mercadoria> buscarMercadoria(int id){

        return mercadoriaService.buscarPorId(id);
    }

}

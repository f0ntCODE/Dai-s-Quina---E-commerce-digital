package edu.daisquina.service;

import java.util.Optional;

import edu.daisquina.banco.CarrinhoPersistencia;
import edu.daisquina.banco.ClientePersistencia;
import edu.daisquina.dominio.Carrinho;
import edu.daisquina.dominio.Cliente;
import edu.daisquina.dominio.Mercadoria;

public class CarrinhoService {

    private final CarrinhoPersistencia carrinhoPersistencia;
    private final ClientePersistencia clientePersistencia;

    public CarrinhoService(CarrinhoPersistencia carrinhoPersistencia, 
        ClientePersistencia clientePersistencia){

        this.carrinhoPersistencia = carrinhoPersistencia;
        this.clientePersistencia = clientePersistencia;

    }

    public Carrinho adicionar(int idCliente, Mercadoria mercadoria, Integer quantidade){
        Carrinho carrinho = carrinhoPersistencia.buscarPorCliente(idCliente)
        .orElseGet(() -> {

            Cliente cliente = clientePersistencia.buscarPorId(idCliente)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            Carrinho carrinhoCriado = carrinhoPersistencia.criar(idCliente, cliente);

            return carrinhoCriado;

        });

        carrinho.adicionarAoCarrinho(mercadoria, quantidade);

        return carrinho;
    }

    public Carrinho remover(int idCliente, Mercadoria mercadoria, Integer quantidade){
        Carrinho carrinho = carrinhoPersistencia.buscarPorCliente(idCliente)
        .orElseThrow(() -> new IllegalArgumentException("Carrinho não encontrado"));

        carrinho.removerDoCarrinho(mercadoria, quantidade);

        return carrinho;

    }

    public Optional<Carrinho> buscarPorId(int idCliente){

        return carrinhoPersistencia.buscarPorCliente(idCliente);

    }


}

package edu.daisquina.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.daisquina.banco.CarrinhoPersistencia;
import edu.daisquina.banco.ClientePersistencia;
import edu.daisquina.dominio.Carrinho;
import edu.daisquina.dominio.Cliente;
import edu.daisquina.dominio.Mercadoria;

@Service
public class CarrinhoService {

    private CarrinhoPersistencia carrinhoPersistencia;
    private ClientePersistencia clientePersistencia;

    public CarrinhoService(CarrinhoPersistencia carrinhoPersistencia, 
        ClientePersistencia clientePersistencia){

        this.carrinhoPersistencia = carrinhoPersistencia;
        this.clientePersistencia = clientePersistencia;

    }

    public Carrinho adicionar(int idCliente, Mercadoria mercadoria, Integer quantidade){

        if(idCliente < 0) throw new IllegalArgumentException("Id inválido");
        
        Carrinho carrinho = carrinhoPersistencia.buscarPorCliente(idCliente)
        .orElseGet(() -> {

            Cliente clienteEncontrado = clientePersistencia.buscarPorId(idCliente)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            Carrinho carrinhoCriado = carrinhoPersistencia.criar(idCliente, clienteEncontrado);

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

package edu.daisquina.banco;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import edu.daisquina.dominio.Carrinho;
import edu.daisquina.dominio.Cliente;
import edu.daisquina.repository.CarrinhoInterface;

public class CarrinhoPersistencia implements CarrinhoInterface{

    private Map<Integer, Carrinho> bancoCarrinho = new HashMap<>();

    //criar carrinho
    @Override
    public Carrinho criar(Integer id, Cliente cliente){
        
        Carrinho carrinho = new Carrinho(cliente);

        bancoCarrinho.put(id, carrinho);

        return carrinho;

    }

    @Override
    public void excluir(int id) {

        bancoCarrinho.remove(id);
    
    }

    @Override
    public Optional<Carrinho> buscarPorCliente(int clienteId) {

        return Optional.ofNullable(bancoCarrinho.get(clienteId));
    
    }

}

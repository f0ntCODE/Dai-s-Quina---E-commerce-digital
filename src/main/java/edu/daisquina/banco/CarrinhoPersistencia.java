package edu.daisquina.banco;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import edu.daisquina.dominio.Carrinho;
import edu.daisquina.dominio.Cliente;
import edu.daisquina.repository.CarrinhoInterface;

@Component
public class CarrinhoPersistencia implements CarrinhoInterface{

    private Map<Long, Carrinho> bancoCarrinho = new HashMap<>();

    //criar carrinho
    @Override
    public Carrinho criar(Long id, Cliente cliente){
        
        Carrinho carrinho = new Carrinho(cliente);

        bancoCarrinho.put(id, carrinho);

        return carrinho;

    }

    @Override
    public void excluir(Long id) {

        bancoCarrinho.remove(id);
    
    }

    @Override
    public Optional<Carrinho> buscarPorCliente(Long clienteId) {

        return Optional.ofNullable(bancoCarrinho.get(clienteId));
    
    }

}

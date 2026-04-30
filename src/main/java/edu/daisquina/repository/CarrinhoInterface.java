package edu.daisquina.repository;

import java.util.Optional;

import edu.daisquina.dominio.Carrinho;
import edu.daisquina.dominio.Cliente;

public interface CarrinhoInterface {

    Carrinho criar(Long id, Cliente cliente);

    void excluir(Long id);

    Optional<Carrinho> buscarPorCliente(Long clienteId);

}

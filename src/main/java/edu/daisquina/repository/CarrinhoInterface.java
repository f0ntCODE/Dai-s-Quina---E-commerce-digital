package edu.daisquina.repository;

import java.util.Optional;

import edu.daisquina.dominio.Carrinho;
import edu.daisquina.dominio.Cliente;

public interface CarrinhoInterface {

    Carrinho criar(Integer id, Cliente cliente);

    void excluir(int id);

    Optional<Carrinho> buscarPorCliente(int clienteId);

}

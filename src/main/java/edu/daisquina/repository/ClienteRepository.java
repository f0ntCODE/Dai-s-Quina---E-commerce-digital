package edu.daisquina.repository;

import java.util.List;
import java.util.Optional;

import edu.daisquina.dominio.Cliente;

public interface ClienteRepository {

    Cliente salvar (Cliente cliente);

    Optional<Cliente> buscarPorId(Integer id);

    void excluir(Integer id);

    List<Cliente> listarTodos();

}

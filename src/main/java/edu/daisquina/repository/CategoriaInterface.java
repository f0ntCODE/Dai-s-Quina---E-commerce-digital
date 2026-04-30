package edu.daisquina.repository;

import java.util.List;
import java.util.Optional;

import edu.daisquina.dominio.Categoria;

public interface CategoriaInterface {

    Categoria salvar(Categoria categoria);

    Optional<Categoria> buscarPorId(Long id);

    void excluir(Long id);

    List<Categoria> listarTodos();

}

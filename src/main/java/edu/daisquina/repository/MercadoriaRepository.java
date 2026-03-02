package edu.daisquina.repository;

import java.util.List;
import java.util.Optional;

import edu.daisquina.dominio.Mercadoria;

public interface MercadoriaRepository {

    Mercadoria salvar(Mercadoria mercadoria);

    Optional<Mercadoria> buscarPorId(Integer id);

    void excluir(Integer id);

    List<Mercadoria> listarTudo();

}

package edu.daisquina.repository;

import java.util.List;
import java.util.Optional;

import edu.daisquina.dominio.Mercadoria;

public interface MercadoriaRepository {

    Mercadoria salvar(Mercadoria mercadoria);

    Optional<Mercadoria> buscarPorId(Long id);

    void excluir(Long id);

    List<Mercadoria> listarTudo();

}

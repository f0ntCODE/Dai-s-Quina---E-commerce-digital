package edu.daisquina.repository;

import java.util.List;
import java.util.Optional;

import edu.daisquina.dominio.Pedido;

public interface PedidoRepository {

    Pedido salvar(Pedido pedido);

    Optional<Pedido> buscarPorId(Integer id);

    void excluir(Integer id);

    List<Pedido> listarTudo();
}

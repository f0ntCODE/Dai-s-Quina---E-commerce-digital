package edu.daisquina.banco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.daisquina.dominio.Pedido;
import edu.daisquina.repository.PedidoRepository;

public class PedidoPersistencia implements PedidoRepository{

    private Map<Integer, Pedido> banco = new HashMap<>();

    @Override
    public Pedido salvar(Pedido pedido) {
        
        banco.put(pedido.getId(), pedido);
        
        return pedido;
    }

    @Override
    public Optional<Pedido> buscarPorId(Integer id) {
        
        return Optional.ofNullable(banco.get(id));

    }

    @Override
    public void excluir(Integer id) {
        
        banco.remove(id);

    }

    @Override
    public List<Pedido> listarTudo() {
        
        return new ArrayList<>(banco.values());

    }

}

package edu.daisquina.banco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.daisquina.dominio.Mercadoria;
import edu.daisquina.repository.MercadoriaRepository;

public class MercadoriaPersistencia implements MercadoriaRepository{

    private Map<Integer, Mercadoria> bancoMercadoria = new HashMap<>();

    @Override
    public Mercadoria salvar(Mercadoria mercadoria) {

        bancoMercadoria.put(mercadoria.getId(), mercadoria);
        
        return mercadoria;

    }

    @Override
    public Optional<Mercadoria> buscarPorId(Integer id) {
        
        return Optional.ofNullable(bancoMercadoria.get(id));
    
    }

    @Override
    public void excluir(Integer id) {
        
        bancoMercadoria.remove(id);
    }

    @Override
    public List<Mercadoria> listarTudo() {
        
        return new ArrayList<>(bancoMercadoria.values());

    }

}

package edu.daisquina.banco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.daisquina.dominio.Mercadoria;
import edu.daisquina.repository.MercadoriaRepository;

public class MercadoriaPersistencia implements MercadoriaRepository{

    private Map<Long, Mercadoria> bancoMercadoria = new HashMap<>();

    @Override
    public Mercadoria salvar(Mercadoria mercadoria) {

        bancoMercadoria.put(mercadoria.getId(), mercadoria);
        
        return mercadoria;

    }

    @Override
    public Optional<Mercadoria> buscarPorId(Long id) {
        
        return Optional.ofNullable(bancoMercadoria.get(id));
    
    }

    @Override
    public void excluir(Long id) {
        
        bancoMercadoria.remove(id);
    }

    @Override
    public List<Mercadoria> listarTudo() {
        
        return new ArrayList<>(bancoMercadoria.values());

    }

}

package edu.daisquina.banco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.daisquina.dominio.Categoria;
import edu.daisquina.repository.CategoriaInterface;

public class CategoriaPersistencia implements CategoriaInterface{

    private Map<Integer, Categoria> bancoCategoria = new HashMap<>();

    @Override
    public Categoria salvar(Categoria categoria) {
        
        bancoCategoria.put(categoria.getId(), categoria);
        
        return categoria;

    }

    @Override
    public Optional<Categoria> buscarPorId(Integer id) {
        
        return Optional.ofNullable(bancoCategoria.get(id));

    }

    @Override
    public void excluir(Integer id) {
        
        bancoCategoria.remove(id);

    }

    @Override
    public List<Categoria> listarTodos() {
        
        return new ArrayList<>(bancoCategoria.values());

    }


}

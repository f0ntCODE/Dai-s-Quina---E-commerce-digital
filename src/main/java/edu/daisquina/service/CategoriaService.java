package edu.daisquina.service;

import java.util.Optional;

import edu.daisquina.banco.CategoriaPersistencia;
import edu.daisquina.dominio.Categoria;

public class CategoriaService {

    private final CategoriaPersistencia categoriaPersistencia;

    private int id;

    public CategoriaService(){
        
        this.id = 0;
        categoriaPersistencia = new CategoriaPersistencia();

    }

    public Categoria criar(String nome){

        Categoria novaCategoria = new Categoria(id++, nome);

        categoriaPersistencia.salvar(novaCategoria);

        return novaCategoria;

    }

    public Categoria editar(int id, String nome){
        
        Categoria categoriaEncontrada = categoriaPersistencia.buscarPorId(id)
        .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoriaEncontrada.atualizar(nome);

        return categoriaEncontrada;

    }

    public void excluir(int id){
    categoriaPersistencia.excluir(id);

    }

    public Optional<Categoria> buscarPorId(int id){

        return categoriaPersistencia.buscarPorId(id);

    }

}

package edu.daisquina.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.daisquina.banco.CategoriaPersistencia;
import edu.daisquina.dominio.Categoria;

@Service
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
        System.out.println("Categoria criada: " + novaCategoria.toString());

        return novaCategoria;

    }

    public Categoria editar(int id, String nome){
        
        Categoria categoriaEncontrada = categoriaPersistencia.buscarPorId(id)
        .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoriaEncontrada.atualizar(nome);

        return categoriaEncontrada;

    }

    public void excluir(int id){
        if(buscarPorId(id).isEmpty()){throw new RuntimeException("Categoria não encontrada");}

        categoriaPersistencia.excluir(id);

    }

    public Optional<Categoria> buscarPorId(int id) throws RuntimeException{

        return categoriaPersistencia.buscarPorId(id);

    }

}

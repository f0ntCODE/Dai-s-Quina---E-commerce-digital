package edu.daisquina.service;

import java.util.Optional;

import edu.daisquina.banco.MercadoriaPersistencia;
import edu.daisquina.dominio.Mercadoria;

public class MercadoriaService {

    private int id;

    private final MercadoriaPersistencia mercadoriaPersistencia;


    public MercadoriaService(){
        this.id = 0;
        this.mercadoriaPersistencia = new MercadoriaPersistencia();

    }

    public Mercadoria criar(String nome, String descricao, double preco){

        Mercadoria novaMercadoria = new Mercadoria(id ++, nome, descricao, preco);
        
        mercadoriaPersistencia.salvar(novaMercadoria);

        return novaMercadoria;

    }

    public void excluir(int id){

        mercadoriaPersistencia.excluir(id);

    }

    public Mercadoria editar(int id, String nome, String descricao, double valor){

        Mercadoria mercadoriaEncontrada = mercadoriaPersistencia.buscarPorId(id)
        .orElseThrow(() -> new RuntimeException("Mercadoria não encontrada"));

        mercadoriaEncontrada.atualizar(nome, descricao, valor);

        return mercadoriaEncontrada;
    }

    public Optional<Mercadoria> buscarPorId(int id){

        return mercadoriaPersistencia.buscarPorId(id);
    
    }

}

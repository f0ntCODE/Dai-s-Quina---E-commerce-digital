package edu.daisquina.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.daisquina.banco.MercadoriaPersistencia;
import edu.daisquina.dominio.Categoria;
import edu.daisquina.dominio.Mercadoria;

@Service
public class MercadoriaService {

    private int id;

    private final MercadoriaPersistencia mercadoriaPersistencia;


    public MercadoriaService(){
        this.id = 0;
        this.mercadoriaPersistencia = new MercadoriaPersistencia();

    }

    public Mercadoria criar(String nome, String descricao, Categoria categoria, double preco){

        System.out.println("ENTROU NO MÉTODO DE CRIAÇÃO");
        Mercadoria novaMercadoria = new Mercadoria(id ++, nome, descricao, categoria, preco);
        
        mercadoriaPersistencia.salvar(novaMercadoria);

        System.out.println("Retornando o objeto de nome " + novaMercadoria.toString());

        return novaMercadoria;

    }

    public void excluir(int id){
        Optional<Mercadoria> mercadoriaEncontrada = buscarPorId(id);

        mercadoriaPersistencia.excluir(mercadoriaEncontrada.get().getId());

    }

    public Mercadoria editar(int id, String nome, String descricao, Categoria categoria,double valor){
        System.out.println("Editando infos da mercadoria");

        Mercadoria mercadoriaEncontrada = mercadoriaPersistencia.buscarPorId(id)
        .orElseThrow(() -> new RuntimeException("Mercadoria não encontrada"));

        System.out.println("Mercadoria encontrada: " + mercadoriaEncontrada.toString());

        mercadoriaEncontrada.atualizar(nome, descricao, categoria, valor);

        System.out.println("Dados da mercadoria alteradas para: " + mercadoriaEncontrada.toString());

        return mercadoriaEncontrada;
    }

    public Optional<Mercadoria> buscarPorId(int id){

        return mercadoriaPersistencia.buscarPorId(id);
    
    }

}

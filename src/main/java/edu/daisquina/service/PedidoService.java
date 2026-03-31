package edu.daisquina.service;

import java.util.Optional;
import java.util.Set;

import edu.daisquina.banco.PedidoPersistencia;
import edu.daisquina.dominio.Carrinho;
import edu.daisquina.dominio.Cliente;
import edu.daisquina.dominio.Mercadoria;
import edu.daisquina.dominio.Pedido;

public class PedidoService{

    private PedidoPersistencia pedidoPersistencia;

    private int id;


    public PedidoService(){
        this.pedidoPersistencia = new PedidoPersistencia();
        this.id = 0;
    }

    public Pedido criar(Cliente cliente, Carrinho carrinho){

        Pedido pedido = new Pedido(id++, cliente.getId(), carrinho);

        pedido = pedidoPersistencia.salvar(pedido);

        return pedido;
    }

    public void excluir(int id){

        pedidoPersistencia.excluir(id);

    }


    public Optional<Pedido> buscarPorId(int id){

        return pedidoPersistencia.buscarPorId(id);

    }

}

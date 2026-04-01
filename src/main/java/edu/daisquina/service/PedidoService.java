package edu.daisquina.service;

import java.util.Optional;

import edu.daisquina.banco.PedidoPersistencia;
import edu.daisquina.dominio.Carrinho;
import edu.daisquina.dominio.Cliente;
import edu.daisquina.dominio.Pedido;

public class PedidoService{

    private PedidoPersistencia pedidoPersistencia;

    private int id;


    public PedidoService(){
        this.pedidoPersistencia = new PedidoPersistencia();
        this.id = 0;
    }

    public Pedido criar(Cliente cliente, Carrinho carrinho) throws NullPointerException{

        Pedido pedido = new Pedido(id++, cliente, carrinho.gerarItensPedido());

        pedido = pedidoPersistencia.salvar(pedido);

        return pedido;
    }

    public void excluir(int id){

        pedidoPersistencia.excluir(id);

    }

    public Cliente buscarPedidoPeloCliente(int id){

        Optional<Pedido> pedido = pedidoPersistencia.buscarPorId(id);

        return pedido.get().getCliente();

    }


    public Optional<Pedido> buscarPorId(int id){

        return pedidoPersistencia.buscarPorId(id);

    }

}

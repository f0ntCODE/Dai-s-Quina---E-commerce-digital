package edu.daisquina.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.daisquina.banco.PedidoPersistencia;
import edu.daisquina.dominio.Carrinho;
import edu.daisquina.dominio.Cliente;
import edu.daisquina.dominio.Pedido;
import edu.daisquina.dtos.RequestClienteDTO;
import edu.daisquina.mappers.ClienteMappers.ClienteMapper;

@Service
public class PedidoService{

    private PedidoPersistencia pedidoPersistencia;

    @Autowired
    private ClienteMapper clienteMapper;

    private int id;


    public PedidoService(){
        this.pedidoPersistencia = new PedidoPersistencia();
        this.id = 0;
    }

    public Pedido criar(RequestClienteDTO requestClienteDTO, Carrinho carrinho) throws NullPointerException{

        Cliente cliente = clienteMapper.toEntity(requestClienteDTO);

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

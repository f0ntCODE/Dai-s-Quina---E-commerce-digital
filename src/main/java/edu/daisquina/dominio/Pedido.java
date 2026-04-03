package edu.daisquina.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.daisquina.enumeradores.StatusPagamento;

public class Pedido {

    private int id;
    
    private Cliente cliente;

    private LocalDate dataPedido;

    private double total;

    private StatusPagamento statusPagamento;

    private Set<ItemPedido> mercadorias = new HashSet<>();

    public Pedido(int id, Cliente cliente, Set<ItemPedido> mercadorias) {
        if(cliente == null) throw new IllegalArgumentException("Cliente não pode ser nulo");

        if(mercadorias == null || mercadorias.isEmpty()) throw new IllegalArgumentException("Mercadoria não pode ser nula");

        this.id = id;
        this.cliente = cliente;
        this.mercadorias = Set.copyOf(mercadorias);
        this.dataPedido = LocalDate.now();
        this.statusPagamento = StatusPagamento.PENDENTE;
    }

    public int getTamanhoListaMercadorias(){

        return mercadorias.size();
    }

    public int getId(){
        
        return this.id;

    }

    public List<ItemPedido> getMercadorias() {

        return new ArrayList<>(mercadorias);
    
    }

    public LocalDate getData(){

        return this.dataPedido;
    
    }

    public Double getValorTotal(){

        calcularTotal();

        return this.total;

    }

    public Cliente getCliente(){

        return this.cliente;

    }

    public String getStatusPagamento(){

        return this.statusPagamento.getDescricao();

    }

    public void setStatusPagamento(StatusPagamento status){
        this.statusPagamento = status;
    }

    //****************** PRIVADOS */

    private void calcularTotal(){

        double valorTotal =  mercadorias.stream()
        .mapToDouble(ItemPedido::getSubTotal)
        .sum();

        this.total = valorTotal;
    }
}

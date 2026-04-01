package edu.daisquina.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pedido {

    private int id;
    
    private Cliente cliente;

    private LocalDateTime dataPedido;

    private double subTotal;

    private double frete;

    private double total;

    private Set<ItemPedido> mercadorias = new HashSet<>();

    public Pedido(int id, Cliente cliente, Set<ItemPedido> mercadorias) {
        if(cliente == null) throw new IllegalArgumentException("Cliente não pode ser nulo");

        if(mercadorias == null || mercadorias.isEmpty()) throw new IllegalArgumentException("Mercadoria não pode ser nula");

        this.id = id;
        this.cliente = cliente;
        this.mercadorias = Set.copyOf(mercadorias);
        this.dataPedido = LocalDateTime.now();
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

    public Double getValorTotal(){

        calcularTotal();

        return this.total;

    }

    public Cliente getCliente(){

        return this.cliente;

    }

    private void calcularTotal(){

        double valorTotal =  mercadorias.stream()
        .mapToDouble(ItemPedido::getSubTotal)
        .sum();

        this.total = valorTotal;
    }
}

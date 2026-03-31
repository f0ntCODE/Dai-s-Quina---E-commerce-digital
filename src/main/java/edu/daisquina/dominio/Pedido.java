package edu.daisquina.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Pedido {

    private int id;
    
    private int clienteId;

    private LocalDateTime dataPedido;

    private double subTotal;

    private double frete;

    private double total;

    private Set<ItemPedido> mercadorias = new HashSet<>();

    public Pedido(int id, int clienteId, Set<ItemPedido> mercadorias) {
        if(clienteId == 0) throw new IllegalArgumentException("Cliente não pode ser nulo");

        if(mercadorias == null || mercadorias.isEmpty()) throw new IllegalArgumentException("Mercadoria não pode ser nula");

        this.id = id;
        this.clienteId = clienteId;
        this.mercadorias = Set.copyOf(mercadorias);
        this.dataPedido = LocalDateTime.now();
    }

    public int getTamanhoListaMercadorias(){

        return mercadorias.size();
    }

    public List<ItemPedido> getMercadorias() {

        return new ArrayList<>(mercadorias);
    
    }

    private int calcularTotal(){
        List<Double> itens =  mercadorias.stream()
        .map(ItemPedido::getSubTotal)
        .collect(Collectors.toList());

    }
}

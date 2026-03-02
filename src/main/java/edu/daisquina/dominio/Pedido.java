package edu.daisquina.dominio;

import java.util.HashSet;
import java.util.Set;

public class Pedido {

    private int id;
    
    private String cliente;

    private Set<Mercadoria> mercadorias = new HashSet<>();

    public Pedido(int id, String cliente, Set<Mercadoria> mercadorias) {
        if(cliente == null) throw new IllegalArgumentException("Cliente não pode ser nulo");

        if(mercadorias == null || mercadorias.isEmpty()) throw new IllegalArgumentException("Mercadoria não pode ser nula");

        this.id = id;
        this.cliente = cliente;
        this.mercadorias = mercadorias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    

    public int getTamanhoListaMercadorias(){

        return mercadorias.size();
    }

    public Set<Mercadoria> getMercadorias() {
        return mercadorias;
    }
}

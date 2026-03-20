package edu.daisquina.dominio;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Carrinho {

    private final Cliente cliente;

    private Set<ItemCarrinho> itensCarrinho = new HashSet<>();

    

    public Carrinho(Cliente cliente) {
    
        if(cliente.equals(null))throw new IllegalArgumentException("Cliente não pode ser nulo");

        this.cliente = cliente;
    }

    public void adicionarAoCarrinho(Mercadoria mercadoria, int quantidade){
        Optional<ItemCarrinho> itemExistente = itensCarrinho.stream().filter(i -> 
            i.getMercadoria().equals(mercadoria)).findFirst();

            if(itemExistente.isPresent()){

                itemExistente.get().adicionarQuantidade(quantidade);
            
            }
            else{

                itensCarrinho.add(new ItemCarrinho(mercadoria, quantidade));
            
            }
    }

    public void removerDoCarrinho(Mercadoria mercadoria, int quantidade){

        ItemCarrinho itemExistente = itensCarrinho.stream()
        .filter(i -> 
            i.getMercadoria().equals(mercadoria))
            .findFirst()
            .orElseThrow(() ->
                new IllegalArgumentException("O item a ser removido não existe no carrinho")
            );

        itemExistente.removerQuantidade(quantidade);

        if(itemExistente.getQuantidade() == 0){

            removerItem(itemExistente);

        }

    }

    public Set<ItemCarrinho> listarTodos(){

        return Collections.unmodifiableSet(itensCarrinho);
    
    }

    private void removerItem(ItemCarrinho item){

        itensCarrinho.remove(item);

    }

}

package edu.daisquina.dominio;

public class ItemCarrinho {
    
    private final Mercadoria mercadoria;

    private int quantidade;

    public ItemCarrinho(Mercadoria mercadoria, int quantidade){
        if(mercadoria == null){ throw new IllegalArgumentException("Deve haver mercadoria para o carrinho");};
        if(quantidade <= 0){throw new IllegalArgumentException("Não é permitida quantidade menor que 0");}

        this.mercadoria = mercadoria;
        this.quantidade = quantidade;

    }

    public void adicionarQuantidade(int quantidade){

        this.quantidade += quantidade;

    }

    public Mercadoria getMercadoria() {
        return mercadoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    
}

package edu.daisquina.dominio;

public class ItemPedido {

    private String nomeProduto;

    private Double precoUnitario;

    private int quantidade;

    public ItemPedido(String nomeProduto, Double precoUnitario, int quantidade){

        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;

    }

    public static ItemPedido doCarrinho(ItemCarrinho itemCarrinho){
        return new ItemPedido(
        
        itemCarrinho.getMercadoria().getNome(), 
        itemCarrinho.getMercadoria().getPreco(), 
        itemCarrinho.getQuantidade()
    
        );
    }

    public double getSubTotal(){
        
        return precoUnitario * quantidade;

    }

}

package edu.daisquina.enumeradores;

public enum StatusPagamento {

    PENDENTE("pendente"), 
    PAGO("pago"), 
    RECUSADO("recusado");

    private String status;

    StatusPagamento(String status){
        this.status = status;

    }

    public String getDescricao(){
        return status;

    }

}

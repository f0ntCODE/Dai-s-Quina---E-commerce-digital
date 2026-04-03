package edu.daisquina.factories.pagamento;

import edu.daisquina.dominio.Pedido;

public interface PagamentoInterface {

    public Pedido processarPagamento(Pedido pedido);

}

package edu.daisquina.factories.metodosPagamento;

import edu.daisquina.dominio.Pedido;
import edu.daisquina.enumeradores.StatusPagamento;
import edu.daisquina.factories.pagamento.PagamentoInterface;

public class PixPagamento implements PagamentoInterface{

    @Override
    public Pedido processarPagamento(Pedido pedido) {
        
        pedido.setStatusPagamento(StatusPagamento.PAGO);
        
        return pedido;
    }

    

}

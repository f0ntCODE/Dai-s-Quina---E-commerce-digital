package edu.daisquina.service;

import org.springframework.stereotype.Service;

import edu.daisquina.dominio.Pedido;
import edu.daisquina.factories.PagamentoFactory;
import edu.daisquina.factories.pagamento.PagamentoInterface;

@Service
public class PagamentoService {

    public Pedido realizarPagamento(Pedido pedido, String metodoPagamento){

        PagamentoInterface pagamento =  PagamentoFactory.criar(metodoPagamento);

        pedido = pagamento.processarPagamento(pedido);

        return pedido;

    }

}

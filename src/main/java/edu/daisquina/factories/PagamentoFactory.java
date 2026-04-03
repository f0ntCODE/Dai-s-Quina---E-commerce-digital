package edu.daisquina.factories;

import edu.daisquina.factories.metodosPagamento.CartaoPagamento;
import edu.daisquina.factories.metodosPagamento.PixPagamento;
import edu.daisquina.factories.pagamento.PagamentoInterface;

public class PagamentoFactory {

    public static PagamentoInterface criar(String pagamento){

        if(pagamento.equals("pix")){

            return new PixPagamento();

        }
        else if(pagamento.equals("cartao")){

            return new CartaoPagamento();

        }
        else{throw new IllegalArgumentException("Meio de pagamento inválido");}

    }

}

package pedido_Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.daisquina.dominio.Mercadoria;
import edu.daisquina.dominio.Pedido;
import edu.daisquina.service.PedidoService;

public class Pedido_Test {

    private PedidoService pedidoService;

    @BeforeEach
    public void setup(){
        pedidoService = new PedidoService();
    }

    @Test
    public void test(){}

    @Test
    public void UmPedidoDeveSerCriado(){
        String cliente = "Afonso";
        Mercadoria mesa = new Mercadoria(1, "Mesa de madeira", "Mesa de madeira de cedro", 365.21);
        Mercadoria fogao = new Mercadoria(1, "Fogão 4 bocas", "É um fogão", 124.75);

        Set<Mercadoria> mercadorias = new HashSet<>();
        mercadorias.add(mesa);
        mercadorias.add(fogao);

        Pedido pedidoCriado = pedidoService.criar(cliente, mercadorias);

        List<String> listaMercadorias = new ArrayList<>();

        listaMercadorias = pedidoCriado.getMercadorias()
        .stream()
        .map(Mercadoria :: getNome)
        .toList();

        assertEquals(cliente, pedidoCriado.getCliente());
        assertEquals(2, pedidoCriado.getTamanhoListaMercadorias()); 
        assertTrue(this.encontrarStringNaLista(mesa.getNome(), listaMercadorias));

    }

    @Test
    public void UmPedidoNaoPodeSerCriadoSemUmProduto(){

        assertThrows(IllegalArgumentException.class, () ->{
            pedidoService.criar("Afonso", null);
        });

    }

    @Test
    public void UmPedidoNaoPodeSerCriadoSemUmCliente(){
        Set<Mercadoria> mercadorias = new HashSet<>();

        mercadorias.add(new Mercadoria(1, "Fogão 4 bocas", "É um fogão", 124.75));

        assertThrows(IllegalArgumentException.class, () ->{
            pedidoService.criar(null, mercadorias);
        });

    }

    @Test
    public void UmPedidoDeveSerExcluido(){
        Set<Mercadoria> mercadorias = new HashSet<>();

        mercadorias.add(new Mercadoria(1, "Cadeira", "Para sentar", 89.80));

        String cliente = "Odair";

        Pedido novoPedido = pedidoService.criar(cliente, mercadorias);
        pedidoService.excluir(novoPedido.getId());

        assertTrue(pedidoService.buscarPorId(novoPedido.getId()).isEmpty());

    }

    //helper
    public boolean encontrarStringNaLista(String nomeASerEncontrado, List<String> lista){

        for(String nome : lista){
                if(nome == nomeASerEncontrado){
                    return true;
                }
            }

            return false;
    }

}

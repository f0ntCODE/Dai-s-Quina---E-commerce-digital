package pedido_Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.daisquina.banco.CarrinhoPersistencia;
import edu.daisquina.banco.ClientePersistencia;
import edu.daisquina.dominio.Carrinho;
import edu.daisquina.dominio.Categoria;
import edu.daisquina.dominio.Cliente;
import edu.daisquina.dominio.ItemPedido;
import edu.daisquina.dominio.Mercadoria;
import edu.daisquina.dominio.Pedido;
import edu.daisquina.service.CarrinhoService;
import edu.daisquina.service.CategoriaService;
import edu.daisquina.service.ClienteService;
import edu.daisquina.service.PedidoService;

public class Pedido_Test {

    private PedidoService pedidoService;
    private CategoriaService categoriaService;
    private CarrinhoService carrinhoService;
    private ClienteService clienteService;

    @BeforeEach
    public void setup(){

        ClientePersistencia clientePersistencia = new ClientePersistencia();
        CarrinhoPersistencia carrinhoPersistencia = new CarrinhoPersistencia();

        pedidoService = new PedidoService();
        categoriaService = new CategoriaService();
        this.carrinhoService = new CarrinhoService(carrinhoPersistencia, clientePersistencia);
        this.clienteService = new ClienteService(clientePersistencia);
    }

    @Test
    public void test(){}

    @Test
    public void UmPedidoDeveSerCriado(){

        Cliente cliente = criarCliente();

        Categoria categoria = criarCategoria();

        Mercadoria mesa = new Mercadoria(1, "Mesa de madeira", "Mesa de madeira de cedro", categoria, 365.21);

        Mercadoria fogao = new Mercadoria(1, "Fogão 4 bocas", "É um fogão", categoria, 124.75);

        Carrinho carrinho = carrinhoService.adicionar(cliente, fogao, 1);
        carrinho = carrinhoService.adicionar(cliente, mesa, 1);

        Pedido pedidoCriado = pedidoService.criar(cliente, carrinho);

        List<ItemPedido> itensPedido = new ArrayList<>();

        itensPedido = pedidoCriado.getMercadorias();

        assertEquals(cliente.getNome(), pedidoCriado.getCliente().getNome());
        assertEquals(2, pedidoCriado.getTamanhoListaMercadorias());
        assertTrue(verificarSeMercadoriaExiste("Mesa de madeira", itensPedido));
        assertEquals(489.96, pedidoCriado.getValorTotal());
        assertEquals(LocalDate.now(), pedidoCriado.getData());

    }

    @Test
    public void UmPedidoNaoPodeSerCriadoSemUmProduto(){

        Cliente cliente = criarCliente();

        assertThrows(NullPointerException.class, () ->{

            pedidoService.criar(cliente, null);

        });

    }

    @Test
    public void UmPedidoDeveSerExcluido(){

        Categoria categoria = criarCategoria();
        Cliente cliente = criarCliente();

        Mercadoria mercadoria = new Mercadoria(1, "Cadeira", "Para sentar", categoria, 89.80);

        Carrinho carrinho = carrinhoService.adicionar(cliente, mercadoria, 1);

        Pedido novoPedido = pedidoService.criar(cliente, carrinho);
        pedidoService.excluir(novoPedido.getId());

        assertTrue(pedidoService.buscarPorId(novoPedido.getId()).isEmpty());

    }

    //helper

    private Categoria criarCategoria(){
        
        Categoria categoria = categoriaService.criar("qualquer");

        return categoria;
    }

    private Cliente criarCliente(){
        Cliente cliente = clienteService.criar("Afonso", "af@email.com", "123456a");

        return cliente;
    }

    private boolean verificarSeMercadoriaExiste(String nomeMercadoria, List<ItemPedido> listaItens){

        return listaItens.stream()
        .anyMatch(i -> 
            i.getNome().equals(nomeMercadoria));

    }

}

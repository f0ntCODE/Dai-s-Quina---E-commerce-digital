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
import edu.daisquina.dtos.RequestClienteDTO;
import edu.daisquina.dtos.ResponseClienteDTO;
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
    }

    @Test
    public void test(){}

    @Test
    public void UmPedidoDeveSerCriado(){

        RequestClienteDTO request = new RequestClienteDTO("Afonso", "af@gmail.com", "123456a");

        ResponseClienteDTO cliente =  clienteService.criar(request);


        Categoria categoria = criarCategoria();

        Mercadoria mesa = new Mercadoria(1L, "Mesa de madeira", "Mesa de madeira de cedro", categoria, 365.21);

        Mercadoria fogao = new Mercadoria(1L, "Fogão 4 bocas", "É um fogão", categoria, 124.75);

        Carrinho carrinho = carrinhoService.adicionar(cliente.id(), fogao, 1);
        carrinho = carrinhoService.adicionar(cliente.id(), mesa, 1);

        Pedido pedidoCriado = pedidoService.criar(request, carrinho);

        List<ItemPedido> itensPedido = new ArrayList<>();

        itensPedido = pedidoCriado.getMercadorias();

        assertEquals(cliente.nome(), pedidoCriado.getCliente().getNome());
        assertEquals(2, pedidoCriado.getTamanhoListaMercadorias());
        assertTrue(verificarSeMercadoriaExiste("Mesa de madeira", itensPedido));
        assertEquals(489.96, pedidoCriado.getValorTotal());
        assertEquals(LocalDate.now(), pedidoCriado.getData());

    }

    @Test
    public void UmPedidoNaoPodeSerCriadoSemUmProduto(){

        RequestClienteDTO request = new RequestClienteDTO("Afonso", "af@gmail.com", "123456a");

        ResponseClienteDTO cliente =  clienteService.criar(request);

        assertThrows(NullPointerException.class, () ->{

            pedidoService.criar(request, null);

        });

    }

    @Test
    public void UmPedidoDeveSerExcluido(){

        Categoria categoria = criarCategoria();

        RequestClienteDTO request = new RequestClienteDTO("Afonso", "af@gmail.com", "123456a");

        ResponseClienteDTO cliente =  clienteService.criar(request);

        Mercadoria mercadoria = new Mercadoria(1L, "Cadeira", "Para sentar", categoria, 89.80);

        Carrinho carrinho = carrinhoService.adicionar(cliente.id(), mercadoria, 1);

        Pedido novoPedido = pedidoService.criar(request, carrinho);
        pedidoService.excluir(novoPedido.getId());

        assertTrue(pedidoService.buscarPorId(novoPedido.getId()).isEmpty());

    }

    //helper

    private Categoria criarCategoria(){
        
        Categoria categoria = categoriaService.criar("qualquer");

        return categoria;
    }

    private boolean verificarSeMercadoriaExiste(String nomeMercadoria, List<ItemPedido> listaItens){

        return listaItens.stream()
        .anyMatch(i -> 
            i.getNome().equals(nomeMercadoria));

    }

}

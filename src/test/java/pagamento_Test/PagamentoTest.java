package pagamento_Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.daisquina.banco.CarrinhoPersistencia;
import edu.daisquina.banco.ClientePersistencia;
import edu.daisquina.dominio.Carrinho;
import edu.daisquina.dominio.Categoria;
import edu.daisquina.dominio.Cliente;
import edu.daisquina.dominio.Mercadoria;
import edu.daisquina.dominio.Pedido;
import edu.daisquina.dtos.RequestClienteDTO;
import edu.daisquina.dtos.ResponseClienteDTO;
import edu.daisquina.service.CarrinhoService;
import edu.daisquina.service.CategoriaService;
import edu.daisquina.service.ClienteService;
import edu.daisquina.service.MercadoriaService;
import edu.daisquina.service.PagamentoService;
import edu.daisquina.service.PedidoService;

public class PagamentoTest {
    private PedidoService pedidoService;
    private ClienteService clienteService;
    private MercadoriaService mercadoriaService;
    private CarrinhoService carrinhoService;
    private CategoriaService categoriaService;
    private PagamentoService pagamentoService;


    @BeforeEach
    void setup(){
        CarrinhoPersistencia carrinhoPersistencia = new CarrinhoPersistencia();
        ClientePersistencia clientePersistencia = new ClientePersistencia();

        this.mercadoriaService = new MercadoriaService();
        this.categoriaService = new CategoriaService();

        this.carrinhoService = new CarrinhoService(carrinhoPersistencia, clientePersistencia);

        this.pedidoService = new PedidoService();
        this.pagamentoService = new PagamentoService();

    }

    @Test
    void test(){}

    @Test
    @DisplayName("Testar a simulação do pagamento do pedido")
    void pagamentoDeveSerAprovado(){
        Pedido pedido = criarPedido();

        assertEquals("pendente", pedido.getStatusPagamento());

        pedido = pagamentoService.realizarPagamento(pedido, "pix");

        assertEquals("pago", pedido.getStatusPagamento());

    }

    @Test
    @DisplayName("Testar levantamento de exceção em caso de método de pagamento inexistente")
    void excecaoMeioPagamentoInvalido(){
        
        assertThrows(IllegalArgumentException.class, () ->{
            
            Pedido pedido = criarPedido();
                
            pedido = pagamentoService.realizarPagamento(pedido, "cheque");

        });

    }

    //helper
    private Pedido criarPedido(){

        RequestClienteDTO request = new RequestClienteDTO("Afonso", "af@email.com", "123456a");

        ResponseClienteDTO cliente = clienteService.criar(request);
        
        Categoria moveis = categoriaService.criar("Móveis");
        
        Mercadoria mercadoria = mercadoriaService.criar("Mesa", "Mesa de madeira", moveis, 100.0);

        Carrinho carrinho = carrinhoService.adicionar(cliente.id(), mercadoria, 1);

        Pedido pedido = pedidoService.criar(request, carrinho);

        return pedido;

    }
}

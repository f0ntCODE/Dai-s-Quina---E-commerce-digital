package carrinho_Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.daisquina.banco.CarrinhoPersistencia;
import edu.daisquina.banco.ClientePersistencia;
import edu.daisquina.dominio.Carrinho;
import edu.daisquina.dominio.Categoria;
import edu.daisquina.dominio.Mercadoria;
import edu.daisquina.dtos.RequestClienteDTO;
import edu.daisquina.dtos.ResponseClienteDTO;
import edu.daisquina.service.CarrinhoService;
import edu.daisquina.service.CategoriaService;
import edu.daisquina.service.ClienteService;
import edu.daisquina.service.MercadoriaService;

public class Carrinho_Test {

    private CarrinhoService carrinhoService;

    @Autowired
    private ClienteService clienteService;

    private MercadoriaService mercadoriaService;
    private CategoriaService categoriaService;

    @BeforeEach
    void setup(){
        ClientePersistencia clientePersistencia = new ClientePersistencia();
        CarrinhoPersistencia carrinhoPersistencia = new CarrinhoPersistencia();

        this.carrinhoService = new CarrinhoService(carrinhoPersistencia, clientePersistencia);
        this.mercadoriaService = new MercadoriaService();
        this.categoriaService = new CategoriaService();
    }

    @Test
    void test(){}

    @Test
    @DisplayName("Deve criar carrinho e adicionar item corretamente")
    void deveCriarCarrinhoEAdicionarItem(){

        ResponseClienteDTO cliente = criarCliente();

        Categoria categoria = categoriaService.criar("cozinha");

        Mercadoria fogao = mercadoriaService.criar("Fogão", "Fogão bom", categoria, 125.0);

        Carrinho carrinho = carrinhoService.adicionar(cliente.id(), fogao, 1);

        // valida criação
        assertNotNull(carrinho);

        // valida item adicionado
        assertEquals(1, carrinho.listarTodos().size());

        // adiciona novamente
        carrinhoService.adicionar(cliente.id(), fogao, 1);

        // valida regra de soma de quantidade
        assertEquals(1, carrinho.listarTodos().size());
        assertEquals(2, carrinho.listarTodos().iterator().next().getQuantidade());
    }

    @Test
    @DisplayName("Deve remover um item do carrinho")
    void deveRemoverItemDoCarrinho(){

        ResponseClienteDTO cliente = criarCliente();

        Categoria categoria = categoriaService.criar("cozinha");

        Mercadoria fogao = mercadoriaService.criar("Fogão", "Fogão bom", categoria, 125.0);

        Carrinho carrinho = carrinhoService.adicionar(cliente.id(), fogao, 2);

        assertEquals(2, carrinho.listarTodos().iterator().next().getQuantidade());

        carrinhoService.remover(cliente.id(), fogao, 1);

        assertEquals(1, carrinho.listarTodos().iterator().next().getQuantidade());

        carrinho = carrinhoService.remover(cliente.id(), fogao, 1);

        assertTrue(carrinho.listarTodos().isEmpty());

    }

    @Test
    @DisplayName("Não é possível remover um número maior do que a que existe no carrinho")
    void naoPodeRemoverQuantidadeItemMaiorDaQueExisteNoCarrinho(){

         ResponseClienteDTO cliente = criarCliente();

        Categoria categoria = categoriaService.criar("cozinha");

        Mercadoria fogao = mercadoriaService.criar("Fogão", "Fogão bom", categoria, 125.0);

        carrinhoService.adicionar(cliente.id(), fogao, 2);

        assertThrows(IllegalArgumentException.class, () ->
        
            carrinhoService.remover(cliente.id(), fogao, 3)

        );

    }

    //método helper
    private ResponseClienteDTO criarCliente(){
        RequestClienteDTO request = new RequestClienteDTO("Afonso", "af@email.com", "123456a");

        return clienteService.criar(request);

    }

}

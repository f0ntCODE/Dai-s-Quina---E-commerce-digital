package carrinho_Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.daisquina.banco.CarrinhoPersistencia;
import edu.daisquina.banco.ClientePersistencia;
import edu.daisquina.dominio.Carrinho;
import edu.daisquina.dominio.Categoria;
import edu.daisquina.dominio.Cliente;
import edu.daisquina.dominio.Mercadoria;
import edu.daisquina.service.CarrinhoService;
import edu.daisquina.service.CategoriaService;
import edu.daisquina.service.ClienteService;
import edu.daisquina.service.MercadoriaService;

public class Carrinho_Test {

    private CarrinhoService carrinhoService;
    private ClienteService clienteService;
    private MercadoriaService mercadoriaService;
    private CategoriaService categoriaService;

    @BeforeEach
    void setup(){
        ClientePersistencia clientePersistencia = new ClientePersistencia();
        CarrinhoPersistencia carrinhoPersistencia = new CarrinhoPersistencia();

        this.carrinhoService = new CarrinhoService(carrinhoPersistencia, clientePersistencia);
        this.clienteService = new ClienteService(clientePersistencia);

        this.mercadoriaService = new MercadoriaService();
        this.categoriaService = new CategoriaService();
    }

    @Test
    void test(){}

    @Test
@DisplayName("Deve criar carrinho e adicionar item corretamente")
void deveCriarCarrinhoEAdicionarItem(){

    Cliente cliente = clienteService.criar("Afonso", "af@email.com", "123456a");

    Categoria categoria = categoriaService.criar("cozinha");

    Mercadoria fogao = mercadoriaService.criar("Fogão", "Fogão bom", categoria, 125.0);

    Carrinho carrinho = carrinhoService.adicionar(cliente.getId(), fogao, 1);

    // valida criação
    assertNotNull(carrinho);

    // valida item adicionado
    assertEquals(1, carrinho.listarTodos().size());

    // adiciona novamente
    carrinhoService.adicionar(cliente.getId(), fogao, 1);

    // valida regra de soma de quantidade
    assertEquals(1, carrinho.listarTodos().size());
    assertEquals(2, carrinho.listarTodos().iterator().next().getQuantidade());
}

}

package cliente_Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.daisquina.dominio.Cliente;
import edu.daisquina.service.ClienteService;

public class Cliente_Test {

    private ClienteService clienteService;

    @BeforeEach
    void setup(){
        this.clienteService = new ClienteService();

    }

    @Test
    void test(){}

    //******************criação de conta */

    @Test
    @DisplayName("Cliente deve ser criado")
    public void clienteDeveSerCriadoNoSistema(){
        Cliente novoCliente = criarCliente();

        assertNotNull(clienteService.buscarPorId(novoCliente.getId()));
        assertEquals("Afonso", novoCliente.getNome());

    }

    @Test
    @DisplayName("Cliente não pode ser criado em caso de email inválido")
    public void clienteNaoPodeSerCriadoEmCasoDeEmailInvalido(){


        assertThrows(IllegalArgumentException.class, 
            () -> {
               
                clienteService.criar("Afonso", "afemail.com", "123456a");
            
            });

    }

    /*********************************** */

    @Test
    @DisplayName("Cliente não pode mais existir mais no banco de dados")
    public void clienteDeveSerRemovidoBanco(){

        Cliente novoCliente = criarCliente();

        clienteService.excluir(novoCliente.getId());

        assertTrue(clienteService.buscarPorId(novoCliente.getId()).isEmpty());

    }

    @Test
    @DisplayName("Dados do cliente devem ser modificados")
    public void dadosDoClienteDevemSerModificados(){
        Cliente novoCliente = criarCliente();

        Cliente clienteEditado = clienteService.editar(novoCliente.getId(), "Odair", novoCliente.getEmail(), novoCliente.getSenha());

        assertEquals("Odair", clienteEditado.getNome());

    }

    //helper
    private Cliente criarCliente(){
        Cliente novoCliente = clienteService.criar("Afonso", "af@gmail.com", "123456a");

        return novoCliente;
    }

}

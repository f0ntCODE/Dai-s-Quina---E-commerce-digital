package cliente_Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.daisquina.dtos.RequestClienteDTO;
import edu.daisquina.dtos.ResponseClienteDTO;
import edu.daisquina.service.ClienteService;

@SpringBootTest(classes = edu.daisquina.Main.class)
public class Cliente_Test {

    private ClienteService clienteService;

    @AfterAll
    void setup(){
        this.clienteService = new ClienteService(null, null, null);
    }

    @Test
    void test(){}

    //******************criação de conta */

    @Test
    @DisplayName("Cliente deve ser criado")
    public void clienteDeveSerCriadoNoSistema(){
        ResponseClienteDTO novoCliente = criarCliente();

        assertNotNull(clienteService.buscarPorId(novoCliente.id()));
        assertEquals("Afonso", novoCliente.nome());

    }

    @Test
    @DisplayName("Cliente não pode ser criado em caso de email inválido")
    public void clienteNaoPodeSerCriadoEmCasoDeEmailInvalido(){


        assertThrows(IllegalArgumentException.class, 
            () -> {
               
                RequestClienteDTO request = new RequestClienteDTO("Afonso", "af@email.com", "123456a");

                clienteService.criar(request);
            
            });

    }

    /*********************************** */

    @Test
    @DisplayName("Cliente não pode mais existir mais no banco de dados")
    public void clienteDeveSerRemovidoBanco(){

        ResponseClienteDTO novoCliente = criarCliente();

        clienteService.excluir(novoCliente.id());

        assertTrue(clienteService.buscarPorId(novoCliente.id()).isEmpty());
        
    }

    @Test
    @DisplayName("Dados do cliente devem ser modificados")
    public void dadosDoClienteDevemSerModificados(){
        ResponseClienteDTO novoCliente = criarCliente();
        RequestClienteDTO novosDados = new RequestClienteDTO("Odair", "od@email.com", "123456a");

        ResponseClienteDTO clienteEditado = clienteService.editar(novoCliente.id(), novosDados);

        assertEquals("Odair", clienteEditado.nome());

    }

    //helper
    private ResponseClienteDTO criarCliente(){
        RequestClienteDTO request = new RequestClienteDTO("Afonso", "af@email.com", "123456a");

        ResponseClienteDTO novoCliente = clienteService.criar(request);

        return novoCliente;
    }

}

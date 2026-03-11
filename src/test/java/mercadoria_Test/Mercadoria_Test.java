package mercadoria_Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.daisquina.dominio.Categoria;
import edu.daisquina.dominio.Mercadoria;
import edu.daisquina.service.CategoriaService;
import edu.daisquina.service.MercadoriaService;

public class Mercadoria_Test {

    private MercadoriaService mercadoriaService;
    private CategoriaService categoriaService;

    @BeforeEach
    public void setup(){
        categoriaService = new CategoriaService();
        mercadoriaService = new MercadoriaService();
    }

    @Test
    void test(){}

    @Test
    @DisplayName("Uma nova mercadoria deve ser adicionada ao sistema")
    public void registrarNovaMercadoriaNoSistema(){

        Categoria categoria = criarCategoria();
        
        Mercadoria mercadoria = mercadoriaService.criar(" cadeira ", "Uma cadeira bonita e bem confortável. Seu traseiro irá agradecer", categoria, 210.45);

        Optional<Mercadoria> mercadoriaEncontrada = mercadoriaService.buscarPorId(mercadoria.getId());

        assertEquals("Cadeira", mercadoria.getNome());
        
        assertTrue(mercadoriaEncontrada.isPresent());

    }

    @Test
    @DisplayName("A mercadoria deve ser excluída do sistema")
    public void excluirMercadoriaDoBancoDeDados(){

        Categoria categoria = criarCategoria();
        
        Mercadoria mercadoria = mercadoriaService.criar("Cadeira", "Uma cadeira bonita e bem confortável. Seu traseiro irá agradecer", categoria,210.45);

        mercadoriaService.excluir(mercadoria.getId());

        assertTrue(mercadoriaService.buscarPorId(mercadoria.getId()).isEmpty());

    }

    @Test
    @DisplayName("A mercadoria deve ser editada")
    public void editarDadosDeMercadoria(){

        Categoria categoria = criarCategoria();

        Mercadoria mercadoria = mercadoriaService.criar("Cadira", "Uma cadeira bonita e bem confortável. Seu traseiro irá agradecer", categoria,210.45);

        assertEquals("Cadira", mercadoria.getNome());

        Mercadoria mercadoriaAtualizada = mercadoriaService.editar(mercadoria.getId(), "Cadeira", mercadoria.getDescricao(), categoria, mercadoria.getPreco());

        assertNotEquals("Cadira", mercadoriaAtualizada.getNome());
        
    }

    @Test
    @DisplayName("Mercadoria não pode ser inválida")
    public void mercadoriaNaoPodeSerInvalida(){
        
        Categoria categoria = criarCategoria();

        assertThrows(IllegalArgumentException.class, 

        () ->{
            mercadoriaService.criar("Geladeira", null, categoria,125.50);
        });

    }

    //helper
    private Categoria criarCategoria(){
        
        Categoria categoria = categoriaService.criar("qualquer");
        
        return categoria;
    }

}

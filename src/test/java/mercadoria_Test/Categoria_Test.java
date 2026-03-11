package mercadoria_Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.daisquina.dominio.Categoria;
import edu.daisquina.dominio.Mercadoria;
import edu.daisquina.service.CategoriaService;
import edu.daisquina.service.MercadoriaService;

public class Categoria_Test {

    private CategoriaService categoriaService;
    private MercadoriaService mercadoriaService;

    @Test
    void test(){}
    
    @BeforeEach
    void setup(){

        this.mercadoriaService = new MercadoriaService();
        this.categoriaService = new CategoriaService();
    }


    @Test
    @DisplayName("Um nome para a categoria deve ser definido")
    public void umNomeParaCategoriaDeveSerDefinida(){

        Categoria eletrodomestico = categoriaService.criar("eletrodomestico");

        Mercadoria fogao = mercadoriaService.criar("Fogão", "Um fogão 4 bocas", eletrodomestico, 125.40);

        Categoria categoriaMercadoria = fogao.getCategoria();

        assertEquals(eletrodomestico.getNome(), categoriaMercadoria.getNome());
        assertTrue(categoriaService.buscarPorId(categoriaMercadoria.getId()).isPresent());

    }

    @Test
    @DisplayName("Uma categoria deve ser editada")
    public void umaCategoriaDeveSerEditada(){
        Categoria categoria = categoriaService.criar("esterior");
        
        Categoria categoriaEditada = categoriaService.editar(categoria.getId(), "exterior");

        assertTrue(categoriaService.buscarPorId(categoriaEditada.getId()).isPresent());
        assertEquals("exterior", categoriaEditada.getNome());

    }

    @Test
    @DisplayName("Categoria deve ser excluida")
    public void CategoriaDeveSerRemovida(){
        Categoria categoria = categoriaService.criar("qualquer");

        categoriaService.excluir(categoria.getId());

        assertTrue(categoriaService.buscarPorId(categoria.getId()).isEmpty());
    }

}

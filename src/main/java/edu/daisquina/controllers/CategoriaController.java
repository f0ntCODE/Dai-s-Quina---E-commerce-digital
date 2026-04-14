package edu.daisquina.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.daisquina.dominio.Categoria;
import edu.daisquina.service.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){

        this.categoriaService = categoriaService;
    }

    @GetMapping("/test")
    public ResponseEntity<?> testeRota(){

        return ResponseEntity.ok("FUNCIONANDO");
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarCategoria(@RequestParam String nomeCategoria){

        Categoria categoria = categoriaService.criar(nomeCategoria);

        return ResponseEntity.ok(categoria.getNome());
    }

    @PostMapping("/editar/{categoriaID}")
    public ResponseEntity<String> editarMercadoria(@RequestParam String novoNomeCategoria,
                                                    @PathVariable int categoriaID
){

        Categoria categoria = categoriaService.editar(categoriaID, novoNomeCategoria);

        return ResponseEntity.ok(categoria.getNome());

    }

    @PostMapping("/excluir/{categoriaID}")
    public ResponseEntity<String> excluirCategoria(@PathVariable int categoriaID) {
        
        categoriaService.excluir(categoriaID);
        
        return ResponseEntity.ok("Categoria Excluída");
    }

    @GetMapping("/buscar/{idCategoria}")
    public ResponseEntity<?> buscarPorId(@PathVariable int idCategoria) {

        Optional<Categoria> categoria = categoriaService.buscarPorId(idCategoria);

        return ResponseEntity.ok(categoria.get().getNome());
    }
    
    
}

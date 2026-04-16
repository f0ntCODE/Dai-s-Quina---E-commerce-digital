package edu.daisquina.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.daisquina.dominio.Categoria;
import edu.daisquina.dominio.Mercadoria;
import edu.daisquina.service.CategoriaService;
import edu.daisquina.service.MercadoriaService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/mercadoria")
public class MercadoriaController {

    private final MercadoriaService mercadoriaService;
    private final CategoriaService categoriaService;

    public MercadoriaController(MercadoriaService mercadoriaService, CategoriaService categoriaService){
        
        this.mercadoriaService = mercadoriaService;
        this.categoriaService  = categoriaService;

    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarMercadoria(@RequestParam String nomeMercadoria,
        @RequestParam String descricaoMercadoria,
        @RequestParam int categoriaID,
        @RequestParam double precoMercadoria
     ){

        Categoria categoria = buscarCategoria(categoriaID).get();
        Mercadoria mercadoria = mercadoriaService.criar(nomeMercadoria, descricaoMercadoria, categoria, precoMercadoria);

        return ResponseEntity.ok(mercadoria.getNome());

    }

    @PostMapping("/editar/{mercadoriaID}")
    public ResponseEntity<String> editarMercadoria(@PathVariable int mercadoriaID,
        @RequestParam String nomeMercadoria,
        @RequestParam String descricaoMercadoria,
        @RequestParam int categoriaID,
        @RequestParam double precoMercadoria
    ){

        Categoria categoria = buscarCategoria(categoriaID).get();

        Mercadoria novaMercadoria = mercadoriaService.editar(mercadoriaID, nomeMercadoria, descricaoMercadoria, categoria, precoMercadoria);

        return ResponseEntity.ok(novaMercadoria.getNome());

    }

    @PostMapping("/excluir/{mercadoriaID}")
    public ResponseEntity<String> excluirMercadoria(@PathVariable int mercadoriaID){
        mercadoriaService.excluir(mercadoriaID);
        
        return ResponseEntity.ok("Excluído");
    }

    @GetMapping("/buscar/{mercadoriaID}")
    public ResponseEntity<String> buscarMercadoria(@PathVariable int mercadoriaID) {

        Optional<Mercadoria> mercadoriaEncontrada = mercadoriaService.buscarPorId(mercadoriaID);
        
        return ResponseEntity.ok(mercadoriaEncontrada.toString());
    }
    

    //método de utilidade

    private Optional<Categoria> buscarCategoria(int categoriaID){

        return categoriaService.buscarPorId(categoriaID);
    }

}

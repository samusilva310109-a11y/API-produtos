package com.samuel.apiprodutos.controller;

import com.samuel.apiprodutos.model.entity.Produto;
import com.samuel.apiprodutos.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto){
        System.out.println("Produto salvo: " + produto);

        var id = UUID.randomUUID().toString();
        produto.setId(id);

        produtoRepository.save(produto);
        return  produto;
    }

    @GetMapping
    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Produto buscarProduto(@PathVariable("id") String id){
        /*Optional<Produto> produto = produtoRepository.findById(id);
        return produto.isPresent() ? produto.get() : null; <- forma mais longa*/

        return produtoRepository.findById(id).orElse(null); //<- Forma mais compacta
    }

    @DeleteMapping("/{id}")
    public void excluirProdutoPorId(@PathVariable("id") String id){
        produtoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable("id") String id, @RequestBody Produto produtoAtualizado){
        produtoAtualizado.setId(id);
        return produtoRepository.save(produtoAtualizado);
    }

}

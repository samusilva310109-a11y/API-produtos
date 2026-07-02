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

    @GetMapping("/")
    public  List<Produto> buscarPorNome(@RequestParam("nome") String nome){
        return produtoRepository.findByNome(nome);
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

    /*
    * Spring Annotations:
    *
    * Métodos HTTP:
    *   @GetMapping() -> Define uma requisição do tipo GET
    *   @PostMapping() -> Define uma requisição do tipo POST
    *   @DeleteMapping() -> Define uma requisição do tipo DELETE
    *   @PutMapping() -> Define uma requisição do tipo PUT
    *
    *   @RequestBody -> Define que os dados enviados no corpo da requisição em JSON devem serem convertidos em formato Obejeto Java
    *   @PathVariable("<variável>") -> Define uma variável params no Endpoint. Tem como parâmetro o nome da variável em específico.
    *   @RequestParam("<query_parms>") -> Define uma variável query params no Endpoint. Tem como parâmetro o nome da variável em específico.
    *
    *   @RequestMapping() -> Utilizada em classes para estabelecer uma rota base para os métodos da classe.
    *                       Define a rota final e o método HTTP
    *
    *   @RestController -> Define uma classe como camada de Controller de API RestFul
    *   @Entity -> Define uma classe com uma entidade no banco de dados
    *   @Table("<nome_tabela>") -> Define a tabela vinculada a uma classe
    *   @Column() -> Define uma variável como uma coluna na tabela
    *   @Id() -> Define uma variável como identificador da tabela
    */
}

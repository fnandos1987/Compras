/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras;

import br.com.fernando.compras.model.Produto;
import br.com.fernando.compras.model.Produto;
import br.com.fernando.compras.repository.ProdutoRepository;
import br.com.fernando.compras.resource.ProdutoResource;
import br.com.fernando.compras.resource.ProdutoResource;
import br.com.fernando.compras.resource.ProdutoResourceAssembler;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fernando.schwambach
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoRestController {
    
    @Autowired
    ProdutoRepository repository;
    
    ProdutoResourceAssembler assembler = new ProdutoResourceAssembler();
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ProdutoResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResource> get(@PathVariable Long id) {
        Produto produto = repository.findOne(id);
        if (produto != null) {
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProdutoResource>> findByNome(@PathVariable String nome) {
        return new ResponseEntity<>(assembler.toResources(repository.findByNomeContaining(nome)), HttpStatus.OK);
    }
    
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<ProdutoResource>> findByMarca(@PathVariable String marca) {
        return new ResponseEntity<>(assembler.toResources(repository.findByMarca(marca)), HttpStatus.OK);
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<List<ProdutoResource>> findByDataFabricacao(@PathVariable Date data) {
        return new ResponseEntity<>(assembler.toResources(repository.findByDataFabricao(data)), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<ProdutoResource> create(@RequestBody Produto produto) {
        produto = repository.save(produto);
        if (produto != null) {
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResource> update(@PathVariable Long id, @RequestBody Produto produto) {
        if (produto != null) {
            produto.setProdutoId(id);
            produto = repository.save(produto);
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_MANAGER")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoResource> delete(@PathVariable Long id) {
        Produto produto = repository.findOne(id);
        if (produto != null) {
            repository.delete(produto);
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
}

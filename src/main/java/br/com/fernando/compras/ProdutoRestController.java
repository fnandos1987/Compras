/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras;

import br.com.fernando.compras.model.Produto;
import br.com.fernando.compras.repository.ProdutoRepository;
import br.com.fernando.compras.resource.ProdutoResource;
import br.com.fernando.compras.resource.ProdutoResourceAssembler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Secured("ROLE_USER")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "retorna todos os produtos")
    public ResponseEntity<List<ProdutoResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @GetMapping("/{id}")
    @ApiOperation(value = "retorna um produto conforme o id informado")
    @ApiParam(value = "id", required = true)
    public ResponseEntity<ProdutoResource> get(@PathVariable Long id) {
        Produto produto = repository.findOne(id);
        if (produto != null) {
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured("ROLE_USER")
    @GetMapping("/nome/{nome}")
    @ApiOperation(value = "retorna um produto conforme o nome")
    @ApiParam(value = "nome", required = true)
    public ResponseEntity<List<ProdutoResource>> findByNome(@PathVariable String nome) {
        return new ResponseEntity<>(assembler.toResources(repository.findByNomeContaining(nome)), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @GetMapping("/marca/{marca}")
    @ApiOperation(value = "retorna um produto conforme a marca")
    @ApiParam(value = "marca", required = true)
    public ResponseEntity<List<ProdutoResource>> findByMarca(@PathVariable String marca) {
        return new ResponseEntity<>(assembler.toResources(repository.findByMarca(marca)), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @GetMapping("/data/{data}")
    @ApiOperation(value = "retorna um produto conforme o data informada", notes = "informar a data no formato aaaa-MM-dd")
    @ApiParam(value = "data", required = true)
    public ResponseEntity<List<ProdutoResource>> findByDataFabricacao(@PathVariable String data) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = (Date) formatter.parse(data);

        return new ResponseEntity<>(assembler.toResources(repository.findByDataFabricacao(date)), HttpStatus.OK);
    }

    @Secured("ROLE_MANAGER")
    @PostMapping
    @ApiOperation(value = "cria um novo produto")
    @ApiParam(value = "produto", required = true)
    public ResponseEntity<ProdutoResource> create(@RequestBody Produto produto) {
        produto = repository.save(produto);
        if (produto != null) {
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_MANAGER")
    @PutMapping("/{id}")
    @ApiOperation(value = "altera um produto")
    public ResponseEntity<ProdutoResource> update(@ApiParam(value = "id", required = true) @PathVariable Long id, 
                                                  @ApiParam(value = "produto", required = true) @RequestBody Produto produto) {
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
    @ApiOperation(value = "exclui um produto")
    @ApiParam(value = "id", required = true)
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

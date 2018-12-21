/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras;

import br.com.fernando.compras.model.Endereco;
import br.com.fernando.compras.repository.EnderecoRepository;
import br.com.fernando.compras.resource.EnderecoResourceAssembler;
import br.com.fernando.compras.resource.EnderecoResource;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/enderecos")
public class EnderecoRestController {
    
    @Autowired
    EnderecoRepository repository;
    
    EnderecoResourceAssembler assembler = new EnderecoResourceAssembler();
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "retorna todos os endereços")
    public ResponseEntity<List<EnderecoResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "retorna um endereço conforme o id informado")
    @ApiParam(value = "id", required = true)
    public ResponseEntity<EnderecoResource> get(@PathVariable Long id) {
        Endereco endereco = repository.findOne(id);
        if (endereco != null) {
            return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    @ApiOperation(value = "cria um novo endereço")
    @ApiParam(value = "endereco", required = true)
    public ResponseEntity<EnderecoResource> create(@RequestBody Endereco endereco) {
        endereco = repository.save(endereco);
        if (endereco != null) {
            return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value = "altera um endereço")    
    public ResponseEntity<EnderecoResource> update(@ApiParam(value = "id", required = true) @PathVariable Long id, 
                                                   @ApiParam(value = "endereco", required = true) @RequestBody Endereco endereco) {
        if (endereco != null) {
            endereco.setEnderecoId(id);
            endereco = repository.save(endereco);
            return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "exclui um endereço")
    @ApiParam(value = "id", required = true)
    public ResponseEntity<EnderecoResource> delete(@PathVariable Long id) {
        Endereco endereco = repository.findOne(id);
        if (endereco != null) {
            repository.delete(endereco);
            return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
}

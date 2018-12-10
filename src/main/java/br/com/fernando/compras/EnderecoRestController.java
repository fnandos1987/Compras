/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras;

import br.com.fernando.compras.model.Endereco;
import br.com.fernando.compras.repository.EnderecoRepository;
import br.com.fernando.compras.resource.EnderecoResource;
import br.com.fernando.compras.resource.EnderecoResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResource> get(@PathVariable Long id) {
        Endereco endereco = repository.findOne(id);
        if (endereco != null) {
            return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras;

import br.com.fernando.compras.repository.ClienteRepository;
import br.com.fernando.compras.resource.ClienteResourceAssembler;
import br.com.fernando.compras.resource.ClienteResource;
import br.com.fernando.compras.model.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fernando.schwambach
 */
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
    
    @Autowired
    ClienteRepository repository;

    ClienteResourceAssembler assembler = new ClienteResourceAssembler();
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ClienteResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResource> get(@PathVariable Long id) {
        Cliente cliente = repository.findOne(id);
        if (cliente != null) {
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResource> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        if (cliente != null) {
            cliente.setClienteId(id);
            cliente = repository.save(cliente);
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_MANAGER")
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResource> delete(@PathVariable Long id) {
        Cliente cliente = repository.findOne(id);
        if (cliente != null) {
            repository.delete(cliente);
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
}

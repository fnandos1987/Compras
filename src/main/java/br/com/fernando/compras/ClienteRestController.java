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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/clientes")
public class ClienteRestController {
    
    @Autowired
    ClienteRepository repository;

    ClienteResourceAssembler assembler = new ClienteResourceAssembler();
    
    @Secured("ROLE_USER")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "retorna todos os clientes")
    public ResponseEntity<List<ClienteResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }
    
    @Secured("ROLE_USER")
    @GetMapping("/{id}")
    @ApiOperation(value = "retorna um cliente conforme o id informado")
    @ApiParam(value = "id", required = true)
    public ResponseEntity<ClienteResource> get(@PathVariable Long id) {
        Cliente cliente = repository.findOne(id);
        if (cliente != null) {
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @Secured("ROLE_USER")
    @GetMapping("/rua/{rua}")
    @ApiOperation(value = "retorna um cliente conforme a rua do endereço vinculado a ele")
    @ApiParam(value = "rua", required = true)
    public ResponseEntity<List<ClienteResource>> findByRua(@PathVariable String rua) {
        return new ResponseEntity<>(assembler.toResources(repository.findByRua(rua)), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @GetMapping("/cidade/{cidade}")
    @ApiOperation(value = "retorna um cliente conforme a cidade do endereço vinculado a ele")
    @ApiParam(value = "cidade", required = true)
    public ResponseEntity<List<ClienteResource>> findByCidade(@PathVariable String cidade) {
        return new ResponseEntity<>(assembler.toResources(repository.findByCidade(cidade)), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @GetMapping("/estado/{estado}")
    @ApiOperation(value = "retorna um cliente conforme o estado do endereço vinculado a ele")
    @ApiParam(value = "estado", required = true)
    public ResponseEntity<List<ClienteResource>> findByEsatdo(@PathVariable String estado) {
        return new ResponseEntity<>(assembler.toResources(repository.findByEstado(estado)), HttpStatus.OK);
    }
    
    @Secured("ROLE_MANAGER")
    @PostMapping
    @ApiOperation(value = "Cria um novo cliente")
    @ApiParam(value = "cliente", required = true)
    public ResponseEntity<ClienteResource> create(@RequestBody Cliente cliente) {
        cliente = repository.save(cliente);
        if (cliente != null) {
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
    @Secured("ROLE_MANAGER")
    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um cliente")    
    public ResponseEntity<ClienteResource> update(@ApiParam(value = "id", required = true) @PathVariable Long id, 
                                                  @ApiParam(value = "cliente", required = true) @RequestBody Cliente cliente) {
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
    @ApiOperation(value = "exclui um cliente")
    @ApiParam(value = "id", required = true)
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

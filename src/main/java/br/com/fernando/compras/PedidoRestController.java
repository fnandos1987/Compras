/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras;

import br.com.fernando.compras.model.Item;
import br.com.fernando.compras.model.Pedido;
import br.com.fernando.compras.repository.ItemRepository;
import br.com.fernando.compras.repository.PedidoRepository;
import br.com.fernando.compras.resource.PedidoResource;
import br.com.fernando.compras.resource.PedidoResourceAssembler;
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
@RequestMapping("/pedidos")
public class PedidoRestController {
    
    @Autowired
    PedidoRepository repository;
    
    @Autowired
    ItemRepository itemRepository;
    
    PedidoResourceAssembler assembler = new PedidoResourceAssembler();
    
    @Secured("ROLE_USER")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "retorna todos os pedidos")
    public ResponseEntity<List<PedidoResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }
    
    @Secured("ROLE_USER")
    @GetMapping("/{numero}")
    @ApiOperation(value = "retorna um pedido conforme o número informado")
    @ApiParam(value = "numero", required = true)
    public ResponseEntity<PedidoResource> get(@PathVariable Long numero) {
        Pedido pedido = repository.findOne(numero);
        if (pedido != null) {
            return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @Secured("ROLE_USER")
    @PostMapping
    @ApiOperation(value = "Cria um novo pedido com seus respectivos itens")
    @ApiParam(value = "pedido", required = true)
    public ResponseEntity<PedidoResource> create(@RequestBody Pedido pedido) {
        pedido = repository.save(pedido);
        if (pedido != null) {
            createItens(pedido);
            return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
    private void createItens(Pedido pedido) {
        for (Item item: pedido.getItens()) {
            item.setPedido(pedido);
            itemRepository.save(item);
        }      
    }
    
    @Secured("ROLE_USER")
    @PutMapping("/{numero}")
    @ApiOperation(value = "Cria um pedido")
    public ResponseEntity<PedidoResource> update(@ApiParam(value = "numero", required = true) @PathVariable Long numero, 
                                                 @ApiParam(value = "pedido", required = true) @RequestBody Pedido pedido) {
        if (pedido != null) {
            pedido.setNumero(numero);
            pedido = repository.save(pedido);
            return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_MANAGER")
    @DeleteMapping("/{numero}")
    @ApiOperation(value = "exclui um pedido")
    @ApiParam(value = "pedido", required = true)
    public ResponseEntity<PedidoResource> delete(@PathVariable Long numero) {
        Pedido pedido = repository.findOne(numero);
        if (pedido != null) {
            repository.delete(pedido);
            return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
}

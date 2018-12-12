/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras;

import br.com.fernando.compras.model.Pedido;
import br.com.fernando.compras.repository.PedidoRepository;
import br.com.fernando.compras.resource.PedidoResource;
import br.com.fernando.compras.resource.PedidoResourceAssembler;
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
@RequestMapping("/pedidos")
public class PedidoRestController {
    
    @Autowired
    PedidoRepository repository;
    
    PedidoResourceAssembler assembler = new PedidoResourceAssembler();
    
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResource> get(@PathVariable Long id) {
        Pedido endereco = repository.findOne(id);
        if (endereco != null) {
            return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

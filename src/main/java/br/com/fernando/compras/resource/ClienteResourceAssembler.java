/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras.resource;

import br.com.fernando.compras.ClienteRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import br.com.fernando.compras.model.Cliente;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author fernando.schwambach
 */
public class ClienteResourceAssembler extends ResourceAssemblerSupport<Cliente, ClienteResource> {

    public ClienteResourceAssembler() {
        super(Cliente.class, ClienteResource.class);
    }   

    @Override
    protected ClienteResource instantiateResource(Cliente cliente) {
        return new ClienteResource(cliente);
    }

    @Override
    public ClienteResource toResource(Cliente cliente) {
        return new ClienteResource(cliente, linkTo(methodOn(ClienteRestController.class).get(cliente.getClienteId())).withSelfRel());
    }
    
}

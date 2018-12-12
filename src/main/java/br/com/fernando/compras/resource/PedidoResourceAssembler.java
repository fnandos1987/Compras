/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras.resource;

import br.com.fernando.compras.PedidoRestController;
import br.com.fernando.compras.model.Pedido;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 *
 * @author fernando.schwambach
 */
public class PedidoResourceAssembler extends ResourceAssemblerSupport<Pedido, PedidoResource> {

    public PedidoResourceAssembler() {
        super(Pedido.class, PedidoResource.class);
    }   

    @Override
    protected PedidoResource instantiateResource(Pedido pedido) {
        return new PedidoResource(pedido);
    }

    @Override
    public PedidoResource toResource(Pedido pedido) {
        return new PedidoResource(pedido, linkTo(methodOn(PedidoRestController.class).get(pedido.getNumero())).withSelfRel());
    }
    
}

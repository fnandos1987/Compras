/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras.resource;

import br.com.fernando.compras.ProdutoRestController;
import br.com.fernando.compras.model.Produto;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 *
 * @author fernando.schwambach
 */
public class ProdutoResourceAssembler extends ResourceAssemblerSupport<Produto, ProdutoResource> {

    public ProdutoResourceAssembler() {
        super(Produto.class, ProdutoResource.class);
    }   

    @Override
    protected ProdutoResource instantiateResource(Produto produto) {
        return new ProdutoResource(produto);
    }

    @Override
    public ProdutoResource toResource(Produto produto) {
        return new ProdutoResource(produto, linkTo(methodOn(ProdutoRestController.class).get(produto.getProdutoId())).withSelfRel());
    }
    
}

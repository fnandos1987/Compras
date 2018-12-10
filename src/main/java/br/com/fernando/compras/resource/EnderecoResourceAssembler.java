/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras.resource;

import br.com.fernando.compras.EnderecoRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import br.com.fernando.compras.model.Endereco;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author fernando.schwambach
 */
public class EnderecoResourceAssembler extends ResourceAssemblerSupport<Endereco, EnderecoResource> {

    public EnderecoResourceAssembler() {
        super(Endereco.class, EnderecoResource.class);
    }   

    @Override
    protected EnderecoResource instantiateResource(Endereco endereco) {
        return new EnderecoResource(endereco);
    }

    @Override
    public EnderecoResource toResource(Endereco endereco) {
        return new EnderecoResource(endereco, linkTo(methodOn(EnderecoRestController.class).get(endereco.getEnderecoId())).withSelfRel());
    }
    
}

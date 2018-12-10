/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import br.com.fernando.compras.model.Produto;

/**
 *
 * @author fernando.schwambach
 */
public class ProdutoResource extends Resource<Produto> {
    
    public ProdutoResource(Produto content, Link... links) {
        super(content, links);
    }
    
}

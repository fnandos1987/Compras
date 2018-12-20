/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras.repository;

import br.com.fernando.compras.model.Produto;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author fernando.schwambach
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    List<Produto> findByNomeContaining(String nome);
    
    List<Produto> findByMarca(String marca);
    
    List<Produto> findByDataFabricacao(Date data);   
    
}

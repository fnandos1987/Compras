/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fernando.compras.model.Cliente;

/**
 *
 * @author fernando.schwambach
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fernando.compras.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author fernando.schwambach
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c, Endereco e WHERE c.clienteId = e.clienteId and e.rua = :rua")
    List<Cliente> findByRua(@Param("rua") String rua);
    
    @Query("SELECT c FROM Cliente c, Endereco e WHERE c.clienteId = e.clienteId and e.cidade = :cidade")
    List<Cliente> findByCidade(@Param("cidade") String cidade);
    
    @Query("SELECT c FROM Cliente c, Endereco e WHERE c.clienteId = e.clienteId and e.estado = :estado")
    List<Cliente> findByEstado(@Param("estado") String estado);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author fernando.schwambach
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @ManyToOne
    @JoinColumn(name="numero", nullable=false)
    @JsonIgnore
    Pedido pedido;
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long itemId;
    Integer quantidade;
    Double total;        
    Long produtoId;
    
}

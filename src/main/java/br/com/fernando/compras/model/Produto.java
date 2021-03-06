/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
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
public class Produto {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long produtoId;
    String nome;
    String descricao;
    String marca;
    Double valor;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dataFabricacao;
}

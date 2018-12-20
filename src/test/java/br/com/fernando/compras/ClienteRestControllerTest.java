/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.compras;

import br.com.fernando.compras.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author fernando.schwambach
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ComprasApplication.class)
@WebMvcTest(ClienteRestController.class)
public class ClienteRestControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper jsonParser;

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].clienteId", equalTo(1)))
                .andExpect(jsonPath("$.[0].nome", equalTo("John Doe")))
                .andExpect(jsonPath("$.[1].clienteId", equalTo(2)))
                .andExpect(jsonPath("$.[1].nome", equalTo("Steve")));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/clientes/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.clienteId", equalTo(1)))
                .andExpect(jsonPath("$.nome", equalTo("John Doe")));
    }

    @Test
    public void testCreate() throws Exception {
        Cliente cliente = new Cliente(null, "John Doe", "john@gmail.com", "000.000.000-00", new Date());
        mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonParser.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.clienteId", equalTo(6)))
                .andExpect(jsonPath("$.nome", equalTo("John Doe")));
    }
    
}

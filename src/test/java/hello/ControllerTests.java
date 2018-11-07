/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hello;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired 
    private ObjectMapper mapper;
    @Autowired
    EnderecoService end;
    
   
    //Teste de integração para requisição de lista de endereço 
    @Test
    public void listaEnderecos() throws Exception {
    	end.save(new Endereco("av. Paulista", "Centro", "São Paulo", "SP", "Brasil", 1234));
    	this.mockMvc.perform(get("/enderecos"))
        		.andDo(print()).andExpect(status().isOk());
                }
    
    //Teste de integração para requisição de consultar endereço
    @Test
    public void retornaEnderecoTest() throws Exception {
    	end.save(new Endereco("av. Paulista", "Centro", "São Paulo", "SP", "Brasil", 1234));
    	this.mockMvc.perform(get("/endereco/{cep}",1234)
    			.contentType(MediaType.APPLICATION_JSON))
    			.andDo(print()).andExpect(status().isOk());
                }

    //Teste de integração para requisição de inserir endereço
    @Test
    public void gravaEnderecoTest() throws Exception {
    	Endereco c = new Endereco("av. Paulista", "Centro", "São Paulo", "SP", "Brasil", 1234);
        String json = mapper.writeValueAsString(c);
    	this.mockMvc.perform(post("/endereco")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(json))
    			.andDo(print())
    			.andExpect(status().isCreated());
               }

   
    //Teste de integração para requisição de deletar endereço
    @Test
    public void deletaEnderecoTeste() throws Exception {
    	Endereco c = new Endereco("av. Paulista", "Centro", "São Paulo", "SP", "Brasil", 1234);
    	end.save(new Endereco("av. Paulista", "Centro", "São Paulo", "SP", "Brasil", 1234));
    	this.mockMvc.perform(delete("/endereco{cep}",1234)
    			.contentType(MediaType.APPLICATION_JSON))
    			.andDo(print())
    			.andExpect(status().isNoContent());
               }
	

    
}


package hello;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
    	end.save(new Endereco("av. Paulista", "Centro", "São Paulo", "SP", "Brasil", 1234));
    	this.mockMvc.perform(delete("/endereco{cep}",1234)
    			.contentType(MediaType.APPLICATION_JSON))
    			.andDo(print())
    			.andExpect(status().isNoContent());
               }
	

    
}

package hello;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Classe responsavel por mapear as requisições HTTP
@RestController
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	//Requisição para retornar todos os endereços
	@RequestMapping(value= "/enderecos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Endereco> getAll() {
		return enderecoService.getAllEndereco();
    }
	
	//Requisição para retornar endereço de acordo com Cep passado como parametro
	@RequestMapping(value= "/endereco/{cep}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes ="application/json")
    public Endereco endereco(@PathVariable("cep") int cep) {
		return enderecoService.getEnderecoId(cep);
    }
	
	//Requisição para gravar um novo endereço
	@RequestMapping(value= "/endereco", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes ="application/json")
    public ResponseEntity<?> endereco(@RequestBody Endereco endereco) {
		try {
			enderecoService.save(endereco);
			return new ResponseEntity<Endereco> (HttpStatus.CREATED);
		} catch (Exception e) {
			//Captura a Exception tratada na classe enderecoService;
			e.printStackTrace();
		}
		//
		return new ResponseEntity<String>("{\"status\": \"erro\" , \"mensagem\": \"verifique se foram passados todos atributos\" }", HttpStatus.BAD_REQUEST);
		
    }
	
	//Requisição para deletar um endereço de acordo com Cep passado como variavel
	@RequestMapping(value= "/endereco{cep}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes ="application/json")
    public ResponseEntity<?> removeEndereco(@PathVariable("cep") int cep) {
		if (enderecoService.removeEndereco(cep)) return new ResponseEntity<Endereco>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<String>("{\"status\": \"erro\" , \"mensagem\": \"verifique o cep informado\" }", HttpStatus.BAD_REQUEST);
		
    }
	
	
	
	
}

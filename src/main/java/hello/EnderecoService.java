package hello;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

@Service("autorizacaoService")
public class EnderecoService {

	//Criação da lista que armazena os endereços
	private static List<Endereco> enderecos = new LinkedList<>();
	
	//Método que retorna todos endereços armazenados na lista
	public List<Endereco> getAllEndereco() {
		return this.enderecos;
	}
	
	//Metodo para salvar o endereço na Lista de endereços
	public void save(Endereco endereco) throws Exception {
		//Laço para a checagem se exste algum atributos null
		for (Field f : endereco.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (Objects.isNull(f.get(endereco)))
                		//adicionando Exception
                		throw new Exception("Null");
                }
            finally {
            }   
         
            }
			enderecos.add(endereco);
		}
	
	//Metodo para retornar um cep de acordo com seu numero
	public Endereco getEnderecoId(int cep) {
		for (Endereco e: enderecos)
			if (e.getCep() == cep) return e;
		return null;
	}
	
	//Metodo para deletar um endereço
	public boolean removeEndereco(int cep) {
		for (Endereco e: enderecos)
			if (e.getCep() == cep) {
				enderecos.remove(e);
				return true;
			}
		return false;
	}
	
}

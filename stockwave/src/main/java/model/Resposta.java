package model;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;

/**
 * Classe que representa uma resposta de uma questão.
 * 
 * Métodos:
 * - Construtores: permitem criar instâncias da classe com diferentes conjuntos de atributos.
 * 
 * Exemplo de uso:
 * 
 * Resposta resposta = new Resposta();
 * resposta.setResposta("Brasília");
 * 
 * System.out.println(resposta.getResposta()); // Imprime a resposta
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see services.RespostaService
 * @see dao.RespostaDao
 * @see controller.RespostaResource
 * 
 * @author Stockwave
 */
public class Resposta {
	
	/**
	 * A resposta da questão.
	 */
	@NotNull(message = "A resposta não pode ser nula.")
	private String resposta;

	/**
	 * Obtém a resposta da questão.
	 * 
	 * @return a resposta da questão
	 */
	public String getResposta() {
		return resposta;
	}

	/**
	 * Define a resposta da questão.
	 * 
	 * @param resposta a resposta da questão
	 */
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	/**
	 * Construtor padrão da classe Resposta.
	 */
	public Resposta() {
		super();
	}
	
	/**
	 * Construtor da classe Resposta que define a resposta.
	 * 
	 * @param resposta a resposta da questão
	 */
	public Resposta(@NotNull(message = "A resposta não pode ser nula.") String resposta) {
		super();
		this.resposta = resposta;
	}
	
	/**
	 * Retorna o código hash da resposta.
	 * 
	 * @return o código hash da resposta
	 */
	@Override
	public int hashCode() {
		return Objects.hash(resposta);
	}
	
	/**
	 * Verifica se a resposta é igual a outro objeto.
	 * 
	 * @param obj o objeto a ser comparado
	 * @return true se a resposta for igual ao objeto fornecido, false caso contrário
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resposta other = (Resposta) obj;
		return Objects.equals(resposta, other.resposta);
	}
	
	/**
	 * Retorna uma representação em formato de string da resposta.
	 * 
	 * @return uma representação em formato de string da resposta
	 */
	@Override
	public String toString() {
		return "Resposta [resposta=" + resposta + "]";
	}
}

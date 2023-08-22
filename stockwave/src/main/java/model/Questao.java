package model;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;

/**
 * Classe que representa uma questão (exercício).
 * 
 * Métodos:
 * - Construtores: permitem criar instâncias da classe com diferentes conjuntos de atributos.
 * 
 * Exemplo de uso:
 * 
 * Questao questao = new Questao();
 * questao.setId_questao(1);
 * questao.setPergunta_questao("Qual é a capital do Brasil?");
 * questao.setAlt_a_questao("São Paulo");
 * questao.setAlt_b_questao("Rio de Janeiro");
 * questao.setAlt_c_questao("Brasília");
 * questao.setAlt_d_questao("Salvador");
 * questao.setAlt_e_questao("Belo Horizonte");
 * questao.setResposta_questao(Resposta.C);
 * 
 * System.out.println(questao.getPergunta_questao()); // Imprime a pergunta da questão
 * System.out.println(questao.getResposta_questao()); // Imprime a resposta correta da questão
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see services.QuestaoService
 * @see dao.QuestaoDao
 * @see controller.QuestaoResource
 * @see model.Modulo
 * @see model.Resposta
 * @author Stockwave
 */
public class Questao {
	
	/**
	 * id da Questao
	 */
	private int id_questao;
	
	/**
	 * pergunta da Questao
	 */
	@NotNull(message = "A pergunta da questão não pode ser nula.")
	private String pergunta_questao;
	
	/**
	 * alternativa A da Questao
	 */
	@NotNull(message = "A alternativa A da questão não pode ser nula.")
	private String alt_a_questao;
	
	/**
	 * alternativa B da Questao
	 */
	@NotNull(message = "A alternativa B da questão não pode ser nula.")
	private String alt_b_questao;
	
	/**
	 * alternativa C da Questao
	 */
	@NotNull(message = "A alternativa C da questão não pode ser nula.")
	private String alt_c_questao;
	
	/**
	 * alternativa D da Questao
	 */
	@NotNull(message = "A alternativa D da questão não pode ser nula.")
	private String alt_d_questao;
	
	/**
	 * alternativa E da Questao
	 */
	@NotNull(message = "A alternativa E da questão não pode ser nula.")
	private String alt_e_questao;
	
	/**
	 * resposta da Questao
	 */
	@NotNull(message = "A resposta da questão não pode ser nula")
	private Resposta resposta_questao;

	/**
	 * Obtém o id da Questao.
	 * 
	 * @return o id da Questao
	 */
	public int getId_questao() {
		return id_questao;
	}

	/**
	 * Define o id da Questao.
	 * 
	 * @param id_questao o id da Questao a ser definido
	 */
	public void setId_questao(int id_questao) {
		this.id_questao = id_questao;
	}

	/**
	 * Obtém a pergunta da Questao.
	 * 
	 * @return a pergunta da Questao
	 */
	public String getPergunta_questao() {
		return pergunta_questao;
	}

	/**
	 * Define a pergunta da Questao.
	 * 
	 * @param pergunta_questao a pergunta da Questao a ser definida
	 */
	public void setPergunta_questao(String pergunta_questao) {
		this.pergunta_questao = pergunta_questao;
	}

	/**
	 * Obtém a alternativa A da Questao.
	 * 
	 * @return a alternativa A da Questao
	 */
	public String getAlt_a_questao() {
		return alt_a_questao;
	}

	/**
	 * Define a alternativa A da Questao.
	 * 
	 * @param alt_a_questao a alternativa A da Questao a ser definida
	 */
	public void setAlt_a_questao(String alt_a_questao) {
		this.alt_a_questao = alt_a_questao;
	}

	/**
	 * Obtém a alternativa B da Questao.
	 * 
	 * @return a alternativa B da Questao
	 */
	public String getAlt_b_questao() {
		return alt_b_questao;
	}

	/**
	 * Define a alternativa B da Questao.
	 * 
	 * @param alt_b_questao a alternativa B da Questao a ser definida
	 */
	public void setAlt_b_questao(String alt_b_questao) {
		this.alt_b_questao = alt_b_questao;
	}

	/**
	 * Obtém a alternativa C da Questao.
	 * 
	 * @return a alternativa C da Questao
	 */
	public String getAlt_c_questao() {
		return alt_c_questao;
	}

	/**
	 * Define a alternativa C da Questao.
	 * 
	 * @param alt_c_questao a alternativa C da Questao a ser definida
	 */
	public void setAlt_c_questao(String alt_c_questao) {
		this.alt_c_questao = alt_c_questao;
	}

	/**
	 * Obtém a alternativa D da Questao.
	 * 
	 * @return a alternativa D da Questao
	 */
	public String getAlt_d_questao() {
		return alt_d_questao;
	}

	/**
	 * Define a alternativa D da Questao.
	 * 
	 * @param alt_d_questao a alternativa D da Questao a ser definida
	 */
	public void setAlt_d_questao(String alt_d_questao) {
		this.alt_d_questao = alt_d_questao;
	}

	/**
	 * Obtém a alternativa E da Questao.
	 * 
	 * @return a alternativa E da Questao
	 */
	public String getAlt_e_questao() {
		return alt_e_questao;
	}

	/**
	 * Define a alternativa E da Questao.
	 * 
	 * @param alt_e_questao a alternativa E da Questao a ser definida
	 */
	public void setAlt_e_questao(String alt_e_questao) {
		this.alt_e_questao = alt_e_questao;
	}

	/**
	 * Obtém a resposta da Questao.
	 * 
	 * @return a resposta da Questao
	 */
	public Resposta getResposta_questao() {
		return resposta_questao;
	}

	/**
	 * Define a resposta da Questao.
	 * 
	 * @param resposta_questao a resposta da Questao a ser definida
	 */
	public void setResposta_questao(Resposta resposta_questao) {
		this.resposta_questao = resposta_questao;
	}
	
	/**
	 * Construtor Padrão da Questao.
	 */
	public Questao() {
		super();
	}
	
	/**
	 * Construtor Não Padrão da Questao.
	 * 
	 * @param id_questao o id da Questao
	 * @param pergunta_questao a pergunta da Questao
	 * @param alt_a_questao a alternativa A da Questao
	 * @param alt_b_questao a alternativa B da Questao
	 * @param alt_c_questao a alternativa C da Questao
	 * @param alt_d_questao a alternativa D da Questao
	 * @param alt_e_questao a alternativa E da Questao
	 * @param resposta_questao a resposta da Questao
	 */
	public Questao(int id_questao,
			@NotNull(message = "A pergunta da questão não pode ser nula.") String pergunta_questao,
			@NotNull(message = "A alternativa A da questão não pode ser nula.") String alt_a_questao,
			@NotNull(message = "A alternativa B da questão não pode ser nula.") String alt_b_questao,
			@NotNull(message = "A alternativa C da questão não pode ser nula.") String alt_c_questao,
			@NotNull(message = "A alternativa D da questão não pode ser nula.") String alt_d_questao,
			@NotNull(message = "A alternativa E da questão não pode ser nula.") String alt_e_questao,
			@NotNull(message = "A resposta da questão não pode ser nula") Resposta resposta_questao) {
		super();
		this.id_questao = id_questao;
		this.pergunta_questao = pergunta_questao;
		this.alt_a_questao = alt_a_questao;
		this.alt_b_questao = alt_b_questao;
		this.alt_c_questao = alt_c_questao;
		this.alt_d_questao = alt_d_questao;
		this.alt_e_questao = alt_e_questao;
		this.resposta_questao = resposta_questao;
	}
	
	/**
	 * Retorna o código hash da Questao.
	 * 
	 * @return o código hash da Questao
	 */
	@Override
	public int hashCode() {
		return Objects.hash(alt_a_questao, alt_b_questao, alt_c_questao, alt_d_questao, alt_e_questao, id_questao,
				pergunta_questao, resposta_questao);
	}
	
	/**
	 * Verifica se a Questao é igual a outro objeto.
	 * 
	 * @param obj o objeto a ser comparado
	 * @return true se a Questao for igual ao objeto fornecido, false caso contrário
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Questao other = (Questao) obj;
		return Objects.equals(alt_a_questao, other.alt_a_questao) && Objects.equals(alt_b_questao, other.alt_b_questao)
				&& Objects.equals(alt_c_questao, other.alt_c_questao)
				&& Objects.equals(alt_d_questao, other.alt_d_questao)
				&& Objects.equals(alt_e_questao, other.alt_e_questao) && id_questao == other.id_questao
				&& Objects.equals(pergunta_questao, other.pergunta_questao)
				&& Objects.equals(resposta_questao, other.resposta_questao);
	}
	
	/**
	 * Retorna uma representação em formato de string da Questao.
	 * 
	 * @return uma representação em formato de string da Questao
	 */
	@Override
	public String toString() {
		return "Questao [id_questao=" + id_questao + ", pergunta_questao=" + pergunta_questao + ", alt_a_questao="
				+ alt_a_questao + ", alt_b_questao=" + alt_b_questao + ", alt_c_questao=" + alt_c_questao
				+ ", alt_d_questao=" + alt_d_questao + ", alt_e_questao=" + alt_e_questao + ", resposta_questao="
				+ resposta_questao + "]";
	}
	
}

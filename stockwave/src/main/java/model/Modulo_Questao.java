package model;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;

/**
 * Classe responsável por representar a associação entre um módulo e uma questão.
 * 
 * A classe Modulo_Questao contém uma instância de Modulo e uma instância de Questao,
 * representando a relação entre um módulo que possui uma determinada questão.
 * 
 * Atributos:
 * - modulo: instância de Modulo associada.
 * - questao: instância de Questao associada.
 * 
 * Métodos:
 * - getters e setters: permitem acessar e modificar os atributos da classe.
 * - construtores: permitem criar instâncias da classe com diferentes conjuntos de atributos.
 * - hashCode e equals: são implementados para permitir a comparação de objetos Modulo_Questao.
 * - toString: retorna uma representação em formato de string do objeto Modulo_Questao.
 * 
 * Exemplo de uso:
 * 
 * Modulo modulo = new Modulo();
 * Questao questao = new Questao();
 * 
 * Modulo_Questao moduloQuestao = new Modulo_Questao(modulo, questao);
 * 
 * System.out.println(moduloQuestao.getModulo()); // Imprime o objeto Modulo associado
 * System.out.println(moduloQuestao.getQuestao()); // Imprime o objeto Questao associado
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see services.Modulo_QuestaoService
 * @see dao.Modulo_QuestaoDao
 * @see controller.Modulo_QuestaoResource
 * @see model.Modulo
 * @see model.Questao
 * 
 * @author Stockwave
 * 
 */
public class Modulo_Questao {
	
	/**
	 * Instância de Modulo associada.
	 */
	@NotNull(message = "O módulo de modulo_questao não pode ser nulo.")
	private Modulo modulo;
	
	/**
	 * Instância de Questao associada.
	 */
	@NotNull(message = "A questão de modulo_questao não pode ser nula.")
	private Questao questao;
	
	/**
	 * Obtém a instância de Modulo associada.
	 * 
	 * @return a instância de Modulo associada.
	 */
	public Modulo getModulo() {
		return modulo;
	}
	
	/**
	 * Define a instância de Modulo associada.
	 * 
	 * @param modulo a instância de Modulo associada.
	 */
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	
	/**
	 * Obtém a instância de Questao associada.
	 * 
	 * @return a instância de Questao associada.
	 */
	public Questao getQuestao() {
		return questao;
	}
	
	/**
	 * Define a instância de Questao associada.
	 * 
	 * @param questao a instância de Questao associada.
	 */
	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	
	/**
	 * Construtor padrão da classe Modulo_Questao.
	 */
	public Modulo_Questao() {
		super();
	}
	
	/**
	 * Construtor da classe Modulo_Questao que permite definir a instância de Modulo e Questao associadas.
	 * 
	 * @param modulo a instância de Modulo associada.
	 * @param questao a instância de Questao associada.
	 */
	public Modulo_Questao(@NotNull(message = "O módulo de modulo_questao não pode ser nulo.") Modulo modulo,
			@NotNull(message = "A questão de modulo_questao não pode ser nula.") Questao questao) {
		super();
		this.modulo = modulo;
		this.questao = questao;
	}
	
	/**
	 * Retorna o código hash do objeto Modulo_Questao.
	 * 
	 * @return o código hash calculado.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(modulo, questao);
	}
	
	/**
	 * Verifica se o objeto Modulo_Questao é igual a outro objeto.
	 * 
	 * @param obj o objeto a ser comparado.
	 * @return true se os objetos são iguais, false caso contrário.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modulo_Questao other = (Modulo_Questao) obj;
		return Objects.equals(modulo, other.modulo) && Objects.equals(questao, other.questao);
	}
	
	/**
	 * Retorna uma representação em formato de string do objeto Modulo_Questao.
	 * 
	 * @return uma representação em formato de string do objeto Modulo_Questao.
	 */
	@Override
	public String toString() {
		return "Modulo_Questao [modulo=" + modulo + ", questao=" + questao + "]";
	}	
}

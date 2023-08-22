package model;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;


/**
 * Classe responsável por representar a associação entre um módulo e uma aula.
 * 
 * A classe Modulo_Aula contém uma instância de Modulo e uma instância de Aula,
 * representando a relação entre um módulo que possui uma determinada aula.
 * 
 * Atributos:
 * - modulo: instância de Modulo associada.
 * - aula: instância de Aula associada.
 * 
 * Métodos:
 * - getters e setters: permitem acessar e modificar os atributos da classe.
 * - construtores: permitem criar instâncias da classe com diferentes conjuntos de atributos.
 * - hashCode e equals: são implementados para permitir a comparação de objetos Modulo_Aula.
 * - toString: retorna uma representação em formato de string do objeto Modulo_Aula.
 * 
 * Exemplo de uso:
 * 
 * Modulo modulo = new Modulo();
 * Aula aula = new Aula();
 * 
 * Modulo_Aula moduloAula = new Modulo_Aula(modulo, aula);
 * 
 * System.out.println(moduloAula.getModulo()); // Imprime o objeto Modulo associado
 * System.out.println(moduloAula.getAula()); // Imprime o objeto Aula associado
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see services.Modulo_AulaService
 * @see dao.Modulo_AulaDao
 * @see controller.Modulo_AulaResource
 * @see model.Modulo
 * @see model.Aula
 * 
 * @author Stockwave
 * 
 */
public class Modulo_Aula {
	
	/**
	 * Instância de Modulo associada.
	 */
	@NotNull(message = "O módulo de modulo_aula não pode ser nulo.")
	private Modulo modulo;
	
	/**
	 * Instância de Aula associada.
	 */
	@NotNull(message = "A aula de modula_aula não pode ser nula.")
	private Aula aula;

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
	 * Obtém a instância de Aula associada.
	 * 
	 * @return a instância de Aula associada.
	 */
	public Aula getAula() {
		return aula;
	}

	/**
	 * Define a instância de Aula associada.
	 * 
	 * @param aula a instância de Aula associada.
	 */
	public void setAula(Aula aula) {
		this.aula = aula;
	}
	
	/**
	 * Construtor padrão da classe Modulo_Aula.
	 */
	public Modulo_Aula() {
		super();
	}
	
	/**
	 * Construtor da classe Modulo_Aula que permite definir a instância de Modulo e Aula associadas.
	 * 
	 * @param modulo a instância de Modulo associada.
	 * @param aula a instância de Aula associada.
	 */
	public Modulo_Aula(@NotNull(message = "O módulo de modulo_aula não pode ser nulo.") Modulo modulo,
			@NotNull(message = "A aula de modula_aula não pode ser nula.") Aula aula) {
		super();
		this.modulo = modulo;
		this.aula = aula;
	}
	
	/**
	 * Calcula o código hash do objeto Modulo_Aula.
	 * 
	 * @return o código hash calculado.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(aula, modulo);
	}
	
	/**
	 * Verifica se o objeto Modulo_Aula é igual a outro objeto.
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
		Modulo_Aula other = (Modulo_Aula) obj;
		return Objects.equals(aula, other.aula) && Objects.equals(modulo, other.modulo);
	}
	
	/**
	 * Retorna uma representação em formato de string do objeto Modulo_Aula.
	 * 
	 * @return uma representação em formato de string do objeto Modulo_Aula.
	 */
	@Override
	public String toString() {
		return "Modulo_Aula [modulo=" + modulo + ", aula=" + aula + "]";
	}
}

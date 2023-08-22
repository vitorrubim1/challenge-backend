package model;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;

/**
 * Classe responsável por representar um nível.
 * 
 * A classe Nivel contém informações sobre um nível, como seu nome.
 * 
 * Atributos:
 * - nome_nivel: nome do nível.
 * 
 * Métodos:
 * - getters e setters: permitem acessar e modificar os atributos da classe.
 * - construtores: permitem criar instâncias da classe com diferentes conjuntos de atributos.
 * - hashCode e equals: são implementados para permitir a comparação de objetos Nivel.
 * - toString: retorna uma representação em formato de string do objeto Nivel.
 * 
 * Exemplo de uso:
 * 
 * Nivel nivel = new Nivel();
 * nivel.setNome_nivel("Nível 1");
 * 
 * System.out.println(nivel.getNome_nivel()); // Imprime o nome do nível
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see services.NivelService
 * @see dao.NivelDao
 * @see controller.NivelResource
 * @see model.Nivel
 * 
 * @author Stockwave
 * 
 */
public class Nivel {
	
	/**
	 * nome do Nivel
	 */
	@NotNull(message = "O nome do nível não pode ser nulo.")
	private String nome_nivel;
	
	/**
	 * Obtém o nome do nível.
	 * 
	 * @return o nome do nível.
	 */
	public String getNome_nivel() {
		return nome_nivel;
	}

	/**
	 * Define o nome do nível.
	 * 
	 * @param nome_nivel o nome do nível.
	 */
	public void setNome_nivel(String nome_nivel) {
		this.nome_nivel = nome_nivel;
	}
	
	/**
	 * Construtor padrão da classe Nivel.
	 */
	public Nivel() {
		super();
	}

	/**
	 * Construtor não padrão da classe Nivel.
	 * 
	 * @param nome_nivel o nome do nível.
	 */
	public Nivel(@NotNull(message = "O nome do nível não pode ser nulo.") String nome_nivel) {
		super();
		this.nome_nivel = nome_nivel;
	}
	
	/**
	 * Retorna o hash code do objeto Nivel.
	 * 
	 * @return o hash code do objeto Nivel.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(nome_nivel);
	}
	
	/**
	 * Verifica se o objeto Nivel é igual a outro objeto.
	 * 
	 * @param obj o objeto a ser comparado.
	 * @return true se os objetos são iguais, false caso contrário.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Nivel other = (Nivel) obj;
		return Objects.equals(nome_nivel, other.nome_nivel);
	}
	
	/**
	 * Retorna uma representação em formato de string do objeto Nivel.
	 * 
	 * @return uma representação em formato de string do objeto Nivel.
	 */
	@Override
	public String toString() {
		return "Nivel [nome_nivel=" + nome_nivel + "]";
	}
}

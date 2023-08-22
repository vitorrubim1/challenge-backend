package model;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;

/**
 * Classe responsável por representar um módulo do curso.
 * 
 * A classe Modulo contém informações sobre um módulo, como seu identificador, nome, URL da imagem e nível associado.
 * 
 * Atributos:
 * - id_modulo: identificador único do módulo.
 * - nome_modulo: nome do módulo.
 * - url_imagem_modulo: URL da imagem do módulo.
 * - nivel_modulo: nível associado ao módulo.
 * 
 * Métodos:
 * - getters e setters: permitem acessar e modificar os atributos da classe.
 * - construtores: permitem criar instâncias da classe com diferentes conjuntos de atributos.
 * - hashCode e equals: são implementados para permitir a comparação de objetos Modulo.
 * - toString: retorna uma representação em formato de string do objeto Modulo.
 * 
 * Exemplo de uso:
 * 
 * Modulo modulo = new Modulo();
 * modulo.setId_modulo(1);
 * modulo.setNome_modulo("Módulo 1");
 * modulo.setUrl_imagem_modulo("http://exemplo.com/imagem.png");
 * modulo.setNivel_modulo(Nivel.BASICO);
 * 
 * System.out.println(modulo.getNome_modulo()); // Imprime "Módulo 1"
 * System.out.println(modulo.getNivel_modulo()); // Imprime o objeto Nivel associado
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see services.ModuloService
 * @see dao.ModuloDao
 * @see controller.ModuloResource
 * @see model.Nivel
 * 
 * @author Stockwave
 * 
 */
public class Modulo {
	
	/**
	 * Identificador único do módulo.
	 */
	private int id_modulo;
	
	/**
	 * Nome do módulo.
	 */
	@NotNull(message = "O nome do módulo não pode ser nulo.")
	private String nome_modulo;
	
	/**
	 * URL da imagem do módulo.
	 */
	@NotNull(message = "A URL da imagem do módulo não pode ser nula.")
	private String url_imagem_modulo;
	
	/**
	 * Nível associado ao módulo.
	 */
	@NotNull(message = "O nível do módulo não pode ser nulo.")
	private Nivel nivel_modulo;
	
	/**
	 * Obtém o identificador único do módulo.
	 * 
	 * @return o identificador único do módulo.
	 */
	public int getId_modulo() {
		return id_modulo;
	}
	
	/**
	 * Define o identificador único do módulo.
	 * 
	 * @param id_modulo o identificador único do módulo.
	 */
	public void setId_modulo(int id_modulo) {
		this.id_modulo = id_modulo;
	}
	
	/**
	 * Obtém o nome do módulo.
	 * 
	 * @return o nome do módulo.
	 */
	public String getNome_modulo() {
		return nome_modulo;
	}
	
	/**
	 * Define o nome do módulo.
	 * 
	 * @param nome_modulo o nome do módulo.
	 */
	public void setNome_modulo(String nome_modulo) {
		this.nome_modulo = nome_modulo;
	}
	
	/**
	 * Obtém a URL da imagem do módulo.
	 * 
	 * @return a URL da imagem do módulo.
	 */
	public String getUrl_imagem_modulo() {
		return url_imagem_modulo;
	}
	
	/**
	 * Define a URL da imagem do módulo.
	 * 
	 * @param url_imagem_modulo a URL da imagem do módulo.
	 */
	public void setUrl_imagem_modulo(String url_imagem_modulo) {
		this.url_imagem_modulo = url_imagem_modulo;
	}
	
	/**
	 * Obtém o nível associado ao módulo.
	 * 
	 * @return o nível associado ao módulo.
	 */
	public Nivel getNivel_modulo() {
		return nivel_modulo;
	}
	
	/**
	 * Define o nível associado ao módulo.
	 * 
	 * @param nivel_modulo o nível associado ao módulo.
	 */
	public void setNivel_modulo(Nivel nivel_modulo) {
		this.nivel_modulo = nivel_modulo;
	}
	
	/**
	 * Construtor padrão da classe Modulo.
	 */
	public Modulo() {
		super();
	}
	
	/**
	 * Construtor da classe Modulo que permite definir todos os atributos.
	 * 
	 * @param id_modulo            o identificador único do módulo.
	 * @param nome_modulo          o nome do módulo.
	 * @param url_imagem_modulo    a URL da imagem do módulo.
	 * @param nivel_modulo         o nível associado ao módulo.
	 */
	public Modulo(int id_modulo, @NotNull(message = "O nome do módulo não pode ser nulo.") String nome_modulo,
			@NotNull(message = "A URL da imagem do módulo não pode ser nula.") String url_imagem_modulo,
			@NotNull(message = "O nível do módulo não pode ser nulo.") Nivel nivel_modulo) {
		super();
		this.id_modulo = id_modulo;
		this.nome_modulo = nome_modulo;
		this.url_imagem_modulo = url_imagem_modulo;
		this.nivel_modulo = nivel_modulo;
	}
	
	/**
	 * Retorna o código hash do objeto Modulo.
	 * 
	 * @return o código hash calculado.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id_modulo, nivel_modulo, nome_modulo, url_imagem_modulo);
	}

	/**
	 * Verifica se o objeto Modulo é igual a outro objeto.
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
		Modulo other = (Modulo) obj;
		return id_modulo == other.id_modulo && Objects.equals(nivel_modulo, other.nivel_modulo)
				&& Objects.equals(nome_modulo, other.nome_modulo)
				&& Objects.equals(url_imagem_modulo, other.url_imagem_modulo);
	}
	
	/**
	 * Retorna uma representação em formato de string do objeto Modulo.
	 * 
	 * @return uma representação em formato de string do objeto Modulo.
	 */
	@Override
	public String toString() {
		return "Modulo [id_modulo=" + id_modulo + ", nome_modulo=" + nome_modulo + ", url_imagem_modulo="
				+ url_imagem_modulo + ", nivel_modulo=" + nivel_modulo + "]";
	}
}

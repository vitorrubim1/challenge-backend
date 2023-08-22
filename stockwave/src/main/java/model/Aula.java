package model;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;

/**
 * Classe responsável por representar as aulas da plataforma.
 * 
 * Esta classe contém os atributos e métodos relacionados às aulas presentes na plataforma.
 * Cada aula possui um identificador único, nome, descrição, conteúdo, URL do vídeo e URL do áudio.
 */
public class Aula {
	
	/**
	 * Identificador único da aula.
	 */
	private int id_aula;
	
	/**
	 * Nome da aula.
	 */
	@NotNull(message = "O nome da aula não pode ser nulo.")
	private String nome_aula;
	
	/**
	 * Descrição da aula.
	 */
	@NotNull(message = "A descrição da aula não pode ser nula.")
	private String descricao_aula;
	
	/**
	 * Conteúdo da aula.
	 */
	@NotNull(message = "O conteúdo da aula não pode ser nulo.")
	private String conteudo_aula;
	
	/**
	 * URL do vídeo da aula.
	 */
	@NotNull(message = "A URL do vídeo da aula não pode ser nula.")
	private String url_video_aula;
	
	/**
	 * URL do áudio da aula.
	 */
	@NotNull(message = "A URL do áudio da aula não pode ser nula.")
	private String url_audio_aula;
	
	/**
	 * Obtém o identificador único da aula.
	 * 
	 * @return o id da aula
	 */
	public int getId_aula() {
		return id_aula;
	}

	/**
	 * Define o identificador único da aula.
	 * 
	 * @param id_aula o id da aula
	 */
	public void setId_aula(int id_aula) {
		this.id_aula = id_aula;
	}
	
	/**
	 * Obtém o nome da aula.
	 * 
	 * @return o nome da aula
	 */
	public String getNome_aula() {
		return nome_aula;
	}
	
	/**
	 * Define o nome da aula.
	 * 
	 * @param nome_aula o nome da aula
	 */
	public void setNome_aula(String nome_aula) {
		this.nome_aula = nome_aula;
	}
	
	/**
	 * Obtém a descrição da aula.
	 * 
	 * @return a descrição da aula
	 */
	public String getDescricao_aula() {
		return descricao_aula;
	}

	/**
	 * Define a descrição da aula.
	 * 
	 * @param descricao_aula a descrição da aula
	 */
	public void setDescricao_aula(String descricao_aula) {
		this.descricao_aula = descricao_aula;
	}
	
	/**
	 * Obtém o conteúdo da aula.
	 * 
	 * @return o conteúdo da aula
	 */
	public String getConteudo_aula() {
		return conteudo_aula;
	}

	/**
	 * Define o conteúdo da aula.
	 * 
	 * @param conteudo_aula o conteúdo da aula
	 */
	public void setConteudo_aula(String conteudo_aula) {
		this.conteudo_aula = conteudo_aula;
	}
	
	/**
	 * Obtém a URL do vídeo da aula.
	 * 
	 * @return a URL do vídeo da aula
	 */
	public String getUrl_video_aula() {
		return url_video_aula;
	}

	/**
	 * Define a URL do vídeo da aula.
	 * 
	 * @param url_video_aula a URL do vídeo da aula
	 */
	public void setUrl_video_aula(String url_video_aula) {
		this.url_video_aula = url_video_aula;
	}
	
	/**
	 * Obtém a URL do áudio da aula.
	 * 
	 * @return a URL do áudio da aula
	 */
	public String getUrl_audio_aula() {
		return url_audio_aula;
	}

	/**
	 * Define a URL do áudio da aula.
	 * 
	 * @param url_audio_aula a URL do áudio da aula
	 */
	public void setUrl_audio_aula(String url_audio_aula) {
		this.url_audio_aula = url_audio_aula;
	}
	
	/**
	 * Construtor padrão da classe Aula.
	 */
	public Aula() {
		super();
	}
	
	/**
	 * Construtor da classe Aula com todos os atributos.
	 * 
	 * @param id_aula o id da aula
	 * @param nome_aula o nome da aula
	 * @param descricao_aula a descrição da aula
	 * @param conteudo_aula o conteúdo da aula
	 * @param url_video_aula a URL do vídeo da aula
	 * @param url_audio_aula a URL do áudio da aula
	 */
	public Aula(int id_aula, @NotNull(message = "O nome da aula não pode ser nulo.") String nome_aula,
			@NotNull(message = "A descrição da aula não pode ser nula.") String descricao_aula,
			@NotNull(message = "O conteúdo da aula não pode ser nulo.") String conteudo_aula,
			@NotNull(message = "A URL do vídeo da aula não pode ser nula.") String url_video_aula,
			@NotNull(message = "A URL do áudio da aula não pode ser nula.") String url_audio_aula) {
		super();
		this.id_aula = id_aula;
		this.nome_aula = nome_aula;
		this.descricao_aula = descricao_aula;
		this.conteudo_aula = conteudo_aula;
		this.url_video_aula = url_video_aula;
		this.url_audio_aula = url_audio_aula;
	}
	
	/**
	 * Calcula o hash code do objeto Aula.
	 * 
	 * @return o hash code calculado
	 */
	@Override
	public int hashCode() {
		return Objects.hash(conteudo_aula, descricao_aula, id_aula, nome_aula, url_audio_aula, url_video_aula);
	}
	
	/**
	 * Compara se o objeto Aula é igual a outro objeto.
	 * 
	 * @param obj o objeto a ser comparado
	 * @return true se os objetos forem iguais, false caso contrário
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(conteudo_aula, other.conteudo_aula)
				&& Objects.equals(descricao_aula, other.descricao_aula) && id_aula == other.id_aula
				&& Objects.equals(nome_aula, other.nome_aula) && Objects.equals(url_audio_aula, other.url_audio_aula)
				&& Objects.equals(url_video_aula, other.url_video_aula);
	}
	
	/**
	 * Retorna uma representação em string do objeto Aula.
	 * 
	 * @return a representação em string do objeto
	 */
	@Override
	public String toString() {
		return "Aula [id_aula=" + id_aula + ", nome_aula=" + nome_aula + ", descricao_aula=" + descricao_aula
				+ ", conteudo_aula=" + conteudo_aula + ", url_video_aula=" + url_video_aula + ", url_audio_aula="
				+ url_audio_aula + "]";
	}
}

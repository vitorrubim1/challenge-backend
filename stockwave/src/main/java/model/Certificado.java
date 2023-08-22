package model;

import java.sql.Date;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

/**
 * Classe responsável por representar os certificados que serão gerados para os alunos.
 * 
 * Esta classe contém os atributos e métodos relacionados aos certificados gerados para os alunos.
 * Cada certificado possui um identificador único e uma data de emissão.
 * 
 * Atributos:
 * - id_certificado: identificador único do certificado.
 * - dt_certificado: data de emissão do certificado.
 * 
 * Métodos:
 * - getters e setters: permitem acessar e modificar os atributos da classe.
 * - construtores: permitem criar instâncias da classe com diferentes conjuntos de atributos.
 * - hashCode e equals: são implementados para permitir a comparação de objetos Certificado.
 * - toString: retorna uma representação em formato de string do objeto Certificado.
 * 
 * Exemplo de uso:
 * 
 * Certificado certificado = new Certificado();
 * certificado.setId_certificado(1);
 * certificado.setDt_certificado(new Date());
 * 
 * System.out.println(certificado.getId_certificado()); // Imprime 1
 * System.out.println(certificado.getDt_certificado()); // Imprime a data atual
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see services.CertificadoService
 * @see dao.CertificadoDao
 * @see controller.CertificadoResource
 * @see model.Aluno
 * 
 * @author Stockwave
 * 
 */
public class Certificado {
	
	/**
	 * Identificador único do certificado.
	 */
	private int id_certificado;
	
	/**
	 * Data de emissão do certificado.
	 */
	@NotNull(message = "A data do certificado não pode ser nula.")
	private Date dt_certificado;
	
	/**
	 * Obtém o identificador único do certificado.
	 * 
	 * @return o identificador único do certificado.
	 */
	public int getId_certificado() {
		return id_certificado;
	}

	/**
	 * Define o identificador único do certificado.
	 * 
	 * @param id_certificado o identificador único do certificado.
	 */
	public void setId_certificado(int id_certificado) {
		this.id_certificado = id_certificado;
	}

	/**
	 * Obtém a data de emissão do certificado.
	 * 
	 * @return a data de emissão do certificado.
	 */
	public Date getDt_certificado() {
		return dt_certificado;
	}

	/**
	 * Define a data de emissão do certificado.
	 * 
	 * @param dt_certificado a data de emissão do certificado.
	 */
	public void setDt_certificado(Date dt_certificado) {
		this.dt_certificado = dt_certificado;
	}
	
	/**
	 * Construtor padrão da classe Certificado.
	 */
	public Certificado() {
		super();
	}
	
	/**
	 * Construtor da classe Certificado que permite definir o identificador único e a data de emissão.
	 * 
	 * @param id_certificado o identificador único do certificado.
	 * @param dt_certificado a data de emissão do certificado.
	 */
	public Certificado(int id_certificado,
			@NotNull(message = "A data do certificado não pode ser nula.") Date dt_certificado) {
		super();
		this.id_certificado = id_certificado;
		this.dt_certificado = dt_certificado;
	}
	
	/**
	 * Calcula o código hash do objeto Certificado.
	 * 
	 * @return o código hash calculado.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dt_certificado, id_certificado);
	}
	
	/**
	 * Verifica se o objeto Certificado é igual a outro objeto.
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
		Certificado other = (Certificado) obj;
		return Objects.equals(dt_certificado, other.dt_certificado) && id_certificado == other.id_certificado;
	}
	
	/**
	 * Retorna uma representação em formato de string do objeto Certificado.
	 * 
	 * @return uma representação em formato de string do objeto Certificado.
	 */
	@Override
	public String toString() {
		return "Certificado [id_certificado=" + id_certificado + ", dt_certificado=" + dt_certificado + "]";
	}
}

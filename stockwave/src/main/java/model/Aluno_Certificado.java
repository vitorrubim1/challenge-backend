package model;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;

/**
 * Classe responsável por representar a associação entre um aluno e um certificado.
 * 
 * A classe Aluno_Certificado contém uma instância de Aluno e uma instância de Certificado,
 * representando a relação entre um aluno que possui um determinado certificado.
 * 
 * Atributos:
 * - aluno: instância de Aluno associada.
 * - certificado: instância de Certificado associada.
 * 
 * Métodos:
 * - getters e setters: permitem acessar e modificar os atributos da classe.
 * - construtores: permitem criar instâncias da classe com diferentes conjuntos de atributos.
 * - hashCode e equals: são implementados para permitir a comparação de objetos Aluno_Certificado.
 * - toString: retorna uma representação em formato de string do objeto Aluno_Certificado.
 * 
 * Exemplo de uso:
 * 
 * Aluno aluno = new Aluno();
 * Certificado certificado = new Certificado();
 * 
 * Aluno_Certificado alunoCertificado = new Aluno_Certificado(aluno, certificado);
 * 
 * System.out.println(alunoCertificado.getAluno()); // Imprime o objeto Aluno associado
 * System.out.println(alunoCertificado.getCertificado()); // Imprime o objeto Certificado associado
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see services.Aluno_CertificadoService
 * @see dao.Aluno_CertificadoDao
 * @see controller.Aluno_CertificadoResource
 * @see model.Aluno
 * @see model.Certificado
 * 
 * @author Stockwave
 * 
 */
public class Aluno_Certificado {
	
	/**
	 * Aluno associado ao Aluno_Certificado.
	 * 
	 * @return O aluno associado.
	 */
	@NotNull(message = "O aluno de aluno_certificado não pode ser nulo.")
	private Aluno aluno;
	
	/**
	 * Certificado associado ao Aluno_Certificado.
	 * 
	 * @return O certificado associado.
	 */
	@NotNull(message = "O certificado de aluno_certificado não pode ser nulo.")
	private Certificado certificado;
	
	/**
	 * Obtém o aluno associado ao Aluno_Certificado.
	 * 
	 * @return O aluno associado.
	 */
	public Aluno getAluno() {
		return aluno;
	}
	
	/**
	 * Define o aluno associado ao Aluno_Certificado.
	 * 
	 * @param aluno O aluno a ser associado.
	 */
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	/**
	 * Obtém o certificado associado ao Aluno_Certificado.
	 * 
	 * @return O certificado associado.
	 */
	public Certificado getCertificado() {
		return certificado;
	}
	
	/**
	 * Define o certificado associado ao Aluno_Certificado.
	 * 
	 * @param certificado O certificado a ser associado.
	 */
	public void setCertificado(Certificado certificado) {
		this.certificado = certificado;
	}
	
	/**
	 * Construtor padrão de Aluno_Certificado.
	 */
	public Aluno_Certificado() {
		super();
	}
	
	/**
	 * Construtor de Aluno_Certificado com parâmetros.
	 * 
	 * @param aluno O aluno a ser associado.
	 * @param certificado O certificado a ser associado.
	 */
	public Aluno_Certificado(@NotNull(message = "O aluno de aluno_certificado não pode ser nulo.") Aluno aluno,
			@NotNull(message = "O certificado de aluno_certificado não pode ser nulo.") Certificado certificado) {
		super();
		this.aluno = aluno;
		this.certificado = certificado;
	}
	
	/**
	 * Calcula o hash code do Aluno_Certificado.
	 * 
	 * @return O hash code calculado.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(aluno, certificado);
	}
	
	/**
	 * Verifica se o Aluno_Certificado é igual a outro objeto.
	 * 
	 * @param obj O objeto a ser comparado.
	 * @return true se forem iguais, false caso contrário.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno_Certificado other = (Aluno_Certificado) obj;
		return Objects.equals(aluno, other.aluno) && Objects.equals(certificado, other.certificado);
	}
	
	/**
	 * Retorna uma representação em formato de string do Aluno_Certificado.
	 * 
	 * @return A representação em formato de string.
	 */
	@Override
	public String toString() {
		return "Aluno_Certificado [aluno=" + aluno + ", certificado=" + certificado + "]";
	}
}

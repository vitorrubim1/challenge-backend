package model;

import java.sql.Date;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

/**
 * Classe responsável por representar os usuários que são alunos.
 * 
 * Esta classe herda da classe Usuario e contém atributos e métodos específicos para alunos.
 * Os alunos são usuários registrados no sistema e possuem informações como data de nascimento, data de registro, senha,
 * quantidade de moedas e nível.
 * 
 * Atributos:
 * - dt_nasc_aluno: data de nascimento do aluno.
 * - dt_reg_aluno: data de registro do aluno.
 * - senha_aluno: senha do aluno.
 * - moedas_aluno: quantidade de moedas do aluno.
 * - nivel_aluno: nível do aluno.
 * 
 * Métodos:
 * - getters e setters: permitem acessar e modificar os atributos da classe.
 * - construtores: permitem criar instâncias da classe com diferentes conjuntos de atributos.
 * - hashCode e equals: são implementados para permitir a comparação de objetos Aluno.
 * - toString: retorna uma representação em formato de string do objeto Aluno.
 * 
 * Exemplo de uso:
 * 
 * Aluno aluno = new Aluno();
 * aluno.setDt_nasc_aluno(new Date(2000, 5, 19));
 * aluno.setDt_reg_aluno(new Date(2022, 1, 1));
 * aluno.setSenha_aluno("senha123");
 * aluno.setMoedas_aluno(100);
 * Nivel nivel = new Nivel("Iniciante");
 * aluno.setNivel_aluno(nivel);
 * 
 * System.out.println(aluno.getDt_nasc_aluno()); // Imprime "2000-06-19"
 * System.out.println(aluno.getMoedas_aluno()); // Imprime 100
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see services.AlunoService
 * @see dao.AlunoDao
 * @see controller.AlunoResource
 * @see Usuario
 * 
 * @author Stockwave
 * 
 */
public class Aluno extends Usuario {
	
	/**
	 * Data de nascimento do aluno.
	 * 
	 * @return A data de nascimento do aluno.
	 */
	@NotNull(message = "A data de nascimento do aluno não pode ser nula.")
	private Date dt_nasc_aluno;
	
	/**
	 * Data de registro do aluno.
	 * 
	 * @return A data de registro do aluno.
	 */
	@NotNull(message = "A data de registro do aluno não pode ser nula.")
	private Date dt_reg_aluno;
	
	/**
	 * Senha do aluno.
	 * 
	 * @return A senha do aluno.
	 */
	@NotNull(message = "A senha do aluno não pode ser nula.")
	private String senha_aluno;
	
	/**
	 * Quantidade de moedas do aluno.
	 * 
	 * @return A quantidade de moedas do aluno.
	 */
	private int moedas_aluno;
	
	/**
	 * Nível do aluno.
	 * 
	 * @return O nível do aluno.
	 */
	@NotNull(message = "O nível do aluno não pode ser nulo.")
	private Nivel nivel_aluno;
	
	/**
	 * Obtém a data de nascimento do aluno.
	 * 
	 * @return A data de nascimento do aluno.
	 */
	public Date getDt_nasc_aluno() {
		return dt_nasc_aluno;
	}
	
	/**
	 * Define a data de nascimento do aluno.
	 * 
	 * @param dt_nasc_aluno A data de nascimento do aluno.
	 */
	public void setDt_nasc_aluno(Date dt_nasc_aluno) {
		this.dt_nasc_aluno = dt_nasc_aluno;
	}
		
	/**
	 * Obtém a data de registro do aluno.
	 * 
	 * @return A data de registro do aluno.
	 */
	public Date getDt_reg_aluno() {
		return dt_reg_aluno;
	}
	
	/**
	 * Define a data de registro do aluno.
	 * 
	 * @param dt_reg_aluno A data de registro do aluno.
	 */
	public void setDt_reg_aluno(Date dt_reg_aluno) {
		this.dt_reg_aluno = dt_reg_aluno;
	}
	
	/**
	 * Obtém a senha do aluno.
	 * 
	 * @return A senha do aluno.
	 */
	public String getSenha_aluno() {
		return senha_aluno;
	}
	
	/**
	 * Define a senha do aluno.
	 * 
	 * @param senha_aluno A senha do aluno.
	 */
	public void setSenha_aluno(String senha_aluno) {
		this.senha_aluno = senha_aluno;
	}
	
	/**
	 * Obtém a quantidade de moedas do aluno.
	 * 
	 * @return A quantidade de moedas do aluno.
	 */
	public int getMoedas_aluno() {
		return moedas_aluno;
	}
	
	/**
	 * Define a quantidade de moedas do aluno.
	 * 
	 * @param moedas_aluno A quantidade de moedas do aluno.
	 */
	public void setMoedas_aluno(int moedas_aluno) {
		this.moedas_aluno = moedas_aluno;
	}
	
	/**
	 * Obtém o nível do aluno.
	 * 
	 * @return O nível do aluno.
	 */
	public Nivel getNivel_aluno() {
		return nivel_aluno;
	}
	
	/**
	 * Define o nível do aluno.
	 * 
	 * @param nivel_aluno O nível do aluno.
	 */
	public void setNivel_aluno(Nivel nivel_aluno) {
		this.nivel_aluno = nivel_aluno;
	}

	/**
	 * Construtor padrão.
	 */
	public Aluno() {
		super();
	}
	
	/**
	 * Construtor não padrão.
	 * 
	 * @param dt_nasc_aluno A data de nascimento do aluno.
	 * @param dt_reg_aluno A data de registro do aluno.
	 * @param senha_aluno A senha do aluno.
	 * @param moedas_aluno A quantidade de moedas do aluno.
	 * @param nivel_aluno O nível do aluno.
	 */
	public Aluno(@NotNull(message = "A data de nascimento do aluno não pode ser nula.") Date dt_nasc_aluno,
			@NotNull(message = "A data de registro do aluno não pode ser nula.") Date dt_reg_aluno,
			@NotNull(message = "A senha do aluno não pode ser nula.") String senha_aluno, int moedas_aluno,
			@NotNull(message = "O nível do aluno não pode ser nulo.") Nivel nivel_aluno) {
		super();
		this.dt_nasc_aluno = dt_nasc_aluno;
		this.dt_reg_aluno = dt_reg_aluno;
		this.senha_aluno = senha_aluno;
		this.moedas_aluno = moedas_aluno;
		this.nivel_aluno = nivel_aluno;
	}
	
	/**
	 * Sobrescreve o método hashCode.
	 * 
	 * @return O código hash do objeto Aluno.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dt_nasc_aluno, dt_reg_aluno, moedas_aluno, nivel_aluno, senha_aluno);
		return result;
	}
	
	/**
	 * Sobrescreve o método equals.
	 * 
	 * @param obj O objeto a ser comparado.
	 * @return true se os objetos são iguais, false caso contrário.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(dt_nasc_aluno, other.dt_nasc_aluno) && Objects.equals(dt_reg_aluno, other.dt_reg_aluno)
				&& moedas_aluno == other.moedas_aluno && Objects.equals(nivel_aluno, other.nivel_aluno)
				&& Objects.equals(senha_aluno, other.senha_aluno);
	}
	
	/**
	 * Retorna uma representação em formato de string do objeto Aluno.
	 * 
	 * @return Uma representação em formato de string do objeto Aluno.
	 */
	@Override
	public String toString() {
		return "Aluno [dt_nasc_aluno=" + dt_nasc_aluno + ", dt_reg_aluno=" + dt_reg_aluno + ", senha_aluno="
				+ senha_aluno + ", moedas_aluno=" + moedas_aluno + ", nivel_aluno=" + nivel_aluno + "]";
	}
}

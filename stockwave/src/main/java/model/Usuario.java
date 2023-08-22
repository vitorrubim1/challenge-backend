package model;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;

/**
 * Classe que representa um usuário da plataforma, podendo ser um Aluno, Funcionário ou Professor.
 * 
 * Métodos:
 * - Construtores: permitem criar instâncias da classe com diferentes conjuntos de atributos.
 * 
 * Exemplo de uso:
 * 
 * Usuario usuario = new Usuario();
 * usuario.setId_usuario(1);
 * usuario.setCpf_usuario("123456789");
 * usuario.setNome_usuario("João da Silva");
 * usuario.setEmail_usuario("joao@example.com");
 * 
 * System.out.println(usuario.getNome_usuario()); // Imprime o nome do usuário
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see services.UsuarioService
 * @see dao.UsuarioDao
 * @see controller.UsuarioResource
 * @see model.Aluno
 * @see model.Funcionario
 * @see model.Professor
 * 
 * @author Stockwave
 */
public class Usuario {
	
	/**
	 * ID do usuário.
	 */
	protected int id_usuario;
	
	/**
	 * CPF do usuário.
	 */
	@NotNull(message = "O CPF do usuário não pode ser nulo.")
	protected String cpf_usuario;
	
	/**
	 * Nome do usuário.
	 */
	@NotNull(message = "O Nome do usuário não pode ser nulo.")
	protected String nome_usuario;
	
	/**
	 * Email do usuário.
	 */
	@NotNull(message = "O Email do usuário não pode ser nulo.")
	protected String email_usuario;

	/**
	 * Obtém o ID do usuário.
	 * 
	 * @return O ID do usuário
	 */
	public int getId_usuario() {
		return id_usuario;
	}
	
	/**
	 * Define o ID do usuário.
	 * 
	 * @param id_usuario O ID do usuário a ser definido
	 */
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	/**
	 * Obtém o CPF do usuário.
	 * 
	 * @return O CPF do usuário
	 */
	public String getCpf_usuario() {
		return cpf_usuario;
	}

	/**
	 * Define o CPF do usuário.
	 * 
	 * @param cpf_usuario O CPF do usuário a ser definido
	 */
	public void setCpf_usuario(String cpf_usuario) {
		this.cpf_usuario = cpf_usuario;
	}

	/**
	 * Obtém o nome do usuário.
	 * 
	 * @return O nome do usuário
	 */
	public String getNome_usuario() {
		return nome_usuario;
	}

	/**
	 * Define o nome do usuário.
	 * 
	 * @param nome_usuario O nome do usuário a ser definido
	 */
	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	/**
	 * Obtém o email do usuário.
	 * 
	 * @return O email do usuário
	 */
	public String getEmail_usuario() {
		return email_usuario;
	}

	/**
	 * Define o email do usuário.
	 * 
	 * @param email_usuario O email do usuário a ser definido
	 */
	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}
	
	/**
	 * Construtor padrão da classe Usuario.
	 */
	public Usuario() {
		super();
	}
	
	/**
	 * Construtor da classe Usuario que define os atributos.
	 * 
	 * @param id_usuario     O ID do usuário
	 * @param cpf_usuario    O CPF do usuário (não pode ser nulo)
	 * @param nome_usuario   O nome do usuário (não pode ser nulo)
	 * @param email_usuario  O email do usuário (não pode ser nulo)
	 */
	public Usuario(int id_usuario, @NotNull(message = "O CPF do usuário não pode ser nulo.") String cpf_usuario,
			@NotNull(message = "O Nome do usuário não pode ser nulo.") String nome_usuario,
			@NotNull(message = "O Email do usuário não pode ser nulo.") String email_usuario) {
		super();
		this.id_usuario = id_usuario;
		this.cpf_usuario = cpf_usuario;
		this.nome_usuario = nome_usuario;
		this.email_usuario = email_usuario;
	}
	
	/**
	 * Retorna o código hash do usuário.
	 * 
	 * @return O código hash do usuário
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cpf_usuario, email_usuario, id_usuario, nome_usuario);
	}
	
	/**
	 * Verifica se o usuário é igual a outro objeto.
	 * 
	 * @param obj O objeto a ser comparado
	 * @return true se o usuário for igual ao objeto fornecido, false caso contrário
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(cpf_usuario, other.cpf_usuario) && Objects.equals(email_usuario, other.email_usuario)
				&& id_usuario == other.id_usuario && Objects.equals(nome_usuario, other.nome_usuario);
	}
	
	/**
	 * Retorna uma representação em formato de string do usuário.
	 * 
	 * @return Uma representação em formato de string do usuário
	 */
	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", cpf_usuario=" + cpf_usuario + ", nome_usuario=" + nome_usuario
				+ ", email_usuario=" + email_usuario + "]";
	}
}

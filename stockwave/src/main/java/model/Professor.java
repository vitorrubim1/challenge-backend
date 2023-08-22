package model;

import jakarta.validation.constraints.NotNull;

/**
 * Classe que representa um professor, que é um tipo de usuário.
 * 
 * A classe Professor herda os atributos e métodos da classe Usuario e adiciona comportamentos específicos para um professor.
 * 
 * Métodos:
 * - Construtores: permitem criar instâncias da classe com diferentes conjuntos de atributos.
 * 
 * Exemplo de uso:
 * 
 * Professor professor = new Professor();
 * professor.setId_usuario(1);
 * professor.setCpf_usuario("123456789");
 * professor.setNome_usuario("John Doe");
 * professor.setEmail_usuario("john.doe@example.com");
 * 
 * System.out.println(professor.getNome_usuario()); // Imprime o nome do professor
 * System.out.println(professor.getEmail_usuario()); // Imprime o email do professor
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see services.ProfessorService
 * @see dao.ProfessorDao
 * @see controller.ProfessorResource
 * @see model.Usuario
 * 
 * @author Stockwave
 * 
 */
public class Professor extends Usuario {
	
	/**
	 * Construtor Padrão
	 */
	public Professor() {
		super();
	}
	
	/**
	 * Construtor Não Padrão
	 * 
	 * @param id_usuario     o ID do usuário
	 * @param cpf_usuario    o CPF do usuário
	 * @param nome_usuario   o nome do usuário
	 * @param email_usuario  o email do usuário
	 */
	public Professor(int id_usuario, @NotNull(message = "O CPF do usuário não pode ser nulo.") String cpf_usuario,
			@NotNull(message = "O Nome do usuário não pode ser nulo.") String nome_usuario,
			 @NotNull(message = "O Email do usuário não pode ser nulo.") String email_usuario) {
		super(id_usuario, cpf_usuario, nome_usuario, email_usuario);
	}
}

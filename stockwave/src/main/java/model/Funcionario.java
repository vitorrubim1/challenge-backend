package model;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;

/**
 * Classe responsável por representar os usuários que são funcionários.
 * 
 * A classe `Funcionario` é uma subclasse da classe `Usuario` e contém informações específicas para funcionários,
 * como senha e cargo.
 *
 * Atributos:
 * - id_usuario: identificador único do funcionário.
 * - senha_funcionario: senha do funcionário.
 * - cargo_funcionario: cargo do funcionário.
 * 
 * A classe `Funcionario` inclui os métodos getters e setters para acessar e modificar os atributos,
 * além dos métodos `hashCode`, `equals` e `toString` para a comparação e representação da classe.
 * 
 * Exemplo de uso:
 * 
 * Funcionario funcionario = new Funcionario();
 * funcionario.setId_usuario(1);
 * funcionario.setSenha_funcionario("senha123");
 * funcionario.setCargo_funcionario("Gerente");
 * 
 * System.out.println(funcionario.getId_usuario()); // Imprime 1
 * System.out.println(funcionario.getSenha_funcionario()); // Imprime "senha123"
 * System.out.println(funcionario.getCargo_funcionario()); // Imprime "Gerente"
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see services.FuncionarioService
 * @see dao.FuncionarioDao
 * @see controller.FuncionarioResource
 * @see model.Usuario
 * 
 * @author Stockwave
 *
 */
public class Funcionario extends Usuario {
	
	/**
	 * Identificador único do funcionário.
	 */
	private int id_usuario;
	
	/**
	 * Senha do funcionário.
	 */
	@NotNull(message = "A senha do funcionário não pode ser nula.")
	private String senha_funcionario;
	
	/**
	 * Cargo do funcionário.
	 */
	@NotNull(message = "O cargo do funcionário não pode ser nulo.")
	private String cargo_funcionario;
	
	/**
	 * Obtém o identificador único do funcionário.
	 * 
	 * @return o identificador único do funcionário.
	 */
	public int getId_usuario() {
		return id_usuario;
	}

	/**
	 * Define o identificador único do funcionário.
	 * 
	 * @param id_usuario o identificador único do funcionário.
	 */
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	/**
	 * Obtém a senha do funcionário.
	 * 
	 * @return a senha do funcionário.
	 */
	public String getSenha_funcionario() {
		return senha_funcionario;
	}

	/**
	 * Define a senha do funcionário.
	 * 
	 * @param senha_funcionario a senha do funcionário.
	 */
	public void setSenha_funcionario(String senha_funcionario) {
		this.senha_funcionario = senha_funcionario;
	}
	
	/**
	 * Obtém o cargo do funcionário.
	 * 
	 * @return o cargo do funcionário.
	 */
	public String getCargo_funcionario() {
		return cargo_funcionario;
	}

	/**
	 * Define o cargo do funcionário.
	 * 
	 * @param cargo_funcionario o cargo do funcionário.
	 */
	public void setCargo_funcionario(String cargo_funcionario) {
		this.cargo_funcionario = cargo_funcionario;
	}
	
	/**
	 * Construtor padrão da classe Funcionario.
	 */
	public Funcionario() {
		super();
	}
	
	/**
	 * Construtor da classe Funcionario que permite definir o id, a senha e o cargo do funcionário.
	 * 
	 * @param id_usuario o identificador único do funcionário.
	 * @param senha_funcionario a senha do funcionário.
	 * @param cargo_funcionario o cargo do funcionário.
	 */
	public Funcionario(int id_usuario,
			@NotNull(message = "A senha do funcionário não pode ser nula.") String senha_funcionario,
			@NotNull(message = "O cargo do funcionário não pode ser nulo.") String cargo_funcionario) {
		super();
		this.id_usuario = id_usuario;
		this.senha_funcionario = senha_funcionario;
		this.cargo_funcionario = cargo_funcionario;
	}
	
	/**
	 * Calcula o código hash do objeto Funcionario.
	 * 
	 * @return o código hash calculado.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cargo_funcionario, id_usuario, senha_funcionario);
	}
	
	/**
	 * Verifica se o objeto Funcionario é igual a outro objeto.
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
		Funcionario other = (Funcionario) obj;
		return Objects.equals(cargo_funcionario, other.cargo_funcionario) && id_usuario == other.id_usuario
				&& Objects.equals(senha_funcionario, other.senha_funcionario);
	}
	
	/**
	 * Retorna uma representação em formato de string do objeto Funcionario.
	 * 
	 * @return uma representação em formato de string do objeto Funcionario.
	 */
	@Override
	public String toString() {
		return "Funcionario [id_usuario=" + id_usuario + ", senha_funcionario=" + senha_funcionario
				+ ", cargo_funcionario=" + cargo_funcionario + "]";
	}
}

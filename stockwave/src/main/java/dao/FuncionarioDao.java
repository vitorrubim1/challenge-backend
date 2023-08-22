package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.validation.Valid;
import model.Funcionario;

/**
 * Classe de acesso a dados para Funcionario.
 * 
 * Essa classe oferece métodos para manipulação dos dados relacionados aos Funcionarios no banco de dados.
 * Ela estende a classe Repository, que fornece a funcionalidade de conexão com o banco de dados.
 * 
 * Exemplo de uso:
 * 
 * // Instanciar a classe FuncionarioDao
 * FuncionarioDao funcionarioDao = new FuncionarioDao();
 * 
 * // Listar todos os Funcionários cadastrados no banco de dados
 * ArrayList&lt;Funcionario&lt; listaFuncionarios = funcionarioDao.listarFuncionarios();
 * 
 * // Buscar um Funcionário por ID
 * Funcionario funcionario = funcionarioDao.buscarFuncionarioPorId(1);
 * 
 * // Atualizar um Funcionário
 * Funcionario novoFuncionario = new Funcionario();
 * // ... configuração das informações do Funcionário ...
 * Funcionario funcionarioAtualizado = funcionarioDao.atualizarFuncionario(novoFuncionario);
 * 
 * // Cadastrar um novo Funcionário
 * Funcionario novoFuncionario = new Funcionario();
 * // ... configuração das informações do Funcionário ...
 * Funcionario funcionarioCadastrado = funcionarioDao.cadastrarFuncionario(novoFuncionario);
 * 
 * // Deletar um Funcionário
 * boolean deletado = funcionarioDao.deletarFuncionario(1);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Funcionario
 * @see services.FuncionarioService
 * @see controller.FuncionarioResource
 * @see dao.Repository
 * 
 * @author Stockwave
 * 
 */
public class FuncionarioDao extends Repository {
	
	/**
     * Retorna uma lista de todos os funcionários cadastrados no banco de dados.
     *
     * @return uma lista de objetos Funcionario
     */
	public ArrayList<Funcionario> listarFuncionarios() {
		String sql = "SELECT usuario.id_usuario, usuario.cpf_usuario, usuario.nome_usuario, usuario.email_usuario, funcionario.senha_funcionario, funcionario.cargo_funcionario FROM usuario INNER JOIN funcionario ON usuario.id_usuario = funcionario.id_usuario";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();

		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Funcionario funcionario = new Funcionario();
					funcionario.setId_usuario(rs.getInt("id_usuario"));
					funcionario.setCpf_usuario(rs.getString("cpf_usuario"));
					funcionario.setNome_usuario(rs.getString("nome_usuario"));
					funcionario.setEmail_usuario(rs.getString("email_usuario"));
					funcionario.setSenha_funcionario(rs.getString("senha_funcionario"));
					funcionario.setCargo_funcionario(rs.getString("cargo_funcionario"));
					
					listaFuncionarios.add(funcionario);
				}
			} else {
				System.out.println("Não foi possível encontrar registros na tabela FUNCIONARIO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem da tabela FUNCIONARIO: " + e.getMessage());
		}

		return listaFuncionarios;
	}
	
	/**
     * Busca um funcionário pelo ID de usuário.
     *
     * @param id_usuario o ID do usuário a ser buscado
     * @return um objeto Funcionario se encontrado, caso contrário, null
     */
	public static Funcionario buscarFuncionarioPorId(int id_usuario) {
		String sql = "SELECT usuario.id_usuario, usuario.cpf_usuario, usuario.nome_usuario, usuario.email_usuario, funcionario.senha_funcionario, funcionario.cargo_funcionario FROM usuario INNER JOIN funcionario ON usuario.id_usuario = funcionario.id_usuario WHERE usuario.id_usuario = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_usuario);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Funcionario funcionario = new Funcionario();
				while (rs.next()) {
					funcionario.setId_usuario(rs.getInt("id_usuario"));
					funcionario.setCpf_usuario(rs.getString("cpf_usuario"));
					funcionario.setNome_usuario(rs.getString("nome_usuario"));
					funcionario.setEmail_usuario(rs.getString("email_usuario"));
					funcionario.setSenha_funcionario(rs.getString("senha_funcionario"));
					funcionario.setCargo_funcionario(rs.getString("cargo_funcionario"));
				}

				return funcionario;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_usuario + " na tabela FUNCIONARIO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar o FUNCIONARIO no banco de dados: " + e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o Prepared Statement: " + e.getMessage());
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o Result Set: " + e.getMessage());
				}
			}
		}

		return null;
	}
	
	/**
     * Atualiza as informações de um funcionário.
     *
     * @param funcionario o objeto Funcionario contendo as informações atualizadas
     * @return o objeto Funcionario atualizado se a atualização for bem-sucedida, caso contrário, null
     */
	public static Funcionario atualizarFuncionario(@Valid Funcionario funcionario) {
		String sql = "UPDATE funcionario SET senha_funcionario = ?, cargo_funcionario = ? WHERE id_usuario = ?";
		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setString(1, funcionario.getSenha_funcionario());
			cs.setString(2, funcionario.getCargo_funcionario());
			cs.setInt(3, funcionario.getId_usuario());
			cs.executeUpdate();

			return funcionario;

		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar o FUNCIONARIO no banco de dados: " + e.getMessage());
		} finally {
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o Callable Statement: " + e.getMessage());
				}
			}
		}

		return null;
	}
	
	/**
     * Cadastra um novo funcionário no banco de dados.
     *
     * @param funcionario_novo o objeto Funcionario contendo as informações do novo funcionário
     * @return o objeto Funcionario cadastrado se o cadastro for bem-sucedido, caso contrário, null
     */
	public static Funcionario cadastrarFuncionario(@Valid Funcionario funcionario_novo) {

	// @formatter:off
    String sql_usuario = "BEGIN INSERT INTO usuario ("
            + "    id_usuario,"
            + "    cpf_usuario,"
            + "    nome_usuario,"
            + "    email_usuario"
            + ") VALUES ("
            + "    SQ_USUARIO.nextval,"
            + "    ?,"
            + "    ?,"
            + "    ?"
            + ") "
            + "RETURNING id_usuario INTO ?; END;";
    // @formatter:on

		CallableStatement cs_usuario = null;

		try {
			cs_usuario = getConnection().prepareCall(sql_usuario);
			cs_usuario.setString(1, funcionario_novo.getCpf_usuario());
			cs_usuario.setString(2, funcionario_novo.getNome_usuario());
			cs_usuario.setString(3, funcionario_novo.getEmail_usuario());
			cs_usuario.registerOutParameter(4, java.sql.Types.INTEGER);
			cs_usuario.executeUpdate();
			funcionario_novo.setId_usuario(cs_usuario.getInt(4));
		} catch (SQLException e) {
			System.out.println("Não foi possível cadastrar novo USUARIO no banco de dados: " + e.getMessage());
			return null;
		} finally {
			if (cs_usuario != null) {
				try {
					cs_usuario.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o Callable Statement: " + e.getMessage());
				}
			}
		}

	// @formatter:off
    String sql_funcionario = "INSERT INTO funcionario ("
            + "id_usuario,"
    		+ "senha_funcionario,"
            + "cargo_funcionario"
            + ") VALUES ("
            + "?,"
            + "?,"
            + "?"
            + ") ";
    // @formatter:on

		CallableStatement cs_funcionario = null;

		try {
			cs_funcionario = getConnection().prepareCall(sql_funcionario);
			cs_funcionario.setInt(1, funcionario_novo.getId_usuario());
			cs_funcionario.setString(2, funcionario_novo.getSenha_funcionario());
			cs_funcionario.setString(3, funcionario_novo.getCargo_funcionario());
			cs_funcionario.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível cadastrar novo FUNCIONARIO no banco de dados: " + e.getMessage());
			return null;
		} finally {
			if (cs_funcionario != null) {
				try {
					cs_funcionario.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o Callable Statement: " + e.getMessage());
				}
			}
		}

		return funcionario_novo;
	}
	
	/**
     * Deleta um funcionário do banco de dados.
     *
     * @param id_usuario o ID do funcionário a ser deletado
     * @return true se o funcionário for deletado com sucesso, caso contrário, false
     */
	public static boolean deletarFuncionario(int id_usuario) {
		String sql_funcionario = "DELETE FROM funcionario WHERE id_usuario = ?";
		PreparedStatement ps_funcionario = null;
		boolean funcionarioDeletado = false;

		try {
			ps_funcionario = getConnection().prepareStatement(sql_funcionario);
			ps_funcionario.setInt(1, id_usuario);
			ps_funcionario.executeUpdate();
			funcionarioDeletado = true;
		} catch (SQLException e) {
			System.out.println("Não foi possível deletar o FUNCIONARIO no banco de dados: " + e.getMessage());
		} finally {
			if (ps_funcionario != null) {
				try {
					ps_funcionario.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o Prepared Statement: " + e.getMessage());
				}
			}
		}

		if (funcionarioDeletado) {
			String sql_usuario = "DELETE FROM usuario WHERE id_usuario = ?";
			PreparedStatement ps_usuario = null;

			try {
				ps_usuario = getConnection().prepareStatement(sql_usuario);
				ps_usuario.setInt(1, id_usuario);
				ps_usuario.executeUpdate();
				return true;
			} catch (SQLException e) {
				System.out.println("Não foi possível deletar o USUARIO no banco de dados: " + e.getMessage());
			} finally {
				if (ps_usuario != null) {
					try {
						ps_usuario.close();
					} catch (SQLException e) {
						System.out.println("Não foi possível fechar o Prepared Statement: " + e.getMessage());
					}
				}
			}
		}

		return false;
	}
}
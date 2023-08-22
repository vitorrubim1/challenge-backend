package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.validation.Valid;
import model.Professor;

/**
 * Classe de acesso a dados para Professor.
 *
 * Essa classe oferece métodos para manipulação dos dados relacionados à tabela Professor no banco de dados.
 * Ela estende a classe Repository, que fornece a funcionalidade de conexão com o banco de dados.
 *
 * Exemplo de uso:
 *
 * // Instanciar a classe ProfessorDao
 * ProfessorDao professorDao = new ProfessorDao();
 *
 * // Listar todos os professores cadastrados no banco de dados
 * ArrayList&lt;Professor&lt; listaProfessores = professorDao.listarProfessores();
 *
 * // Buscar um professor por ID
 * Professor professor = ProfessorDao.buscarProfessorPorId(1);
 *
 * // Atualizar um professor
 * Professor novoProfessor = new Professor();
 * // ... configuração das informações do professor ...
 * Professor professorAtualizado = ProfessorDao.atualizarProfessor(novoProfessor);
 *
 * // Cadastrar um novo professor
 * Professor novoProfessor = new Professor();
 * // ... configuração das informações do professor ...
 * Professor professorCadastrado = ProfessorDao.cadastrarProfessor(novoProfessor);
 *
 * // Deletar um professor
 * boolean deletado = ProfessorDao.deletarProfessor(1);
 *
 * @since 1.0
 * @version 1.0
 *
 * @see model.Professor
 * @see services.ProfessorService
 * @see controller.ProfessorResource
 * @see dao.Repository
 * 
 * @author Stockwave
 * 
 */
public class ProfessorDao extends Repository {
	
	/**
	 * Lista todos os professores cadastrados no banco de dados.
	 *
	 * @return uma lista de objetos Professor com as informações dos professores cadastrados
	 */
	public ArrayList<Professor> listarProfessores() {
		String sql = "SELECT usuario.id_usuario, usuario.cpf_usuario, usuario.nome_usuario, usuario.email_usuario FROM usuario INNER JOIN professor ON usuario.id_usuario = professor.id_usuario";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Professor> listaProfessores = new ArrayList<>();

		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Professor professor = new Professor();
					professor.setId_usuario(rs.getInt("id_usuario"));
					professor.setCpf_usuario(rs.getString("cpf_usuario"));
					professor.setNome_usuario(rs.getString("nome_usuario"));
					professor.setEmail_usuario(rs.getString("email_usuario"));
					listaProfessores.add(professor);
				}
			} else {
				System.out.println("Não foi possível encontrar registros na tabela PROFESSOR do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem da tabela PROFESSOR: " + e.getMessage());
		}

		return listaProfessores;
	}
	
	/**
	 * Busca um professor pelo ID.
	 *
	 * @param id_usuario o ID do usuário do professor a ser buscado
	 * @return o objeto Professor correspondente ao ID fornecido, ou null se não encontrado
	 */
	public static Professor buscarProfessorPorId(int id_usuario) {
		String sql = "SELECT usuario.id_usuario, usuario.cpf_usuario, usuario.nome_usuario, usuario.email_usuario"
				+ " FROM usuario" + " INNER JOIN professor ON usuario.id_usuario = professor.id_usuario"
				+ " WHERE usuario.id_usuario = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_usuario);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Professor professor = new Professor();
				while (rs.next()) {
					professor.setId_usuario(rs.getInt("id_usuario"));
					professor.setCpf_usuario(rs.getString("cpf_usuario"));
					professor.setNome_usuario(rs.getString("nome_usuario"));
					professor.setEmail_usuario(rs.getString("email_usuario"));
				}

				return professor;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_usuario + " na tabela PROFESSOR do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar o PROFESSOR no banco de dados: " + e.getMessage());
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
	 * Atualiza um professor no banco de dados.
	 *
	 * @param professor o objeto Professor com as informações atualizadas
	 * @return o objeto Professor atualizado, ou null se a atualização não foi bem-sucedida
	 */
	public static Professor atualizarProfessor(@Valid Professor professor) {
		String sql = "UPDATE usuario SET cpf_usuario = ?, nome_usuario = ?, email_usuario = ? WHERE id_usuario = ?";
		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setString(1, professor.getCpf_usuario());
			cs.setString(2, professor.getNome_usuario());
			cs.setString(3, professor.getEmail_usuario());
			cs.setInt(4, professor.getId_usuario());
			cs.executeUpdate();

			return professor;

		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar o PROFESSOR no banco de dados: " + e.getMessage());
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
	 * Cadastra um novo professor no banco de dados.
	 *
	 * @param professor_novo o objeto Professor a ser cadastrado
	 * @return o objeto Professor cadastrado, ou null se o cadastro não foi bem-sucedido
	 */
	public static Professor cadastrarProfessor(@Valid Professor professor_novo) {

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
			cs_usuario.setString(1, professor_novo.getCpf_usuario());
			cs_usuario.setString(2, professor_novo.getNome_usuario());
			cs_usuario.setString(3, professor_novo.getEmail_usuario());
			cs_usuario.registerOutParameter(4, java.sql.Types.INTEGER);
			cs_usuario.executeUpdate();
			professor_novo.setId_usuario(cs_usuario.getInt(4));
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
    String sql_professor = "INSERT INTO professor ("
            + "id_usuario"
            + ") VALUES ("
            + "?"
            + ") ";
    // @formatter:on

		CallableStatement cs_professor = null;

		try {
			cs_professor = getConnection().prepareCall(sql_professor);
			cs_professor.setInt(1, professor_novo.getId_usuario());
			cs_professor.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível cadastrar novo PROFESSOR no banco de dados: " + e.getMessage());
			return null;
		} finally {
			if (cs_professor != null) {
				try {
					cs_professor.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o Callable Statement: " + e.getMessage());
				}
			}
		}

		return professor_novo;
	}
	
	/**
	 * Deleta um professor do banco de dados.
	 *
	 * @param id_usuario o ID do usuário do professor a ser deletado
	 * @return true se o professor foi deletado com sucesso, false caso contrário
	 */
	public static boolean deletarProfessor(int id_usuario) {
		String sql_professor = "DELETE FROM professor WHERE id_usuario = ?";
		PreparedStatement ps_professor = null;
		boolean professorDeletado = false;

		try {
			ps_professor = getConnection().prepareStatement(sql_professor);
			ps_professor.setInt(1, id_usuario);
			ps_professor.executeUpdate();
			professorDeletado = true;
		} catch (SQLException e) {
			System.out.println("Não foi possível deletar o PROFESSOR no banco de dados: " + e.getMessage());
		} finally {
			if (ps_professor != null) {
				try {
					ps_professor.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o Prepared Statement: " + e.getMessage());
				}
			}
		}

		if (professorDeletado) {
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
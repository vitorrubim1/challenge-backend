package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.validation.Valid;
import model.Nivel;

/**
 * Classe de acesso a dados para Nivel.
 *
 * Essa classe oferece métodos para manipulação dos dados relacionados à tabela Nivel no banco de dados.
 * Ela estende a classe Repository, que fornece a funcionalidade de conexão com o banco de dados.
 *
 * Exemplo de uso:
 *
 * // Instanciar a classe NivelDao
 * NivelDao nivelDao = new NivelDao();
 *
 * // Listar todos os níveis cadastrados no banco de dados
 * ArrayList&lt;String&lt; listaNiveis = nivelDao.listarNiveis();
 *
 * // Buscar um nível por nome
 * Nivel nivel = NivelDao.buscarNivelPorNome("Iniciante");
 *
 * // Atualizar um nível
 * Nivel novoNivel = new Nivel();
 * // ... configuração das informações do nível ...
 * Nivel nivelAtualizado = NivelDao.atualizarNivel(novoNivel, "Iniciante");
 *
 * // Cadastrar um novo nível
 * Nivel novoNivel = new Nivel();
 * // ... configuração das informações do nível ...
 * Nivel nivelCadastrado = NivelDao.cadastrarNivel(novoNivel);
 *
 * // Deletar um nível
 * boolean deletado = NivelDao.deletarNivel("Iniciante");
 *
 * @since 1.0
 * @version 1.0
 *
 * @see model.Nivel
 * @see dao.Repository
 * 
 * @author Stockwave
 * 
 */
public class NivelDao extends Repository {
	
	/**
	 * Lista todos os níveis cadastrados no banco de dados.
	 *
	 * @return uma lista de strings com os nomes dos níveis
	 */
	public ArrayList<String> listarNiveis() {
		String sql = "SELECT * FROM nivel ORDER BY CASE nome_nivel WHEN 'Iniciante' THEN 1 WHEN 'Intermediário' THEN 2 WHEN 'Avançado' THEN 3 ELSE 4 END";

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> listaNiveis = new ArrayList<>();

		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String nivel = rs.getString("nome_nivel");
					listaNiveis.add(nivel);
					
				}
			} else {
				System.out.println("Não foi possível encontrar registros na tabela NIVEL do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem da tabela NIVEL: " + e.getMessage());
		}

		return listaNiveis;
	}
	
	/**
	 * Busca um nível pelo nome.
	 *
	 * @param nome_nivel o nome do nível a ser buscado
	 * @return o objeto Nivel correspondente ao nome fornecido, ou null se não encontrado
	 */
	public static Nivel buscarNivelPorNome(String nome_nivel) {
		String sql = "SELECT * FROM nivel WHERE nome_nivel = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, nome_nivel);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Nivel nivel = new Nivel();
				while (rs.next()) {
					nivel.setNome_nivel(rs.getString("nome_nivel"));
				}

				return nivel;

			} else {
				System.out.println("Não foi possível encontrar registros na tabela NIVEL do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar o NIVEL no banco de dados: " + e.getMessage());
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
	 * Atualiza um nível no banco de dados.
	 *
	 * @param nivel o objeto Nivel com as informações atualizadas
	 * @param nome_nivel o nome do nível a ser atualizado
	 * @return o objeto Nivel atualizado, ou null se a atualização não foi bem-sucedida
	 */
	public static Nivel atualizarNivel(@Valid Nivel nivel, String nome_nivel) {
		String sql = "UPDATE nivel SET nome_nivel = ? WHERE nome_nivel = ?";
		PreparedStatement ps = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, nivel.getNome_nivel());
			ps.setString(2, nome_nivel);
			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				nivel.setNome_nivel(nivel.getNome_nivel());
				return nivel;
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar o NIVEL no banco de dados: " + e.getMessage());
			return null;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o PreparedStatement: " + e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Cadastra um novo nível no banco de dados.
	 *
	 * @param nivel_novo o objeto Nivel a ser cadastrado
	 * @return o objeto Nivel cadastrado, ou null se o cadastro não foi bem-sucedido
	 */
	public static Nivel cadastrarNivel(@Valid Nivel nivel_novo) {

		// @formatter:off
		String sql = "INSERT INTO nivel ("
				+ "nome_nivel"
				+ ") VALUES ("
				+ "?"
				+ ") ";
		// @formatter:on

		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setString(1, nivel_novo.getNome_nivel());
			cs.executeUpdate();
			nivel_novo.setNome_nivel(nivel_novo.getNome_nivel());

			return nivel_novo;

		} catch (SQLException e) {
			System.out.println("Não foi possível cadastrar novo NIVEL no banco de dados: " + e.getMessage());
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
	 * Deleta um nível do banco de dados.
	 *
	 * @param nome_nivel o nome do nível a ser deletado
	 * @return true se o nível foi deletado com sucesso, false caso contrário
	 */
	public static boolean deletarNivel(String nome_nivel) {

		Nivel nivel_deletar = null;
		String sql = "DELETE FROM nivel WHERE nome_nivel = ?";
		PreparedStatement ps = null;
		nivel_deletar = buscarNivelPorNome(nome_nivel);

		if (nivel_deletar == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, nome_nivel);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("Não foi possível deletar o NIVEL no banco de dados: " + e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o Prepared Statement: " + e.getMessage());
				}
			}
		}

		return false;
	}
}
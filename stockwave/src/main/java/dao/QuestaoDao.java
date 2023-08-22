package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.validation.Valid;
import model.Questao;
import model.Resposta;

/**
 * Classe de acesso a dados para Questao.
 *
 * Essa classe oferece métodos para manipulação dos dados relacionados à tabela Questao no banco de dados.
 * Ela estende a classe Repository, que fornece a funcionalidade de conexão com o banco de dados.
 *
 * Exemplo de uso:
 *
 * // Instanciar a classe QuestaoDao
 * QuestaoDao questaoDao = new QuestaoDao();
 *
 * // Listar todas as questões cadastradas no banco de dados
 * ArrayList&lt;Questao&lt; listaQuestoes = questaoDao.listarQuestoes();
 *
 * // Buscar uma questão por ID
 * Questao questao = QuestaoDao.buscarQuestaoPorId(1);
 *
 * // Atualizar uma questão
 * Questao novaQuestao = new Questao();
 * // ... configuração das informações da questão ...
 * Questao questaoAtualizada = QuestaoDao.atualizarQuestao(novaQuestao);
 *
 * // Cadastrar uma nova questão
 * Questao novaQuestao = new Questao();
 * // ... configuração das informações da questão ...
 * Questao questaoCadastrada = QuestaoDao.cadastrarQuestao(novaQuestao);
 *
 * // Deletar uma questão
 * boolean deletado = QuestaoDao.deletarQuestao(1);
 *
 * @since 1.0
 * @version 1.0
 *
 * @see model.Questao
 * @see services.QuestaoService
 * @see controller.QuestaoResource
 * @see model.Resposta
 * @see dao.Repository
 * 
 * @author Stockwave
 * 
 */
public class QuestaoDao extends Repository {
	
	/**
	 * Lista todas as questões cadastradas no banco de dados.
	 *
	 * @return uma lista de objetos Questao com as informações das questões
	 */
	public ArrayList<Questao> listarQuestoes() {
		String sql = "SELECT * FROM questao ORDER BY id_questao";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Questao> listaQuestoes = new ArrayList<>();

		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Questao questao = new Questao();
					questao.setId_questao(rs.getInt("id_questao"));
					questao.setPergunta_questao(rs.getString("pergunta_questao"));
					questao.setAlt_a_questao(rs.getString("alt_a_questao"));
					questao.setAlt_b_questao(rs.getString("alt_b_questao"));
					questao.setAlt_c_questao(rs.getString("alt_c_questao"));
					questao.setAlt_d_questao(rs.getString("alt_d_questao"));
					questao.setAlt_e_questao(rs.getString("alt_e_questao"));
					Resposta resposta_questao = new Resposta();
					resposta_questao.setResposta(rs.getString("resposta_questao"));
					questao.setResposta_questao(resposta_questao);

					listaQuestoes.add(questao);
				}
			} else {
				System.out.println("Não foi possível encontrar registros na tabela QUESTAO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem da tabela USUARIO: " + e.getMessage());
		}

		return listaQuestoes;
	}
	
	/**
	 * Busca uma questão pelo ID.
	 *
	 * @param id_questao o ID da questão a ser buscada
	 * @return o objeto Questao correspondente ao ID fornecido, ou null se não encontrado
	 */
	public static Questao buscarQuestaoPorId(int id_questao) {
		String sql = "SELECT * FROM questao WHERE id_questao = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_questao);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Questao questao = new Questao();
				while (rs.next()) {
					questao.setId_questao(rs.getInt("id_questao"));
					questao.setPergunta_questao(rs.getString("pergunta_questao"));
					questao.setAlt_a_questao(rs.getString("alt_a_questao"));
					questao.setAlt_b_questao(rs.getString("alt_b_questao"));
					questao.setAlt_c_questao(rs.getString("alt_c_questao"));
					questao.setAlt_d_questao(rs.getString("alt_d_questao"));
					questao.setAlt_e_questao(rs.getString("alt_e_questao"));
					Resposta resposta_questao = new Resposta();
					resposta_questao.setResposta(rs.getString("resposta_questao"));
					questao.setResposta_questao(resposta_questao);
				}

				return questao;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_questao + " na tabela QUESTAO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a QUESTAO no banco de dados: " + e.getMessage());
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
	 * Atualiza uma questão no banco de dados.
	 *
	 * @param questao o objeto Questao com as informações atualizadas
	 * @return o objeto Questao atualizado, ou null se a atualização não foi bem-sucedida
	 */
	public static Questao atualizarQuestao(@Valid Questao questao) {
		String sql = "UPDATE questao SET pergunta_questao = ?, alt_a_questao = ?, alt_b_questao = ?, alt_c_questao = ?, alt_d_questao = ?, alt_e_questao = ?, resposta_questao = ? WHERE id_questao = ?";
		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setString(1, questao.getPergunta_questao());
			cs.setString(2, questao.getAlt_a_questao());
			cs.setString(3, questao.getAlt_b_questao());
			cs.setString(4, questao.getAlt_c_questao());
			cs.setString(5, questao.getAlt_d_questao());
			cs.setString(6, questao.getAlt_e_questao());
			Resposta resposta_questao = new Resposta();
			resposta_questao = questao.getResposta_questao();
			cs.setString(7, resposta_questao.getResposta()); 
			cs.setInt(8, questao.getId_questao());
			cs.executeUpdate();

			return questao;

		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar a QUESTAO no banco de dados: " + e.getMessage());
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
	 * Cadastra uma nova questão no banco de dados.
	 *
	 * @param questao_nova o objeto Questao a ser cadastrado
	 * @return o objeto Questao cadastrado, ou null se o cadastro não foi bem-sucedido
	 */
	public static Questao cadastrarQuestao(@Valid Questao questao_nova) {

		// @formatter:off
		String sql = "INSERT INTO questao ("
		        + "    id_questao,"
		        + "    pergunta_questao,"
		        + "    alt_a_questao,"
		        + "    alt_b_questao,"
		        + "    alt_c_questao,"
		        + "    alt_d_questao,"
		        + "    alt_e_questao,"
		        + "    resposta_questao"
		        + ") VALUES ("
		        + "    SQ_QUESTAO.nextval,"
		        + "    ?,"
		        + "    ?,"
		        + "    ?,"
		        + "    ?,"
		        + "    ?,"
		        + "    ?,"
		        + "    ?"
		        + ")";
		// @formatter:on

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
		    ps = getConnection().prepareStatement(sql, new String[] {"id_questao"});
		    ps.setString(1, questao_nova.getPergunta_questao());
		    ps.setString(2, questao_nova.getAlt_a_questao());
		    ps.setString(3, questao_nova.getAlt_b_questao());
		    ps.setString(4, questao_nova.getAlt_c_questao());
		    ps.setString(5, questao_nova.getAlt_d_questao());
		    ps.setString(6, questao_nova.getAlt_e_questao());
		    ps.setString(7, questao_nova.getResposta_questao().getResposta());
		    ps.executeUpdate();
		    rs = ps.getGeneratedKeys();
		    if (rs.next()) {
		        questao_nova.setId_questao(rs.getInt(1));
		    }

		    return questao_nova;
		} catch (SQLException e) {
		    System.out.println("Não foi possível cadastrar nova QUESTAO no banco de dados: " + e.getMessage());
		} finally {
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) {
		            System.out.println("Não foi possível fechar o ResultSet: " + e.getMessage());
		        }
		    }
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (SQLException e) {
		            System.out.println("Não foi possível fechar o PreparedStatement: " + e.getMessage());
		        }
		    }
		}

		return null;
	}
	
	/**
	 * Deleta uma questão do banco de dados.
	 *
	 * @param id_questao o ID da questão a ser deletada
	 * @return true se a questão foi deletada com sucesso, false caso contrário
	 */
	public static boolean deletarQuestao(int id_questao) {

		Questao questao_deletar = null;
		String sql = "DELETE FROM questao WHERE id_questao = ?";
		PreparedStatement ps = null;
		questao_deletar = buscarQuestaoPorId(id_questao);

		if (questao_deletar == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_questao);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("Não foi possível deletar a QUESTAO no banco de dados: " + e.getMessage());
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
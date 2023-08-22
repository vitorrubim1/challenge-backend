package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.validation.Valid;
import model.Modulo_Questao;
import model.Nivel;
import model.Modulo;
import model.Questao;
import model.Resposta;

/**
 * Classe de acesso a dados para Modulo_Aula.
 *
 * Essa classe oferece métodos para manipulação dos dados relacionados a tabela Modulo_Aula no banco de dados.
 * Ela estende a classe Repository, que fornece a funcionalidade de conexão com o banco de dados.
 *
 * Exemplo de uso:
 *
 * // Instanciar a classe Modulo_AulaDao
 * Modulo_AulaDao modulo_aulaDao = new Modulo_AulaDao();
 *
 * // Listar todos os Módulos e Aulas cadastrados no banco de dados
 * ArrayList&lt;Modulo_Aula&lt; listaModulo_Aulas = modulo_aulaDao.listarModulo_Aulas();
 *
 * // Buscar um Módulo e Aula por ID
 * Modulo_Aula modulo_aula = modulo_aulaDao.buscarModulo_AulaPorId(1, 1);
 *
 * // Buscar Módulos e Aulas por ID de Módulo
 * ArrayList&lt;Modulo_Aula&lt; listaModulo_AulasPorId = modulo_aulaDao.buscarModulo_AulasPorId(1);
 *
 * // Atualizar um Módulo e Aula
 * Modulo_Aula novoModulo_Aula = new Modulo_Aula();
 * // ... configuração das informações do Módulo e Aula ...
 * Modulo_Aula modulo_aulaAtualizado = modulo_aulaDao.atualizarModulo_Aula(novoModulo_Aula, 1);
 *
 * // Cadastrar um novo Módulo e Aula
 * Modulo_Aula novoModulo_Aula = new Modulo_Aula();
 * // ... configuração das informações do Módulo e Aula ...
 * Modulo_Aula modulo_aulaCadastrado = modulo_aulaDao.cadastrarModulo_Aula(novoModulo_Aula);
 *
 * // Deletar um Módulo e Aula
 * boolean deletado = modulo_aulaDao.deletarModulo_Aula(1, 1);
 *
 * @since 1.0
 * @version 1.0
 *
 * @see model.Modulo_Aula
 * @see services.Modulo_AulaService
 * @see controller.Modulo_AulaResource
 * @see dao.Repository
 * @see model.Modulo
 * @see model.Aula
 * 
 * @author Stockwave
 * 
 */
public class Modulo_QuestaoDao extends Repository {
	
	/**
     * Lista todas as instâncias de Modulo_Questao do banco de dados.
     *
     * @return ArrayList contendo as instâncias de Modulo_Questao encontradas no banco de dados.
     */
	public ArrayList<Modulo_Questao> listarModulo_Questoes() {
		String sql = "SELECT m.id_modulo, m.nome_modulo, m.url_imagem_modulo, m.nivel_modulo,"
				+ " q.id_questao, q.pergunta_questao, q.alt_a_questao, q.alt_b_questao, q.alt_c_questao, q.alt_d_questao, q.alt_e_questao, q.resposta_questao"
				+ " FROM modulo m"
				+ " JOIN modulo_questao mq ON m.id_modulo = mq.id_modulo"
				+ " JOIN questao q ON q.id_questao = mq.id_questao"
				+ " JOIN nivel n ON m.nivel_modulo = n.nome_nivel"
				+ " JOIN resposta r ON q.resposta_questao = r.resposta"
				+ " ORDER BY mq.id_modulo";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Modulo_Questao> listaModulo_Questoes = new ArrayList<>();

		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Modulo_Questao modulo_questao = new Modulo_Questao();
					
					Modulo modulo = new Modulo();
					modulo.setId_modulo(rs.getInt("id_modulo"));
					modulo.setNome_modulo(rs.getString("nome_modulo"));
					modulo.setUrl_imagem_modulo(rs.getString("url_imagem_modulo"));
					Nivel nivel_modulo = new Nivel();
					nivel_modulo.setNome_nivel(rs.getString("nivel_modulo"));
					modulo.setNivel_modulo(nivel_modulo);
					
					modulo_questao.setModulo(modulo);
					
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
					
					modulo_questao.setQuestao(questao);

					listaModulo_Questoes.add(modulo_questao);
				}
			} else {
				System.out.println("Não foi possível encontrar registros na tabela MODULO_QUESTAO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem da tabela MODULO_QUESTAO: " + e.getMessage());
		}

		return listaModulo_Questoes;
	}
	
	/**
     * Busca uma instância de Modulo_Questao pelo ID do módulo e ID da questão.
     *
     * @param id_modulo  o ID do módulo a ser buscado.
     * @param id_questao o ID da questão a ser buscada.
     * @return a instância de Modulo_Questao encontrada no banco de dados, ou null se não encontrada.
     */
	public static Modulo_Questao buscarModulo_QuestaoPorId(int id_modulo, int id_questao) {
		String sql = "SELECT m.id_modulo, m.nome_modulo, m.url_imagem_modulo, m.nivel_modulo,"
				+ " q.id_questao, q.pergunta_questao, q.alt_a_questao, q.alt_b_questao, q.alt_c_questao, q.alt_d_questao, q.alt_e_questao, q.resposta_questao"
				+ " FROM modulo m"
				+ " JOIN modulo_questao mq ON m.id_modulo = mq.id_modulo"
				+ " JOIN questao q ON q.id_questao = mq.id_questao"
				+ " JOIN nivel n ON m.nivel_modulo = n.nome_nivel"
				+ " JOIN resposta r ON q.resposta_questao = r.resposta"
				+ " WHERE m.id_modulo = ? AND q.id_questao = ?"
				+ " ORDER BY mq.id_modulo";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_modulo);
			ps.setInt(2, id_questao);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Modulo_Questao modulo_questao = new Modulo_Questao();
				while (rs.next()) {
					Modulo modulo = new Modulo();
					modulo.setId_modulo(rs.getInt("id_modulo"));
					modulo.setNome_modulo(rs.getString("nome_modulo"));
					modulo.setUrl_imagem_modulo(rs.getString("url_imagem_modulo"));
					Nivel nivel_modulo = new Nivel();
					nivel_modulo.setNome_nivel(rs.getString("nivel_modulo"));
					modulo.setNivel_modulo(nivel_modulo);
					
					modulo_questao.setModulo(modulo);
					
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
					
					modulo_questao.setQuestao(questao);
					
				}

				return modulo_questao;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_modulo + " na tabela MODULO_QUESTAO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar o MODULO_QUESTAO no banco de dados: " + e.getMessage());
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
     * Busca todas as instâncias de Modulo_Questao relacionadas a um ID de módulo específico.
     *
     * @param id_modulo o ID do módulo a ser buscado.
     * @return ArrayList contendo as instâncias de Modulo_Questao encontradas no banco de dados.
     */
	public static ArrayList<Modulo_Questao> buscarModulo_QuestoesPorId(int id_modulo) {
		String sql = "SELECT m.id_modulo, m.nome_modulo, m.url_imagem_modulo, m.nivel_modulo,"
				+ " q.id_questao, q.pergunta_questao, q.alt_a_questao, q.alt_b_questao, q.alt_c_questao, q.alt_d_questao, q.alt_e_questao, q.resposta_questao"
				+ " FROM modulo m"
				+ " JOIN modulo_questao mq ON m.id_modulo = mq.id_modulo"
				+ " JOIN questao q ON q.id_questao = mq.id_questao"
				+ " JOIN nivel n ON m.nivel_modulo = n.nome_nivel"
				+ " JOIN resposta r ON q.resposta_questao = r.resposta"
				+ " WHERE m.id_modulo = ?"
				+ " ORDER BY mq.id_modulo";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_modulo);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				ArrayList<Modulo_Questao> listaModulo_Questoes = new ArrayList<>();
				while (rs.next()) {
					Modulo_Questao modulo_questao = new Modulo_Questao();
					
					Modulo modulo = new Modulo();
					modulo.setId_modulo(rs.getInt("id_modulo"));
					modulo.setNome_modulo(rs.getString("nome_modulo"));
					modulo.setUrl_imagem_modulo(rs.getString("url_imagem_modulo"));
					Nivel nivel_modulo = new Nivel();
					nivel_modulo.setNome_nivel(rs.getString("nivel_modulo"));
					modulo.setNivel_modulo(nivel_modulo);
					
					modulo_questao.setModulo(modulo);
					
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
					
					modulo_questao.setQuestao(questao);
					
					listaModulo_Questoes.add(modulo_questao);
				}

				return listaModulo_Questoes;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_modulo + " na tabela MODULO_QUESTAO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar o MODULO_QUESTAO no banco de dados: " + e.getMessage());
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
     * Atualiza uma instância de Modulo_Questao no banco de dados.
     *
     * @param modulo_questao a instância de Modulo_Questao a ser atualizada.
     * @param id_questao     o ID da questão a ser atualizada.
     * @return a instância de Modulo_Questao atualizada, ou null se não for possível atualizar.
     */
	public static Modulo_Questao atualizarModulo_Questao(@Valid Modulo_Questao modulo_questao, int id_questao) {
		String sql = "UPDATE modulo_questao SET id_modulo = ?, id_questao = ? WHERE id_modulo = ? AND id_questao = ?";
		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setInt(1, modulo_questao.getModulo().getId_modulo());
			cs.setInt(2, modulo_questao.getQuestao().getId_questao());
			cs.setInt(3, modulo_questao.getModulo().getId_modulo());
			cs.setInt(4, id_questao);
			cs.executeUpdate();

			return modulo_questao;

		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar o MODULO_QUESTAO no banco de dados: " + e.getMessage());
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
     * Cadastra uma nova instância de Modulo_Questao no banco de dados.
     *
     * @param modulo_questao_novo a nova instância de Modulo_Questao a ser cadastrada.
     * @return a instância de Modulo_Questao cadastrada, ou null se não for possível cadastrar.
     */
	public static Modulo_Questao cadastrarModulo_Questao(@Valid Modulo_Questao modulo_questao_novo) {

		// @formatter:off
		String sql = "INSERT INTO modulo_questao ("
		        + "    id_modulo,"
		        + "    id_questao"
		        + ") VALUES ("
		        + "    ?,"
		        + "    ?"
		        + ")";
		// @formatter:on

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
		    ps = getConnection().prepareStatement(sql, new String[] {"id_modulo"});
		    ps.setInt(1, modulo_questao_novo.getModulo().getId_modulo());
		    ps.setInt(2, modulo_questao_novo.getQuestao().getId_questao());
		    ps.executeUpdate();
		    rs = ps.getGeneratedKeys();
		    if (rs.next()) {
		        modulo_questao_novo.getModulo().setId_modulo(rs.getInt(1));
		    }

		    return modulo_questao_novo;
		} catch (SQLException e) {
		    System.out.println("Não foi possível cadastrar novo MODULO_QUESTAO no banco de dados: " + e.getMessage());
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
     * Deleta uma instância de Modulo_Questao do banco de dados.
     *
     * @param id_modulo  o ID do módulo a ser deletado.
     * @param id_questao o ID da questão a ser deletada.
     * @return true se a instância foi deletada com sucesso, false caso contrário.
     */
	public static boolean deletarModulo_Questao(int id_modulo, int id_questao) {

		Modulo_Questao modulo_questao_deletar = null;
		String sql = "DELETE FROM modulo_questao WHERE id_modulo = ? AND id_questao = ?";
		PreparedStatement ps = null;
		modulo_questao_deletar = buscarModulo_QuestaoPorId(id_modulo, id_questao);

		if (modulo_questao_deletar == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_modulo);
			ps.setInt(2, id_questao);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("Não foi possível deletar o MODULO_QUESTAO no banco de dados: " + e.getMessage());
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
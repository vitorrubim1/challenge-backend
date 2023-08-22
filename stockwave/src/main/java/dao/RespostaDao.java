package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.validation.Valid;
import model.Resposta;

/**
 * Classe de acesso a dados para Resposta.
 *
 * Essa classe oferece métodos para manipulação dos dados relacionados à tabela Resposta no banco de dados.
 * Ela estende a classe Repository, que fornece a funcionalidade de conexão com o banco de dados.
 *
 * Exemplo de uso:
 *
 * // Instanciar a classe RespostaDao
 * RespostaDao respostaDao = new RespostaDao();
 *
 * // Listar todas as respostas cadastradas no banco de dados
 * ArrayList&lt;Resposta&lt; listaRespostas = respostaDao.listarRespostas();
 *
 * // Buscar uma resposta por nome
 * Resposta resposta = RespostaDao.buscarRespostaPorNome("Resposta 1");
 *
 * // Atualizar uma resposta
 * Resposta novaResposta = new Resposta();
 * // ... configuração das informações da resposta ...
 * Resposta respostaAtualizada = RespostaDao.atualizarResposta(novaResposta, "Resposta 1");
 *
 * // Cadastrar uma nova resposta
 * Resposta novaResposta = new Resposta();
 * // ... configuração das informações da resposta ...
 * Resposta respostaCadastrada = RespostaDao.cadastrarResposta(novaResposta);
 *
 * // Deletar uma resposta
 * boolean deletada = RespostaDao.deletarResposta("Resposta 1");
 *
 * @since 1.0
 * @version 1.0
 *
 * @see model.Resposta
 * @see services.RespostaService
 * @see controller.RespostaResource
 * @see dao.Repository
 * 
 * @author Stockwave
 * 
 */
public class RespostaDao extends Repository {
	
	/**
	 * Lista todas as respostas cadastradas no banco de dados.
	 *
	 * @return uma lista de objetos Resposta com as respostas cadastradas
	 */
	public ArrayList<Resposta> listarRespostas() {
		String sql = "SELECT * FROM resposta ORDER BY resposta";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Resposta> listaRespostas = new ArrayList<>();

		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Resposta resposta = new Resposta();
					resposta.setResposta(rs.getString("resposta"));
					listaRespostas.add(resposta);
				}
			} else {
				System.out.println("Não foi possível encontrar registros na tabela RESPOSTA do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem da tabela RESPOSTA: " + e.getMessage());
		}

		return listaRespostas;
	}
	
	/**
	 * Busca uma resposta pelo nome.
	 *
	 * @param str_resposta o nome da resposta a ser buscada
	 * @return o objeto Resposta correspondente ao nome fornecido, ou null se não encontrado
	 */
	public static Resposta buscarRespostaPorNome(String str_resposta) {
		String sql = "SELECT * FROM resposta WHERE resposta = '?'";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, str_resposta);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Resposta resposta = new Resposta();
				while (rs.next()) {
					resposta.setResposta(rs.getString("resposta"));
				}

				return resposta;

			} else {
				System.out.println("Não foi possível encontrar registros na tabela RESPOSTA do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a RESPOSTA no banco de dados: " + e.getMessage());
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
	 * Atualiza uma resposta no banco de dados.
	 *
	 * @param resposta o objeto Resposta com as informações atualizadas
	 * @param resposta_novo o nome da resposta a ser atualizada
	 * @return o objeto Resposta atualizado, ou null se a atualização não foi bem-sucedida
	 */
	public static Resposta atualizarResposta(@Valid Resposta resposta, String resposta_novo) {
		String sql = "UPDATE resposta SET resposta = ? WHERE resposta = ?";
		PreparedStatement ps = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, resposta.getResposta());
			ps.setString(2, resposta_novo);
			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				resposta.setResposta(resposta.getResposta());
				return resposta;
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar a RESPOSTA no banco de dados: " + e.getMessage());
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
	 * Cadastra uma nova resposta no banco de dados.
	 *
	 * @param resposta_novo o objeto Resposta a ser cadastrado
	 * @return o objeto Resposta cadastrado, ou null se o cadastro não foi bem-sucedido
	 */
	public static Resposta cadastrarResposta(@Valid Resposta resposta_novo) {

		// @formatter:off
		String sql = "INSERT INTO resposta ("
				+ "resposta"
				+ ") VALUES ("
				+ "?"
				+ ") ";
		// @formatter:on

		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setString(1, resposta_novo.getResposta());
			cs.executeUpdate();
			resposta_novo.setResposta(resposta_novo.getResposta());

			return resposta_novo;

		} catch (SQLException e) {
			System.out.println("Não foi possível cadastrar nova RESPOSTA no banco de dados: " + e.getMessage());
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
	 * Deleta uma resposta do banco de dados.
	 *
	 * @param resposta o nome da resposta a ser deletada
	 * @return true se a resposta foi deletada com sucesso, false caso contrário
	 */
	public static boolean deletarResposta(String resposta) {

		Resposta resposta_deletar = null;
		String sql = "DELETE FROM resposta WHERE resposta = ?";
		PreparedStatement ps = null;
		resposta_deletar = buscarRespostaPorNome(resposta);

		if (resposta_deletar == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, resposta);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("Não foi possível deletar a RESPOSTA no banco de dados: " + e.getMessage());
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
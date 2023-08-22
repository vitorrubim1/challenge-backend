package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.validation.Valid;
import model.Modulo_Aula;
import model.Nivel;
import model.Modulo;
import model.Aula;

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
public class Modulo_AulaDao extends Repository {
	
	/**
	 * Retorna uma lista de todas as instâncias de Modulo_Aula existentes no banco de dados.
	 *
	 * @return ArrayList contendo as Modulo_Aulas encontradas.
	 */
	public ArrayList<Modulo_Aula> listarModulo_Aulas() {
		String sql = "SELECT m.id_modulo, m.nome_modulo, m.url_imagem_modulo, m.nivel_modulo,"
				+ " a.id_aula, a.nome_aula, a.descricao_aula, a.conteudo_aula, a.url_video_aula, a.url_audio_aula"
				+ " FROM modulo m"
				+ " JOIN modulo_aula ma ON m.id_modulo = ma.id_modulo"
				+ " JOIN aula a ON a.id_aula = ma.id_aula"
				+ " JOIN nivel n ON m.nivel_modulo = n.nome_nivel"
				+ " ORDER BY ma.id_modulo";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Modulo_Aula> listaModulo_Aulas = new ArrayList<>();

		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Modulo_Aula modulo_aula = new Modulo_Aula();
					
					Modulo modulo = new Modulo();
					modulo.setId_modulo(rs.getInt("id_modulo"));
					modulo.setNome_modulo(rs.getString("nome_modulo"));
					modulo.setUrl_imagem_modulo(rs.getString("url_imagem_modulo"));
					Nivel nivel_modulo = new Nivel();
					nivel_modulo.setNome_nivel(rs.getString("nivel_modulo"));
					modulo.setNivel_modulo(nivel_modulo);
					
					modulo_aula.setModulo(modulo);
					
					Aula aula = new Aula();
					aula.setId_aula(rs.getInt("id_aula"));
					aula.setNome_aula(rs.getString("nome_aula"));
					aula.setDescricao_aula(rs.getString("descricao_aula"));
					aula.setConteudo_aula(rs.getString("conteudo_aula"));
					aula.setUrl_video_aula(rs.getString("url_video_aula"));
					aula.setUrl_audio_aula(rs.getString("url_audio_aula"));
					
					modulo_aula.setAula(aula);

					listaModulo_Aulas.add(modulo_aula);
				}
			} else {
				System.out.println("Não foi possível encontrar registros na tabela MODULO_AULA do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem da tabela MODULO_AULA: " + e.getMessage());
		}

		return listaModulo_Aulas;
	}
	
	/**
	 * Busca e retorna uma instância de Modulo_Aula com base nos IDs de módulo e aula fornecidos.
	 *
	 * @param id_modulo O ID do módulo.
	 * @param id_aula O ID da aula.
	 * @return A instância de Modulo_Aula encontrada ou null se não encontrada.
	 */
	public static Modulo_Aula buscarModulo_AulaPorId(int id_modulo, int id_aula) {
		String sql = "SELECT m.id_modulo, m.nome_modulo, m.url_imagem_modulo, m.nivel_modulo,"
				+ " a.id_aula, a.nome_aula, a.descricao_aula, a.conteudo_aula, a.url_video_aula, a.url_audio_aula"
				+ " FROM modulo m"
				+ " JOIN modulo_aula ma ON m.id_modulo = ma.id_modulo"
				+ " JOIN aula a ON a.id_aula = ma.id_aula"
				+ " JOIN nivel n ON m.nivel_modulo = n.nome_nivel"
				+ " WHERE ma.id_modulo = ? AND ma.id_aula = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_modulo);
			ps.setInt(2, id_aula);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Modulo_Aula modulo_aula = new Modulo_Aula();
				while (rs.next()) {
					Modulo modulo = new Modulo();
					modulo.setId_modulo(rs.getInt("id_modulo"));
					modulo.setNome_modulo(rs.getString("nome_modulo"));
					modulo.setUrl_imagem_modulo(rs.getString("url_imagem_modulo"));
					Nivel nivel_modulo = new Nivel();
					nivel_modulo.setNome_nivel(rs.getString("nivel_modulo"));
					modulo.setNivel_modulo(nivel_modulo);
					
					modulo_aula.setModulo(modulo);
					
					Aula aula = new Aula();
					aula.setId_aula(rs.getInt("id_aula"));
					aula.setNome_aula(rs.getString("nome_aula"));
					aula.setDescricao_aula(rs.getString("descricao_aula"));
					aula.setConteudo_aula(rs.getString("conteudo_aula"));
					aula.setUrl_video_aula(rs.getString("url_video_aula"));
					aula.setUrl_audio_aula(rs.getString("url_audio_aula"));
					
					modulo_aula.setAula(aula);
					
				}

				return modulo_aula;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_modulo + " na tabela MODULO_AULA do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar o MODULO_AULA no banco de dados: " + e.getMessage());
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
	 * Busca e retorna uma lista de instâncias de Modulo_Aula relacionadas a um determinado ID de módulo.
	 *
	 * @param id_modulo O ID do módulo.
	 * @return ArrayList contendo as Modulo_Aulas encontradas.
	 */
	public static ArrayList<Modulo_Aula> buscarModulo_AulasPorId(int id_modulo) {
		String sql = "SELECT m.id_modulo, m.nome_modulo, m.url_imagem_modulo, m.nivel_modulo,"
				+ " a.id_aula, a.nome_aula, a.descricao_aula, a.conteudo_aula, a.url_video_aula, a.url_audio_aula"
				+ " FROM modulo m"
				+ " JOIN modulo_aula ma ON m.id_modulo = ma.id_modulo"
				+ " JOIN aula a ON a.id_aula = ma.id_aula"
				+ " JOIN nivel n ON m.nivel_modulo = n.nome_nivel"
				+ " WHERE ma.id_modulo = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_modulo);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				ArrayList<Modulo_Aula> listaModulo_Aulas = new ArrayList<>();
				while (rs.next()) {
					Modulo_Aula modulo_aula = new Modulo_Aula();
					
					Modulo modulo = new Modulo();
					modulo.setId_modulo(rs.getInt("id_modulo"));
					modulo.setNome_modulo(rs.getString("nome_modulo"));
					modulo.setUrl_imagem_modulo(rs.getString("url_imagem_modulo"));
					Nivel nivel_modulo = new Nivel();
					nivel_modulo.setNome_nivel(rs.getString("nivel_modulo"));
					modulo.setNivel_modulo(nivel_modulo);
					
					modulo_aula.setModulo(modulo);
					
					Aula aula = new Aula();
					aula.setId_aula(rs.getInt("id_aula"));
					aula.setNome_aula(rs.getString("nome_aula"));
					aula.setDescricao_aula(rs.getString("descricao_aula"));
					aula.setConteudo_aula(rs.getString("conteudo_aula"));
					aula.setUrl_video_aula(rs.getString("url_video_aula"));
					aula.setUrl_audio_aula(rs.getString("url_audio_aula"));
					
					modulo_aula.setAula(aula);
					
					listaModulo_Aulas.add(modulo_aula);
				}

				return listaModulo_Aulas;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_modulo + " na tabela MODULO_AULA do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar o MODULO_AULA no banco de dados: " + e.getMessage());
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
	 * Atualiza uma instância de Modulo_Aula com base nos parâmetros fornecidos.
	 *
	 * @param modulo_aula A instância de Modulo_Aula a ser atualizada.
	 * @param id_aula O ID da aula a ser atualizada.
	 * @return A instância de Modulo_Aula atualizada ou null se não for possível atualizar.
	 */
	public static Modulo_Aula atualizarModulo_Aula(@Valid Modulo_Aula modulo_aula, int id_aula) {
		String sql = "UPDATE modulo_aula SET id_modulo = ?, id_aula = ? WHERE id_modulo = ? AND id_aula = ?";
		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setInt(1, modulo_aula.getModulo().getId_modulo());
			cs.setInt(2, modulo_aula.getAula().getId_aula());
			cs.setInt(3, modulo_aula.getModulo().getId_modulo());
			cs.setInt(4, id_aula);
			cs.executeUpdate();

			return modulo_aula;

		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar o MODULO_AULA no banco de dados: " + e.getMessage());
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
	 * Cadastra uma nova instância de Modulo_Aula no banco de dados.
	 *
	 * @param modulo_aula_novo A nova instância de Modulo_Aula a ser cadastrada.
	 * @return A instância de Modulo_Aula cadastrada ou null se não for possível cadastrar.
	 */
	public static Modulo_Aula cadastrarModulo_Aula(@Valid Modulo_Aula modulo_aula_novo) {

		// @formatter:off
		String sql = "INSERT INTO modulo_aula ("
		        + "    id_modulo,"
		        + "    id_aula"
		        + ") VALUES ("
		        + "    ?,"
		        + "    ?"
		        + ")";
		// @formatter:on

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
		    ps = getConnection().prepareStatement(sql, new String[] {"id_modulo"});
		    ps.setInt(1, modulo_aula_novo.getModulo().getId_modulo());
		    ps.setInt(2, modulo_aula_novo.getAula().getId_aula());
		    ps.executeUpdate();
		    rs = ps.getGeneratedKeys();
		    if (rs.next()) {
		        modulo_aula_novo.getModulo().setId_modulo(rs.getInt(1));
		    }

		    return modulo_aula_novo;
		} catch (SQLException e) {
		    System.out.println("Não foi possível cadastrar novo MODULO_AULA no banco de dados: " + e.getMessage());
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
	 * Deleta uma instância de Modulo_Aula com base nos IDs de módulo e aula fornecidos.
	 *
	 * @param id_modulo O ID do módulo a ser deletado.
	 * @param id_aula O ID da aula a ser deletada.
	 * @return true se a Modulo_Aula foi deletada com sucesso, false caso contrário.
	 */
	public static boolean deletarModulo_Aula(int id_modulo, int id_aula) {

		Modulo_Aula modulo_aula_deletar = null;
		String sql = "DELETE FROM modulo_aula WHERE id_modulo = ? AND id_aula = ?";
		PreparedStatement ps = null;
		modulo_aula_deletar = buscarModulo_AulaPorId(id_modulo, id_aula);

		if (modulo_aula_deletar == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_modulo);
			ps.setInt(2, id_aula);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("Não foi possível deletar o MODULO_AULA no banco de dados: " + e.getMessage());
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

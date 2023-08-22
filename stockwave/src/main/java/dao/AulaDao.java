package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.validation.Valid;
import model.Aula;

/**
 * Classe de acesso a dados para Aula.
 * 
 * Essa classe oferece métodos para manipulação dos dados relacionados às Aulas no banco de dados.
 * Ela estende a classe Repository, que fornece a funcionalidade de conexão com o banco de dados.
 * 
 * Exemplo de uso:
 * 
 * // Instanciar a classe AulaDao
 * AulaDao aulaDao = new AulaDao();
 * 
 * // Listar todas as Aulas cadastradas no banco de dados
 * ArrayList&lt;Aula&lt; listaAulas = aulaDao.listarAulas();
 * 
 * // Buscar uma Aula por ID
 * Aula aula = aulaDao.buscarAulaPorId(1);
 * 
 * // Atualizar uma Aula
 * Aula novaAula = new Aula();
 * // ... configuração das informações da Aula ...
 * Aula aulaAtualizada = aulaDao.atualizarAula(novaAula);
 * 
 * // Cadastrar uma nova Aula
 * Aula novaAula = new Aula();
 * // ... configuração das informações da Aula ...
 * Aula aulaCadastrada = aulaDao.cadastrarAula(novaAula);
 * 
 * // Deletar uma Aula
 * boolean deletado = aulaDao.deletarAula(1);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Aula
 * @see services.AulaService
 * @see controller.AulaResource
 * @see dao.Repository
 * 
 * @author Stockwave
 * 
 */
public class AulaDao extends Repository {
	
	/**
     * Lista todas as Aulas cadastradas no banco de dados.
     *
     * @return uma lista de Aulas
     */
	public ArrayList<Aula> listarAulas() {
		String sql = "SELECT * FROM aula ORDER BY id_aula";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Aula> listaAulas = new ArrayList<>();

		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Aula aula = new Aula();
					aula.setId_aula(rs.getInt("id_aula"));
					aula.setNome_aula(rs.getString("nome_aula"));
					aula.setDescricao_aula(rs.getString("descricao_aula"));
					aula.setConteudo_aula(rs.getString("conteudo_aula"));
					aula.setUrl_video_aula(rs.getString("url_video_aula"));
					aula.setUrl_audio_aula(rs.getString("url_audio_aula"));

					listaAulas.add(aula);
				}
			} else {
				System.out.println("Não foi possível encontrar registros na tabela AULA do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem da tabela USUARIO: " + e.getMessage());
		}

		return listaAulas;
	}
	
	/**
     * Busca uma Aula pelo ID.
     *
     * @param id_aula o ID da Aula a ser buscada
     * @return a Aula encontrada ou null se não encontrada
     */
	public static Aula buscarAulaPorId(int id_aula) {
		String sql = "SELECT * FROM aula WHERE id_aula = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_aula);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Aula aula = new Aula();
				while (rs.next()) {
					aula.setId_aula(rs.getInt("id_aula"));
					aula.setNome_aula(rs.getString("nome_aula"));
					aula.setDescricao_aula(rs.getString("descricao_aula"));
					aula.setConteudo_aula(rs.getString("conteudo_aula"));
					aula.setUrl_video_aula(rs.getString("url_video_aula"));
					aula.setUrl_audio_aula(rs.getString("url_audio_aula"));
				}

				return aula;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_aula + " na tabela AULA do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a AULA no banco de dados: " + e.getMessage());
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
     * Atualiza uma Aula.
     *
     * @param aula a Aula com as informações atualizadas
     * @return a Aula atualizada ou null se ocorrer um erro
     */
	public static Aula atualizarAula(@Valid Aula aula) {
		String sql = "UPDATE aula SET nome_aula = ?, descricao_aula = ?, conteudo_aula = ?, url_video_aula = ?, url_audio_aula = ? WHERE id_aula = ?";
		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setString(1, aula.getNome_aula());
			cs.setString(2, aula.getDescricao_aula());
			cs.setString(3, aula.getConteudo_aula());
			cs.setString(4, aula.getUrl_video_aula());
			cs.setString(5, aula.getUrl_audio_aula());
			cs.setInt(6, aula.getId_aula());
			cs.executeUpdate();

			return aula;

		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar a AULA no banco de dados: " + e.getMessage());
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
     * Cadastra uma nova Aula.
     *
     * @param aula_nova a nova Aula a ser cadastrada
     * @return a Aula cadastrada ou null se ocorrer um erro
     */
	public static Aula cadastrarAula(@Valid Aula aula_nova) {

		// @formatter:off
		String sql = "INSERT INTO aula ("
		        + "    id_aula,"
		        + "    nome_aula,"
		        + "    descricao_aula,"
		        + "    conteudo_aula,"
		        + "    url_video_aula,"
		        + "    url_audio_aula"
		        + ") VALUES ("
		        + "    SQ_AULA.nextval,"
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
		    ps = getConnection().prepareStatement(sql, new String[] {"id_aula"});
		    ps.setString(1, aula_nova.getNome_aula());
		    ps.setString(2, aula_nova.getDescricao_aula());
		    ps.setString(3, aula_nova.getConteudo_aula());
		    ps.setString(4, aula_nova.getUrl_video_aula());
		    ps.setString(5, aula_nova.getUrl_audio_aula());
		    ps.executeUpdate();
		    rs = ps.getGeneratedKeys();
		    if (rs.next()) {
		    	aula_nova.setId_aula(rs.getInt(1));
		    }

		    return aula_nova;
		} catch (SQLException e) {
		    System.out.println("Não foi possível cadastrar nova AULA no banco de dados: " + e.getMessage());
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
    * Deleta uma Aula pelo ID.
    *
    * @param id_aula o ID da Aula a ser deletada
    * @return true se a Aula foi deletada com sucesso, false caso contrário
    */
	public static boolean deletarAula(int id_aula) {
		Aula aula_deletar = null;
		String sql = "DELETE FROM aula WHERE id_aula = ?";
		PreparedStatement ps = null;
		aula_deletar = buscarAulaPorId(id_aula);

		if (aula_deletar == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_aula);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("Não foi possível deletar a AULA no banco de dados: " + e.getMessage());
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
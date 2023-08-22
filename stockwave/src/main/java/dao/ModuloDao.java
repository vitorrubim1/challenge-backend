package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import model.Modulo;
import model.Nivel;

/**
 * Classe de acesso a dados para Modulo.
 *
 * Essa classe oferece métodos para manipulação dos dados relacionados à tabela Modulo no banco de dados.
 * Ela estende a classe Repository, que fornece a funcionalidade de conexão com o banco de dados.
 *
 * Exemplo de uso:
 *
 * // Instanciar a classe ModuloDao
 * ModuloDao moduloDao = new ModuloDao();
 *
 * // Listar todos os módulos cadastrados no banco de dados
 * ArrayList&lt;Modulo&lt; listaModulos = moduloDao.listarModulos();
 *
 * // Listar módulos divididos em subgrupos
 * Map&lt;String, List&lt;Map&lt;String, Object&lt;&lt;&lt; subgrupos = moduloDao.listarModulosSubgrupos();
 *
 * // Buscar um módulo por ID
 * Modulo modulo = ModuloDao.buscarModuloPorId(1);
 *
 * // Atualizar um módulo
 * Modulo novoModulo = new Modulo();
 * // ... configuração das informações do módulo ...
 * Modulo moduloAtualizado = ModuloDao.atualizarModulo(novoModulo);
 *
 * // Cadastrar um novo módulo
 * Modulo novoModulo = new Modulo();
 * // ... configuração das informações do módulo ...
 * Modulo moduloCadastrado = ModuloDao.cadastrarModulo(novoModulo);
 *
 * // Deletar um módulo
 * boolean deletado = ModuloDao.deletarModulo(1);
 *
 * @since 1.0
 * @version 1.0
 *
 * @see model.Modulo
 * @see services.ModuloService
 * @see controller.ModuloResource
 * @see dao.Repository
 * @see model.Nivel
 * 
 * @author Stockwave
 * 
 */
public class ModuloDao extends Repository {
	
	/**
     * Lista todos os módulos cadastrados no banco de dados.
     *
     * @return ArrayList contendo os módulos encontrados no banco de dados.
     */
	public ArrayList<Modulo> listarModulos() {
		String sql = "SELECT * FROM modulo ORDER BY id_modulo";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Modulo> listaModulos = new ArrayList<>();

		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Modulo modulo = new Modulo();
					modulo.setId_modulo(rs.getInt("id_modulo"));
					modulo.setNome_modulo(rs.getString("nome_modulo"));
					modulo.setUrl_imagem_modulo(rs.getString("url_imagem_modulo"));
					Nivel nivel_modulo = new Nivel();
					nivel_modulo.setNome_nivel(rs.getString("nivel_modulo"));
					modulo.setNivel_modulo(nivel_modulo);

					listaModulos.add(modulo);
				}
			} else {
				System.out.println("Não foi possível encontrar registros na tabela MODULO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem da tabela MODULO: " + e.getMessage());
		}

		return listaModulos;
	}
	
	/**
     * Lista os módulos divididos em subgrupos.
     *
     * @return Map contendo os subgrupos de módulos.
     * A chave "first" contém a lista de módulos do primeiro subgrupo.
     * A chave "second" contém a lista de módulos do segundo subgrupo.
     */
	public Map<String, List<Map<String, Object>>> listarModulosSubgrupos() {
	    String sql = "SELECT * FROM modulo ORDER BY id_modulo";
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Map<String, List<Map<String, Object>>> subgrupos = new HashMap<>();
	    List<Map<String, Object>> first = new ArrayList<>();
	    List<Map<String, Object>> second = new ArrayList<>();

	    try {
	        ps = getConnection().prepareStatement(sql);
	        rs = ps.executeQuery();

	        int count = 0;
	        while (rs.next()) {
	            Map<String, Object> modulo = new HashMap<>();
	            modulo.put("id_modulo", rs.getString("id_modulo"));
	            modulo.put("nome_modulo", rs.getString("nome_modulo"));
	            modulo.put("nivel_modulo", rs.getString("nivel_modulo"));
	            modulo.put("imagem_modulo", rs.getString("url_imagem_modulo"));

	            if (count < 3) {
	                first.add(modulo);
	            } else {
	                second.add(modulo);
	            }

	            count++;
	        }

	        subgrupos.put("first", first);
	        subgrupos.put("second", second);

	    } catch (SQLException e) {
	        System.out.println("Não foi possível consultar a listagem da tabela MODULO: " + e.getMessage());
	    }

	    return subgrupos;
	}
	
	/**
     * Busca um módulo pelo seu ID.
     *
     * @param id_modulo o ID do módulo a ser buscado.
     * @return o módulo encontrado no banco de dados, ou null se não encontrado.
     */
	public static Modulo buscarModuloPorId(int id_modulo) {
		String sql = "SELECT * FROM modulo WHERE id_modulo = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_modulo);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Modulo modulo = new Modulo();
				while (rs.next()) {
					modulo.setId_modulo(rs.getInt("id_modulo"));
					modulo.setId_modulo(rs.getInt("id_modulo"));
					modulo.setNome_modulo(rs.getString("nome_modulo"));
					modulo.setUrl_imagem_modulo(rs.getString("url_imagem_modulo"));
					Nivel nivel_modulo = new Nivel();
					nivel_modulo.setNome_nivel(rs.getString("nivel_modulo"));
					modulo.setNivel_modulo(nivel_modulo);
				}

				return modulo;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_modulo + " na tabela MODULO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar o MODULO no banco de dados: " + e.getMessage());
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
     * Atualiza um módulo existente.
     *
     * @param modulo o objeto Modulo contendo as informações atualizadas do módulo.
     * @return o módulo atualizado, ou null se não foi possível atualizar.
     */
	public static Modulo atualizarModulo(@Valid Modulo modulo) {
		String sql = "UPDATE modulo SET nome_modulo = ?, url_imagem_modulo = ?, nivel_modulo = ? WHERE id_modulo = ?";
		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setString(1, modulo.getNome_modulo());
			cs.setString(2, modulo.getUrl_imagem_modulo());
			Nivel nivel_modulo = new Nivel();
			nivel_modulo = modulo.getNivel_modulo();
			cs.setString(3, nivel_modulo.getNome_nivel()); 
			cs.setInt(4, modulo.getId_modulo());
			cs.executeUpdate();

			return modulo;

		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar o MODULO no banco de dados: " + e.getMessage());
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
     * Cadastra um novo módulo no banco de dados.
     *
     * @param modulo_novo o objeto Modulo contendo as informações do novo módulo a ser cadastrado.
     * @return o módulo cadastrado, ou null se não foi possível cadastrar.
     */
	public static Modulo cadastrarModulo(@Valid Modulo modulo_novo) {

		// @formatter:off
		String sql = "INSERT INTO modulo ("
		        + "    id_modulo,"
		        + "    nome_modulo,"
		        + "    url_imagem_modulo,"
		        + "    nivel_modulo"
		        + ") VALUES ("
		        + "    SQ_MODULO.nextval,"
		        + "    ?,"
		        + "    ?,"
		        + "    ?"
		        + ")";
		// @formatter:on

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
		    ps = getConnection().prepareStatement(sql, new String[] {"id_modulo"});
		    ps.setString(1, modulo_novo.getNome_modulo());
		    ps.setString(2, modulo_novo.getUrl_imagem_modulo());
		    ps.setString(3, modulo_novo.getNivel_modulo().getNome_nivel());;
		    ps.executeUpdate();
		    rs = ps.getGeneratedKeys();
		    if (rs.next()) {
		        modulo_novo.setId_modulo(rs.getInt(1));
		    }

		    return modulo_novo;
		} catch (SQLException e) {
		    System.out.println("Não foi possível cadastrar novo MODULO no banco de dados: " + e.getMessage());
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
     * Deleta um módulo do banco de dados.
     *
     * @param id_modulo o ID do módulo a ser deletado.
     * @return true se o módulo foi deletado com sucesso, false caso contrário.
     */
	public static boolean deletarModulo(int id_modulo) {

		Modulo modulo_deletar = null;
		String sql = "DELETE FROM modulo WHERE id_modulo = ?";
		PreparedStatement ps = null;
		modulo_deletar = buscarModuloPorId(id_modulo);

		if (modulo_deletar == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_modulo);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("Não foi possível deletar o MODULO no banco de dados: " + e.getMessage());
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
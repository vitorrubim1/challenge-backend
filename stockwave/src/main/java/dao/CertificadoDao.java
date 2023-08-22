package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.validation.Valid;
import model.Certificado;

/**
 * Classe de acesso a dados para Certificado.
 * 
 * Essa classe oferece métodos para manipulação dos dados relacionados aos Certificados no banco de dados.
 * Ela estende a classe Repository, que fornece a funcionalidade de conexão com o banco de dados.
 * 
 * Exemplo de uso:
 * 
 * // Instanciar a classe CertificadoDao
 * CertificadoDao certificadoDao = new CertificadoDao();
 * 
 * // Listar todos os Certificados cadastrados no banco de dados
 * ArrayList&lt;Certificado&lt; listaCertificados = certificadoDao.listarCertificados();
 * 
 * // Buscar um Certificado por ID
 * Certificado certificado = certificadoDao.buscarCertificadoPorId(1);
 * 
 * // Atualizar um Certificado
 * Certificado novoCertificado = new Certificado();
 * // ... configuração das informações do Certificado ...
 * Certificado certificadoAtualizado = certificadoDao.atualizarCertificado(novoCertificado);
 * 
 * // Cadastrar um novo Certificado
 * Certificado novoCertificado = new Certificado();
 * // ... configuração das informações do Certificado ...
 * Certificado certificadoCadastrado = certificadoDao.cadastrarCertificado(novoCertificado);
 * 
 * // Deletar um Certificado
 * boolean deletado = certificadoDao.deletarCertificado(1);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Certificado
 * @see services.CertificadoService
 * @see controller.CertificadoResource
 * @see dao.Repository
 * @see model.Aluno
 * 
 * @author Stockwave
 * 
 */
public class CertificadoDao extends Repository {
	
	/**
     * Lista todos os Certificados cadastrados no banco de dados.
     *
     * @return uma lista de Certificados
     */
	public ArrayList<Certificado> listarCertificados() {
		String sql = "SELECT * FROM certificado ORDER BY id_certificado";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Certificado> listaCertificados = new ArrayList<>();

		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Certificado certificado = new Certificado();
					certificado.setId_certificado(rs.getInt("id_certificado"));
					certificado.setDt_certificado(rs.getDate("dt_certificado"));

					listaCertificados.add(certificado);
				}
			} else {
				System.out.println("Não foi possível encontrar registros na tabela CERTIFICADO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem da tabela CERTIFICADO: " + e.getMessage());
		}

		return listaCertificados;
	}
	
	/**
     * Busca um Certificado pelo ID.
     *
     * @param id_certificado o ID do Certificado a ser buscado
     * @return o Certificado encontrado ou null se não encontrado
     */
	public static Certificado buscarCertificadoPorId(int id_certificado) {
		String sql = "SELECT * FROM certificado WHERE id_certificado = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_certificado);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Certificado certificado = new Certificado();
				while (rs.next()) {
					certificado.setId_certificado(rs.getInt("id_certificado"));
					certificado.setDt_certificado(rs.getDate("dt_certificado"));
				}

				return certificado;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_certificado + " na tabela CERTIFICADO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar o CERTIFICADO no banco de dados: " + e.getMessage());
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
     * Atualiza um Certificado.
     *
     * @param certificado o Certificado com as informações atualizadas
     * @return o Certificado atualizado ou null se ocorrer um erro
     */
	public static Certificado atualizarCertificado(@Valid Certificado certificado) {
		String sql = "UPDATE certificado SET dt_certificado = ? WHERE id_certificado = ?";
		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setDate(1, certificado.getDt_certificado());
			cs.setInt(2, certificado.getId_certificado());
			cs.executeUpdate();

			return certificado;

		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar o CERTIFICADO no banco de dados: " + e.getMessage());
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
     * Cadastra um novo Certificado.
     *
     * @param certificado_novo o novo Certificado a ser cadastrado
     * @return o Certificado cadastrado ou null se ocorrer um erro
     */
	public static Certificado cadastrarCertificado(@Valid Certificado certificado_novo) {
		
		// @formatter:off
		String sql = "INSERT INTO certificado ("
		        + "    id_certificado,"
		        + "    dt_certificado"
		        + ") VALUES ("
		        + "    SQ_CERTIFICADO.nextval,"
		        + "    ?"
		        + ")";
		// @formatter:on

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
		    ps = getConnection().prepareStatement(sql, new String[] {"id_certificado"});
		    ps.setDate(1, certificado_novo.getDt_certificado());
		    ps.executeUpdate();
		    rs = ps.getGeneratedKeys();
		    if (rs.next()) {
		        certificado_novo.setId_certificado(rs.getInt(1));
		    }

		    return certificado_novo;
		} catch (SQLException e) {
		    System.out.println("Não foi possível cadastrar novo CERTIFICADO no banco de dados: " + e.getMessage());
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
     * Deleta um Certificado pelo ID.
     *
     * @param id_certificado o ID do Certificado a ser deletado
     * @return true se o Certificado foi deletado com sucesso, false caso contrário
     */
	public static boolean deletarCertificado(int id_certificado) {

		Certificado certificado_deletar = null;
		String sql = "DELETE FROM certificado WHERE id_certificado = ?";
		PreparedStatement ps = null;
		certificado_deletar = buscarCertificadoPorId(id_certificado);

		if (certificado_deletar == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_certificado);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("Não foi possível deletar o CERTIFICADO no banco de dados: " + e.getMessage());
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
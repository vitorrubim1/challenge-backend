package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.validation.Valid;
import model.Aluno;
import model.Aluno_Certificado;
import model.Certificado;
import model.Nivel;

/**
 * Classe de acesso a dados para Aluno_Certificado.
 * 
 * Essa classe oferece métodos para manipulação dos dados relacionados a tabela Aluno_Certificado no banco de dados.
 * Ela estende a classe Repository, que fornece a funcionalidade de conexão com o banco de dados.
 * 
 * Exemplo de uso:
 * 
 * // Instanciar a classe Aluno_CertificadoDao
 * Aluno_CertificadoDao alunoCertificadoDao = new Aluno_CertificadoDao();
 * 
 * // Listar todos os Aluno_Certificados cadastrados no banco de dados
 * ArrayList&lt;Aluno_Certificado&lt; listaAlunoCertificados = alunoCertificadoDao.listarAluno_Certificados();
 * 
 * // Buscar um Aluno_Certificado por id_usuario e id_certificado
 * Aluno_Certificado alunoCertificado = alunoCertificadoDao.buscarAluno_CertificadoPorId(1, 1);
 * 
 * // Buscar todos os Aluno_Certificados associados a um id_usuario
 * ArrayList&lt;Aluno_Certificado&lt; listaAlunoCertificadosPorUsuario = alunoCertificadoDao.buscarAluno_CertificadosPorId(1);
 * 
 * // Atualizar um Aluno_Certificado
 * Aluno_Certificado novoAlunoCertificado = new Aluno_Certificado();
 * // ... configuração das informações do Aluno_Certificado ...
 * Aluno_Certificado alunoCertificadoAtualizado = alunoCertificadoDao.atualizarAluno_Certificado(novoAlunoCertificado, 1);
 * 
 * // Cadastrar um novo Aluno_Certificado
 * Aluno_Certificado novoAlunoCertificado = new Aluno_Certificado();
 * // ... configuração das informações do Aluno_Certificado ...
 * Aluno_Certificado alunoCertificadoCadastrado = alunoCertificadoDao.cadastrarAluno_Certificado(novoAlunoCertificado);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Aluno_Certificado
 * @see services.Aluno_CertificadoService
 * @see controller.Aluno_CertificadoResource
 * @see model.Aluno
 * @see model.Certificado
 * @see dao.Repository
 * 
 * @author Stockwave
 * 
 */
public class Aluno_CertificadoDao extends Repository {
	
	/**
	 * Retorna uma lista de todos os Aluno_Certificados cadastrados no banco de dados.
	 *
	 * @return uma lista de Aluno_Certificados
	 */
	public ArrayList<Aluno_Certificado> listarAluno_Certificados() {
		String sql = "SELECT a.id_usuario, u.cpf_usuario, u.nome_usuario, u.email_usuario,"
				+ " a.dt_nasc_aluno, a.dt_reg_aluno, a.senha_aluno, a.moedas_aluno, a.nivel_aluno, "
				+ " ac.id_certificado,"
				+ " c.dt_certificado"
				+ " FROM aluno a"
				+ " JOIN usuario u ON a.id_usuario = u.id_usuario"
				+ " JOIN aluno_certificado ac ON a.id_usuario = ac.id_usuario"
				+ " LEFT JOIN certificado c ON ac.id_certificado = c.id_certificado"
				+ " ORDER BY ac.id_usuario";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Aluno_Certificado> listaAluno_Certificados = new ArrayList<>();

		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Aluno_Certificado aluno_certificado = new Aluno_Certificado();
					
					Aluno aluno = new Aluno();
					aluno.setId_usuario(rs.getInt("id_usuario"));
					aluno.setCpf_usuario(rs.getString("cpf_usuario"));
					aluno.setNome_usuario(rs.getString("nome_usuario"));
					aluno.setEmail_usuario(rs.getString("email_usuario"));
					aluno.setDt_nasc_aluno(rs.getDate("dt_nasc_aluno"));
					aluno.setDt_reg_aluno(rs.getDate("dt_reg_aluno"));
					aluno.setSenha_aluno(rs.getString("senha_aluno"));
					aluno.setMoedas_aluno(rs.getInt("moedas_aluno"));
					Nivel nivel_aluno = new Nivel();
					nivel_aluno.setNome_nivel(rs.getString("nivel_aluno"));
					aluno.setNivel_aluno(nivel_aluno);
					
					aluno_certificado.setAluno(aluno);
					
					Certificado certificado = new Certificado();
					certificado.setId_certificado(rs.getInt("id_certificado"));
					certificado.setDt_certificado(rs.getDate("dt_certificado"));
					
					aluno_certificado.setCertificado(certificado);

					listaAluno_Certificados.add(aluno_certificado);
				}
			} else {
				System.out.println("Não foi possível encontrar registros na tabela ALUNO_CERTIFICADO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem da tabela ALUNO_CERTIFICADO: " + e.getMessage());
		}

		return listaAluno_Certificados;
	}
	
	/**
	 * Busca um Aluno_Certificado por seu id_usuario e id_certificado.
	 *
	 * @param id_usuario    o id do usuário
	 * @param id_certificado o id do certificado
	 * @return o Aluno_Certificado encontrado ou null se não encontrado
	 */
	public static Aluno_Certificado buscarAluno_CertificadoPorId(int id_usuario, int id_certificado) {
		String sql = "SELECT a.id_usuario, u.cpf_usuario, u.nome_usuario, u.email_usuario,"
				+ " a.dt_nasc_aluno, a.dt_reg_aluno, a.senha_aluno, a.moedas_aluno, a.nivel_aluno, "
				+ " ac.id_certificado,"
				+ " c.dt_certificado"
				+ " FROM aluno a"
				+ " JOIN usuario u ON a.id_usuario = u.id_usuario"
				+ " JOIN aluno_certificado ac ON a.id_usuario = ac.id_usuario"
				+ " LEFT JOIN certificado c ON ac.id_certificado = c.id_certificado"
				+ " WHERE ac.id_usuario = ? AND ac.id_certificado = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_usuario);
			ps.setInt(2, id_certificado);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Aluno_Certificado aluno_certificado = new Aluno_Certificado();
				while (rs.next()) {
					Aluno aluno = new Aluno();
					aluno.setId_usuario(rs.getInt("id_usuario"));
					aluno.setCpf_usuario(rs.getString("cpf_usuario"));
					aluno.setNome_usuario(rs.getString("nome_usuario"));
					aluno.setEmail_usuario(rs.getString("email_usuario"));
					aluno.setDt_nasc_aluno(rs.getDate("dt_nasc_aluno"));
					aluno.setDt_reg_aluno(rs.getDate("dt_reg_aluno"));
					aluno.setSenha_aluno(rs.getString("senha_aluno"));
					aluno.setMoedas_aluno(rs.getInt("moedas_aluno"));
					Nivel nivel_aluno = new Nivel();
					nivel_aluno.setNome_nivel(rs.getString("nivel_aluno"));
					aluno.setNivel_aluno(nivel_aluno);
					
					aluno_certificado.setAluno(aluno);
					
					Certificado certificado = new Certificado();
					certificado.setId_certificado(rs.getInt("id_certificado"));
					certificado.setDt_certificado(rs.getDate("dt_certificado"));
					
					aluno_certificado.setCertificado(certificado);					
				}

				return aluno_certificado;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_usuario + " na tabela ALUNO_CERTIFICADO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar o ALUNO_CERTIFICADO no banco de dados: " + e.getMessage());
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
	 * Busca todos os Aluno_Certificados associados a um determinado id_usuario.
	 *
	 * @param id_usuario o id do usuário
	 * @return uma lista de Aluno_Certificados associados ao id_usuario
	 */
	public static ArrayList<Aluno_Certificado> buscarAluno_CertificadosPorId(int id_usuario) {
		String sql = "SELECT a.id_usuario, u.cpf_usuario, u.nome_usuario, u.email_usuario,"
				+ " a.dt_nasc_aluno, a.dt_reg_aluno, a.senha_aluno, a.moedas_aluno, a.nivel_aluno, "
				+ " ac.id_certificado,"
				+ " c.dt_certificado"
				+ " FROM aluno a"
				+ " JOIN usuario u ON a.id_usuario = u.id_usuario"
				+ " JOIN aluno_certificado ac ON a.id_usuario = ac.id_usuario"
				+ " LEFT JOIN certificado c ON ac.id_certificado = c.id_certificado"
				+ " WHERE ac.id_usuario = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_usuario);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				ArrayList<Aluno_Certificado> listaAluno_Certificados = new ArrayList<>();
				while (rs.next()) {
					Aluno_Certificado aluno_certificado = new Aluno_Certificado();
					
					Aluno aluno = new Aluno();
					aluno.setId_usuario(rs.getInt("id_usuario"));
					aluno.setCpf_usuario(rs.getString("cpf_usuario"));
					aluno.setNome_usuario(rs.getString("nome_usuario"));
					aluno.setEmail_usuario(rs.getString("email_usuario"));
					aluno.setDt_nasc_aluno(rs.getDate("dt_nasc_aluno"));
					aluno.setDt_reg_aluno(rs.getDate("dt_reg_aluno"));
					aluno.setSenha_aluno(rs.getString("senha_aluno"));
					aluno.setMoedas_aluno(rs.getInt("moedas_aluno"));
					Nivel nivel_aluno = new Nivel();
					nivel_aluno.setNome_nivel(rs.getString("nivel_aluno"));
					aluno.setNivel_aluno(nivel_aluno);
					
					aluno_certificado.setAluno(aluno);
					
					Certificado certificado = new Certificado();
					certificado.setId_certificado(rs.getInt("id_certificado"));
					certificado.setDt_certificado(rs.getDate("dt_certificado"));
					
					aluno_certificado.setCertificado(certificado);
					
					listaAluno_Certificados.add(aluno_certificado);
				}

				return listaAluno_Certificados;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_usuario + " na tabela ALUNO_CERTIFICADO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar o ALUNO_CERTIFICADO no banco de dados: " + e.getMessage());
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
	 * Atualiza um Aluno_Certificado no banco de dados.
	 *
	 * @param aluno_certificado o Aluno_Certificado a ser atualizado
	 * @param id_certificado    o id do certificado a ser atualizado
	 * @return o Aluno_Certificado atualizado ou null se a atualização falhar
	 */
	public static Aluno_Certificado atualizarAluno_Certificado(@Valid Aluno_Certificado aluno_certificado, int id_certificado) {
		String sql = "UPDATE aluno_certificado SET id_usuario = ?, id_certificado = ? WHERE id_usuario = ? AND id_certificado = ?";
		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setInt(1, aluno_certificado.getAluno().getId_usuario());
			cs.setInt(2, aluno_certificado.getCertificado().getId_certificado());
			cs.setInt(3, aluno_certificado.getAluno().getId_usuario());
			cs.setInt(4, id_certificado);
			cs.executeUpdate();

			return aluno_certificado;

		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar o ALUNO_CERTIFICADO no banco de dados: " + e.getMessage());
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
	 * Cadastra um novo Aluno_Certificado no banco de dados.
	 *
	 * @param aluno_certificado_novo o novo Aluno_Certificado a ser cadastrado
	 * @return o Aluno_Certificado cadastrado ou null se o cadastro falhar
	 */
	public static Aluno_Certificado cadastrarAluno_Certificado(@Valid Aluno_Certificado aluno_certificado_novo) {

		// @formatter:off
		String sql = "INSERT INTO aluno_certificado ("
		        + "    id_usuario,"
		        + "    id_certificado"
		        + ") VALUES ("
		        + "    ?,"
		        + "    ?"
		        + ")";
		// @formatter:on

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
		    ps = getConnection().prepareStatement(sql, new String[] {"id_usuario"});
		    ps.setInt(1, aluno_certificado_novo.getAluno().getId_usuario());
		    ps.setInt(2, aluno_certificado_novo.getCertificado().getId_certificado());
		    ps.executeUpdate();
		    rs = ps.getGeneratedKeys();
		    if (rs.next()) {
		        aluno_certificado_novo.getAluno().setId_usuario(rs.getInt(1));
		    }

		    return aluno_certificado_novo;
		} catch (SQLException e) {
		    System.out.println("Não foi possível cadastrar novo ALUNO_CERTIFICADO no banco de dados: " + e.getMessage());
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
	 * Deleta um Aluno_Certificado do banco de dados.
	 *
	 * @param id_usuario    o id do usuário do Aluno_Certificado a ser deletado
	 * @param id_certificado o id do certificado do Aluno_Certificado a ser deletado
	 * @return true se o Aluno_Certificado for deletado com sucesso, false caso contrário
	 */
	public static boolean deletarAluno_Certificado(int id_usuario, int id_certificado) {

		Aluno_Certificado aluno_certificado_deletar = null;
		String sql = "DELETE FROM aluno_certificado WHERE id_usuario = ? AND id_certificado = ?";
		PreparedStatement ps = null;
		aluno_certificado_deletar = buscarAluno_CertificadoPorId(id_usuario, id_certificado);

		if (aluno_certificado_deletar == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_usuario);
			ps.setInt(2, id_certificado);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("Não foi possível deletar o ALUNO_CERTIFICADO no banco de dados: " + e.getMessage());
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
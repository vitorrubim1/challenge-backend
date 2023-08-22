package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jakarta.validation.Valid;
import model.Aluno;
import model.Nivel;

/**
 * Classe de acesso a dados para Aluno.
 * 
 * Essa classe oferece métodos para manipulação dos dados relacionados aos Alunos no banco de dados.
 * Ela estende a classe Repository, que fornece a funcionalidade de conexão com o banco de dados.
 * 
 * Exemplo de uso:
 * 
 * // Instanciar a classe AlunoDao
 * AlunoDao alunoDao = new AlunoDao();
 * 
 * // Listar todos os Alunos cadastrados no banco de dados
 * ArrayList&lt;Aluno&lt; listaAlunos = alunoDao.listarAlunos();
 * 
 * // Buscar um Aluno por ID
 * Aluno aluno = alunoDao.buscarAlunoPorId(1);
 * 
 * // Atualizar um Aluno
 * Aluno novoAluno = new Aluno();
 * // ... configuração das informações do Aluno ...
 * Aluno alunoAtualizado = alunoDao.atualizarAluno(novoAluno, 1);
 * 
 * // Cadastrar um novo Aluno
 * Aluno novoAluno = new Aluno();
 * // ... configuração das informações do Aluno ...
 * Aluno alunoCadastrado = alunoDao.cadastrarAluno(novoAluno);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Aluno
 * @see services.AlunoService
 * @see controller.AlunoResource
 * @see dao.Repository
 * @see model.Usuario
 * 
 * @author Stockwave
 * 
 */
public class AlunoDao extends Repository {
	
	/**
	 * Retorna uma lista de todos os alunos cadastrados no banco de dados.
	 *
	 * @return ArrayList contendo os objetos Aluno correspondentes aos registros encontrados, ou uma lista vazia se nenhum registro for encontrado.
	 */
	public ArrayList<Aluno> listarAlunos() {
		String sql = "SELECT usuario.id_usuario, usuario.cpf_usuario, usuario.nome_usuario, usuario.email_usuario, aluno.dt_nasc_aluno, aluno.dt_reg_aluno, aluno.senha_aluno, aluno.moedas_aluno, aluno.nivel_aluno FROM usuario INNER JOIN aluno ON usuario.id_usuario = aluno.id_usuario";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Aluno> listaAlunos = new ArrayList<>();

		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
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
					
					listaAlunos.add(aluno);
				}
			} else {
				System.out.println("Não foi possível encontrar registros na tabela ALUNO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem da tabela ALUNO: " + e.getMessage());
		}

		return listaAlunos;
	}
	
	/**
	 * Busca um aluno no banco de dados pelo ID do usuário.
	 *
	 * @param id_usuario O ID do usuário do aluno a ser buscado.
	 * @return O objeto Aluno correspondente ao registro encontrado, ou null se nenhum registro for encontrado.
	 */
	public static Aluno buscarAlunoPorId(int id_usuario) {
		String sql = "SELECT usuario.id_usuario, usuario.cpf_usuario, usuario.nome_usuario, usuario.email_usuario, aluno.dt_nasc_aluno, aluno.dt_reg_aluno, aluno.senha_aluno, aluno.moedas_aluno, aluno.nivel_aluno FROM usuario INNER JOIN aluno ON usuario.id_usuario = aluno.id_usuario WHERE aluno.id_usuario = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_usuario);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Aluno aluno = new Aluno();
				while (rs.next()) {
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
				}

				return aluno;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_usuario + " na tabela ALUNO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar o ALUNO no banco de dados: " + e.getMessage());
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
	 * Busca um aluno no banco de dados pelo email do usuário.
	 *
	 * @param email_usuario O email do usuário do aluno a ser buscado.
	 * @return O objeto Aluno correspondente ao registro encontrado, ou null se nenhum registro for encontrado.
	 */
	public static Aluno buscarAlunoPorEmail(String email_usuario) {
		String sql = "SELECT usuario.id_usuario, usuario.cpf_usuario, usuario.nome_usuario, usuario.email_usuario, aluno.dt_nasc_aluno, aluno.dt_reg_aluno, aluno.senha_aluno, aluno.moedas_aluno, aluno.nivel_aluno FROM usuario INNER JOIN aluno ON usuario.id_usuario = aluno.id_usuario WHERE UPPER(usuario.email_usuario) = UPPER(?)";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setString(1, email_usuario);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Aluno aluno = new Aluno();
				while (rs.next()) {
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
				}

				return aluno;

			} else {
				System.out.println("Não foi possível encontrar o email: " + email_usuario + " na tabela ALUNO do banco de dados");
				return null;
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar o ALUNO no banco de dados: " + e.getMessage());
			return null;
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
	}
	
	/**
	 * Atualiza as informações de um aluno no banco de dados.
	 *
	 * @param aluno O objeto Aluno com as informações atualizadas.
	 * @return O objeto Aluno atualizado, ou null se a atualização falhar.
	 */
	public static Aluno atualizarAluno(@Valid Aluno aluno) {
		String sql = "UPDATE aluno SET dt_nasc_aluno = ?, dt_reg_aluno = ?, senha_aluno = ?, moedas_aluno = ?, nivel_aluno = ? WHERE id_usuario = ?";
		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setDate(1, aluno.getDt_nasc_aluno());
			cs.setDate(2, aluno.getDt_reg_aluno());
			cs.setString(3, aluno.getSenha_aluno());
			cs.setInt(4,  aluno.getMoedas_aluno());
			cs.setString(5, aluno.getNivel_aluno().getNome_nivel());
			cs.setInt(6, aluno.getId_usuario());
			cs.executeUpdate();

			return aluno;

		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar o ALUNO no banco de dados: " + e.getMessage());
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
	 * Cadastra um novo aluno no banco de dados.
	 *
	 * @param aluno_novo O objeto Aluno contendo as informações do novo aluno.
	 * @return O objeto Aluno cadastrado, ou null se o cadastro falhar.
	 */
	public static Aluno cadastrarAluno(@Valid Aluno aluno_novo) {

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
			cs_usuario.setString(1, aluno_novo.getCpf_usuario());
			cs_usuario.setString(2, aluno_novo.getNome_usuario());
			cs_usuario.setString(3, aluno_novo.getEmail_usuario());
			cs_usuario.registerOutParameter(4, java.sql.Types.INTEGER);
			cs_usuario.executeUpdate();
			aluno_novo.setId_usuario(cs_usuario.getInt(4));
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
    String sql_aluno = "INSERT INTO aluno ("
            + "id_usuario,"
    		+ "dt_nasc_aluno,"
            + "dt_reg_aluno,"
    		+ "senha_aluno,"
            + "moedas_aluno,"
    		+ "nivel_aluno"
            + ") VALUES ("
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?"
            + ") ";
    // @formatter:on

		CallableStatement cs_aluno = null;

		try {
			cs_aluno = getConnection().prepareCall(sql_aluno);
			cs_aluno.setInt(1, aluno_novo.getId_usuario());
			cs_aluno.setDate(2, aluno_novo.getDt_nasc_aluno());
			
			SimpleDateFormat formatar = new SimpleDateFormat("yyyy/MM/dd");
			String timeStamp = formatar.format(new Date());
			try {
			    java.util.Date data_registro = formatar.parse(timeStamp);
			    long millis = data_registro.getTime();
			    java.sql.Date sqlDate = new java.sql.Date(millis);
			    cs_aluno.setDate(3, sqlDate);
			} catch (ParseException e) {
			    e.printStackTrace();
			}

			cs_aluno.setString(4, aluno_novo.getSenha_aluno());
			cs_aluno.setInt(5, aluno_novo.getMoedas_aluno());
			cs_aluno.setString(6, aluno_novo.getNivel_aluno().getNome_nivel());
			cs_aluno.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível cadastrar novo ALUNO no banco de dados: " + e.getMessage());
			return null;
		} finally {
			if (cs_aluno != null) {
				try {
					cs_aluno.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o Callable Statement: " + e.getMessage());
				}
			}
		}

		return aluno_novo;
	}
	
	/**
	 * Deleta um aluno do banco de dados pelo ID do usuário.
	 *
	 * @param id_usuario O ID do usuário do aluno a ser deletado.
	 * @return true se o aluno foi deletado com sucesso, false caso contrário.
	 */
	public static boolean deletarAluno(int id_usuario) {
		String sql_aluno = "DELETE FROM aluno WHERE id_usuario = ?";
		PreparedStatement ps_aluno = null;
		boolean alunoDeletado = false;

		try {
			ps_aluno = getConnection().prepareStatement(sql_aluno);
			ps_aluno.setInt(1, id_usuario);
			ps_aluno.executeUpdate();
			alunoDeletado = true;
		} catch (SQLException e) {
			System.out.println("Não foi possível deletar o ALUNO no banco de dados: " + e.getMessage());
		} finally {
			if (ps_aluno != null) {
				try {
					ps_aluno.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o Prepared Statement: " + e.getMessage());
				}
			}
		}

		if (alunoDeletado) {
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
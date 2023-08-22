package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.validation.Valid;
import model.Movimentacao;
import model.Usuario;
import model.Produto;

/**
 * Classe de acesso a dados para Movimentacao.
 * 
 * Essa classe oferece métodos para manipulação dos dados relacionados à tabela Movimentacao no banco de dados.
 * Ela estende a classe Repository, que fornece a funcionalidade de conexão com o banco de dados.
 *
 * Exemplo de uso:
 *
 * // Instanciar a classe MovimentacaoDao
 * MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
 *
 * // Listar todas as movimentações cadastradas no banco de dados
 * ArrayList&lt;Movimentacao&lt; listaMovimentacoes = movimentacaoDao.listarMovimentacoes();
 *
 * // Buscar uma movimentação por ID
 * Movimentacao movimentacao = MovimentacaoDao.buscarMovimentacaoPorId(1);
 *
 * // Atualizar uma movimentação
 * Movimentacao novaMovimentacao = new Movimentacao();
 * // ... configuração das informações da movimentação ...
 * Movimentacao movimentacaoAtualizada = MovimentacaoDao.atualizarMovimentacao(novaMovimentacao);
 *
 * // Cadastrar uma nova movimentação
 * Movimentacao novaMovimentacao = new Movimentacao();
 * // ... configuração das informações da movimentação ...
 * Movimentacao movimentacaoCadastrada = MovimentacaoDao.cadastrarMovimentacao(novaMovimentacao);
 *
 * // Deletar uma movimentação
 * boolean deletado = MovimentacaoDao.deletarMovimentacao(1);
 *
 * @since 1.0
 * @version 1.0
 *
 * @see model.Movimentacao
 * @see services.MovimentacaoService
 * @see controller.MovimentacaoResource
 * @see dao.Repository
 * @see model.Usuario
 * @see model.Produto
 * 
 * @author Stockwave
 */
public class MovimentacaoDao extends Repository {
	
	/**
     * Lista todas as movimentações cadastradas.
     *
     * @return uma lista de objetos Movimentacao com todas as movimentações cadastradas.
     */
	public ArrayList<Movimentacao> listarMovimentacoes() {
		String sql = "SELECT "
				+ "    mov.id_movimentacao,"
				+ "    mov.dt_movimentacao,"
				+ "    mov.usuario_movimentacao,"
				+ "    usr.cpf_usuario,"
				+ "    usr.nome_usuario,"
				+ "    usr.email_usuario,"
				+ "    mov.produto_movimentacao,"
				+ "    pdt.nome_produto,"
				+ "    pdt.valor_produto,"
				+ "    pdt.qtd_produto,"
				+ "    pdt.imagem_produto,"
				+ "    mov.qtd_movimentacao "
				+ "FROM "
				+ "    movimentacao mov"
				+ "    JOIN usuario usr ON mov.usuario_movimentacao = usr.id_usuario"
				+ "    JOIN produto pdt ON mov.produto_movimentacao = pdt.id_produto";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Movimentacao> listaMovimentacoes = new ArrayList<>();

		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Movimentacao movimentacao = new Movimentacao();
					movimentacao.setId_movimentacao(rs.getInt("id_movimentacao"));
					movimentacao.setDt_movimentacao(rs.getDate("dt_movimentacao"));
					
					Usuario usuario_movimentacao = new Usuario();
					usuario_movimentacao.setId_usuario(rs.getInt("usuario_movimentacao"));
					usuario_movimentacao.setCpf_usuario(rs.getString("cpf_usuario"));
					usuario_movimentacao.setNome_usuario(rs.getString("nome_usuario"));
					usuario_movimentacao.setEmail_usuario(rs.getString("email_usuario"));
					movimentacao.setUsuario_movimentacao(usuario_movimentacao);
					
					Produto produto_movimentacao = new Produto();
					produto_movimentacao.setId_produto(rs.getInt("produto_movimentacao"));
					produto_movimentacao.setNome_produto(rs.getString("nome_produto"));
					produto_movimentacao.setValor_produto(rs.getDouble("valor_produto"));
					produto_movimentacao.setQtd_produto(rs.getInt("qtd_produto"));
					produto_movimentacao.setImagem_produto(rs.getString("imagem_produto"));
					movimentacao.setProduto_movimentacao(produto_movimentacao);
					
					movimentacao.setQtd_movimentacao(rs.getInt("qtd_movimentacao"));

					listaMovimentacoes.add(movimentacao);
				}
			} else {
				System.out.println("Não foi possível encontrar registros na tabela MOVIMENTACAO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem da tabela MOVIMENTACAO: " + e.getMessage());
		}

		return listaMovimentacoes;
	}
	
	/**
     * Busca uma movimentação pelo ID.
     *
     * @param id_movimentacao o ID da movimentação a ser buscada.
     * @return a movimentação encontrada, ou null se não foi encontrada.
     */
	public static Movimentacao buscarMovimentacaoPorId(int id_movimentacao) {
		String sql = "SELECT "
				+ "    mov.id_movimentacao, "
				+ "    mov.dt_movimentacao, "
				+ "    mov.usuario_movimentacao, "
				+ "    usr.cpf_usuario, "
				+ "    usr.nome_usuario, "
				+ "    usr.email_usuario, "
				+ "    mov.produto_movimentacao, "
				+ "    pdt.nome_produto, "
				+ "    pdt.valor_produto, "
				+ "    pdt.qtd_produto, "
				+ "    pdt.imagem_produto, "
				+ "    mov.qtd_movimentacao "
				+ "FROM "
				+ "    movimentacao mov "
				+ "    INNER JOIN usuario usr ON mov.usuario_movimentacao = usr.id_usuario "
				+ "    INNER JOIN produto pdt ON mov.produto_movimentacao = pdt.id_produto "
				+ "WHERE "
				+ "    mov.id_movimentacao = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_movimentacao);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Movimentacao movimentacao = new Movimentacao();
				while (rs.next()) {
					movimentacao.setId_movimentacao(rs.getInt("id_movimentacao"));
					movimentacao.setDt_movimentacao(rs.getDate("dt_movimentacao"));
					
					Usuario usuario_movimentacao = new Usuario();
					usuario_movimentacao.setId_usuario(rs.getInt("usuario_movimentacao"));
					usuario_movimentacao.setCpf_usuario(rs.getString("cpf_usuario"));
					usuario_movimentacao.setNome_usuario(rs.getString("nome_usuario"));
					usuario_movimentacao.setEmail_usuario(rs.getString("email_usuario"));
					movimentacao.setUsuario_movimentacao(usuario_movimentacao);
					
					Produto produto_movimentacao = new Produto();
					produto_movimentacao.setId_produto(rs.getInt("produto_movimentacao"));
					produto_movimentacao.setNome_produto(rs.getString("nome_produto"));
					produto_movimentacao.setValor_produto(rs.getDouble("valor_produto"));
					produto_movimentacao.setQtd_produto(rs.getInt("qtd_produto"));
					produto_movimentacao.setImagem_produto(rs.getString("imagem_produto"));
					movimentacao.setProduto_movimentacao(produto_movimentacao);
					
					movimentacao.setQtd_movimentacao(rs.getInt("qtd_movimentacao"));
				}

				return movimentacao;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_movimentacao + " na tabela MOVIMENTACAO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a MOVIMENTACAO no banco de dados: " + e.getMessage());
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
     * Atualiza uma movimentação existente.
     *
     * @param movimentacao o objeto Movimentacao com as informações atualizadas.
     * @return a movimentação atualizada, ou null se não foi possível atualizar.
     */
	public static Movimentacao atualizarMovimentacao(@Valid Movimentacao movimentacao) {
		String sql = "UPDATE movimentacao SET dt_movimentacao = ?, usuario_movimentacao = ?, produto_movimentacao = ?, qtd_movimentacao = ? WHERE id_movimentacao = ?";
		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setDate(1, movimentacao.getDt_movimentacao());
			cs.setInt(2, movimentacao.getUsuario_movimentacao().getId_usuario());
			cs.setInt(3, movimentacao.getProduto_movimentacao().getId_produto());
			cs.setInt(4, movimentacao.getQtd_movimentacao());
			cs.setInt(5, movimentacao.getId_movimentacao());
			cs.executeUpdate();

			return movimentacao;

		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar a MOVIMENTACAO no banco de dados: " + e.getMessage());
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
     * Cadastra uma nova movimentação.
     *
     * @param movimentacao_nova o objeto Movimentacao com as informações da nova movimentação a ser cadastrada.
     * @return a movimentação cadastrada, ou null se não foi possível cadastrar.
     */
	public static Movimentacao cadastrarMovimentacao(@Valid Movimentacao movimentacao_nova) {
		
		// @formatter:off
		String sql = "INSERT INTO movimentacao ("
		        + "    id_movimentacao,"
		        + "    dt_movimentacao,"
		        + "    usuario_movimentacao,"
		        + "    produto_movimentacao,"
		        + "    qtd_movimentacao"
		        + ") VALUES ("
		        + "    SQ_MOVIMENTACAO.nextval,"
		        + "    ?,"
		        + "    ?,"
		        + "    ?,"
		        + "    ?"
		        + ")";
		// @formatter:on

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
		    ps = getConnection().prepareStatement(sql, new String[] {"id_movimentacao"});
		    ps.setDate(1, movimentacao_nova.getDt_movimentacao());
		    ps.setInt(2, movimentacao_nova.getUsuario_movimentacao().getId_usuario());
		    ps.setInt(3, movimentacao_nova.getProduto_movimentacao().getId_produto());
		    ps.setInt(4, movimentacao_nova.getQtd_movimentacao());
		    ps.executeUpdate();
		    rs = ps.getGeneratedKeys();
		    if (rs.next()) {
		        movimentacao_nova.setId_movimentacao(rs.getInt(1));
		    }

		    return movimentacao_nova;
		} catch (SQLException e) {
		    System.out.println("Não foi possível cadastrar nova MOVIMENTACAO no banco de dados: " + e.getMessage());
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
     * Deleta uma movimentação do banco de dados.
     *
     * @param id_movimentacao o ID da movimentação a ser deletada.
     * @return true se a movimentação foi deletada com sucesso, false caso contrário.
     */
	public static boolean deletarMovimentacao(int id_movimentacao) {

		Movimentacao movimentacao_deletar = null;
		String sql = "DELETE FROM movimentacao WHERE id_movimentacao = ?";
		PreparedStatement ps = null;
		movimentacao_deletar = buscarMovimentacaoPorId(id_movimentacao);

		if (movimentacao_deletar == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_movimentacao);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("Não foi possível deletar a MOVIMENTACAO no banco de dados: " + e.getMessage());
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
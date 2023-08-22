package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.validation.Valid;
import model.Produto;

/**
 * Classe de acesso a dados para Produto.
 *
 * Essa classe oferece métodos para manipulação dos dados relacionados à tabela Produto no banco de dados.
 * Ela estende a classe Repository, que fornece a funcionalidade de conexão com o banco de dados.
 *
 * Exemplo de uso:
 *
 * // Instanciar a classe ProdutoDao
 * ProdutoDao produtoDao = new ProdutoDao();
 *
 * // Listar todos os produtos cadastrados no banco de dados
 * ArrayList&lt;Produto&lt; listaProdutos = produtoDao.listarProdutos();
 *
 * // Buscar um produto por ID
 * Produto produto = ProdutoDao.buscarProdutoPorId(1);
 *
 * // Atualizar um produto
 * Produto novoProduto = new Produto();
 * // ... configuração das informações do produto ...
 * Produto produtoAtualizado = ProdutoDao.atualizarProduto(novoProduto);
 *
 * // Cadastrar um novo produto
 * Produto novoProduto = new Produto();
 * // ... configuração das informações do produto ...
 * Produto produtoCadastrado = ProdutoDao.cadastrarProduto(novoProduto);
 *
 * // Deletar um produto
 * boolean deletado = ProdutoDao.deletarProduto(1);
 *
 * @since 1.0
 * @version 1.0
 *
 * @see model.Produto
 * @see services.ProdutoService
 * @see controller.ProdutoResource
 * @see dao.Repository
 * 
 * @author Stockwave
 * 
 */
public class ProdutoDao extends Repository {
	
	/**
	 * Lista todos os produtos cadastrados no banco de dados.
	 *
	 * @return uma lista de objetos Produto
	 */
	public ArrayList<Produto> listarProdutos() {
		String sql = "SELECT * FROM produto ORDER BY nome_produto";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Produto> listaProdutos = new ArrayList<>();

		try {

			ps = getConnection().prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Produto produto = new Produto();
					produto.setId_produto(rs.getInt("id_produto"));
					produto.setNome_produto(rs.getString("nome_produto"));
					produto.setValor_produto(rs.getDouble("valor_produto"));
					produto.setQtd_produto(rs.getInt("qtd_produto"));
					produto.setImagem_produto(rs.getString("imagem_produto"));
					System.out.println(produto.getId_produto());
					listaProdutos.add(produto);
				}
				
			} else {
				System.out.println("Não foi possível encontrar registros na tabela PRODUTO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem da tabela PRODUTO: " + e.getMessage());
		}

		return listaProdutos;
	}
	
	/**
	 * Busca um produto pelo ID.
	 *
	 * @param id_produto o ID do produto a ser buscado
	 * @return o objeto Produto correspondente ao ID fornecido, ou null se não encontrado
	 */
	public static Produto buscarProdutoPorId(int id_produto) {
		String sql = "SELECT * FROM produto WHERE id_produto = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_produto);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Produto produto = new Produto();
				while (rs.next()) {
					produto.setId_produto(rs.getInt("id_produto"));
					produto.setNome_produto(rs.getString("nome_produto"));
					produto.setValor_produto(rs.getDouble("valor_produto"));
					produto.setQtd_produto(rs.getInt("qtd_produto"));
					produto.setImagem_produto(rs.getString("imagem_produto"));
				}

				return produto;

			} else {
				System.out.println(
						"Não foi possível encontrar o id: " + id_produto + " na tabela PRODUTO do banco de dados");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar o PRODUTO no banco de dados: " + e.getMessage());
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
	 * Atualiza um produto no banco de dados.
	 *
	 * @param produto o objeto Produto com as informações atualizadas
	 * @return o objeto Produto atualizado, ou null se a atualização não foi bem-sucedida
	 */
	public static Produto atualizarProduto(@Valid Produto produto) {
		String sql = "UPDATE produto SET nome_produto = ?, valor_produto = ?, qtd_produto = ?, imagem_produto = ? WHERE id_produto = ?";
		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setString(1, produto.getNome_produto());
			cs.setDouble(2, produto.getValor_produto());
			cs.setInt(3, produto.getQtd_produto());
			cs.setString(4, produto.getImagem_produto()); 
			cs.setInt(5, produto.getId_produto());
			cs.executeUpdate();

			return produto;

		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar o PRODUTO no banco de dados: " + e.getMessage());
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
	 * Cadastra um novo produto no banco de dados.
	 *
	 * @param produto_novo o objeto Produto a ser cadastrado
	 * @return o objeto Produto cadastrado, ou null se o cadastro não foi bem-sucedido
	 */
	public static Produto cadastrarProduto(@Valid Produto produto_novo) {

		// @formatter:off
		String sql = "INSERT INTO produto ("
		        + "    id_produto,"
		        + "    nome_produto,"
		        + "    valor_produto,"
		        + "    qtd_produto,"
		        + "    imagem_produto"
		        + ") VALUES ("
		        + "    SQ_PRODUTO.nextval,"
		        + "    ?,"
		        + "    ?,"
		        + "    ?,"
		        + "    ?"
		        + ")";
		// @formatter:on

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
		    ps = getConnection().prepareStatement(sql, new String[] {"id_produto"});
		    ps.setString(1, produto_novo.getNome_produto());
		    ps.setDouble(2, produto_novo.getValor_produto());
		    ps.setInt(3, produto_novo.getQtd_produto());
		    ps.setString(4, produto_novo.getImagem_produto());
		    ps.executeUpdate();
		    rs = ps.getGeneratedKeys();
		    if (rs.next()) {
		        produto_novo.setId_produto(rs.getInt(1));
		    }

		    return produto_novo;
		} catch (SQLException e) {
		    System.out.println("Não foi possível cadastrar novo PRODUTO no banco de dados: " + e.getMessage());
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
	 * Deleta um produto do banco de dados.
	 *
	 * @param id_produto o ID do produto a ser deletado
	 * @return true se o produto foi deletado com sucesso, false caso contrário
	 */
	public static boolean deletarProduto(int id_produto) {

		Produto produto_deletar = null;
		String sql = "DELETE FROM produto WHERE id_produto = ?";
		PreparedStatement ps = null;
		produto_deletar = buscarProdutoPorId(id_produto);

		if (produto_deletar == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setInt(1, id_produto);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("Não foi possível deletar o PRODUTO no banco de dados: " + e.getMessage());
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
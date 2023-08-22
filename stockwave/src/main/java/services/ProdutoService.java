package services;

import dao.ProdutoDao;
import model.Produto;

/**
 * Classe de serviços para Produto.
 * 
 * Métodos:
 * - validarIdProduto: verifica se um Produto com o ID especificado existe.
 * - exibirProdutoPorId: busca e retorna um Produto pelo ID.
 * - atualizarProduto: atualiza um Produto com as informações fornecidas.
 * - cadastrarProduto: cadastra um novo Produto.
 * - deletarProduto: exclui um Produto pelo ID.
 * 
 * Exemplo de uso:
 * 
 * // Verificar se um Produto com o ID 123 existe
 * boolean existe = ProdutoService.validarIdProduto(123);
 * 
 * // Buscar um Produto pelo ID
 * Produto produto = ProdutoService.exibirProdutoPorId(123);
 * 
 * // Atualizar um Produto
 * Produto novoProduto = new Produto();
 * // ... configuração das informações do Produto ...
 * Produto produtoAtualizado = ProdutoService.atualizarProduto(123, novoProduto);
 * 
 * // Cadastrar um novo Produto
 * Produto novoProduto = new Produto();
 * // ... configuração das informações do Produto ...
 * Produto produtoCadastrado = ProdutoService.cadastrarProduto(novoProduto);
 * 
 * // Deletar um Produto pelo ID
 * boolean deletado = ProdutoService.deletarProduto(123);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Produto
 * @see dao.ProdutoDao
 * @see controller.ProdutoResource
 * 
 * @author Stockwave
 * 
 */
public class ProdutoService {

	/**
	 * Verifica se um Produto com o ID especificado existe.
	 *
	 * @param id_produto o ID do Produto
	 * @return true se um Produto com o ID especificado existe, caso contrário, false
	 */
	public static boolean validarIdProduto(int id_produto) {
		return ProdutoDao.buscarProdutoPorId(id_produto) != null;
	}

	/**
	 * Busca e retorna um Produto pelo ID.
	 *
	 * @param id_produto o ID do Produto
	 * @return o Produto correspondente ao ID, ou null se não encontrado
	 */
	public static Produto exibirProdutoPorId(int id_produto) {
		return ProdutoDao.buscarProdutoPorId(id_produto);
	}

	/**
	 * Atualiza um Produto com as informações fornecidas.
	 *
	 * @param id_produto o ID do Produto a ser atualizado
	 * @param produto o objeto Produto com as novas informações
	 * @return o Produto atualizado, ou null se o Produto não existir
	 */
	public static Produto atualizarProduto(int id_produto, Produto produto) {
		Produto produto_atualizar = exibirProdutoPorId(id_produto);

		if (produto_atualizar == null || produto_atualizar.getId_produto() != produto.getId_produto()) {
			return null;
		} else {
			Produto produto_novo = ProdutoDao.atualizarProduto(produto);

			return produto_novo;
		}
	}

	/**
	 * Cadastra um novo Produto.
	 *
	 * @param produto_novo o objeto Produto a ser cadastrado
	 * @return o Produto cadastrado
	 */
	public static Produto cadastrarProduto(Produto produto_novo) {
		return ProdutoDao.cadastrarProduto(produto_novo);
	}

	/**
	 * Exclui um Produto pelo ID.
	 *
	 * @param id_produto o ID do Produto a ser excluído
	 * @return true se o Produto foi excluído com sucesso, caso contrário, false
	 */
	public static boolean deletarProduto(int id_produto) {
		if (validarIdProduto(id_produto)) {
			return ProdutoDao.deletarProduto(id_produto);
		} else {
			return false;
		}
	}
}
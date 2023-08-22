package services;

import dao.MovimentacaoDao;
import model.Movimentacao;

/**
 * Classe de serviços para Movimentacao.
 * 
 * Métodos:
 * - validarIdMovimentacao: verifica se uma Movimentacao com o ID especificado existe.
 * - exibirMovimentacaoPorId: busca e retorna uma Movimentacao pelo ID.
 * - atualizarMovimentacao: atualiza uma Movimentacao com as informações fornecidas.
 * - cadastrarMovimentacao: cadastra uma nova Movimentacao.
 * - deletarMovimentacao: exclui uma Movimentacao com o ID especificado.
 * 
 * Exemplo de uso:
 * 
 * // Verificar se a Movimentacao com ID 1 existe
 * boolean existe = MovimentacaoService.validarIdMovimentacao(1);
 * 
 * // Buscar uma Movimentacao pelo ID
 * Movimentacao movimentacao = MovimentacaoService.exibirMovimentacaoPorId(1);
 * 
 * // Atualizar uma Movimentacao
 * Movimentacao novaMovimentacao = new Movimentacao();
 * // ... configuração das informações da Movimentacao ...
 * Movimentacao movimentacaoAtualizada = MovimentacaoService.atualizarMovimentacao(1, novaMovimentacao);
 * 
 * // Cadastrar uma nova Movimentacao
 * Movimentacao novaMovimentacao = new Movimentacao();
 * // ... configuração das informações da Movimentacao ...
 * Movimentacao movimentacaoCadastrada = MovimentacaoService.cadastrarMovimentacao(novaMovimentacao);
 * 
 * // Deletar uma Movimentacao
 * boolean deletada = MovimentacaoService.deletarMovimentacao(1);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Movimentacao
 * @see dao.MovimentacaoDao
 * @see controller.MovimentacaoResource
 * @see model.Usuario
 * @see model.Produto
 * 
 * @author Stockwave
 * 
 */
public class MovimentacaoService {
	
	/**
	 * Verifica se uma Movimentacao com o ID especificado existe.
	 *
	 * @param id_movimentacao o ID da Movimentacao
	 * @return true se uma Movimentacao com o ID especificado existe, caso contrário, false
	 */
	public static boolean validarIdMovimentacao(int id_movimentacao) {
		return MovimentacaoDao.buscarMovimentacaoPorId(id_movimentacao) != null;
	}

	/**
	 * Busca e retorna uma Movimentacao pelo ID.
	 *
	 * @param id_movimentacao o ID da Movimentacao
	 * @return a Movimentacao correspondente ao ID, ou null se não encontrada
	 */
	public static Movimentacao exibirMovimentacaoPorId(int id_movimentacao) {
		return MovimentacaoDao.buscarMovimentacaoPorId(id_movimentacao);
	}

	/**
	 * Atualiza uma Movimentacao com as informações fornecidas.
	 *
	 * @param id_movimentacao o ID da Movimentacao
	 * @param movimentacao o objeto Movimentacao com as novas informações
	 * @return a Movimentacao atualizada, ou null se a Movimentacao não existir ou os IDs não coincidirem
	 */
	public static Movimentacao atualizarMovimentacao(int id_movimentacao, Movimentacao movimentacao) {
		Movimentacao movimentacao_atualizar = exibirMovimentacaoPorId(id_movimentacao);

		if (movimentacao_atualizar == null || movimentacao_atualizar.getId_movimentacao() != movimentacao.getId_movimentacao()) {
			return null;
		} else {
			Movimentacao movimentacao_nova = MovimentacaoDao.atualizarMovimentacao(movimentacao);
			return movimentacao_nova;
		}
	}

	/**
	 * Cadastra uma nova Movimentacao.
	 *
	 * @param movimentacao_nova o objeto Movimentacao a ser cadastrado
	 * @return a Movimentacao cadastrada
	 */
	public static Movimentacao cadastrarMovimentacao(Movimentacao movimentacao_nova) {
		return MovimentacaoDao.cadastrarMovimentacao(movimentacao_nova);
	}

	/**
	 * Exclui uma Movimentacao com o ID especificado.
	 *
	 * @param id_movimentacao o ID da Movimentacao
	 * @return true se a Movimentacao foi excluída com sucesso, caso contrário, false
	 */
	public static boolean deletarMovimentacao(int id_movimentacao) {
		if (validarIdMovimentacao(id_movimentacao)) {
			return MovimentacaoDao.deletarMovimentacao(id_movimentacao);
		} else {
			return false;
		}
	}
}
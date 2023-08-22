package services;

import dao.Modulo_QuestaoDao;
import model.Modulo_Questao;

/**
 * Classe de serviços para Modulo_Questao.
 * 
 * Métodos:
 * - validarIdModulo_Questao: verifica se um Modulo_Questao com o ID especificado existe.
 * - exibirModulo_QuestaoPorId: busca e retorna um Modulo_Questao pelo ID do módulo e ID da questão.
 * - atualizarModulo_Questao: atualiza um Modulo_Questao com as informações fornecidas.
 * - cadastrarModulo_Questao: cadastra um novo Modulo_Questao.
 * - deletarModulo_Questao: exclui um Modulo_Questao com o ID do módulo e ID da questão especificados.
 * 
 * Exemplo de uso:
 * 
 * // Verificar se o Modulo_Questao com ID do módulo 1 existe
 * boolean existe = Modulo_QuestaoService.validarIdModulo_Questao(1);
 * 
 * // Buscar um Modulo_Questao pelo ID do módulo e ID da questão
 * Modulo_Questao modulo_questao = Modulo_QuestaoService.exibirModulo_QuestaoPorId(1, 2);
 * 
 * // Atualizar um Modulo_Questao
 * Modulo_Questao novoModulo_Questao = new Modulo_Questao();
 * // ... configuração das informações do Modulo_Questao ...
 * Modulo_Questao modulo_QuestaoAtualizado = Modulo_QuestaoService.atualizarModulo_Questao(1, 2, novoModulo_Questao);
 * 
 * // Cadastrar um novo Modulo_Questao
 * Modulo_Questao novoModulo_Questao = new Modulo_Questao();
 * // ... configuração das informações do Modulo_Questao ...
 * Modulo_Questao modulo_QuestaoCadastrado = Modulo_QuestaoService.cadastrarModulo_Questao(novoModulo_Questao);
 * 
 * // Deletar um Modulo_Questao
 * boolean deletado = Modulo_QuestaoService.deletarModulo_Questao(1, 2);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Modulo_Questao
 * @see dao.Modulo_QuestaoDao
 * @see controller.Modulo_QuestaoResource
 * @see model.Modulo
 * @see model.Questao
 * 
 * @author Stockwave
 * 
 */
public class Modulo_QuestaoService {

	/**
	 * Verifica se um Modulo_Questao com o ID especificado existe.
	 *
	 * @param id_modulo o ID do Modulo_Questao
	 * @return true se um Modulo_Questao com o ID especificado existe, caso contrário, false
	 */
	public static boolean validarIdModulo_Questao(int id_modulo) {
		return Modulo_QuestaoDao.buscarModulo_QuestoesPorId(id_modulo) != null;
	}

	/**
	 * Busca e retorna um Modulo_Questao pelo ID do módulo e ID da questão.
	 *
	 * @param id_modulo o ID do módulo
	 * @param id_questao o ID da questão
	 * @return o Modulo_Questao correspondente ao ID do módulo e ID da questão, ou null se não encontrado
	 */
	public static Modulo_Questao exibirModulo_QuestaoPorId(int id_modulo, int id_questao) {
		return Modulo_QuestaoDao.buscarModulo_QuestaoPorId(id_modulo, id_questao);
	}

	/**
	 * Atualiza um Modulo_Questao com as informações fornecidas.
	 *
	 * @param id_modulo o ID do módulo
	 * @param id_questao o ID da questão
	 * @param modulo_questao o objeto Modulo_Questao com as novas informações
	 * @return o Modulo_Questao atualizado, ou null se o Modulo_Questao não existir ou os IDs não coincidirem
	 */
	public static Modulo_Questao atualizarModulo_Questao(int id_modulo, int id_questao, Modulo_Questao modulo_questao) {
		Modulo_Questao modulo_questao_atualizar = exibirModulo_QuestaoPorId(id_modulo, id_questao);

		if (modulo_questao_atualizar == null || modulo_questao_atualizar.getModulo().getId_modulo() != modulo_questao.getModulo().getId_modulo()) {
			return null;
		} else {
			Modulo_Questao modulo_questao_novo = Modulo_QuestaoDao.atualizarModulo_Questao(modulo_questao, id_questao);
			return modulo_questao_novo;
		}
	}

	/**
	 * Cadastra um novo Modulo_Questao.
	 *
	 * @param modulo_questao_novo o objeto Modulo_Questao a ser cadastrado
	 * @return o Modulo_Questao cadastrado
	 */
	public static Modulo_Questao cadastrarModulo_Questao(Modulo_Questao modulo_questao_novo) {
		return Modulo_QuestaoDao.cadastrarModulo_Questao(modulo_questao_novo);
	}

	/**
	 * Exclui um Modulo_Questao com o ID do módulo e ID da questão especificados.
	 *
	 * @param id_modulo o ID do módulo
	 * @param id_questao o ID da questão
	 * @return true se o Modulo_Questao foi excluído com sucesso, caso contrário, false
	 */
	public static boolean deletarModulo_Questao(int id_modulo, int id_questao) {
		if (validarIdModulo_Questao(id_modulo)) {
			return Modulo_QuestaoDao.deletarModulo_Questao(id_modulo, id_questao);
		} else {
			return false;
		}
	}
}
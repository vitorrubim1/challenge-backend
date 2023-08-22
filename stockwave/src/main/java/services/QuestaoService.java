package services;

import dao.QuestaoDao;
import model.Questao;
import model.Resposta;

/**
 * Classe de serviços para Questão.
 * 
 * Métodos:
 * - validarIdQuestao: verifica se uma Questão com o ID especificado existe.
 * - exibirQuestaoPorId: busca e retorna uma Questão pelo ID.
 * - atualizarQuestao: atualiza uma Questão com as informações fornecidas.
 * - cadastrarQuestao: cadastra uma nova Questão.
 * - deletarQuestao: exclui uma Questão pelo ID.
 * - verificarRespostaQuestao: verifica se a resposta fornecida corresponde à resposta da Questão.
 * 
 * Exemplo de uso:
 * 
 * // Verificar se uma Questão com o ID 123 existe
 * boolean existe = QuestaoService.validarIdQuestao(123);
 * 
 * // Buscar uma Questão pelo ID
 * Questao questao = QuestaoService.exibirQuestaoPorId(123);
 * 
 * // Atualizar uma Questão
 * Questao novaQuestao = new Questao();
 * // ... configuração das informações da Questão ...
 * Questao questaoAtualizada = QuestaoService.atualizarQuestao(123, novaQuestao);
 * 
 * // Cadastrar uma nova Questão
 * Questao novaQuestao = new Questao();
 * // ... configuração das informações da Questão ...
 * Questao questaoCadastrada = QuestaoService.cadastrarQuestao(novaQuestao);
 * 
 * // Deletar uma Questão pelo ID
 * boolean deletada = QuestaoService.deletarQuestao(123);
 * 
 * // Verificar se a resposta fornecida corresponde à resposta da Questão
 * Resposta resposta = new Resposta();
 * resposta.setResposta("A");
 * boolean respostaCorreta = QuestaoService.verificarRespostaQuestao(123, resposta);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Questao
 * @see dao.QuestaoDao
 * @see controller.QuestaoResource
 * @see model.Resposta
 * @see model.Modulo
 * 
 * @author Stockwave
 * 
 */
public class QuestaoService {

	/**
	 * Verifica se uma Questão com o ID especificado existe.
	 *
	 * @param id_questao o ID da Questão
	 * @return true se uma Questão com o ID especificado existe, caso contrário, false
	 */
	public static boolean validarIdQuestao(int id_questao) {
		return QuestaoDao.buscarQuestaoPorId(id_questao) != null;
	}

	/**
	 * Busca e retorna uma Questão pelo ID.
	 *
	 * @param id_questao o ID da Questão
	 * @return a Questão correspondente ao ID, ou null se não encontrada
	 */
	public static Questao exibirQuestaoPorId(int id_questao) {
		return QuestaoDao.buscarQuestaoPorId(id_questao);
	}

	/**
	 * Atualiza uma Questão com as informações fornecidas.
	 *
	 * @param id_questao o ID da Questão a ser atualizada
	 * @param questao a Questão com as novas informações
	 * @return a Questão atualizada, ou null se a Questão não existir
	 */
	public static Questao atualizarQuestao(int id_questao, Questao questao) {
		Questao questao_atualizar = exibirQuestaoPorId(id_questao);

		if (questao_atualizar == null || questao_atualizar.getId_questao() != questao.getId_questao()) {
			return null;
		} else {
			Questao questao_nova = QuestaoDao.atualizarQuestao(questao);

			return questao_nova;
		}
	}

	/**
	 * Cadastra uma nova Questão.
	 *
	 * @param questao_nova a nova Questão a ser cadastrada
	 * @return a Questão cadastrada
	 */
	public static Questao cadastrarQuestao(Questao questao_nova) {
		return QuestaoDao.cadastrarQuestao(questao_nova);
	}

	/**
	 * Exclui uma Questão pelo ID.
	 *
	 * @param id_questao o ID da Questão a ser excluída
	 * @return true se a Questão foi excluída com sucesso, caso contrário, false
	 */
	public static boolean deletarQuestao(int id_questao) {
		if (validarIdQuestao(id_questao)) {
			return QuestaoDao.deletarQuestao(id_questao);
		} else {
			return false;
		}
	}
	
	/**
	 * Verifica se a resposta fornecida corresponde à resposta da Questão.
	 *
	 * @param id_questao o ID da Questão
	 * @param resposta a resposta fornecida
	 * @return true se a resposta fornecida corresponde à resposta da Questão, caso contrário, false
	 */
	public static boolean verificarRespostaQuestao(int id_questao, Resposta resposta) {
		Questao questao_verificar_resposta = exibirQuestaoPorId(id_questao);
		
		if (questao_verificar_resposta.getResposta_questao().getResposta().equalsIgnoreCase(resposta.getResposta())) {
			return true;
			}
		else {
			return false;
		}
	}
}
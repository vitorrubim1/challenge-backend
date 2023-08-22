package services;

import dao.RespostaDao;
import model.Resposta;

/**
 * Classe de serviços para Resposta.
 *
 * Métodos:
 * - validarNomeResposta: verifica se uma Resposta com o nome especificado existe.
 * - exibirRespostaPorNome: busca e retorna uma Resposta pelo nome.
 * - atualizarResposta: atualiza uma Resposta com as informações fornecidas.
 * - cadastrarResposta: cadastra uma nova Resposta.
 * - deletarResposta: exclui uma Resposta pelo nome.
 *
 * Exemplo de uso:
 *
 * // Verificar se uma Resposta com o nome "A" existe
 * boolean existe = RespostaService.validarNomeResposta("A");
 *
 * // Buscar uma Resposta pelo nome
 * Resposta resposta = RespostaService.exibirRespostaPorNome("A");
 *
 * // Atualizar uma Resposta
 * Resposta novaResposta = new Resposta();
 * // ... configuração das informações da Resposta ...
 * Resposta respostaAtualizada = RespostaService.atualizarResposta("A", novaResposta);
 *
 * // Cadastrar uma nova Resposta
 * Resposta novaResposta = new Resposta();
 * // ... configuração das informações da Resposta ...
 * Resposta respostaCadastrada = RespostaService.cadastrarResposta(novaResposta);
 *
 * // Deletar uma Resposta pelo nome
 * boolean deletada = RespostaService.deletarResposta("A");
 *
 * @since 1.0
 * @version 1.0
 *
 * @see model.Resposta
 * @see dao.RespostaDao
 * @see controller.RespostaResource
 * @see model.Questao
 * 
 * @author Stockwave
 *
 */
public class RespostaService {

	/**
	 * Verifica se uma Resposta com o nome especificado existe.
	 *
	 * @param str_resposta o nome da Resposta
	 * @return true se uma Resposta com o nome especificado existe, caso contrário, false
	 */
	public static boolean validarNomeResposta(String str_resposta) {
		return RespostaDao.buscarRespostaPorNome(str_resposta) != null;
	}

	/**
	 * Busca e retorna uma Resposta pelo nome.
	 *
	 * @param str_resposta o nome da Resposta
	 * @return a Resposta correspondente ao nome, ou null se não encontrada
	 */
	public static Resposta exibirRespostaPorNome(String str_resposta) {
		return RespostaDao.buscarRespostaPorNome(str_resposta);
	}

	/**
	 * Atualiza uma Resposta com as informações fornecidas.
	 *
	 * @param str_resposta o nome da Resposta a ser atualizada
	 * @param resposta a Resposta com as novas informações
	 * @return a Resposta atualizada, ou null se a Resposta não existir
	 */
	public static Resposta atualizarResposta(String str_resposta, Resposta resposta) {
		Resposta resposta_atualizar = exibirRespostaPorNome(str_resposta);
		Resposta resposta_nova = null;

		if (resposta_atualizar != null) {
			resposta_nova = RespostaDao.atualizarResposta(resposta, str_resposta);
			if (resposta_nova == null) {
				return null;
			}
		}

		return resposta_nova;
	}

	/**
	 * Cadastra uma nova Resposta.
	 *
	 * @param resposta_novo a nova Resposta a ser cadastrada
	 * @return a Resposta cadastrada
	 */
	public static Resposta cadastrarResposta(Resposta resposta_novo) {
		return RespostaDao.cadastrarResposta(resposta_novo);
	}

	/**
	 * Exclui uma Resposta pelo nome.
	 *
	 * @param str_resposta o nome da Resposta a ser excluída
	 * @return true se a Resposta foi excluída com sucesso, caso contrário, false
	 */
	public static boolean deletarResposta(String str_resposta) {
		if (validarNomeResposta(str_resposta)) {
			return RespostaDao.deletarResposta(str_resposta);
		} else {
			return false;
		}
	}
}
package services;

import dao.AulaDao;
import model.Aula;

/**
 * Classe de serviços para Aula.
 * 
 * Métodos:
 * - validarIdAula: verifica se uma Aula com o ID especificado existe.
 * - exibirAulaPorId: busca e retorna uma Aula pelo ID.
 * - atualizarAula: atualiza uma Aula com as informações fornecidas.
 * - cadastrarAula: cadastra uma nova Aula.
 * - deletarAula: exclui uma Aula com o ID especificado.
 * 
 * Exemplo de uso:
 * 
 * // Verificar se a Aula com ID 1 existe
 * boolean existe = AulaService.validarIdAula(1);
 * 
 * // Buscar uma Aula pelo ID
 * Aula aula = AulaService.exibirAulaPorId(1);
 * 
 * // Atualizar uma Aula
 * Aula novaAula = new Aula();
 * // ... configuração das informações da Aula ...
 * Aula aulaAtualizada = AulaService.atualizarAula(1, novaAula);
 * 
 * // Cadastrar uma nova Aula
 * Aula novaAula = new Aula();
 * // ... configuração das informações da Aula ...
 * Aula aulaCadastrada = AulaService.cadastrarAula(novaAula);
 * 
 * // Deletar uma Aula
 * boolean deletado = AulaService.deletarAula(1);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Aula
 * @see dao.AulaDao
 * @see controller.AulaResource
 * 
 * @author Stockwave
 * 
 */
public class AulaService {

	/**
	 * Verifica se uma Aula com o ID especificado existe.
	 *
	 * @param id_aula o ID da Aula
	 * @return true se uma Aula com o ID especificado existe, caso contrário, false
	 */
	public static boolean validarIdAula(int id_aula) {
		return AulaDao.buscarAulaPorId(id_aula) != null;
	}

	/**
	 * Busca e retorna uma Aula pelo ID.
	 *
	 * @param id_aula o ID da Aula
	 * @return a Aula correspondente ao ID, ou null se não encontrada
	 */
	public static Aula exibirAulaPorId(int id_aula) {
		return AulaDao.buscarAulaPorId(id_aula);
	}

	/**
	 * Atualiza uma Aula com as informações fornecidas.
	 *
	 * @param id_aula o ID da Aula
	 * @param aula o objeto Aula contendo as informações atualizadas
	 * @return a Aula atualizada, ou null se a Aula não existir ou não pertencer ao ID especificado
	 */
	public static Aula atualizarAula(int id_aula, Aula aula) {
		Aula aula_atualizar = AulaService.exibirAulaPorId(id_aula);

		if (aula_atualizar == null || aula_atualizar.getId_aula() != aula.getId_aula()) {
			return null;
		} else {
			Aula aula_nova = AulaDao.atualizarAula(aula);

			return aula_nova;
		}
	}

	/**
	 * Cadastra uma nova Aula.
	 *
	 * @param aula_nova o objeto Aula a ser cadastrado
	 * @return a Aula cadastrada
	 */
	public static Aula cadastrarAula(Aula aula_nova) {
		return AulaDao.cadastrarAula(aula_nova);
	}

	/**
	 * Exclui uma Aula com o ID especificado.
	 *
	 * @param id_aula o ID da Aula
	 * @return true se a Aula foi excluída com sucesso, caso contrário, false
	 */
	public static boolean deletarAula(int id_aula) {
		if (validarIdAula(id_aula)) {
			return AulaDao.deletarAula(id_aula);
		} else {
			return false;
		}
	}
}
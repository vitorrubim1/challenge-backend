package services;

import dao.Modulo_AulaDao;
import model.Modulo_Aula;

/**
 * Classe de serviços para Módulo_Aula.
 * 
 * Métodos:
 * - validarIdModulo_Aula: verifica se um Módulo_Aula com o ID especificado existe.
 * - exibirModulo_AulaPorId: busca e retorna um Módulo_Aula pelo ID do módulo e ID da aula.
 * - atualizarModulo_Aula: atualiza um Módulo_Aula com as informações fornecidas.
 * - cadastrarModulo_Aula: cadastra um novo Módulo_Aula.
 * - deletarModulo_Aula: exclui um Módulo_Aula com o ID do módulo e ID da aula especificados.
 * 
 * Exemplo de uso:
 * 
 * // Verificar se o Módulo_Aula com ID do módulo 1 existe
 * boolean existe = Modulo_AulaService.validarIdModulo_Aula(1);
 * 
 * // Buscar um Módulo_Aula pelo ID do módulo e ID da aula
 * Modulo_Aula modulo_aula = Modulo_AulaService.exibirModulo_AulaPorId(1, 2);
 * 
 * // Atualizar um Módulo_Aula
 * Modulo_Aula novoModulo_Aula = new Modulo_Aula();
 * // ... configuração das informações do Módulo_Aula ...
 * Modulo_Aula modulo_AulaAtualizado = Modulo_AulaService.atualizarModulo_Aula(1, 2, novoModulo_Aula);
 * 
 * // Cadastrar um novo Módulo_Aula
 * Modulo_Aula novoModulo_Aula = new Modulo_Aula();
 * // ... configuração das informações do Módulo_Aula ...
 * Modulo_Aula modulo_AulaCadastrado = Modulo_AulaService.cadastrarModulo_Aula(novoModulo_Aula);
 * 
 * // Deletar um Módulo_Aula
 * boolean deletado = Modulo_AulaService.deletarModulo_Aula(1, 2);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Modulo_Aula
 * @see dao.Modulo_AulaDao
 * @see controller.Modulo_AulaResource
 * @see model.Modulo
 * @see model.Aula
 * 
 * @author Stockwave
 * 
 */
public class Modulo_AulaService {

	/**
	 * Verifica se um Módulo_Aula com o ID especificado existe.
	 *
	 * @param id_modulo o ID do Módulo_Aula
	 * @return true se um Módulo_Aula com o ID especificado existe, caso contrário, false
	 */
	public static boolean validarIdModulo_Aula(int id_modulo) {
		return Modulo_AulaDao.buscarModulo_AulasPorId(id_modulo) != null;
	}

	/**
	 * Busca e retorna um Módulo_Aula pelo ID do módulo e ID da aula.
	 *
	 * @param id_modulo o ID do módulo
	 * @param id_aula o ID da aula
	 * @return o Módulo_Aula correspondente ao ID do módulo e ID da aula, ou null se não encontrado
	 */
	public static Modulo_Aula exibirModulo_AulaPorId(int id_modulo, int id_aula) {
		return Modulo_AulaDao.buscarModulo_AulaPorId(id_modulo, id_aula);
	}

	/**
	 * Atualiza um Módulo_Aula com as informações fornecidas.
	 *
	 * @param id_modulo o ID do módulo
	 * @param id_aula o ID da aula
	 * @param modulo_Aula o objeto Modulo_Aula com as novas informações
	 * @return o Módulo_Aula atualizado, ou null se o Módulo_Aula não existir ou os IDs não coincidirem
	 */
	public static Modulo_Aula atualizarModulo_Aula(int id_modulo, int id_aula, Modulo_Aula modulo_Aula) {
		Modulo_Aula modulo_Aula_atualizar = exibirModulo_AulaPorId(id_modulo, id_aula);

		if (modulo_Aula_atualizar == null || modulo_Aula_atualizar.getModulo().getId_modulo() != modulo_Aula.getModulo().getId_modulo()) {
			return null;
		} else {
			Modulo_Aula modulo_Aula_novo = Modulo_AulaDao.atualizarModulo_Aula(modulo_Aula, id_aula);
			return modulo_Aula_novo;
		}
	}

	/**
	 * Cadastra um novo Módulo_Aula.
	 *
	 * @param modulo_Aula_novo o objeto Modulo_Aula a ser cadastrado
	 * @return o Módulo_Aula cadastrado
	 */
	public static Modulo_Aula cadastrarModulo_Aula(Modulo_Aula modulo_Aula_novo) {
		return Modulo_AulaDao.cadastrarModulo_Aula(modulo_Aula_novo);
	}

	/**
	 * Exclui um Módulo_Aula com o ID do módulo e ID da aula especificados.
	 *
	 * @param id_modulo o ID do módulo
	 * @param id_aula o ID da aula
	 * @return true se o Módulo_Aula foi excluído com sucesso, caso contrário, false
	 */
	public static boolean deletarModulo_Aula(int id_modulo, int id_aula) {
		if (validarIdModulo_Aula(id_modulo)) {
			return Modulo_AulaDao.deletarModulo_Aula(id_modulo, id_aula);
		} else {
			return false;
		}
	}
}
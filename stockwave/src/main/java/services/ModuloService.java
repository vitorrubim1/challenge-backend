package services;

import dao.ModuloDao;
import model.Modulo;

/**
 * Classe de serviços para Modulo.
 * 
 * Métodos:
 * - validarIdModulo: verifica se um Modulo com o ID especificado existe.
 * - exibirModuloPorId: busca e retorna um Modulo pelo ID.
 * - atualizarModulo: atualiza um Modulo com as informações fornecidas.
 * - cadastrarModulo: cadastra um novo Modulo.
 * - deletarModulo: exclui um Modulo com o ID especificado.
 * 
 * Exemplo de uso:
 * 
 * // Verificar se o Modulo com ID 1 existe
 * boolean existe = ModuloService.validarIdModulo(1);
 * 
 * // Buscar um Modulo pelo ID
 * Modulo modulo = ModuloService.exibirModuloPorId(1);
 * 
 * // Atualizar um Modulo
 * Modulo novoModulo = new Modulo();
 * // ... configuração das informações do Modulo ...
 * Modulo moduloAtualizado = ModuloService.atualizarModulo(1, novoModulo);
 * 
 * // Cadastrar um novo Modulo
 * Modulo novoModulo = new Modulo();
 * // ... configuração das informações do Modulo ...
 * Modulo moduloCadastrado = ModuloService.cadastrarModulo(novoModulo);
 * 
 * // Deletar um Modulo
 * boolean deletado = ModuloService.deletarModulo(1);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Modulo
 * @see dao.ModuloDao
 * @see controller.ModuloResource
 * @see model.Aula
 * @see model.Questao
 * 
 * @author Stockwave
 * 
 */
public class ModuloService {

	/**
	 * Verifica se um Modulo com o ID especificado existe.
	 *
	 * @param id_modulo o ID do Modulo
	 * @return true se um Modulo com o ID especificado existe, caso contrário, false
	 */
	public static boolean validarIdModulo(int id_modulo) {
		return ModuloDao.buscarModuloPorId(id_modulo) != null;
	}

	/**
	 * Busca e retorna um Modulo pelo ID.
	 *
	 * @param id_modulo o ID do Modulo
	 * @return o Modulo correspondente ao ID, ou null se não encontrado
	 */
	public static Modulo exibirModuloPorId(int id_modulo) {
		return ModuloDao.buscarModuloPorId(id_modulo);
	}

	/**
	 * Atualiza um Modulo com as informações fornecidas.
	 *
	 * @param id_modulo o ID do Modulo
	 * @param modulo o objeto Modulo com as novas informações
	 * @return o Modulo atualizado, ou null se o Modulo não existir ou os IDs não coincidirem
	 */
	public static Modulo atualizarModulo(int id_modulo, Modulo modulo) {
		Modulo modulo_atualizar = exibirModuloPorId(id_modulo);

		if (modulo_atualizar == null || modulo_atualizar.getId_modulo() != modulo.getId_modulo()) {
			return null;
		} else {
			Modulo modulo_novo = ModuloDao.atualizarModulo(modulo);
			return modulo_novo;
		}
	}

	/**
	 * Cadastra um novo Modulo.
	 *
	 * @param modulo_novo o objeto Modulo a ser cadastrado
	 * @return o Modulo cadastrado
	 */
	public static Modulo cadastrarModulo(Modulo modulo_novo) {
		return ModuloDao.cadastrarModulo(modulo_novo);
	}

	/**
	 * Exclui um Modulo com o ID especificado.
	 *
	 * @param id_modulo o ID do Modulo
	 * @return true se o Modulo foi excluído com sucesso, caso contrário, false
	 */
	public static boolean deletarModulo(int id_modulo) {
		if (validarIdModulo(id_modulo)) {
			return ModuloDao.deletarModulo(id_modulo);
		} else {
			return false;
		}
	}
}
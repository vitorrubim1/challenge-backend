package services;

import dao.NivelDao;
import model.Nivel;

/**
 * Classe de serviços para Nível.
 * 
 * Métodos:
 * - validarNomeNivel: verifica se um Nível com o nome especificado existe.
 * - exibirNivelPorNome: busca e retorna um Nível pelo nome.
 * - atualizarNivel: atualiza um Nível com as informações fornecidas.
 * - cadastrarNivel: cadastra um novo Nível.
 * - deletarNivel: exclui um Nível pelo nome.
 * 
 * Exemplo de uso:
 * 
 * // Verificar se um Nível com o nome "Iniciante" existe
 * boolean existe = NivelService.validarNomeNivel("Iniciante");
 * 
 * // Buscar um Nível pelo nome
 * Nivel nivel = NivelService.exibirNivelPorNome("Iniciante");
 * 
 * // Atualizar um Nível
 * Nivel novoNivel = new Nivel();
 * // ... configuração das informações do Nível ...
 * Nivel nivelAtualizado = NivelService.atualizarNivel("Iniciante", novoNivel);
 * 
 * // Cadastrar um novo Nível
 * Nivel novoNivel = new Nivel();
 * // ... configuração das informações do Nível ...
 * Nivel nivelCadastrado = NivelService.cadastrarNivel(novoNivel);
 * 
 * // Deletar um Nível pelo nome
 * boolean deletado = NivelService.deletarNivel("Iniciante");
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Nivel
 * @see dao.NivelDao
 * @see controller.NivelResource
 * @see model.Aluno
 * @see model.Modulo
 * 
 * @author Stockwave
 * 
 */
public class NivelService {

	/**
	 * Verifica se um Nível com o nome especificado existe.
	 *
	 * @param nome_nivel o nome do Nível
	 * @return true se um Nível com o nome especificado existe, caso contrário, false
	 */
	public static boolean validarNomeNivel(String nome_nivel) {
		return NivelDao.buscarNivelPorNome(nome_nivel) != null;
	}

	/**
	 * Busca e retorna um Nível pelo nome.
	 *
	 * @param nome_nivel o nome do Nível
	 * @return o Nível correspondente ao nome, ou null se não encontrado
	 */
	public static Nivel exibirNivelPorNome(String nome_nivel) {
		return NivelDao.buscarNivelPorNome(nome_nivel);
	}

	/**
	 * Atualiza um Nível com as informações fornecidas.
	 *
	 * @param nome_nivel o nome do Nível a ser atualizado
	 * @param nivel o objeto Nível com as novas informações
	 * @return o Nível atualizado, ou null se o Nível não existir
	 */
	public static Nivel atualizarNivel(String nome_nivel, Nivel nivel) {
		Nivel nivel_atualizar = exibirNivelPorNome(nome_nivel);
		Nivel nivel_novo = null;

		if (nivel_atualizar != null) {
			nivel_novo = NivelDao.atualizarNivel(nivel, nome_nivel);
			if (nivel_novo == null) {
				return null;
			}
		}

		return nivel_novo;
	}

	/**
	 * Cadastra um novo Nível.
	 *
	 * @param nivel_novo o objeto Nível a ser cadastrado
	 * @return o Nível cadastrado
	 */
	public static Nivel cadastrarNivel(Nivel nivel_novo) {
		return NivelDao.cadastrarNivel(nivel_novo);
	}

	/**
	 * Exclui um Nível pelo nome.
	 *
	 * @param nome_nivel o nome do Nível a ser excluído
	 * @return true se o Nível foi excluído com sucesso, caso contrário, false
	 */
	public static boolean deletarNivel(String nome_nivel) {
		if (validarNomeNivel(nome_nivel)) {
			return NivelDao.deletarNivel(nome_nivel);
		} else {
			return false;
		}
	}
}
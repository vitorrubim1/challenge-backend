package services;

import dao.ProfessorDao;
import model.Professor;

/**
 * Classe de serviços para Professor.
 * 
 * Métodos:
 * - validarIdProfessor: verifica se um Professor com o ID de usuário especificado existe.
 * - exibirProfessorPorId: busca e retorna um Professor pelo ID de usuário.
 * - atualizarProfessor: atualiza um Professor com as informações fornecidas.
 * - cadastrarProfessor: cadastra um novo Professor.
 * - deletarProfessor: exclui um Professor pelo ID de usuário.
 * 
 * Exemplo de uso:
 * 
 * // Verificar se um Professor com o ID de usuário 123 existe
 * boolean existe = ProfessorService.validarIdProfessor(123);
 * 
 * // Buscar um Professor pelo ID de usuário
 * Professor professor = ProfessorService.exibirProfessorPorId(123);
 * 
 * // Atualizar um Professor
 * Professor novoProfessor = new Professor();
 * // ... configuração das informações do Professor ...
 * Professor professorAtualizado = ProfessorService.atualizarProfessor(123, novoProfessor);
 * 
 * // Cadastrar um novo Professor
 * Professor novoProfessor = new Professor();
 * // ... configuração das informações do Professor ...
 * Professor professorCadastrado = ProfessorService.cadastrarProfessor(novoProfessor);
 * 
 * // Deletar um Professor pelo ID de usuário
 * boolean deletado = ProfessorService.deletarProfessor(123);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Professor
 * @see dao.ProfessorDao
 * @see controller.ProfessorResource
 * @see model.Usuario
 * 
 * @author Stockwave
 * 
 */
public class ProfessorService {

	/**
	 * Verifica se um Professor com o ID de usuário especificado existe.
	 *
	 * @param id_usuario o ID de usuário do Professor
	 * @return true se um Professor com o ID de usuário especificado existe, caso contrário, false
	 */
	public static boolean validarIdProfessor(int id_usuario) {
		return ProfessorDao.buscarProfessorPorId(id_usuario) != null;
	}

	/**
	 * Busca e retorna um Professor pelo ID de usuário.
	 *
	 * @param id_usuario o ID de usuário do Professor
	 * @return o Professor correspondente ao ID de usuário, ou null se não encontrado
	 */
	public static Professor exibirProfessorPorId(int id_usuario) {
		return ProfessorDao.buscarProfessorPorId(id_usuario);
	}

	/**
	 * Atualiza um Professor com as informações fornecidas.
	 *
	 * @param id_usuario o ID de usuário do Professor a ser atualizado
	 * @param professor o objeto Professor com as novas informações
	 * @return o Professor atualizado, ou null se o Professor não existir
	 */
	public static Professor atualizarProfessor(int id_usuario, Professor professor) {
		Professor professor_atualizar = exibirProfessorPorId(id_usuario);

		if (professor_atualizar == null || professor_atualizar.getId_usuario() != professor.getId_usuario()) {
			return null;
		} else {
			Professor professor_novo = ProfessorDao.atualizarProfessor(professor);

			return professor_novo;
		}
	}

	/**
	 * Cadastra um novo Professor.
	 *
	 * @param professor_novo o objeto Professor a ser cadastrado
	 * @return o Professor cadastrado
	 */
	public static Professor cadastrarProfessor(Professor professor_novo) {
		return ProfessorDao.cadastrarProfessor(professor_novo);
	}

	/**
	 * Exclui um Professor pelo ID de usuário.
	 *
	 * @param id_usuario o ID de usuário do Professor a ser excluído
	 * @return true se o Professor foi excluído com sucesso, caso contrário, false
	 */
	public static boolean deletarProfessor(int id_usuario) {
		if (validarIdProfessor(id_usuario)) {
			return ProfessorDao.deletarProfessor(id_usuario);
		} else {
			return false;
		}
	}
}
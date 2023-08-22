package services;

import dao.AlunoDao;
import model.Aluno;

/**
 * Classe de serviços para Aluno.
 * 
 * Métodos:
 * - validarIdAluno: verifica se um Aluno com o ID especificado existe.
 * - exibirAlunoPorId: busca e retorna um Aluno pelo ID do usuário.
 * - atualizarAluno: atualiza um Aluno com as informações fornecidas.
 * - cadastrarAluno: cadastra um novo Aluno.
 * - deletarAluno: exclui um Aluno com o ID especificado.
 * - validarLoginAluno: verifica se as credenciais de login do Aluno são válidas.
 * 
 * Exemplo de uso:
 * 
 * // Verificar se o Aluno com ID do usuário 1 existe
 * boolean existe = AlunoService.validarIdAluno(1);
 * 
 * // Buscar um Aluno pelo ID do usuário
 * Aluno aluno = AlunoService.exibirAlunoPorId(1);
 * 
 * // Atualizar um Aluno
 * Aluno novoAluno = new Aluno();
 * // ... configuração das informações do Aluno ...
 * Aluno alunoAtualizado = AlunoService.atualizarAluno(1, novoAluno);
 * 
 * // Cadastrar um novo Aluno
 * Aluno novoAluno = new Aluno();
 * // ... configuração das informações do Aluno ...
 * Aluno alunoCadastrado = AlunoService.cadastrarAluno(novoAluno);
 * 
 * // Deletar um Aluno
 * boolean deletado = AlunoService.deletarAluno(1);
 * 
 * // Verificar se as credenciais de login do Aluno são válidas
 * Aluno alunoLogin = AlunoService.validarLoginAluno("email@example.com", "senha123");
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Aluno
 * @see dao.AlunoDao
 * @see controller.AlunoResource
 * @see model.Usuario
 * 
 * @author Stockwave
 * 
 */
public class AlunoService {

	/**
	 * Verifica se um Aluno com o ID especificado existe.
	 *
	 * @param id_usuario o ID do usuário associado ao Aluno
	 * @return true se um Aluno com o ID especificado existe, caso contrário, false
	 */
	public static boolean validarIdAluno(int id_usuario) {
		return AlunoDao.buscarAlunoPorId(id_usuario) != null;
	}

	/**
	 * Busca e retorna um Aluno pelo ID do usuário.
	 *
	 * @param id_usuario o ID do usuário associado ao Aluno
	 * @return o Aluno correspondente ao ID do usuário, ou null se não encontrado
	 */
	public static Aluno exibirAlunoPorId(int id_usuario) {
		return AlunoDao.buscarAlunoPorId(id_usuario);
	}

	/**
	 * Atualiza um Aluno com as informações fornecidas.
	 *
	 * @param id_usuario o ID do usuário associado ao Aluno
	 * @param aluno o objeto Aluno contendo as informações atualizadas
	 * @return o Aluno atualizado, ou null se o Aluno não existir ou não pertencer ao usuário especificado
	 */
	public static Aluno atualizarAluno(int id_usuario, Aluno aluno) {
		Aluno aluno_atualizar = AlunoService.exibirAlunoPorId(id_usuario);

		if (aluno_atualizar == null || aluno_atualizar.getId_usuario() != aluno.getId_usuario()) {
			return null;
		} else {
			 Aluno aluno_novo = AlunoDao.atualizarAluno(aluno);

			return aluno_novo;
		}
	}

	/**
	 * Cadastra um novo Aluno.
	 *
	 * @param aluno_novo o objeto Aluno a ser cadastrado
	 * @return o Aluno cadastrado
	 */
	public static Aluno cadastrarAluno(Aluno aluno_novo) {
		return AlunoDao.cadastrarAluno(aluno_novo);
	}

	/**
	 * Exclui um Aluno com o ID especificado.
	 *
	 * @param id_usuario o ID do usuário associado ao Aluno
	 * @return true se o Aluno foi excluído com sucesso, caso contrário, false
	 */
	public static boolean deletarAluno(int id_usuario) {
		if (validarIdAluno(id_usuario)) {
			return AlunoDao.deletarAluno(id_usuario);
		} else {
			return false;
		}
	}

	/**
	 * Verifica se as credenciais de login do Aluno são válidas.
	 *
	 * @param email_usuario o email do Aluno
	 * @param senha_aluno a senha do Aluno
	 * @return o Aluno correspondente às credenciais de login válidas, ou null se as credenciais forem inválidas
	 */
	public static Aluno validarLoginAluno(String email_usuario, String senha_aluno) {
	    Aluno aluno_login = AlunoDao.buscarAlunoPorEmail(email_usuario);

	    if (aluno_login != null && senha_aluno.equals(aluno_login.getSenha_aluno())) {
	        return aluno_login;
	    } else {
	        return null;
	    }
	}
}
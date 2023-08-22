package services;

import dao.Aluno_CertificadoDao;
import model.Aluno_Certificado;

/**
 * Classe de serviços para Aluno_Certificado.
 * 
 * Métodos:
 * - validarIdAluno_Certificado: verifica se um Aluno_Certificado com o ID especificado existe.
 * - exibirAluno_CertificadoPorId: busca e retorna um Aluno_Certificado pelo ID do aluno e ID do certificado.
 * - atualizarAluno_Certificado: atualiza um Aluno_Certificado com as informações fornecidas.
 * - cadastrarAluno_Certificado: cadastra um novo Aluno_Certificado.
 * - deletarAluno_Certificado: exclui um Aluno_Certificado com o ID especificado.
 * 
 * Exemplo de uso:
 * 
 * // Verificar se o Aluno_Certificado com ID do usuário 1 existe
 * boolean existe = Aluno_CertificadoService.validarIdAluno_Certificado(1);
 * 
 * // Buscar um Aluno_Certificado pelo ID do aluno e ID do certificado
 * Aluno_Certificado alunoCertificado = Aluno_CertificadoService.exibirAluno_CertificadoPorId(1, 1);
 * 
 * // Atualizar um Aluno_Certificado
 * Aluno_Certificado novoAlunoCertificado = new Aluno_Certificado();
 * // ... configuração das informações do Aluno_Certificado ...
 * Aluno_Certificado alunoCertificadoAtualizado = Aluno_CertificadoService.atualizarAluno_Certificado(1, 1, novoAlunoCertificado);
 * 
 * // Cadastrar um novo Aluno_Certificado
 * Aluno_Certificado novoAlunoCertificado = new Aluno_Certificado();
 * // ... configuração das informações do Aluno_Certificado ...
 * Aluno_Certificado alunoCertificadoCadastrado = Aluno_CertificadoService.cadastrarAluno_Certificado(novoAlunoCertificado);
 * 
 * // Deletar um Aluno_Certificado
 * boolean deletado = Aluno_CertificadoService.deletarAluno_Certificado(1, 1);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Aluno_Certificado
 * @see dao.Aluno_CertificadoDao
 * @see controller.Aluno_CertificadoResource
 * @see model.Aluno
 * @see model.Certificado
 * 
 * @author Stockwave
 * 
 */
public class Aluno_CertificadoService {

	/**
	 * Verifica se um Aluno_Certificado com o ID especificado existe.
	 *
	 * @param id_usuario o ID do usuário associado ao Aluno_Certificado
	 * @return true se um Aluno_Certificado com o ID especificado existe, caso contrário, false
	 */
	public static boolean validarIdAluno_Certificado(int id_usuario) {
		return Aluno_CertificadoDao.buscarAluno_CertificadosPorId(id_usuario) != null;
	}

	/**
	 * Busca e retorna um Aluno_Certificado pelo ID do aluno e ID do certificado.
	 *
	 * @param id_usuario o ID do usuário associado ao Aluno_Certificado
	 * @param id_certificado o ID do certificado
	 * @return o Aluno_Certificado correspondente aos IDs fornecidos, ou null se não encontrado
	 */
	public static Aluno_Certificado exibirAluno_CertificadoPorId(int id_usuario, int id_certificado) {
		return Aluno_CertificadoDao.buscarAluno_CertificadoPorId(id_usuario, id_certificado);
	}

	/**
	 * Atualiza um Aluno_Certificado com as informações fornecidas.
	 *
	 * @param id_usuario o ID do usuário associado ao Aluno_Certificado
	 * @param id_certificado o ID do certificado
	 * @param modulo_Aula o objeto Aluno_Certificado contendo as informações atualizadas
	 * @return o Aluno_Certificado atualizado, ou null se o Aluno_Certificado não existir ou não pertencer ao aluno especificado
	 */
	public static Aluno_Certificado atualizarAluno_Certificado(int id_usuario, int id_certificado, Aluno_Certificado modulo_Aula) {
		Aluno_Certificado modulo_Aula_atualizar = Aluno_CertificadoService.exibirAluno_CertificadoPorId(id_usuario, id_certificado);

		if (modulo_Aula_atualizar == null || modulo_Aula_atualizar.getAluno().getId_usuario() != modulo_Aula.getAluno().getId_usuario()) {
			return null;
		} else {
			Aluno_Certificado modulo_Aula_novo = Aluno_CertificadoDao.atualizarAluno_Certificado(modulo_Aula, id_certificado);

			return modulo_Aula_novo;
		}
	}

	/**
	 * Cadastra um novo Aluno_Certificado.
	 *
	 * @param modulo_Aula_novo o objeto Aluno_Certificado a ser cadastrado
	 * @return o Aluno_Certificado cadastrado
	 */
	public static Aluno_Certificado cadastrarAluno_Certificado(Aluno_Certificado modulo_Aula_novo) {
		return Aluno_CertificadoDao.cadastrarAluno_Certificado(modulo_Aula_novo);
	}

	/**
	 * Exclui um Aluno_Certificado com o ID especificado.
	 *
	 * @param id_usuario o ID do usuário associado ao Aluno_Certificado
	 * @param id_certificado o ID do certificado
	 * @return true se o Aluno_Certificado foi excluído com sucesso, caso contrário, false
	 */
	public static boolean deletarAluno_Certificado(int id_usuario, int id_certificado) {
		if (validarIdAluno_Certificado(id_usuario)) {
			return Aluno_CertificadoDao.deletarAluno_Certificado(id_usuario, id_certificado);
		} else {
			return false;
		}
	}
}
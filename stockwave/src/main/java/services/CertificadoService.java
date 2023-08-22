package services;

import dao.CertificadoDao;
import model.Certificado;

/**
 * Classe de serviços para Certificado.
 * 
 * Métodos:
 * - validarIdCertificado: verifica se um Certificado com o ID especificado existe.
 * - exibirCertificadoPorId: busca e retorna um Certificado pelo ID.
 * - atualizarCertificado: atualiza um Certificado com as informações fornecidas.
 * - cadastrarCertificado: cadastra um novo Certificado.
 * - deletarCertificado: exclui um Certificado com o ID especificado.
 * 
 * Exemplo de uso:
 * 
 * // Verificar se o Certificado com ID 1 existe
 * boolean existe = CertificadoService.validarIdCertificado(1);
 * 
 * // Buscar um Certificado pelo ID
 * Certificado certificado = CertificadoService.exibirCertificadoPorId(1);
 * 
 * // Atualizar um Certificado
 * Certificado novoCertificado = new Certificado();
 * // ... configuração das informações do Certificado ...
 * Certificado certificadoAtualizado = CertificadoService.atualizarCertificado(1, novoCertificado);
 * 
 * // Cadastrar um novo Certificado
 * Certificado novoCertificado = new Certificado();
 * // ... configuração das informações do Certificado ...
 * Certificado certificadoCadastrado = CertificadoService.cadastrarCertificado(novoCertificado);
 * 
 * // Deletar um Certificado
 * boolean deletado = CertificadoService.deletarCertificado(1);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Certificado
 * @see dao.CertificadoDao
 * @see controller.CertificadoResource
 * 
 * @author Stockwave
 * 
 */
public class CertificadoService {
	
	/**
	 * Verifica se um Certificado com o ID especificado existe.
	 *
	 * @param id_certificado o ID do Certificado
	 * @return true se um Certificado com o ID especificado existe, caso contrário, false
	 */
	public static boolean validarIdCertificado(int id_certificado) {
		return CertificadoDao.buscarCertificadoPorId(id_certificado) != null;
	}

	/**
	 * Busca e retorna um Certificado pelo ID.
	 *
	 * @param id_certificado o ID do Certificado
	 * @return o Certificado correspondente ao ID, ou null se não encontrado
	 */
	public static Certificado exibirCertificadoPorId(int id_certificado) {
		return CertificadoDao.buscarCertificadoPorId(id_certificado);
	}

	/**
	 * Atualiza um Certificado com as informações fornecidas.
	 *
	 * @param id_certificado o ID do Certificado
	 * @param certificado o objeto Certificado contendo as informações atualizadas
	 * @return o Certificado atualizado, ou null se o Certificado não existir ou não pertencer ao ID especificado
	 */
	public static Certificado atualizarCertificado(int id_certificado, Certificado certificado) {
		Certificado certificado_atualizar = CertificadoService.exibirCertificadoPorId(id_certificado);

		if (certificado_atualizar == null || certificado_atualizar.getId_certificado() != certificado.getId_certificado()) {
			return null;
		} else {
			Certificado certificado_novo = CertificadoDao.atualizarCertificado(certificado);

			return certificado_novo;
		}
	}

	/**
	 * Cadastra um novo Certificado.
	 *
	 * @param certificado_novo o objeto Certificado a ser cadastrado
	 * @return o Certificado cadastrado
	 */
	public static Certificado cadastrarCertificado(Certificado certificado_novo) {
		return CertificadoDao.cadastrarCertificado(certificado_novo);
	}

	/**
	 * Exclui um Certificado com o ID especificado.
	 *
	 * @param id_certificado o ID do Certificado
	 * @return true se o Certificado foi excluído com sucesso, caso contrário, false
	 */
	public static boolean deletarCertificado(int id_certificado) {
		if (validarIdCertificado(id_certificado)) {
			return CertificadoDao.deletarCertificado(id_certificado);
		} else {
			return false;
		}
	}
}
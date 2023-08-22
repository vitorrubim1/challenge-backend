package services;

import dao.UsuarioDao;
import model.Usuario;

/**
 * Classe de serviços para Usuário.
 *
 * Métodos:
 * - validarIdUsuario: verifica se um Usuário com o ID especificado existe.
 * - exibirUsuarioPorId: busca e retorna um Usuário pelo ID.
 * - atualizarUsuario: atualiza um Usuário com as informações fornecidas.
 * - cadastrarUsuario: cadastra um novo Usuário.
 * - deletarUsuario: exclui um Usuário pelo ID.
 *
 * Exemplo de uso:
 *
 * // Verificar se um Usuário com o ID 1 existe
 * boolean existe = UsuarioService.validarIdUsuario(1);
 *
 * // Buscar um Usuário pelo ID
 * Usuario usuario = UsuarioService.exibirUsuarioPorId(1);
 *
 * // Atualizar um Usuário
 * Usuario novoUsuario = new Usuario();
 * // ... configuração das informações do Usuário ...
 * Usuario usuarioAtualizado = UsuarioService.atualizarUsuario(1, novoUsuario);
 *
 * // Cadastrar um novo Usuário
 * Usuario novoUsuario = new Usuario();
 * // ... configuração das informações do Usuário ...
 * Usuario usuarioCadastrado = UsuarioService.cadastrarUsuario(novoUsuario);
 *
 * // Deletar um Usuário pelo ID
 * boolean deletado = UsuarioService.deletarUsuario(1);
 *
 * @since 1.0
 * @version 1.0
 *
 * @see model.Usuario
 * @see dao.UsuarioDao
 * @see controller.UsuarioResource
 * @see model.Aluno
 * @see model.Funcionario
 * @see model.Professor
 * 
 * @author Stockwave
 *
 */
public class UsuarioService {

	/**
	 * Verifica se um Usuário com o ID especificado existe.
	 *
	 * @param id_usuario o ID do Usuário
	 * @return true se um Usuário com o ID especificado existe, caso contrário, false
	 */
	public static boolean validarIdUsuario(int id_usuario) {
		return UsuarioDao.buscarUsuarioPorId(id_usuario) != null;
	}

	/**
	 * Busca e retorna um Usuário pelo ID.
	 *
	 * @param id_usuario o ID do Usuário
	 * @return o Usuário correspondente ao ID, ou null se não encontrado
	 */
	public static Usuario exibirUsuarioPorId(int id_usuario) {
		return UsuarioDao.buscarUsuarioPorId(id_usuario);
	}

	/**
	 * Atualiza um Usuário com as informações fornecidas.
	 *
	 * @param id_usuario o ID do Usuário a ser atualizado
	 * @param usuario o Usuário com as novas informações
	 * @return o Usuário atualizado, ou null se o Usuário não existir
	 */
	public static Usuario atualizarUsuario(int id_usuario, Usuario usuario) {
		Usuario usuario_atualizar = exibirUsuarioPorId(id_usuario);

		if (usuario_atualizar == null || usuario_atualizar.getId_usuario() != usuario.getId_usuario()) {
			return null;
		} else {
			Usuario usuario_novo = UsuarioDao.atualizarUsuario(usuario);

			return usuario_novo;
		}
	}

	/**
	 * Cadastra um novo Usuário.
	 *
	 * @param usuario_novo o novo Usuário a ser cadastrado
	 * @return o Usuário cadastrado
	 */
	public static Usuario cadastrarUsuario(Usuario usuario_novo) {
		return UsuarioDao.cadastrarUsuario(usuario_novo);
	}

	/**
	 * Exclui um Usuário pelo ID.
	 *
	 * @param id_usuario o ID do Usuário a ser excluído
	 * @return true se o Usuário foi excluído com sucesso, caso contrário, false
	 */
	public static boolean deletarUsuario(int id_usuario) {
		if (validarIdUsuario(id_usuario)) {
			return UsuarioDao.deletarUsuario(id_usuario);
		} else {
			return false;
		}
	}
}
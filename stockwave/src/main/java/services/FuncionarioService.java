package services;

import dao.FuncionarioDao;
import model.Funcionario;

/**
 * Classe de serviços para Funcionário.
 * 
 * Métodos:
 * - validarIdFuncionario: verifica se um Funcionário com o ID especificado existe.
 * - exibirFuncionarioPorId: busca e retorna um Funcionário pelo ID do usuário.
 * - atualizarFuncionario: atualiza um Funcionário com as informações fornecidas.
 * - cadastrarFuncionario: cadastra um novo Funcionário.
 * - deletarFuncionario: exclui um Funcionário com o ID especificado.
 * 
 * Exemplo de uso:
 * 
 * // Verificar se o Funcionário com ID do usuário 1 existe
 * boolean existe = FuncionarioService.validarIdFuncionario(1);
 * 
 * // Buscar um Funcionário pelo ID do usuário
 * Funcionario funcionario = FuncionarioService.exibirFuncionarioPorId(1);
 * 
 * // Atualizar um Funcionário
 * Funcionario novoFuncionario = new Funcionario();
 * // ... configuração das informações do Funcionário ...
 * Funcionario funcionarioAtualizado = FuncionarioService.atualizarFuncionario(1, novoFuncionario);
 * 
 * // Cadastrar um novo Funcionário
 * Funcionario novoFuncionario = new Funcionario();
 * // ... configuração das informações do Funcionário ...
 * Funcionario funcionarioCadastrado = FuncionarioService.cadastrarFuncionario(novoFuncionario);
 * 
 * // Deletar um Funcionário
 * boolean deletado = FuncionarioService.deletarFuncionario(1);
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Funcionario
 * @see dao.FuncionarioDao
 * @see controller.FuncionarioResource
 * @see model.Usuario
 * 
 * @author Stockwave
 * 
 */
public class FuncionarioService {

	/**
	 * Verifica se um Funcionário com o ID especificado existe.
	 *
	 * @param id_usuario o ID do Funcionário
	 * @return true se um Funcionário com o ID especificado existe, caso contrário, false
	 */
	public static boolean validarIdFuncionario(int id_usuario) {
		return FuncionarioDao.buscarFuncionarioPorId(id_usuario) != null;
	}

	/**
	 * Busca e retorna um Funcionário pelo ID do usuário.
	 *
	 * @param id_usuario o ID do Funcionário
	 * @return o Funcionário correspondente ao ID do usuário, ou null se não encontrado
	 */
	public static Funcionario exibirFuncionarioPorId(int id_usuario) {
		return FuncionarioDao.buscarFuncionarioPorId(id_usuario);
	}

	/**
	 * Atualiza um Funcionário com as informações fornecidas.
	 *
	 * @param id_usuario o ID do Funcionário
	 * @param funcionario o objeto Funcionário contendo as informações atualizadas
	 * @return o Funcionário atualizado, ou null se o Funcionário não existir ou não pertencer ao ID especificado
	 */
	public static Funcionario atualizarFuncionario(int id_usuario, Funcionario funcionario) {
		Funcionario funcionario_atualizar = FuncionarioService.exibirFuncionarioPorId(id_usuario);

		if (funcionario_atualizar == null || funcionario_atualizar.getId_usuario() != funcionario.getId_usuario()) {
			return null;
		} else {
			Funcionario funcionario_novo = FuncionarioDao.atualizarFuncionario(funcionario);

			return funcionario_novo;
		}
	}

	/**
	 * Cadastra um novo Funcionário.
	 *
	 * @param funcionario_novo o objeto Funcionário a ser cadastrado
	 * @return o Funcionário cadastrado
	 */
	public static Funcionario cadastrarFuncionario(Funcionario funcionario_novo) {
		return FuncionarioDao.cadastrarFuncionario(funcionario_novo);
	}

	/**
	 * Exclui um Funcionário com o ID especificado.
	 *
	 * @param id_usuario o ID do Funcionário
	 * @return true se o Funcionário foi excluído com sucesso, caso contrário, false
	 */
	public static boolean deletarFuncionario(int id_usuario) {
		if (validarIdFuncionario(id_usuario)) {
			return FuncionarioDao.deletarFuncionario(id_usuario);
		} else {
			return false;
		}
	}
}
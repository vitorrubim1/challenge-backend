package controller;

import java.net.URI;
import java.util.ArrayList;

import dao.AlunoDao;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.UriBuilder;
import model.Aluno;
import services.AlunoService;

/**
 * Classe responsável por representar o recurso REST para manipulação de Aluno.
 * 
 * Esta classe define os métodos HTTP para listar, exibir, atualizar, cadastrar e deletar Alunos.
 * 
 * Métodos:
 * - listarAlunos: Retorna a lista de todos os Alunos.
 * - exibirAlunoPorId: Retorna um Aluno específico com base no ID do usuário.
 * - atualizarAluno: Atualiza um Aluno existente com base no ID do usuário.
 * - cadastrarAluno: Cadastra um novo Aluno.
 * - deletarAluno: Deleta um Aluno existente com base no ID do usuário.
 * - validarLoginAluno: Valida o login de um aluno.
 * 
 * Exemplo de uso:
 * 
 * AlunoResource resource = new AlunoResource();
 * Response response = resource.listarAlunos();
 * ArrayList&lt;Aluno&lt; alunos = (ArrayList&lt;Aluno&lt;) response.getEntity();
 * for (Aluno aluno : alunos) {
 *     System.out.println(aluno.getId_usuario());
 * }
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Aluno
 * @see services.AlunoService
 * @see dao.AlunoDao
 * @see model.Certificado
 * @see model.Usuario
 * 
 * @author Stockwave
 * 
 */
@Path("/aluno")
public class AlunoResource {
	
	/**
	 * Retorna a lista de todos os Alunos.
	 *
	 * @return Uma resposta HTTP contendo a lista de Alunos no formato JSON.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarAlunos() {
		AlunoDao repositorio = new AlunoDao();
		ArrayList<Aluno> retorno = repositorio.listarAlunos();
		ResponseBuilder response = Response.ok();
		response.entity(retorno);
		return response.build();
	}
	
	/**
	 * Retorna um Aluno específico com base no ID do usuário.
	 *
	 * @param id_usuario O ID do usuário.
	 * @return Uma resposta HTTP contendo o Aluno no formato JSON, se encontrado.
	 *         Retorna um código de status 404 caso contrário.
	 */
	@GET
	@Path("/{id}")
	public Response exibirAlunoPorId(@PathParam("id") int id_usuario) {
		Aluno aluno_buscado = AlunoDao.buscarAlunoPorId(id_usuario);

		if (aluno_buscado != null) {
			ResponseBuilder response = Response.ok();
			response.entity(aluno_buscado);
			return response.build();
		} else {
			ResponseBuilder response = Response.status(404)
					.entity("Não foi possível encontrar o ALUNO de id_usuario: " + id_usuario);
			return response.build();
		}
	}
	
	/**
	 * Atualiza um Aluno existente com base no ID do usuário.
	 *
	 * @param id_usuario O ID do usuário.
	 * @param aluno O objeto Aluno com as informações atualizadas.
	 * @return Uma resposta HTTP contendo o Aluno atualizado no formato JSON, se a atualização for bem-sucedida.
	 *         Retorna um código de status 404 caso contrário.
	 */
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarAluno(@PathParam("id") int id_usuario, @Valid Aluno aluno) {
		Aluno aluno_novo = null;
		aluno_novo = AlunoService.atualizarAluno(id_usuario, aluno);
		if (aluno_novo != null) {
			return Response.ok(aluno_novo).build();
		} else {
			return Response.status(404)
					.entity("Não foi possível atualizar o ALUNO de id_usuario: " + id_usuario
							+ ". O id da URI e o ID do objeto JSON devem ser iguais e devem existir no banco de dados.")
					.build();
		}
	}
	
	/**
	 * Cadastra um novo Aluno.
	 *
	 * @param aluno_novo O objeto Aluno a ser cadastrado.
	 * @return Uma resposta HTTP contendo o Aluno cadastrado no formato JSON.
	 *         Retorna um código de status 201 caso o cadastro seja bem-sucedido.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarAluno(@Valid Aluno aluno_novo) {
		Aluno resp = AlunoService.cadastrarAluno(aluno_novo);
		final URI alunoUri = UriBuilder.fromResource(UsuarioResource.class).path("/usuario/{id}")
				.build(resp.getId_usuario());
		ResponseBuilder response = Response.created(alunoUri);
		response.entity(resp);
		return response.build();
	}
	
	/**
	 * Deleta um Aluno existente com base no ID do usuário.
	 *
	 * @param id_usuario O ID do usuário.
	 * @return Uma resposta HTTP vazia com código de status 204 se a exclusão for bem-sucedida.
	 *         Retorna um código de status 404 caso contrário.
	 */
	@DELETE
	@Path("/{id}")
	public Response deletarAluno(@PathParam("id") int id_usuario) {
		if (AlunoService.deletarAluno(id_usuario)) {
			ResponseBuilder response = Response.noContent();
			return response.build();
		} else {
			System.out.println("Não foi possível remover o ALUNO: " + id_usuario);
			ResponseBuilder response = Response.status(404)
					.entity("Não foi possível remover o ALUNO de id_usuario: " + id_usuario);
			return response.build();
		}
	}
	
	/**
	 * Valida o login de um aluno.
	 *
	 * @param alunoLogin O objeto Aluno contendo o email e a senha do aluno a serem validados.
	 * @return A resposta HTTP com o status e o objeto Aluno logado em caso de sucesso,
	 *         ou uma resposta HTTP de erro com uma mensagem em caso de falha na validação do login.
	 */
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validarLoginAluno(Aluno alunoLogin) {
		String email_usuario = alunoLogin.getEmail_usuario();
		String senha_aluno = alunoLogin.getSenha_aluno();

		try {
			Aluno aluno_logado = AlunoService.validarLoginAluno(email_usuario, senha_aluno);

			if (aluno_logado != null) {
				ResponseBuilder response = Response.ok();
				response.entity(aluno_logado);
				return response.build();
			} else {
				return Response.status(401).entity("Email e/ou senha inválida.").build();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			return Response.status(401).entity("Email e/ou senha inválida.").build();
		}
	}
}

package controller;

import java.net.URI;
import java.util.ArrayList;

import dao.Aluno_CertificadoDao;
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
import model.Aluno_Certificado;
import services.Aluno_CertificadoService;

/**
 * Classe responsável por representar o recurso REST para manipulação de Aluno_Certificado.
 * 
 * Esta classe define os métodos HTTP para listar, exibir, atualizar, cadastrar e deletar Aluno_Certificados.
 * Os Aluno_Certificados são registros de certificados associados a alunos.
 * 
 * Métodos:
 * - listarAluno_Certificados: retorna a lista de todos os Aluno_Certificados.
 * - exibirAluno_CertificadosPorId: retorna um Aluno_Certificado específico com base no ID do usuário.
 * - atualizarAluno_Certificado: atualiza um Aluno_Certificado existente com base no ID do usuário e ID do certificado.
 * - cadastrarAluno_Certificado: cadastra um novo Aluno_Certificado.
 * - deletarAluno_Certificado: deleta um Aluno_Certificado existente com base no ID do usuário e ID do certificado.
 * 
 * Exemplo de uso:
 * 
 * Aluno_CertificadoResource resource = new Aluno_CertificadoResource();
 * Response response = resource.listarAluno_Certificados();
 * ArrayList&lt;Aluno_Certificado&lt; aluno_certificados = (ArrayList&lt;Aluno_Certificado&lt;) response.getEntity();
 * for (Aluno_Certificado aluno_certificado : aluno_certificados) {
 *     System.out.println(aluno_certificado.getId());
 * }
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Aluno_Certificado
 * @see services.Aluno_CertificadoService
 * @see dao.Aluno_CertificadoDao
 * @see model.Aluno
 * @see model.Certificado
 * 
 * @author Stockwave
 * 
 */
@Path("/aluno_certificado")
public class Aluno_CertificadoResource {
	
	/**
     * Retorna a lista de todos os Aluno_Certificados.
     *
     * @return Uma resposta HTTP contendo a lista de Aluno_Certificados no formato JSON.
     */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarAluno_Certificados() {
		Aluno_CertificadoDao repositorio = new Aluno_CertificadoDao();
		ArrayList<Aluno_Certificado> retorno = repositorio.listarAluno_Certificados();
		ResponseBuilder response = Response.ok();
		response.entity(retorno);
		return response.build();
	}
	
	/**
     * Retorna um Aluno_Certificado específico com base no ID do usuário.
     *
     * @param id_usuario O ID do usuário.
     * @return Uma resposta HTTP contendo o Aluno_Certificado no formato JSON, se encontrado.
     *         Retorna um código de status 404 caso contrário.
     */
	@GET
	@Path("/{id}")
	public Response exibirAluno_CertificadosPorId(@PathParam("id") int id_usuario) {
		ArrayList<Aluno_Certificado> aluno_certificados_buscado = Aluno_CertificadoDao.buscarAluno_CertificadosPorId(id_usuario);

		if (aluno_certificados_buscado != null) {
			ResponseBuilder response = Response.ok();
			response.entity(aluno_certificados_buscado);
			return response.build();
		} else {
			ResponseBuilder response = Response.status(404)
					.entity("Não foi possível encontrar o ALUNO_CERTIFICADO de id_usuario: " + id_usuario);
			return response.build();
		}
	}
	
	/**
     * Atualiza um Aluno_Certificado existente com base no ID do usuário e ID do certificado.
     *
     * @param id_usuario         O ID do usuário.
     * @param id_certificado     O ID do certificado.
     * @param aluno_certificado  O objeto Aluno_Certificado com as informações atualizadas.
     * @return Uma resposta HTTP contendo o Aluno_Certificado atualizado no formato JSON, se a atualização for bem-sucedida.
     *         Retorna um código de status 404 caso contrário.
     */
	@PUT
	@Path("/{id}/{id_certificado}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarAluno_Certificado(@PathParam("id") int id_usuario, @PathParam("id_certificado") int id_certificado, @Valid Aluno_Certificado aluno_certificado) {
		Aluno_Certificado aluno_certificado_novo = null;
		aluno_certificado_novo = Aluno_CertificadoService.atualizarAluno_Certificado(id_usuario, id_certificado, aluno_certificado);
		if (aluno_certificado_novo != null) {
			return Response.ok(aluno_certificado_novo).build();
		} else {
			return Response.status(404)
					.entity("Não foi possível atualizar o ALUNO_CERTIFICADO de id_usuario: " + id_usuario
							+ ". O id da URI e o ID do objeto JSON devem ser iguais e deve existir no banco de dados.")
					.build();
		}

	}
	
	/**
     * Cadastra um novo Aluno_Certificado.
     *
     * @param aluno_certificado_novo O objeto Aluno_Certificado a ser cadastrado.
     * @return Uma resposta HTTP contendo o Aluno_Certificado cadastrado no formato JSON.
     *         Retorna um código de status 201 com a URI do recurso criado.
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarAluno_Certificado(@Valid Aluno_Certificado aluno_certificado_novo) {
		Aluno_Certificado resp = Aluno_CertificadoService.cadastrarAluno_Certificado(aluno_certificado_novo);
		final URI aluno_certificadoUri = UriBuilder.fromResource(Aluno_CertificadoResource.class).path("/aluno_certificado/{id}")
				.build(resp.getAluno().getId_usuario());
		ResponseBuilder response = Response.created(aluno_certificadoUri);
		response.entity(resp);
		return response.build();
	}
	
	/**
     * Deleta um Aluno_Certificado existente com base no ID do usuário e ID do certificado.
     *
     * @param id_usuario         O ID do usuário.
     * @param id_certificado     O ID do certificado.
     * @return Uma resposta HTTP com o código de status 204 se a deleção for bem-sucedida.
     *         Retorna um código de status 404 caso contrário.
     */
	@DELETE
	@Path("/{id}/{id_certificado}")
	public Response deletarAluno_Certificado(@PathParam("id") int id_usuario, @PathParam("id_certificado") int id_certificado) {
		if (Aluno_CertificadoService.deletarAluno_Certificado(id_usuario, id_certificado)) {
			ResponseBuilder response = Response.noContent();
			return response.build();
		} else {
			System.out.println("Não foi possível remover o ALUNO_CERTIFICADO: " + id_usuario);
			ResponseBuilder response = Response.status(404)
					.entity("Não foi possível remover o ALUNO_CERTIFICADO de id_usuario: " + id_usuario);
			return response.build();
		}
	}
}
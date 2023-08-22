package controller;

import java.net.URI;
import java.util.ArrayList;

import dao.AulaDao;
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
import model.Aula;
import services.AulaService;

/**
 * Classe responsável por representar o recurso REST para manipulação de Aulas.
 * 
 * Esta classe define os métodos HTTP para listar, exibir, cadastrar, atualizar e deletar Aulas.
 * As Aulas são registros de aulas associadas a um curso ou disciplina.
 * 
 * Métodos:
 * - listarAulas: Retorna a lista de todas as Aulas.
 * - exibirAulaPorId: Retorna uma Aula específica com base no ID da aula.
 * - cadastrarAula: Cadastra uma nova Aula.
 * - atualizarAula: Atualiza uma Aula existente com base no ID da aula.
 * - deletarAula: Deleta uma Aula existente com base no ID da aula.
 * 
 * Exemplo de uso:
 * 
 * AulaResource resource = new AulaResource();
 * Response response = resource.listarAulas();
 * ArrayList&lt;Aula&lt; aulas = (ArrayList&lt;Aula&lt;) response.getEntity();
 * for (Aula aula : aulas) {
 *     System.out.println(aula.getId());
 * }
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Aula
 * @see services.AulaService
 * @see dao.AulaDao
 * 
 * @author Stockwave
 * 
 */
@Path("/aula")
public class AulaResource {
	
	/**
	 * Retorna a lista de todas as Aulas.
	 *
	 * @return Uma resposta HTTP contendo a lista de Aulas no formato JSON.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarAulas() {
		AulaDao repositorio = new AulaDao();
		ArrayList<Aula> retorno = repositorio.listarAulas();
		ResponseBuilder response = Response.ok();
		response.entity(retorno);
		return response.build();
	}
	
	/**
	 * Retorna uma Aula específica com base no ID da aula.
	 *
	 * @param id O ID da aula.
	 * @return Uma resposta HTTP contendo a Aula no formato JSON, se encontrada.
	 *         Retorna um código de status 404 caso contrário.
	 */
	@GET
	@Path("/{id}")
	public Response exibirAulaPorId(@PathParam("id") int id) {
		Aula aula_buscada = AulaDao.buscarAulaPorId(id);

		if (aula_buscada != null) {
			ResponseBuilder response = Response.ok();
			response.entity(aula_buscada);
			return response.build();
		} else {
			ResponseBuilder response = Response.status(404)
					.entity("Não foi possível encontrar a AULA de ID: " + id);
			return response.build();
		}
	}
	
	/**
	 * Cadastra uma nova Aula.
	 *
	 * @param aula O objeto Aula a ser cadastrado.
	 * @return Uma resposta HTTP contendo a Aula cadastrada no formato JSON.
	 *         Retorna um código de status 201 caso o cadastro seja bem-sucedido.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarAula(@Valid Aula aula) {
		Aula resp = AulaService.cadastrarAula(aula);
		final URI aulaUri = UriBuilder.fromResource(AulaResource.class).path("/aula/{id}")
				.build(resp.getId_aula());
		ResponseBuilder response = Response.created(aulaUri);
		response.entity(resp);
		return response.build();
	}
	
	/**
	 * Atualiza uma Aula existente com base no ID da aula.
	 *
	 * @param id O ID da aula.
	 * @param aula O objeto Aula com as informações atualizadas.
	 * @return Uma resposta HTTP contendo a Aula atualizada no formato JSON, se a atualização for bem-sucedida.
	 *         Retorna um código de status 404 caso contrário.
	 */
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarAula(@PathParam("id") int id, @Valid Aula aula) {
		Aula aula_atualizada = AulaService.atualizarAula(id, aula);
		if (aula_atualizada != null) {
			return Response.ok(aula_atualizada).build();
		} else {
			return Response.status(404)
					.entity("Não foi possível atualizar a AULA de ID: " + id
								+ ". O ID da URI e o ID do objeto JSON devem ser iguais e devem existir no banco de dados.")
					.build();
		}
	}
	
	/**
	 * Deleta uma Aula existente com base no ID da aula.
	 *
	 * @param id O ID da aula.
	 * @return Uma resposta HTTP vazia com código de status 204 se a exclusão for bem-sucedida.
	 *         Retorna um código de status 404 caso contrário.
	 */
	@DELETE
	@Path("/{id}")
	public Response deletarAula(@PathParam("id") int id) {
		if (AulaService.deletarAula(id)) {
			ResponseBuilder response = Response.noContent();
			return response.build();
		} else {
			System.out.println("Não foi possível remover a AULA de ID: " + id);
			ResponseBuilder response = Response.status(404)
					.entity("Não foi possível remover a AULA de ID: " + id);
			return response.build();
		}
	}
}

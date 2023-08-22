package controller;

import java.net.URI;
import java.util.ArrayList;

import dao.NivelDao;
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
import model.Nivel;
import services.NivelService;

/**
 * Classe que representa o recurso de nível do sistema.
 * 
 * Esta classe define as operações CRUD para os níveis, incluindo listar, buscar por nome,
 * cadastrar, atualizar e deletar níveis.
 * 
 * Métodos:
 * - listarNiveis: retorna a lista de todos os níveis cadastrados.
 * - exibirNivelPorNome: busca e retorna um nível específico com base em seu nome.
 * - cadastrarNivel: cadastra um novo nível.
 * - atualizarNivel: atualiza um nível existente com base em seu nome.
 * - deletarNivel: deleta um nível existente com base em seu nome.
 * 
 * Exemplo de uso:
 * 
 * NivelResource nivelResource = new NivelResource();
 * Response response = nivelResource.listarNiveis();
 * ArrayList&lt;String&lt; niveis = (ArrayList&lt;String&lt;) response.getEntity();
 * 
 * @since 1.0
 * @version 1.0
 * 
 * 
 * @see dao.NivelDao
 * @see services.NivelService
 * @see model.Nivel
 * 
 * @author Stockwave
 * 
 */
@Path("/nivel")
public class NivelResource {

	/**
	 * Retorna a lista de todos os níveis cadastrados.
	 *
	 * @return uma Response contendo a lista de níveis cadastrados no formato JSON.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarNiveis() {
		NivelDao repositorio = new NivelDao();
		ArrayList<String> retorno = repositorio.listarNiveis();
		ResponseBuilder response = Response.ok();
		response.entity(retorno);
		return response.build();
	}

	/**
	 * Busca e retorna um nível específico com base em seu nome.
	 *
	 * @param nome_nivel o nome do nível a ser buscado.
	 * @return uma Response contendo o nível encontrado no formato JSON, caso seja encontrado. 
	 * Caso contrário, retorna uma Response com status 404 e uma mensagem de erro.
	 */
	@GET
	@Path("/{nome_nivel}")
	public Response exibirNivelPorNome(@PathParam("nome_nivel") String nome_nivel) {
		Nivel nivel_buscado = NivelDao.buscarNivelPorNome(nome_nivel);

		if (nivel_buscado != null) {
			ResponseBuilder response = Response.ok();
			response.entity(nivel_buscado);
			return response.build();
		} else {
			ResponseBuilder response = Response.status(404)
					.entity("Não foi possível encontrar o nível de nome_nivel: " + nome_nivel);
			return response.build();
		}
	}

	/**
	 * Cadastra um novo nível.
	 *
	 * @param nivel_novo o objeto Nivel contendo as informações do nível a ser cadastrado.
	 * @return uma Response contendo o nível cadastrado no formato JSON, caso o cadastro seja bem-sucedido. 
	 * Caso contrário, retorna uma Response com status 500 e uma mensagem de erro.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarNivel(@Valid Nivel nivel_novo) {
		Nivel resp = NivelService.cadastrarNivel(nivel_novo);
		if (resp != null) {
			final URI nivelUri = UriBuilder.fromResource(NivelResource.class).path("/nivel/{nome_nivel}")
					.build(resp.getNome_nivel());
			ResponseBuilder response = Response.created(nivelUri);
			response.entity(resp);
			return response.build();
		} else {
			return Response.status(500).entity("Não foi possível cadastrar o novo NIVEL").build();
		}
	}

	/**
	 * Atualiza um nível existente com base em seu nome.
	 *
	 * @param nome_nivel o nome do nível a ser atualizado.
	 * @param nivel o objeto Nivel contendo as informações atualizadas do nível.
	 * @return uma Response contendo o nível atualizado no formato JSON, caso a atualização seja bem-sucedida.
	 * Caso contrário, retorna uma Response com status 404 e uma mensagem de erro.
	 */
	@PUT
	@Path("/{nome_nivel}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarNivel(@PathParam("nome_nivel") String nome_nivel, @Valid Nivel nivel) {
		Nivel nivel_novo = NivelService.atualizarNivel(nome_nivel, nivel);
		if (nivel_novo != null) {
			return Response.ok(nivel_novo).build();
		} else {
			return Response.status(404).entity("Não foi possível atualizar o NIVEL de nome_nivel: " + nome_nivel)
					.build();
		}
	}

	/**
	 * Deleta um nível existente com base em seu nome.
	 *
	 * @param nome_nivel o nome do nível a ser deletado.
	 * @return uma Response com status 204 (No Content) caso a deleção seja bem-sucedida.
	 * Caso contrário, retorna uma Response com status 404 e uma mensagem de erro.
	 */
	@DELETE
	@Path("/{nome_nivel}")
	public Response deletarNivel(@PathParam("nome_nivel") String nome_nivel) {
		if (NivelService.deletarNivel(nome_nivel)) {
			ResponseBuilder response = Response.noContent();
			return response.build();
		} else {
			System.out.println("Não foi possível remover o NIVEL: " + nome_nivel);
			ResponseBuilder response = Response.status(404)
					.entity("Não foi possível remover o NIVEL de nome_nivel: " + nome_nivel);
			return response.build();
		}
	}
}
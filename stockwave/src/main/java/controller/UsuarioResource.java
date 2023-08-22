package controller;

import java.net.URI;
import java.util.ArrayList;

import dao.UsuarioDao;
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
import model.Usuario;
import services.UsuarioService;

/**
 * Classe que representa o recurso de usuário do sistema.
 *
 * Esta classe define as operações CRUD para os usuários, incluindo listar, buscar por ID,
 * cadastrar, atualizar e deletar usuários.
 *
 * Exemplo de uso:
 *
 * UsuarioResource usuarioResource = new UsuarioResource();
 * Response response = usuarioResource.listarUsuarios();
 * ArrayList&lt;Usuario&lt; usuarios = (ArrayList&lt;Usuario&lt;) response.getEntity();
 *
 * @since 1.0
 * @version 1.0
 *
 * @see dao.UsuarioDao
 * @see services.UsuarioService
 * @see model.Usuario
 *
 * @author Stockwave
 */

@Path("/usuario")
public class UsuarioResource {
	
	/**
	 * Recupera a lista de usuários cadastrados no sistema.
	 *
	 * @return uma resposta contendo a lista de usuários em formato JSON.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarUsuarios() {
		UsuarioDao repositorio = new UsuarioDao();
		ArrayList<Usuario> retorno = repositorio.listarUsuarios();
		ResponseBuilder response = Response.ok();
		response.entity(retorno);
		return response.build();
	}
	
	/**
	 * Recupera um usuário pelo seu ID.
	 *
	 * @param id_usuario o ID do usuário a ser buscado.
	 * @return uma resposta contendo o usuário em formato JSON.
	 */
	@GET
	@Path("/{id}")
	public Response exibirUsuarioPorId(@PathParam("id") int id_usuario) {
		Usuario usuario_buscado = UsuarioDao.buscarUsuarioPorId(id_usuario);

		if (usuario_buscado != null) {
			ResponseBuilder response = Response.ok();
			response.entity(usuario_buscado);
			return response.build();
		} else {
			ResponseBuilder response = Response.status(404)
					.entity("Não foi possível encontrar o USUARIO de id_usuario: " + id_usuario);
			return response.build();
		}
	}
	
	/**
	 * Cadastra um novo usuário no sistema.
	 *
	 * @param usuario_novo o objeto Usuario contendo os dados do usuário a ser cadastrado.
	 * @return uma resposta contendo o usuário cadastrado em formato JSON.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarUsuario(@Valid Usuario usuario_novo) {
		Usuario resp = UsuarioService.cadastrarUsuario(usuario_novo);
		final URI usuarioUri = UriBuilder.fromResource(UsuarioResource.class).path("/usuario/{id}")
				.build(resp.getId_usuario());
		ResponseBuilder response = Response.created(usuarioUri);
		response.entity(resp);
		return response.build();
	}
	
	/**
	 * Atualiza os dados de um usuário existente no sistema.
	 *
	 * @param id_usuario o ID do usuário a ser atualizado.
	 * @param usuario o objeto Usuario contendo os novos dados do usuário.
	 * @return uma resposta contendo o usuário atualizado em formato JSON.
	 */
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarUsuario(@PathParam("id") int id_usuario, @Valid Usuario usuario) {
		Usuario usuario_novo = null;
		usuario_novo = UsuarioService.atualizarUsuario(id_usuario, usuario);
		if (usuario_novo != null) {
			return Response.ok(usuario_novo).build();
		} else {
			return Response.status(404)
					.entity("Não foi possível atualizar o USUARIO de id_usuario: " + id_usuario
							+ ". O id da URI e o ID do objeto JSON devem ser iguais e deve existir no banco de dados.")
					.build();
		}

	}
	
	/**
	 * Remove um usuário do sistema.
	 *
	 * @param id_usuario o ID do usuário a ser removido.
	 * @return uma resposta indicando o sucesso ou falha da operação.
	 */
	@DELETE
	@Path("/{id}")
	public Response deletarUsuario(@PathParam("id") int id_usuario) {
		if (UsuarioService.deletarUsuario(id_usuario)) {
			ResponseBuilder response = Response.noContent();
			return response.build();
		} else {
			System.out.println("Não foi possível remover o USUARIO: " + id_usuario);
			ResponseBuilder response = Response.status(404)
					.entity("Não foi possível remover o USUARIO de id_usuario: " + id_usuario);
			return response.build();
		}
	}
}
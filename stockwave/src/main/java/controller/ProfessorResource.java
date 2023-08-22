package controller;

import java.net.URI;
import java.util.ArrayList;

import dao.ProfessorDao;
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
import model.Professor;
import services.ProfessorService;

/**
 * Classe que representa o recurso de professor do sistema.
 *
 * Esta classe define as operações CRUD para os professores, incluindo listar, buscar por ID,
 * cadastrar, atualizar e deletar professores.
 *
 * Exemplo de uso:
 *
 * ProfessorResource professorResource = new ProfessorResource();
 * Response response = professorResource.listarProfessores();
 * ArrayList&lt;Professor&lt; professores = (ArrayList&lt;Professor&lt;) response.getEntity();
 *
 * @since 1.0
 * @version 1.0
 *
 * @see dao.ProfessorDao
 * @see services.ProfessorService
 * @see model.Professor
 *
 * @author Stockwave
 *
 */
@Path("/professor")
public class ProfessorResource {

    /**
     * Retorna a lista de todos os professores cadastrados.
     *
     * @return uma Response contendo a lista de professores cadastrados no formato JSON.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProfessores() {
        ProfessorDao repositorio = new ProfessorDao();
        ArrayList<Professor> retorno = repositorio.listarProfessores();
        ResponseBuilder response = Response.ok();
        response.entity(retorno);
        return response.build();
    }

    /**
     * Busca e retorna um professor específico com base em seu ID.
     *
     * @param id_usuario o ID do professor a ser buscado.
     * @return uma Response contendo o professor encontrado no formato JSON, caso seja encontrado.
     * Caso contrário, retorna uma Response com status 404 e uma mensagem de erro.
     */
    @GET
    @Path("/{id}")
    public Response exibirProfessorPorId(@PathParam("id") int id_usuario) {
        Professor professor_buscado = ProfessorDao.buscarProfessorPorId(id_usuario);

        if (professor_buscado != null) {
            ResponseBuilder response = Response.ok();
            response.entity(professor_buscado);
            return response.build();
        } else {
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível encontrar o PROFESSOR de id_usuario: " + id_usuario);
            return response.build();
        }
    }

    /**
     * Cadastra um novo professor.
     *
     * @param professor_novo o objeto Professor contendo as informações do professor a ser cadastrado.
     * @return uma Response contendo o professor cadastrado no formato JSON, caso o cadastro seja bem-sucedido.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarProfessor(@Valid Professor professor_novo) {
        Professor resp = ProfessorService.cadastrarProfessor(professor_novo);
        final URI professorUri = UriBuilder.fromResource(UsuarioResource.class).path("/usuario/{id}")
                .build(resp.getId_usuario());
        ResponseBuilder response = Response.created(professorUri);
        response.entity(resp);
        return response.build();
    }

    /**
     * Atualiza um professor existente com base em seu ID.
     *
     * @param id_usuario o ID do professor a ser atualizado.
     * @param professor o objeto Professor contendo as informações atualizadas do professor.
     * @return uma Response contendo o professor atualizado no formato JSON, caso a atualização seja bem-sucedida.
     * Caso contrário, retorna uma Response com status 404 e uma mensagem de erro.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarProfessor(@PathParam("id") int id_usuario, @Valid Professor professor) {
        Professor professor_novo = null;
        professor_novo = ProfessorService.atualizarProfessor(id_usuario, professor);
        if (professor_novo != null) {
            return Response.ok(professor_novo).build();
        } else {
            return Response.status(404)
                    .entity("Não foi possível atualizar o PROFESSOR de id_usuario: " + id_usuario
                            + ". O id da URI e o ID do objeto JSON devem ser iguais e deve existir no banco de dados.")
                    .build();
        }
    }

    /**
     * Deleta um professor existente com base em seu ID.
     *
     * @param id_usuario o ID do professor a ser deletado.
     * @return uma Response com status 204 (No Content) caso a deleção seja bem-sucedida.
     * Caso contrário, retorna uma Response com status 404 e uma mensagem de erro.
     */
    @DELETE
    @Path("/{id}")
    public Response deletarProfessor(@PathParam("id") int id_usuario) {
        if (ProfessorService.deletarProfessor(id_usuario)) {
            ResponseBuilder response = Response.noContent();
            return response.build();
        } else {
            System.out.println("Não foi possível remover o PROFESSOR: " + id_usuario);
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível remover o PROFESSOR de id_usuario: " + id_usuario);
            return response.build();
        }
    }
}
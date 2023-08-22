package controller;

import java.net.URI;
import java.util.ArrayList;

import dao.Modulo_AulaDao;
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
import model.Modulo_Aula;
import services.Modulo_AulaService;

/**
 * Classe que representa o recurso de módulo-aula do sistema.
 * 
 * Esta classe define as operações CRUD para os módulos-aula, incluindo listar, buscar por ID,
 * atualizar, cadastrar e deletar módulos-aula.
 * 
 * Métodos:
 * - listarModulo_Aulas: retorna a lista de todos os módulos-aula cadastrados.
 * - exibirModulo_AulasPorId: busca e retorna um módulo-aula específico com base em seu ID.
 * - atualizarModulo_Aula: atualiza um módulo-aula existente com base em seu ID e ID da aula.
 * - cadastrarModulo_Aula: cadastra um novo módulo-aula.
 * - deletarModulo_Aula: deleta um módulo-aula existente com base em seu ID e ID da aula.
 * 
 * Exemplo de uso:
 * 
 * Modulo_AulaResource moduloAulaResource = new Modulo_AulaResource();
 * Response response = moduloAulaResource.listarModulo_Aulas();
 * ArrayList&lt;Modulo_Aula&lt; moduloAulas = (ArrayList&lt;Modulo_Aula&lt;) response.getEntity();
 * 
 * @since 1.0
 * @version 1.0
 * 
 * 
 * @see dao.Modulo_AulaDao
 * @see services.Modulo_AulaService
 * @see model.Modulo_Aula
 * 
 * @author Stockwave
 * 
 */
@Path("/modulo_aula")
public class Modulo_AulaResource {

    /**
     * Retorna a lista de todos os módulos-aula cadastrados.
     *
     * @return Uma Response contendo a lista de módulos-aula como entidade.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarModulo_Aulas() {
        Modulo_AulaDao repositorio = new Modulo_AulaDao();
        ArrayList<Modulo_Aula> retorno = repositorio.listarModulo_Aulas();
        ResponseBuilder response = Response.ok();
        response.entity(retorno);
        return response.build();
    }

    /**
     * Busca e retorna um módulo-aula específico com base em seu ID.
     *
     * @param id_modulo O ID do módulo-aula a ser buscado.
     * @return Uma Response contendo o módulo-aula buscado como entidade, se encontrado,
     *         ou uma Response de status 404 com uma mensagem de erro, caso contrário.
     */
    @GET
    @Path("/{id}")
    public Response exibirModulo_AulasPorId(@PathParam("id") int id_modulo) {
        ArrayList<Modulo_Aula> modulo_aulas_buscado = Modulo_AulaDao.buscarModulo_AulasPorId(id_modulo);

        if (modulo_aulas_buscado != null) {
            ResponseBuilder response = Response.ok();
            response.entity(modulo_aulas_buscado);
            return response.build();
        } else {
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível encontrar o MODULO_AULA de id_modulo: " + id_modulo);
            return response.build();
        }
    }

    /**
     * Atualiza um módulo-aula existente com base em seu ID e ID da aula.
     *
     * @param id_modulo O ID do módulo-aula a ser atualizado.
     * @param id_aula O ID da aula relacionada ao módulo-aula.
     * @param modulo_aula O objeto Modulo_Aula contendo os dados atualizados do módulo-aula.
     * @return Uma Response contendo o módulo-aula atualizado como entidade, se a atualização for bem-sucedida,
     *         ou uma Response de status 404 com uma mensagem de erro, caso contrário.
     */
    @PUT
    @Path("/{id}/{id_aula}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarModulo_Aula(@PathParam("id") int id_modulo, @PathParam("id_aula") int id_aula,
            @Valid Modulo_Aula modulo_aula) {
        Modulo_Aula modulo_aula_novo = null;
        modulo_aula_novo = Modulo_AulaService.atualizarModulo_Aula(id_modulo, id_aula, modulo_aula);
        if (modulo_aula_novo != null) {
            return Response.ok(modulo_aula_novo).build();
        } else {
            return Response.status(404)
                    .entity("Não foi possível atualizar o MODULO_AULA de id_modulo: " + id_modulo
                            + ". O id da URI e o ID do objeto JSON devem ser iguais e deve existir no banco de dados.")
                    .build();
        }
    }

    /**
     * Cadastra um novo módulo-aula.
     *
     * @param modulo_aula_novo O objeto Modulo_Aula contendo os dados do novo módulo-aula a ser cadastrado.
     * @return Uma Response contendo o módulo-aula cadastrado como entidade, se o cadastro for bem-sucedido,
     *         ou uma Response de status 404 com uma mensagem de erro, caso contrário.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarModulo_Aula(@Valid Modulo_Aula modulo_aula_novo) {
        Modulo_Aula resp = Modulo_AulaService.cadastrarModulo_Aula(modulo_aula_novo);
        final URI modulo_aulaUri = UriBuilder.fromResource(Modulo_AulaResource.class).path("/modulo_aula/{id}")
                .build(resp.getModulo().getId_modulo());
        ResponseBuilder response = Response.created(modulo_aulaUri);
        response.entity(resp);
        return response.build();
    }

    /**
     * Deleta um módulo-aula existente com base em seu ID e ID da aula.
     *
     * @param id_modulo O ID do módulo-aula a ser deletado.
     * @param id_aula O ID da aula relacionada ao módulo-aula.
     * @return Uma Response de status 204 se a deleção for bem-sucedida,
     *         ou uma Response de status 404 com uma mensagem de erro, caso contrário.
     */
    @DELETE
    @Path("/{id}/{id_aula}")
    public Response deletarModulo_Aula(@PathParam("id") int id_modulo, @PathParam("id_aula") int id_aula) {
        if (Modulo_AulaService.deletarModulo_Aula(id_modulo, id_aula)) {
            ResponseBuilder response = Response.noContent();
            return response.build();
        } else {
            System.out.println("Não foi possível remover o MODULO_AULA: " + id_modulo);
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível remover o MODULO_AULA de id_modulo: " + id_modulo);
            return response.build();
        }
    }
}
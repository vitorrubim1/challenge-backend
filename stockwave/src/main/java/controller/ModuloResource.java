package controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.ModuloDao;
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
import model.Modulo;
import services.ModuloService;

/**
 * Classe que representa o recurso de módulo do sistema.
 * 
 * Esta classe define as operações CRUD para os módulos, incluindo listar, buscar por ID,
 * atualizar, cadastrar e deletar módulos.
 * 
 * Métodos:
 * - listarModulos: retorna a lista de todos os módulos cadastrados.
 * - listarModulosSubgrupos: retorna um mapa contendo os subgrupos de módulos.
 * - exibirModuloPorId: busca e retorna um módulo específico com base em seu ID.
 * - cadastrarModulo: cadastra um novo módulo.
 * - atualizarModulo: atualiza um módulo existente com base em seu ID.
 * - deletarModulo: deleta um módulo existente com base em seu ID.
 * 
 * Exemplo de uso:
 * 
 * ModuloResource moduloResource = new ModuloResource();
 * Response response = moduloResource.listarModulos();
 * ArrayList&lt;Modulo&lt; modulos = (ArrayList&lt;Modulo&lt;) response.getEntity();
 * 
 * @since 1.0
 * @version 1.0
 * 
 * 
 * @see dao.ModuloDao
 * @see services.ModuloService
 * @see model.Modulo
 * 
 * @author Stockwave
 * 
 */
@Path("/modulo")
public class ModuloResource {

    /**
     * Retorna a lista de todos os módulos cadastrados.
     *
     * @return Uma Response contendo a lista de todos os módulos cadastrados como entidade.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarModulos() {
        ModuloDao repositorio = new ModuloDao();
        ArrayList<Modulo> retorno = repositorio.listarModulos();
        ResponseBuilder response = Response.ok();
        response.entity(retorno);
        return response.build();
    }

    /**
     * Retorna um mapa contendo os subgrupos de módulos.
     *
     * @return Uma Response contendo o mapa de subgrupos de módulos como entidade.
     */
    @GET
    @Path("/subgrupos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarModulosSubgrupos() {
        ModuloDao repositorio = new ModuloDao();
        Map<String, List<Map<String, Object>>> subgrupos = repositorio.listarModulosSubgrupos();

        return Response.ok(subgrupos).build();
    }

    /**
     * Busca e retorna um módulo específico com base em seu ID.
     *
     * @param id_modulo O ID do módulo a ser buscado.
     * @return Uma Response contendo o módulo encontrado como entidade, se existir,
     *         ou uma Response de status 404 com uma mensagem de erro, caso contrário.
     */
    @GET
    @Path("/{id}")
    public Response exibirModuloPorId(@PathParam("id") int id_modulo) {
        Modulo modulo_buscado = ModuloDao.buscarModuloPorId(id_modulo);

        if (modulo_buscado != null) {
            ResponseBuilder response = Response.ok();
            response.entity(modulo_buscado);
            return response.build();
        } else {
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível encontrar o MODULO de id_modulo: " + id_modulo);
            return response.build();
        }
    }

    /**
     * Cadastra um novo módulo.
     *
     * @param modulo_novo O objeto Modulo a ser cadastrado.
     * @return Uma Response de status 201 (Created) contendo o URI do módulo cadastrado
     *         e o próprio módulo como entidade.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarModulo(@Valid Modulo modulo_novo) {
        Modulo resp = ModuloService.cadastrarModulo(modulo_novo);
        final URI moduloUri = UriBuilder.fromResource(ModuloResource.class).path("/modulo/{id}")
                .build(resp.getId_modulo());
        ResponseBuilder response = Response.created(moduloUri);
        response.entity(resp);
        return response.build();
    }

    /**
     * Atualiza um módulo existente com base em seu ID.
     *
     * @param id_modulo O ID do módulo a ser atualizado.
     * @param modulo O objeto Modulo contendo os dados atualizados.
     * @return Uma Response contendo o módulo atualizado como entidade, se a atualização for bem-sucedida,
     *         ou uma Response de status 404 com uma mensagem de erro, caso contrário.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarModulo(@PathParam("id") int id_modulo, @Valid Modulo modulo) {
        Modulo modulo_novo = null;
        modulo_novo = ModuloService.atualizarModulo(id_modulo, modulo);
        if (modulo_novo != null) {
            return Response.ok(modulo_novo).build();
        } else {
            return Response.status(404)
                    .entity("Não foi possível atualizar o MODULO de id_modulo: " + id_modulo
                            + ". O id da URI e o ID do objeto JSON devem ser iguais e deve existir no banco de dados.")
                    .build();
        }
    }

    /**
     * Deleta um módulo existente com base em seu ID.
     *
     * @param id_modulo O ID do módulo a ser deletado.
     * @return Uma Response de status 204 se a deleção for bem-sucedida,
     *         ou uma Response de status 404 com uma mensagem de erro, caso contrário.
     */
    @DELETE
    @Path("/{id}")
    public Response deletarModulo(@PathParam("id") int id_modulo) {
        if (ModuloService.deletarModulo(id_modulo)) {
            ResponseBuilder response = Response.noContent();
            return response.build();
        } else {
            System.out.println("Não foi possível remover o MODULO: " + id_modulo);
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível remover o MODULO de id_modulo: " + id_modulo);
            return response.build();
        }
    }
}
package controller;

import java.net.URI;
import java.util.ArrayList;

import dao.RespostaDao;
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
import model.Resposta;
import services.RespostaService;

/**
 * Classe que representa o recurso de resposta do sistema.
 *
 * Esta classe define as operações CRUD para as respostas, incluindo listar, buscar por nome,
 * cadastrar, atualizar e deletar respostas.
 *
 * Exemplo de uso:
 *
 * RespostaResource respostaResource = new RespostaResource();
 * Response response = respostaResource.listarRespostas();
 * ArrayList&lt;Resposta&lt; respostas = (ArrayList&lt;Resposta&lt;) response.getEntity();
 *
 * @since 1.0
 * @version 1.0
 *
 * @see dao.RespostaDao
 * @see services.RespostaService
 * @see model.Resposta
 *
 * @author Stockwave
 */

@Path("/resposta")
public class RespostaResource {

    /**
     * Retorna uma lista de respostas.
     *
     * @return uma Response contendo a lista de respostas no formato JSON.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarRespostas() {
        RespostaDao repositorio = new RespostaDao();
        ArrayList<Resposta> retorno = repositorio.listarRespostas();
        ResponseBuilder response = Response.ok();
        response.entity(retorno);
        return response.build();
    }

    /**
     * Exibe uma resposta com base em seu nome.
     *
     * @param str_resposta o nome da resposta a ser exibida.
     * @return uma Response contendo a resposta no formato JSON, caso seja encontrada.
     * Caso contrário, retorna uma Response com status 404 e uma mensagem de erro.
     */
    @GET
    @Path("/{str_resposta}")
    public Response exibirRespostaPorNome(@PathParam("str_resposta") String str_resposta) {
        Resposta resposta_buscada = RespostaDao.buscarRespostaPorNome(str_resposta);

        if (resposta_buscada != null) {
            ResponseBuilder response = Response.ok();
            response.entity(resposta_buscada);
            return response.build();
        } else {
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível encontrar a RESPOSTA: " + str_resposta);
            return response.build();
        }
    }

    /**
     * Cadastra uma nova resposta.
     *
     * @param resposta_nova a nova resposta a ser cadastrada.
     * @return uma Response contendo a resposta cadastrada no formato JSON, caso o cadastro seja bem-sucedido.
     * Caso contrário, retorna uma Response com status 500 e uma mensagem de erro.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarResposta(@Valid Resposta resposta_nova) {
        Resposta resp = RespostaService.cadastrarResposta(resposta_nova);
        if (resp != null) {
            final URI respostaUri = UriBuilder.fromResource(RespostaResource.class).path("/resposta/{str_resposta}")
                    .build(resp.getResposta());
            ResponseBuilder response = Response.created(respostaUri);
            response.entity(resp);
            return response.build();
        } else {
            return Response.status(500).entity("Não foi possível cadastrar a nova Resposta").build();
        }
    }

    /**
     * Atualiza uma resposta existente com base em seu nome.
     *
     * @param str_resposta o nome da resposta a ser atualizada.
     * @param resposta o objeto Resposta contendo as informações atualizadas da resposta.
     * @return uma Response contendo a resposta atualizada no formato JSON, caso a atualização seja bem-sucedida.
     * Caso contrário, retorna uma Response com status 404 e uma mensagem de erro.
     */
    @PUT
    @Path("/{str_resposta}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarResposta(@PathParam("str_resposta") String str_resposta, @Valid Resposta resposta) {
        Resposta resposta_nova = RespostaService.atualizarResposta(str_resposta, resposta);
        if (resposta_nova != null) {
            return Response.ok(resposta_nova).build();
        } else {
            return Response.status(404).entity("Não foi possível atualizar a Resposta: " + str_resposta).build();
        }
    }

    /**
     * Deleta uma resposta com base em seu nome.
     *
     * @param str_resposta o nome da resposta a ser deletada.
     * @return uma Response com status 204 (No Content) caso a resposta seja deletada com sucesso.
     * Caso contrário, retorna uma Response com status 404 e uma mensagem de erro.
     */
    @DELETE
    @Path("/{str_resposta}")
    public Response deletarResposta(@PathParam("str_resposta") String str_resposta) {
        if (RespostaService.deletarResposta(str_resposta)) {
            ResponseBuilder response = Response.noContent();
            return response.build();
        } else {
            System.out.println("Não foi possível remover a Resposta: " + str_resposta);
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível remover a Resposta: " + str_resposta);
            return response.build();
        }
    }
}
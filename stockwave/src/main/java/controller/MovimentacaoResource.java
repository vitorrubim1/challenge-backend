package controller;

import java.net.URI;
import java.util.ArrayList;

import dao.MovimentacaoDao;
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
import model.Movimentacao;
import services.MovimentacaoService;

/**
 * Classe que representa o recurso de movimentação do sistema.
 *
 * Esta classe define as operações CRUD para as movimentações, incluindo listar, buscar por ID,
 * atualizar, cadastrar e deletar movimentações.
 *
 * Métodos:
 * - listarMovimentacoes: retorna a lista de todas as movimentações cadastradas.
 * - exibirMovimentacaoPorId: busca e retorna uma movimentação específica com base em seu ID.
 * - atualizarMovimentacao: atualiza uma movimentação existente com base em seu ID.
 * - cadastrarMovimentacao: cadastra uma nova movimentação.
 * - deletarMovimentacao: deleta uma movimentação existente com base em seu ID.
 *
 * Exemplo de uso:
 *
 * MovimentacaoResource movimentacaoResource = new MovimentacaoResource();
 * Response response = movimentacaoResource.listarMovimentacoes();
 * ArrayList&lt;Movimentacao&lt; movimentacoes = (ArrayList&lt;Movimentacao&lt;) response.getEntity();
 *
 * @since 1.0
 * @version 1.0
 *
 * @see dao.MovimentacaoDao
 * @see services.MovimentacaoService
 * @see model.Movimentacao
 * 
 * @author Stockwave
 */
@Path("/movimentacao")
public class MovimentacaoResource {

    /**
     * Retorna a lista de todas as movimentações cadastradas.
     *
     * @return Uma Response contendo a lista de todas as movimentações cadastradas como entidade.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarMovimentacoes() {
        MovimentacaoDao repositorio = new MovimentacaoDao();
        ArrayList<Movimentacao> retorno = repositorio.listarMovimentacoes();
        ResponseBuilder response = Response.ok();
        response.entity(retorno);
        return response.build();
    }

    /**
     * Busca e retorna uma movimentação específica com base em seu ID.
     *
     * @param id_movimentacao O ID da movimentação a ser buscada.
     * @return Uma Response contendo a movimentação encontrada como entidade, se existir,
     *         ou uma Response de status 404 com uma mensagem de erro, caso contrário.
     */
    @GET
    @Path("/{id}")
    public Response exibirMovimentacaoPorId(@PathParam("id") int id_movimentacao) {
        Movimentacao movimentacao_buscada = MovimentacaoDao.buscarMovimentacaoPorId(id_movimentacao);

        if (movimentacao_buscada != null) {
            ResponseBuilder response = Response.ok();
            response.entity(movimentacao_buscada);
            return response.build();
        } else {
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível encontrar a MOVIMENTACAO de id_movimentacao: " + id_movimentacao);
            return response.build();
        }
    }

    /**
     * Atualiza uma movimentação existente com base em seu ID.
     *
     * @param id_movimentacao O ID da movimentação a ser atualizada.
     * @param movimentacao O objeto Movimentacao contendo os dados atualizados.
     * @return Uma Response contendo a movimentação atualizada como entidade, se a atualização for bem-sucedida,
     *         ou uma Response de status 404 com uma mensagem de erro, caso contrário.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarMovimentacao(@PathParam("id") int id_movimentacao, @Valid Movimentacao movimentacao) {
        Movimentacao movimentacao_nova = null;
        movimentacao_nova = MovimentacaoService.atualizarMovimentacao(id_movimentacao, movimentacao);
        if (movimentacao_nova != null) {
            return Response.ok(movimentacao_nova).build();
        } else {
            return Response.status(404)
                    .entity("Não foi possível atualizar a MOVIMENTACAO de id_movimentacao: " + id_movimentacao
                            + ". O id da URI e o ID do objeto JSON devem ser iguais e deve existir no banco de dados.")
                    .build();
        }

    }

    /**
     * Cadastra uma nova movimentação.
     *
     * @param movimentacao_nova O objeto Movimentacao a ser cadastrado.
     * @return Uma Response de status 201 (Created) contendo o URI da movimentação cadastrada
     *         e a própria movimentação como entidade.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarMovimentacao(@Valid Movimentacao movimentacao_nova) {
        Movimentacao resp = MovimentacaoService.cadastrarMovimentacao(movimentacao_nova);
        final URI movimentacaoUri = UriBuilder.fromResource(MovimentacaoResource.class).path("/movimentacao/{id}")
                .build(resp.getId_movimentacao());
        ResponseBuilder response = Response.created(movimentacaoUri);
        response.entity(resp);
        return response.build();
    }

    /**
     * Deleta uma movimentação existente com base em seu ID.
     *
     * @param id_movimentacao O ID da movimentação a ser deletada.
     * @return Uma Response de status 204 se a deleção for bem-sucedida,
     *         ou uma Response de status 404 com uma mensagem de erro, caso contrário.
     */
    @DELETE
    @Path("/{id}")
    public Response deletarMovimentacao(@PathParam("id") int id_movimentacao) {
        if (MovimentacaoService.deletarMovimentacao(id_movimentacao)) {
            ResponseBuilder response = Response.noContent();
            return response.build();
        } else {
            System.out.println("Não foi possível remover a MOVIMENTACAO: " + id_movimentacao);
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível remover a MOVIMENTACAO de id_movimentacao: " + id_movimentacao);
            return response.build();
        }
    }
}
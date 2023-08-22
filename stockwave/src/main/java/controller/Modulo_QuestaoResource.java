package controller;

import java.net.URI;
import java.util.ArrayList;

import dao.Modulo_QuestaoDao;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
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
import model.Modulo_Questao;
import model.Questao;
import services.Modulo_QuestaoService;

/**
 * Classe que representa o recurso de módulo-questão do sistema.
 * 
 * Esta classe define as operações CRUD para os módulos de questão, incluindo listar, buscar por ID,
 * atualizar, cadastrar e deletar módulos de questão.
 * 
 * Métodos:
 * - listarModulo_Questoes: retorna a lista de todos os módulos de questão cadastrados.
 * - exibirModulo_QuestoesPorId: busca e retorna um módulo de questão específico com base em seu ID.
 * - atualizarModulo_Questao: atualiza um módulo de questão existente com base em seu ID e ID da questão.
 * - cadastrarModulo_Questao: cadastra um novo módulo de questão.
 * - deletarModulo_Questao: deleta um módulo de questão existente com base em seu ID e ID da questão.
 * 
 * Exemplo de uso:
 * 
 * Modulo_QuestaoResource moduloQuestaoResource = new Modulo_QuestaoResource();
 * Response response = moduloQuestaoResource.listarModulo_Questoes();
 * ArrayList&lt;Modulo_Questao&lt; moduloQuestoes = (ArrayList&lt;Modulo_Questao&lt;) response.getEntity();
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see dao.Modulo_QuestaoDao
 * @see services.Modulo_QuestaoService
 * @see model.Modulo_Questao
 * 
 * @author Stockwave
 * 
 */
@Path("/modulo_questao")
public class Modulo_QuestaoResource {

    /**
     * Obtém a lista de todos os Módulos de Questão.
     *
     * @return Uma resposta contendo a lista de Módulos de Questão no formato JSON.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarModulo_Questoes() {
        Modulo_QuestaoDao repositorio = new Modulo_QuestaoDao();
        ArrayList<Modulo_Questao> retorno = repositorio.listarModulo_Questoes();
        ResponseBuilder response = Response.ok();
        response.entity(retorno);
        return response.build();
    }

    /**
     * Obtém um Módulo de Questão específico pelo seu ID.
     *
     * @param id_modulo O ID do Módulo de Questão a ser obtido.
     * @return Uma resposta contendo o Módulo de Questão no formato JSON, se encontrado,
     *         ou uma mensagem de erro, se não encontrado.
     */
    @GET
    @Path("/{id}")
    public Response exibirModulo_QuestoesPorId(@PathParam("id") int id_modulo) {
        ArrayList<Modulo_Questao> modulo_questoes_buscado = Modulo_QuestaoDao.buscarModulo_QuestoesPorId(id_modulo);
        
        if (modulo_questoes_buscado != null) {
            JsonArrayBuilder questoesArrayBuilder = Json.createArrayBuilder();

            for (Modulo_Questao modulo_questao : modulo_questoes_buscado) {
            	
                Questao questao = modulo_questao.getQuestao();
                String resposta_questao = questao.getResposta_questao().getResposta();
                
                if (resposta_questao.length() == 1) {
                	JsonObject questaoJsonObject = Json.createObjectBuilder()
                		.add("id_questao", questao.getId_questao())
                        .add("pergunta_questao", questao.getPergunta_questao())
                        .add("tipo_questao", "alternativa")
                        .add("alt_a_questao", questao.getAlt_a_questao())
                        .add("alt_b_questao", questao.getAlt_b_questao())
                        .add("alt_c_questao", questao.getAlt_c_questao())
                        .add("alt_d_questao", questao.getAlt_d_questao())
                        .add("alt_e_questao", questao.getAlt_e_questao())
                        .build();
                    questoesArrayBuilder.add(questaoJsonObject);
                } else {
                	JsonObject questaoJsonObject = Json.createObjectBuilder()
                		.add("id_questao", questao.getId_questao())
                        .add("pergunta_questao", questao.getPergunta_questao())
                        .add("tipo_questao", "completar")
                        .add("alt_a_questao", questao.getAlt_a_questao())
                        .add("alt_b_questao", questao.getAlt_b_questao())
                        .add("alt_c_questao", questao.getAlt_c_questao())
                        .add("alt_d_questao", questao.getAlt_d_questao())
                        .add("alt_e_questao", questao.getAlt_e_questao())
                        .build();
                    questoesArrayBuilder.add(questaoJsonObject);
                }
            }

            JsonObject modulo_questaoJsonObject = Json.createObjectBuilder()
                    .add("id_modulo", modulo_questoes_buscado.get(0).getModulo().getId_modulo())
                    .add("questoes", questoesArrayBuilder.build())
                    .build();

            ResponseBuilder response = Response.ok();
            response.entity(modulo_questaoJsonObject);
            return response.build();
        } else {
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível encontrar o Módulo de Questão com o ID: " + id_modulo);
            return response.build();
        }
    }

    /**
     * Atualiza um Módulo de Questão existente.
     *
     * @param id_modulo O ID do Módulo de Questão a ser atualizado.
     * @param id_questao O ID da Questão a ser atualizada no Módulo.
     * @param modulo_questao O objeto Modulo_Questao contendo os dados atualizados.
     * @return Uma resposta contendo o Módulo de Questão atualizado no formato JSON, se a operação for bem-sucedida,
     *         ou uma mensagem de erro, se a operação falhar.
     */
    @PUT
    @Path("/{id}/{id_questao}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarModulo_Questao(@PathParam("id") int id_modulo, @PathParam("id_questao") int id_questao, @Valid Modulo_Questao modulo_questao) {
        Modulo_Questao modulo_questao_novo = null;
        modulo_questao_novo = Modulo_QuestaoService.atualizarModulo_Questao(id_modulo, id_questao, modulo_questao);
        if (modulo_questao_novo != null) {
            return Response.ok(modulo_questao_novo).build();
        } else {
            return Response.status(404)
                    .entity("Não foi possível atualizar o Módulo de Questão com o ID: " + id_modulo
                            + ". O ID da URI e o ID do objeto JSON devem ser iguais e devem existir no banco de dados.")
                    .build();
        }
    }
    
    /**
     * Cadastra um novo Módulo de Questão.
     *
     * @param modulo_questao_novo O objeto Modulo_Questao contendo os dados do novo Módulo de Questão a ser cadastrado.
     * @return Uma resposta contendo o Módulo de Questão cadastrado no formato JSON, se a operação for bem-sucedida,
     *         ou uma mensagem de erro, se a operação falhar.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarModulo_Questao(@Valid Modulo_Questao modulo_questao_novo) {
        Modulo_Questao resp = Modulo_QuestaoService.cadastrarModulo_Questao(modulo_questao_novo);
        final URI modulo_questaoUri = UriBuilder.fromResource(Modulo_QuestaoResource.class).path("/modulo_questao/{id}")
                .build(resp.getModulo().getId_modulo());
        ResponseBuilder response = Response.created(modulo_questaoUri);
        response.entity(resp);
        return response.build();
    }

    /**
     * Remove um Módulo de Questão.
     *
     * @param id_modulo O ID do Módulo de Questão a ser removido.
     * @param id_questao O ID da Questão a ser removida do Módulo.
     * @return Uma resposta indicando o sucesso ou a falha da operação.
     */
    @DELETE
    @Path("/{id}/{id_questao}")
    public Response deletarModulo_Questao(@PathParam("id") int id_modulo, @PathParam("id_questao") int id_questao) {
        if (Modulo_QuestaoService.deletarModulo_Questao(id_modulo, id_questao)) {
            ResponseBuilder response = Response.noContent();
            return response.build();
        } else {
            System.out.println("Não foi possível remover o Módulo de Questão: " + id_modulo);
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível remover o Módulo de Questão com o ID: " + id_modulo);
            return response.build();
        }
    }
}
package controller;

import java.net.URI;
import java.util.ArrayList;

import dao.QuestaoDao;
import jakarta.json.Json;
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
import model.Questao;
import model.Resposta;
import services.QuestaoService;

/**
 * Classe que representa o recurso de questão do sistema.
 *
 * Esta classe define as operações CRUD para as questões, incluindo listar, buscar por ID,
 * cadastrar, atualizar e deletar questões, além de verificar as respostas das questões.
 *
 * Exemplo de uso:
 *
 * QuestaoResource questaoResource = new QuestaoResource();
 * Response response = questaoResource.listarQuestoes();
 * ArrayList&lt;Questao&lt; questoes = (ArrayList&lt;Questao&lt;) response.getEntity();
 *
 * @since 1.0
 * @version 1.0
 *
 * @see dao.QuestaoDao
 * @see services.QuestaoService
 * @see model.Questao
 * @see model.Resposta
 *
 * @author Stockwave
 *
 */
@Path("/questao")
public class QuestaoResource {

    /**
     * Retorna a lista de todas as questões cadastradas.
     *
     * @return uma Response contendo a lista de questões cadastradas no formato JSON.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarQuestoes() {
        QuestaoDao repositorio = new QuestaoDao();
        ArrayList<Questao> retorno = repositorio.listarQuestoes();
        ResponseBuilder response = Response.ok();
        response.entity(retorno);
        return response.build();
    }

    /**
     * Busca e retorna uma questão específica com base em seu ID.
     *
     * @param id_questao o ID da questão a ser buscada.
     * @return uma Response contendo a questão encontrada no formato JSON, retirando a sua resposta e acrescentando o tipo da questão, caso seja encontrada.
     * Caso contrário, retorna uma Response com status 404 e uma mensagem de erro.
     */
    @GET
    @Path("/{id}")
    public Response exibirQuestaoPorId(@PathParam("id") int id_questao) {
        Questao questao_buscada = QuestaoDao.buscarQuestaoPorId(id_questao);
        JsonObject jsonObject = null;
        
        if (questao_buscada != null) {
            String resposta_questao = questao_buscada.getResposta_questao().getResposta();
            
            if (resposta_questao.length() == 1) {
                jsonObject = Json.createObjectBuilder()
            		.add("id_questao", questao_buscada.getId_questao())
                    .add("pergunta_questao", questao_buscada.getPergunta_questao())
                    .add("tipo_questao", "alternativa")
                    .add("alt_a_questao", questao_buscada.getAlt_a_questao())
                    .add("alt_b_questao", questao_buscada.getAlt_b_questao())
                    .add("alt_c_questao", questao_buscada.getAlt_c_questao())
                    .add("alt_d_questao", questao_buscada.getAlt_d_questao())
                    .add("alt_e_questao", questao_buscada.getAlt_e_questao())
                    .build();
            } else {
                jsonObject = Json.createObjectBuilder()
            		.add("id_questao", questao_buscada.getId_questao())
                    .add("pergunta_questao", questao_buscada.getPergunta_questao())
                    .add("tipo_questao", "completar")
                    .add("alt_a_questao", questao_buscada.getAlt_a_questao())
                    .add("alt_b_questao", questao_buscada.getAlt_b_questao())
                    .add("alt_c_questao", questao_buscada.getAlt_c_questao())
                    .add("alt_d_questao", questao_buscada.getAlt_d_questao())
                    .add("alt_e_questao", questao_buscada.getAlt_e_questao())
                    .build();
            }

            JsonObject responseJsonObject = Json.createObjectBuilder()
                    .add("questao", jsonObject)
                    .build();

            ResponseBuilder response = Response.ok();
            response.entity(responseJsonObject);
            return response.build();
        } else {
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível encontrar a QUESTAO de id_questao: " + id_questao);
            return response.build();
        }
    }


    /**
     * Cadastra uma nova questão.
     *
     * @param questao_nova a nova questão a ser cadastrada.
     * @return uma Response contendo a questão cadastrada no formato JSON, caso o cadastro seja bem-sucedido.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarQuestao(@Valid Questao questao_nova) {
        Questao resp = QuestaoService.cadastrarQuestao(questao_nova);
        final URI questaoUri = UriBuilder.fromResource(QuestaoResource.class).path("/questao/{id}")
                .build(resp.getId_questao());
        ResponseBuilder response = Response.created(questaoUri);
        response.entity(resp);
        return response.build();
    }

    /**
     * Atualiza uma questão existente com base em seu ID.
     *
     * @param id_questao o ID da questão a ser atualizada.
     * @param questao o objeto Questao contendo as informações atualizadas da questão.
     * @return uma Response contendo a questão atualizada no formato JSON, caso a atualização seja bem-sucedida.
     * Caso contrário, retorna uma Response com status 404 e uma mensagem de erro.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarQuestao(@PathParam("id") int id_questao, @Valid Questao questao) {
        Questao questao_nova = null;
        questao_nova = QuestaoService.atualizarQuestao(id_questao, questao);
        if (questao_nova != null) {
            return Response.ok(questao_nova).build();
        } else {
            return Response.status(404)
                    .entity("Não foi possível atualizar a QUESTAO de id_questao: " + id_questao
                            + ". O id da URI e o ID do objeto JSON devem ser iguais e deve existir no banco de dados.")
                    .build();
        }
    }

    /**
     * Deleta uma questão existente com base em seu ID.
     *
     * @param id_questao o ID da questão a ser deletada.
     * @return uma Response com status 204 (No Content) caso a deleção seja bem-sucedida.
     * Caso contrário, retorna uma Response com status 404 e uma mensagem de erro.
     */
    @DELETE
    @Path("/{id}")
    public Response deletarQuestao(@PathParam("id") int id_questao) {
        if (QuestaoService.deletarQuestao(id_questao)) {
            ResponseBuilder response = Response.noContent();
            return response.build();
        } else {
            System.out.println("Não foi possível remover a QUESTAO: " + id_questao);
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível remover a QUESTAO de id_questao: " + id_questao);
            return response.build();
        }
    }
    
    /**
     * Verifica a resposta de uma questão.
     *
     * @param id_questao o ID da questão a ser verificada.
     * @param resposta a resposta a ser verificada.
     * @return uma Response contendo um objeto JSON com a validação da resposta e uma observação associada.
     * Se a resposta for válida, a resposta JSON terá o campo "resposta" como true e o campo "observacao" com a observação associada.
     * Caso contrário, o campo "resposta" será false e o campo "observacao" conterá a observação associada.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}/verificar")
    public Response verificarRespostaQuestao(@PathParam("id") int id_questao, Resposta resposta) {
        boolean resposta_validada = QuestaoService.verificarRespostaQuestao(id_questao, resposta);
        
        String observacao = null;
        
        if (resposta_validada) {
            if (id_questao == 1) {
                observacao = "Uma IPO permite que uma empresa levante capital significativo vendendo ações para investidores públicos. "
                        + "E aumenta a visibilidade da empresa e pode gerar interesse.";
            } else if (id_questao == 24) {
                observacao = "Boa, a realização de um IPO pode representar uma chance para uma empresa obter um montante expressivo de capital e elevar sua visibilidade no mercado, porém, ao mesmo tempo, traz consigo possíveis riscos.";
            } else {
            	observacao = "...";
            }
            
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("resposta", true)
                    .add("observacao", observacao)
                    .build();
            return Response.ok().entity(jsonObject.toString()).build();
        } else {
        	if (id_questao == 1) {
                observacao = "Os principais benefícios para um IPO é tornar a empresa pública abrindo seu capital a novos investidores.";
            } else if (id_questao == 24) {
                observacao = "O IPO pode fornecer uma oportunidade para uma empresa levantar 'capital' significativo e aumentar seu perfil público, mas também pode envolver 'riscos'";
            } else {
            	observacao = "...";
            }
            
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("resposta", false)
                    .add("observacao", observacao)
                    .build();
            return Response.status(200).entity(jsonObject.toString()).build();
        }
    }
}
package controller;

import java.net.URI;
import java.util.ArrayList;

import dao.FuncionarioDao;
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
import model.Funcionario;
import services.FuncionarioService;

/**
 * Classe que representa um recurso REST para manipulação de funcionários.
 * 
 * Esta classe define os endpoints para listar, exibir, cadastrar, atualizar e deletar funcionários.
 * Os métodos são anotados com as respectivas anotações JAX-RS para mapeamento de URLs e definição dos tipos de mídia.
 * 
 * Métodos:
 * - listarFuncionarios: retorna a lista de funcionários cadastrados.
 * - exibirFuncionarioPorId: exibe as informações de um funcionário específico com base no ID fornecido.
 * - cadastrarFuncionario: cadastra um novo funcionário no sistema.
 * - atualizarFuncionario: atualiza as informações de um funcionário existente.
 * - deletarFuncionario: remove um funcionário do sistema.
 * 
 * Exemplo de uso:
 * 
 * FuncionarioResource funcionarioResource = new FuncionarioResource();
 * Response response = funcionarioResource.listarFuncionarios();
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Funcionario
 * @see dao.FuncionarioDao
 * @see services.FuncionarioService
 * @see model.Usuario
 * 
 * @author Stockwave
 * 
 */
@Path("/funcionario")
public class FuncionarioResource {

    /**
     * Retorna a lista de funcionários cadastrados.
     *
     * @return A resposta HTTP contendo a lista de funcionários.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarFuncionarios() {
        FuncionarioDao repositorio = new FuncionarioDao();
        ArrayList<Funcionario> retorno = repositorio.listarFuncionarios();
        ResponseBuilder response = Response.ok();
        response.entity(retorno);
        return response.build();
    }

    /**
     * Exibe as informações de um funcionário específico com base no ID fornecido.
     *
     * @param id_usuario O ID do funcionário a ser exibido.
     * @return A resposta HTTP contendo as informações do funcionário ou uma mensagem de erro caso não seja encontrado.
     */
    @GET
    @Path("/{id}")
    public Response exibirFuncionarioPorId(@PathParam("id") int id_usuario) {
        Funcionario funcionario_buscado = FuncionarioDao.buscarFuncionarioPorId(id_usuario);

        if (funcionario_buscado != null) {
            ResponseBuilder response = Response.ok();
            response.entity(funcionario_buscado);
            return response.build();
        } else {
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível encontrar o FUNCIONARIO de id_usuario: " + id_usuario);
            return response.build();
        }
    }

    /**
     * Atualiza as informações de um funcionário existente.
     *
     * @param id_usuario O ID do funcionário a ser atualizado.
     * @param funcionario O objeto Funcionario contendo as novas informações do funcionário.
     * @return A resposta HTTP contendo o funcionário atualizado ou uma mensagem de erro caso não seja possível atualizar.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarFuncionario(@PathParam("id") int id_usuario, @Valid Funcionario funcionario) {
        Funcionario funcionario_novo = null;
        funcionario_novo = FuncionarioService.atualizarFuncionario(id_usuario, funcionario);
        if (funcionario_novo != null) {
            return Response.ok(funcionario_novo).build();
        } else {
            return Response.status(404)
                    .entity("Não foi possível atualizar o FUNCIONARIO de id_usuario: " + id_usuario
                            + ". O id da URI e o ID do objeto JSON devem ser iguais e deve existir no banco de dados.")
                    .build();
        }
    }

    /**
     * Cadastra um novo funcionário no sistema.
     *
     * @param funcionario_novo O objeto Funcionario contendo as informações do novo funcionário.
     * @return A resposta HTTP contendo o funcionário cadastrado ou uma mensagem de erro caso não seja possível cadastrar.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarFuncionario(@Valid Funcionario funcionario_novo) {
        Funcionario resp = FuncionarioService.cadastrarFuncionario(funcionario_novo);
        final URI funcionarioUri = UriBuilder.fromResource(UsuarioResource.class).path("/usuario/{id}")
                .build(resp.getId_usuario());
        ResponseBuilder response = Response.created(funcionarioUri);
        response.entity(resp);
        return response.build();
    }

    /**
     * Remove um funcionário do sistema.
     *
     * @param id_usuario O ID do funcionário a ser removido.
     * @return A resposta HTTP indicando sucesso ou uma mensagem de erro caso não seja possível remover.
     */
    @DELETE
    @Path("/{id}")
    public Response deletarFuncionario(@PathParam("id") int id_usuario) {
        if (FuncionarioService.deletarFuncionario(id_usuario)) {
            ResponseBuilder response = Response.noContent();
            return response.build();
        } else {
            System.out.println("Não foi possível remover o FUNCIONARIO: " + id_usuario);
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível remover o FUNCIONARIO de id_usuario: " + id_usuario);
            return response.build();
        }
    }
}
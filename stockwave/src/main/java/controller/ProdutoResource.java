package controller;

import java.net.URI;
import java.util.ArrayList;

import dao.ProdutoDao;
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
import model.Produto;
import services.ProdutoService;

/**
 * Classe que representa o recurso de produto do sistema.
 *
 * Esta classe define as operações CRUD para os produtos, incluindo listar, buscar por ID,
 * cadastrar, atualizar e deletar produtos.
 *
 * Exemplo de uso:
 *
 * ProdutoResource produtoResource = new ProdutoResource();
 * Response response = produtoResource.listarProdutos();
 * ArrayList&lt;Produto&lt; produtos = (ArrayList&lt;Produto&lt;) response.getEntity();
 *
 * @since 1.0
 * @version 1.0
 *
 * @see dao.ProdutoDao
 * @see services.ProdutoService
 * @see model.Produto
 * 
 * @author Stockwave
 */
@Path("/produto")
public class ProdutoResource {

    /**
     * Retorna a lista de todos os produtos cadastrados.
     *
     * @return Uma resposta HTTP contendo a lista de produtos cadastrados no formato JSON.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProdutos() {
        ProdutoDao repositorio = new ProdutoDao();
        ArrayList<Produto> retorno = repositorio.listarProdutos();
        ResponseBuilder response = Response.ok();
        response.entity(retorno);
        return response.build();
    }

    /**
     * Busca e retorna um produto específico com base em seu ID.
     *
     * @param id_produto O ID do produto a ser buscado.
     * @return Uma resposta HTTP contendo o produto encontrado no formato JSON, caso seja encontrado.
     *         Retorna uma resposta HTTP com status 404 e uma mensagem de erro, caso contrário.
     */
    @GET
    @Path("/{id}")
    public Response exibirProdutoPorId(@PathParam("id") int id_produto) {
        Produto produto_buscado = ProdutoDao.buscarProdutoPorId(id_produto);

        if (produto_buscado != null) {
            ResponseBuilder response = Response.ok();
            response.entity(produto_buscado);
            return response.build();
        } else {
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível encontrar o PRODUTO de id_produto: " + id_produto);
            return response.build();
        }
    }

    /**
     * Cadastra um novo produto.
     *
     * @param produto_novo O objeto Produto contendo as informações do produto a ser cadastrado.
     * @return Uma resposta HTTP contendo o produto cadastrado no formato JSON, caso o cadastro seja bem-sucedido.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarProduto(@Valid Produto produto_novo) {
        Produto resp = ProdutoService.cadastrarProduto(produto_novo);
        final URI produtoUri = UriBuilder.fromResource(ProdutoResource.class).path("/produto/{id}")
                .build(resp.getId_produto());
        ResponseBuilder response = Response.created(produtoUri);
        response.entity(resp);
        return response.build();
    }

    /**
     * Atualiza um produto existente com base em seu ID.
     *
     * @param id_produto O ID do produto a ser atualizado.
     * @param produto O objeto Produto contendo as informações atualizadas do produto.
     * @return Uma resposta HTTP contendo o produto atualizado no formato JSON, caso a atualização seja bem-sucedida.
     *         Retorna uma resposta HTTP com status 404 e uma mensagem de erro, caso contrário.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarProduto(@PathParam("id") int id_produto, @Valid Produto produto) {
        Produto produto_novo = null;
        produto_novo = ProdutoService.atualizarProduto(id_produto, produto);
        if (produto_novo != null) {
            return Response.ok(produto_novo).build();
        } else {
            return Response.status(404)
                    .entity("Não foi possível atualizar o PRODUTO de id_produto: " + id_produto
                            + ". O id da URI e o ID do objeto JSON devem ser iguais e deve existir no banco de dados.")
                    .build();
        }

    }

    /**
     * Deleta um produto existente com base em seu ID.
     *
     * @param id_produto O ID do produto a ser deletado.
     * @return Uma resposta HTTP vazia com status 204 (No Content) caso a deleção seja bem-sucedida.
     *         Retorna uma resposta HTTP com status 404 e uma mensagem de erro, caso contrário.
     */
    @DELETE
    @Path("/{id}")
    public Response deletarProduto(@PathParam("id") int id_produto) {
        if (ProdutoService.deletarProduto(id_produto)) {
            ResponseBuilder response = Response.noContent();
            return response.build();
        } else {
            System.out.println("Não foi possível remover o PRODUTO: " + id_produto);
            ResponseBuilder response = Response.status(404)
                    .entity("Não foi possível remover o PRODUTO de id_produto: " + id_produto);
            return response.build();
        }
    }
}

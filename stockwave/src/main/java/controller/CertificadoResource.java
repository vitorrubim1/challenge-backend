package controller;

import java.net.URI;
import java.util.ArrayList;

import dao.CertificadoDao;
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
import model.Certificado;
import services.CertificadoService;

/**
 * Classe responsável por representar o recurso REST para manipulação de Certificados.
 * 
 * Esta classe define os métodos HTTP para listar, exibir, cadastrar, atualizar e deletar Certificados.
 * Os Certificados são registros de certificados de conclusão do curso.
 * 
 * Métodos:
 * - listarCertificados: Retorna a lista de todos os Certificados.
 * - exibirCertificadoPorId: Retorna um Certificado específico com base no ID do certificado.
 * - cadastrarCertificado: Cadastra um novo Certificado.
 * - atualizarCertificado: Atualiza um Certificado existente com base no ID do certificado.
 * - deletarCertificado: Deleta um Certificado existente com base no ID do certificado.
 * 
 * Exemplo de uso:
 * 
 * CertificadoResource resource = new CertificadoResource();
 * Response response = resource.listarCertificados();
 * ArrayList&lt;Certificado&lt; certificados = (ArrayList&lt;Certificado&lt;) response.getEntity();
 * for (Certificado certificado : certificados) {
 *     System.out.println(certificado.getId());
 * }
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see model.Certificado
 * @see services.CertificadoService
 * @see dao.CertificadoDao
 * @see model.Aluno
 * @see model.Aluno_Certificado
 * 
 * @author Stockwave
 * 
 */
@Path("/certificado")
public class CertificadoResource {
	
	/**
     * Retorna a lista de todos os Certificados.
     *
     * @return Uma resposta HTTP contendo a lista de Certificados no formato JSON.
     */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCertificados() {
		CertificadoDao repositorio = new CertificadoDao();
		ArrayList<Certificado> retorno = repositorio.listarCertificados();
		ResponseBuilder response = Response.ok();
		response.entity(retorno);
		return response.build();
	}
	
	/**
     * Retorna um Certificado específico com base no ID do certificado.
     *
     * @param id_certificado O ID do certificado.
     * @return Uma resposta HTTP contendo o Certificado no formato JSON, se encontrado.
     *         Retorna um código de status 404 caso contrário.
     */
	@GET
	@Path("/{id}")
	public Response exibirCertificadoPorId(@PathParam("id") int id_certificado) {
		Certificado certificado_buscado = CertificadoDao.buscarCertificadoPorId(id_certificado);

		if (certificado_buscado != null) {
			ResponseBuilder response = Response.ok();
			response.entity(certificado_buscado);
			return response.build();
		} else {
			ResponseBuilder response = Response.status(404)
					.entity("Não foi possível encontrar o CERTIFICADO de id_certificado: " + id_certificado);
			return response.build();
		}
	}
	
	/**
     * Atualiza um Certificado existente com base no ID do certificado.
     *
     * @param id_certificado O ID do certificado a ser atualizado.
     * @param certificado O objeto Certificado contendo as novas informações.
     * @return Uma resposta HTTP contendo o Certificado atualizado no formato JSON, se encontrado.
     *         Retorna um código de status 404 caso contrário.
     */
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarCertificado(@PathParam("id") int id_certificado, @Valid Certificado certificado) {
		Certificado certificado_novo = null;
		certificado_novo = CertificadoService.atualizarCertificado(id_certificado, certificado);
		if (certificado_novo != null) {
			return Response.ok(certificado_novo).build();
		} else {
			return Response.status(404)
					.entity("Não foi possível atualizar o CERTIFICADO de id_certificado: " + id_certificado
							+ ". O id da URI e o ID do objeto JSON devem ser iguais e deve existir no banco de dados.")
					.build();
		}

	}
	
	/**
     * Cadastra um novo Certificado.
     *
     * @param certificado_novo O objeto Certificado a ser cadastrado.
     * @return Uma resposta HTTP contendo o Certificado cadastrado no formato JSON.
     */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarCertificado(@Valid Certificado certificado_novo) {
		Certificado resp = CertificadoService.cadastrarCertificado(certificado_novo);
		final URI certificadoUri = UriBuilder.fromResource(CertificadoResource.class).path("/certificado/{id}")
				.build(resp.getId_certificado());
		ResponseBuilder response = Response.created(certificadoUri);
		response.entity(resp);
		return response.build();
	}
	
	/**
     * Deleta um Certificado existente com base no ID do certificado.
     *
     * @param id_certificado O ID do certificado a ser deletado.
     * @return Uma resposta HTTP com status 204 No Content, se o Certificado for deletado com sucesso.
     *         Retorna um código de status 404 caso contrário.
     */
	@DELETE
	@Path("/{id}")
	public Response deletarCertificado(@PathParam("id") int id_certificado) {
		if (CertificadoService.deletarCertificado(id_certificado)) {
			ResponseBuilder response = Response.noContent();
			return response.build();
		} else {
			System.out.println("Não foi possível remover o CERTIFICADO: " + id_certificado);
			ResponseBuilder response = Response.status(404)
					.entity("Não foi possível remover o CERTIFICADO de id_certificado: " + id_certificado);
			return response.build();
		}
	}
}

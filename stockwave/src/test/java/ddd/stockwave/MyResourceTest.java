package ddd.stockwave;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de teste para MyResource.
 */
public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;

    /**
     * Configuração inicial para cada teste.
     *
     * @throws Exception se ocorrer algum erro durante a configuração.
     */
    @BeforeEach
    public void setUp() throws Exception {
        // Inicia o servidor
        server = Main.startServer();
        // Cria o cliente
        Client c = ClientBuilder.newClient();

        // Descomente a linha a seguir se quiser habilitar
        // o suporte a JSON no cliente (também é necessário descomentar
        // a dependência do módulo jersey-media-json no arquivo pom.xml e no método Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    /**
     * Executa as operações de limpeza após cada teste.
     *
     * @throws Exception se ocorrer algum erro durante a limpeza.
     */
    @SuppressWarnings("deprecation")
	@AfterEach
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Testa se a mensagem "Got it!" é enviada na resposta.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Stockwave!", responseMsg);
    }
}

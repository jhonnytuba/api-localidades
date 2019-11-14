package br.com.jhonatanserafim.localidades.api.v1;

import br.com.jhonatanserafim.localidades.BaseIT;
import com.jayway.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class LocalidadeRestIT extends BaseIT {

    private static final String MOCK_ESTADOS = "[{\"id\":42,\"sigla\":\"SC\",\"nome\":\"Santa Catarina\",\"regiao\":{" +
            "\"id\":4,\"sigla\":\"S\",\"nome\":\"Sul\"}}]";

    private static final String MOCK_MUNICIPIOS = "[{\"id\":4209102,\"nome\":\"Joinville\",\"microrregiao\":{" +
            "\"id\":42008,\"nome\":\"Joinville\",\"mesorregiao\":{\"id\":4202,\"nome\":\"Norte Catarinense\",\"UF\":{" +
            "\"id\":42,\"sigla\":\"SC\",\"nome\":\"Santa Catarina\",\"regiao\":{" +
            "\"id\":4,\"sigla\":\"S\",\"nome\":\"Sul\"}}}}}]";

    @BeforeClass
    public static void beforeClass() {
        mockRetornoComStatus200("/api/v1/localidades/estados", MOCK_ESTADOS);
        mockRetornoComStatus200("/api/v1/localidades/estados/42/municipios", MOCK_MUNICIPIOS);
    }

    @Test
    public void deveExportarLocalidadesEmJson() {
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .get("/api/v1/localidades")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(1))
                .body("[0].idEstado", equalTo(42))
                .body("[0].siglaEstado", equalTo("SC"))
                .body("[0].regiaoNome", equalTo("Sul"))
                .body("[0].nomeCidade", equalTo("Joinville"))
                .body("[0].nomeMesorregiao", equalTo("Norte Catarinense"))
                .body("[0].nomeFormatado", equalTo("Joinville/SC"));
    }

    @Test
    public void deveExportarLocalidadesEmCsv() {
        Response response = given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .get("/api/v1/localidades/csv");

        response.then().statusCode(HttpStatus.OK.value());
        assertThat(response.asString())
                .isEqualTo("idEstado;siglaEstado;regiaoNome;nomeCidade;nomeMesorregiao;nomeFormatado\n" +
                        "42;SC;Sul;Joinville;Norte Catarinense;Joinville/SC\n");
    }

    @Test
    public void deveRetornarIdCidade() {
        Response response = given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .get("/api/v1/localidades/Joinville");

        response.then().statusCode(HttpStatus.OK.value());
        assertThat(response.asString()).isEqualTo("4209102");
    }

    @Test
    public void deveRetornarErroQuandoNaoEncontrarIdCidade() {
        Response response = given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .get("/api/v1/localidades/Florianópolis");

        response.then().statusCode(HttpStatus.NOT_FOUND.value());
    }

    private static void mockRetornoComStatus200(String url, String jsonMock) {
        stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(jsonMock)));
    }
}

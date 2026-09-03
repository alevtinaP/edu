package ru.alfabank.edu.day17;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExtraServeRestTest {

    private static String productId;
    private static String productName;
    private static String adminToken;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://serverest.dev";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        long timestamp = System.currentTimeMillis();
        String email = "admin_" + timestamp + "@qa.com";

        given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "nome": "Админ",
                          "email": "%s",
                          "password": "secret123",
                          "administrador": "true"
                        }
                        """.formatted(email))
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201);

        adminToken =
                given()
                        .contentType(ContentType.JSON)
                        .body("""
                                {
                                  "email": "%s",
                                  "password": "secret123"
                                }
                                """.formatted(email))
                        .when()
                        .post("/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("authorization");
    }

    @Test
    @Order(1)
    void shouldCreateNewProduct() {
        long timestamp = System.currentTimeMillis();
        productName = "TesteVibração " + timestamp;

        Response response =
                given()
                        .header("Authorization", adminToken)
                        .contentType(ContentType.JSON)
                        .body("""
                                {
                                  "nome": "%s",
                                  "preco": 19990,
                                  "descricao": "Produto criado via API",
                                  "quantidade": 5
                                }
                                """.formatted(productName))
                        .when()
                        .post("/produtos")

                        .then()
                        .statusCode(201)
                        .body("message", equalTo("Cadastro realizado com sucesso"))
                        .body("_id", notNullValue())
                        .extract()
                        .response();

        productId = response.path("_id");
    }

    @Test
    @Order(2)
    void shouldGetProductById() {
        given()
                .pathParam("id", productId)
                .when()
                .get("/produtos/{id}")

                .then()
                .statusCode(200)
                .body("nome", equalTo(productName))
                .body("preco", greaterThan(0))
                .body("quantidade", equalTo(5));
    }

    @Test
    @Order(3)
    void shouldGetAllProductsContainNewProduct() {
        given()
                .when()
                .get("/produtos")

                .then()
                .statusCode(200)
                .body("produtos.nome", hasItem(productName));
    }

    @Test
    @Order(4)
    void shouldUpdateProduct() {
        given()
                .header("Authorization", adminToken)
                .pathParam("id", productId)
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "nome": "%s atualizado",
                          "preco": 24990,
                          "descricao": "Produto atualizado via API",
                          "quantidade": 10
                        }
                        """.formatted(productName))
                .when()
                .put("/produtos/{id}")

                .then()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));

        given()
                .pathParam("id", productId)
                .when()
                .get("/produtos/{id}")

                .then()
                .statusCode(200)
                .body("nome", equalTo(productName + " atualizado"));
    }

    @Test
    @Order(5)
    void shouldGetProductByQueryParams() {
        given()
                .queryParam("nome", productName + " atualizado")
                .when()
                .get("/produtos")

                .then()
                .statusCode(200)
                .body("quantidade", greaterThan(0))
                .body("produtos[0].nome", equalTo(productName + " atualizado"));
    }

    @Test
    @Order(6)
    void shouldDeleteProduct() {
        given()
                .header("Authorization", adminToken)
                .pathParam("id", productId)
                .when()
                .delete("/produtos/{id}")

                .then()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));

        given()
                .pathParam("id", productId)
                .when()
                .get("/produtos/{id}")

                .then()
                .statusCode(400)
                .body("message", equalTo("Produto não encontrado"));
    }

    @Test
    @Order(7)
    void shouldNotCreateProductWithoutAuth() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "nome": "Sem Auth",
                          "preco": 1000,
                          "descricao": "Sem token",
                          "quantidade": 1
                        }
                        """)
                .when()
                .post("/produtos")

                .then()
                .statusCode(401)
                .body("message", equalTo("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais"));
    }

    @Test
    @Order(8)
    void shouldNotLoginWithWrongPassword() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "email": "fulano@qa.com",
                          "password": "senha_errada"
                        }
                        """)
                .when()
                .post("/login")

                .then()
                .statusCode(401)
                .body("message", equalTo("Email e/ou senha inválidos"));
    }
}
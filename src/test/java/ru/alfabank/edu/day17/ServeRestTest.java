package ru.alfabank.edu.day17;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServeRestTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://serverest.dev";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @Order(1)
    void shouldGetAllUsers() {
        given()
                .when()
                .get("/usuarios")

                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("quantidade", greaterThan(0))
                .body("usuarios", not(emptyArray()));

    }

    @Test
    @Order(2)
    void shouldFindUserByEmail() {
        String email =
                given()
                        .when()
                        .get("/usuarios")

                        .then()
                        .extract()
                        .path("usuarios[0].email");

        System.out.println("Почта первого пользователя: " + email);

        given()
                .queryParam("email", email)

                .when()
                .get("/usuarios")

                .then()
                .statusCode(200)
                .body("quantidade", equalTo(1))
                .body("usuarios[0].email", equalTo(email));

    }

    private static String userId;
    private static String userEmail;

    @Test
    @Order(3)
    void shouldCreateNewUser() {
        long timestamp = System.currentTimeMillis();
        userEmail = "spy_" + timestamp + "@qa.com";

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body("""
                                {
                                  "nome": "Тайный Покупатель",
                                  "email": "%s",
                                  "password": "secret123",
                                  "administrador": "true"
                                }
                                """.formatted(userEmail))

                        .when()
                        .post("/usuarios")

                        .then()
                        .statusCode(201)
                        .body("message", equalTo("Cadastro realizado com sucesso"))
                        .body("_id", notNullValue())
                        .extract()
                        .response();

        userId = response.path("_id");

        System.out.println("Создался пользователь с id  " + userId);

    }


    @Test
    @Order(4)
    void shouldUpdateUser() {

        System.out.println("Обновляем пользоваеля с id " + userId);

        given()
                .pathParam("id", userId)
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "nome": "Обновлённый Покупатель",
                          "email": "%s",
                          "password": "secret123",
                          "administrador": "false"
                        }
                        """.formatted(userEmail))
                .when()
                .put("/usuarios/{id}")

                .then()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));

    }

    private static String token;

    @Test
    @Order(5)
    void shouldLogin() {
        token =
                given()
                        .contentType(ContentType.JSON)
                        .body("""
                                {
                                  "email": "%s",
                                  "password": "secret123"
                                }
                                """.formatted(userEmail))

                        .when()
                        .post("/login")

                        .then()
                        .statusCode(200)
                        .body("message", equalTo("Login realizado com sucesso"))
                        .body("authorization", notNullValue())
                        .extract()
                        .path("authorization");

        System.out.println("Покупатель залогинился. Токен" + token);

    }

    @Test
    @Order(6)
    void shouldDeleteUser() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .pathParam("id", userId)

                .when()
                .delete("/usuarios/{id}")

                .then()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));

        System.out.println("Пользователь удален");

        given()
                .pathParam("id", userId)

                .when()
                .get("/usuarios/{id}")

                .then()
                .statusCode(400)
                .body("message", equalTo("Usuário não encontrado"));

        System.out.println("Пользователь не найден");
    }

    @Test
    @Order(7)
    void shouldGetAllProducts() {
        given()
                .when()
                .get("/produtos")

                .then()
                .statusCode(200)
                .body("quantidade", greaterThan(0))
                .body("produtos.preco", everyItem(greaterThan(0)))
                .body("produtos.nome", everyItem(notNullValue()))
                .body("produtos.nome", hasItem("Logitech MX Vertical 1787737100273"));

    }
}

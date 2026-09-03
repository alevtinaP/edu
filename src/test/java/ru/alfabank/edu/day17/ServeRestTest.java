package ru.alfabank.edu.day17;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServeRestTest {

    private static final String PASSWORD = "secret123";
    private static final String BASE_EMAIL_DOMAIN = "@qa.com";

    private static String userId;
    private static String userEmail;
    private static String token;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://serverest.dev";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    private static String uniqueEmail(String prefix) {
        return prefix + "_" + System.currentTimeMillis() + BASE_EMAIL_DOMAIN;
    }

    @Test
    @Order(1)
    @DisplayName("Список пользователей непустой и в формате JSON")
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
    @DisplayName("Поиск пользователя по email возвращает одного пользователя")
    void shouldFindUserByEmail() {
        String email = given()
                .when()
                .get("/usuarios")

                .then()
                .statusCode(200)
                .extract()
                .path("usuarios[0].email");

        given()
                .queryParam("email", email)

                .when()
                .get("/usuarios")

                .then()
                .statusCode(200)
                .body("quantidade", equalTo(1))
                .body("usuarios[0].email", equalTo(email));
    }

    @Test
    @Order(3)
    @DisplayName("Создание нового пользователя с уникальным email")
    void shouldCreateNewUser() {
        userEmail = uniqueEmail("spy");

        Response response = given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "nome": "Secret Buyer",
                          "email": "%s",
                          "password": "%s",
                          "administrador": "true"
                        }
                        """.formatted(userEmail, PASSWORD))

                .when()
                .post("/usuarios")

                .then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", notNullValue())
                .extract()
                .response();

        userId = response.path("_id");
    }

    @Test
    @Order(4)
    @DisplayName("Обновление данных пользователя")
    void shouldUpdateUser() {
        given()
                .pathParam("id", userId)
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "nome": "Updated Buyer",
                          "email": "%s",
                          "password": "%s",
                          "administrador": "false"
                        }
                        """.formatted(userEmail, PASSWORD))

                .when()
                .put("/usuarios/{id}")

                .then()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));
    }

    @Test
    @Order(5)
    @DisplayName("Логин пользователя возвращает токен")
    void shouldLogin() {
        token = given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "email": "%s",
                          "password": "%s"
                        }
                        """.formatted(userEmail, PASSWORD))

                .when()
                .post("/login")

                .then()
                .statusCode(200)
                .body("message", equalTo("Login realizado com sucesso"))
                .body("authorization", notNullValue())
                .extract()
                .path("authorization");
    }

    @Test
    @Order(6)
    @DisplayName("Удаление пользователя и проверка его отсутствия")
    void shouldDeleteUser() {
        given()
                .header("Authorization", token)
                .pathParam("id", userId)

                .when()
                .delete("/usuarios/{id}")

                .then()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));

        given()
                .pathParam("id", userId)

                .when()
                .get("/usuarios/{id}")

                .then()
                .statusCode(400)
                .body("message", equalTo("Usuário não encontrado"));
    }

    @Test
    @Order(7)
    @DisplayName("Количество товаров соответствует метаданным и данные валидны")
    void shouldGetAllProducts() {
        int total = given()
                .when()
                .get("/produtos")

                .then()
                .statusCode(200)
                .extract()
                .path("quantidade");

        given()
                .when()
                .get("/produtos")

                .then()
                .statusCode(200)
                .body("quantidade", equalTo(total))
                .body("produtos", hasSize(total))
                .body("produtos.preco", everyItem(greaterThan(0)))
                .body("produtos.nome", everyItem(notNullValue()));
    }
}
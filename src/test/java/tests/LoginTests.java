package tests;

import io.qameta.allure.Owner;
import models.lombok.UserModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static specs.Spec.*;

@Owner("renat.khairullin")
public class LoginTests {
    @Test
    @DisplayName("Успешная авторизация пользователя")
    void successLoginTest() {

        UserModel user = UserModel.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();

        given()
                .spec(request)
                .body(user)
                .when()
                .post("/login")
                .then()
                .spec(response200Code)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Авторизация пользователя без пароля")
    void unsuccessLoginTest() {

        UserModel user = UserModel.builder()
                .email("peter@klaven")
                .build();

        given()
                .spec(request)
                .body(user)
                .when()
                .post("/login")
                .then()
                .spec(response400Code)
                .body("error", equalTo("Missing password"));
    }
}

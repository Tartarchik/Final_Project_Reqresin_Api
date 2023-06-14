package tests;

import io.qameta.allure.Owner;
import models.lombok.UserModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static specs.Spec.request;
import static specs.Spec.*;

@Owner("renat.khairullin")
public class RegisterTests {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    void successRegisterTest() {

        UserModel user = UserModel.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        given()
                .spec(request)
                .body(user)
                .when()
                .post("/register")
                .then()
                .spec(response200Code)
                .body("token", is(notNullValue()));
    }

    @Test
    @DisplayName("Регистрация пользователя без пароля")
    void unSuccessRegisterTest() {
        UserModel user = UserModel.builder()
                .email("eve.holt@reqres.in")
                .build();

        given()
                .spec(request)
                .body(user)
                .when()
                .post("/register")
                .then()
                .spec(response400Code)
                .body("error", equalTo("Missing password"));
    }
}

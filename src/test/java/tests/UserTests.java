package tests;

import io.qameta.allure.Owner;
import models.lombok.UserCreateModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specs.Spec.*;

@Owner("renat.khairullin")
public class UserTests {
    @Test
    @DisplayName("Получить список пользователей")
    void getUsersListTest() {

        given()
                .spec(request)
                .when()
                .get("/users?page=2")
                .then()
                .spec(response200Code)
                .body("total", equalTo(12));
    }

    @Test
    @DisplayName("Получить пользователей")
    void getUserTest() {

        given()
                .spec(request)
                .when()
                .get("/users/2")
                .then()
                .spec(response200Code)
                .body("data.email", equalTo("janet.weaver@reqres.in"));
    }

    @Test
    @DisplayName("Создать пользователя")
    void createUserTest() {
        UserCreateModel creatUser = UserCreateModel.builder()
                .name("morpheus")
                .job("leader")
                .build();

        given()
                .spec(request)
                .body(creatUser)
                .when()
                .post("/users")
                .then()
                .spec(response201Code)
                .body("name", equalTo("morpheus"));
    }
}

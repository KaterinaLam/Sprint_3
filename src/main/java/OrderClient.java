import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends ScooterRestClient {

    public static final String ORDER_PATH = "api/v1/orders";

    public ValidatableResponse create(Order information) {
        return given()
                .spec(getBaseSpec())
                .body(information)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    public ValidatableResponse delete(int trackNumber) {
        return given()
                .spec(getBaseSpec())
                .body(trackNumber)
                .when()
                .put(ORDER_PATH + "/cancel")
                .then();
    }

    public ValidatableResponse orders() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }
}
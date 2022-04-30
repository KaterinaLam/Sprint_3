import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends ScooterRestClient {

    public static final String COURIER_PATH = "api/v1/courier/";

    public ValidatableResponse login(CourierLogin credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(COURIER_PATH + "login")
                .then();
    }

    public ValidatableResponse create(Courier credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(COURIER_PATH)
                .then();
    }


    public ValidatableResponse delete(int courierId) {
        return given()
                .spec(getBaseSpec())
                .body(courierId)
                .when()
                .delete(COURIER_PATH + ":" + courierId)
                .then();
    }
}
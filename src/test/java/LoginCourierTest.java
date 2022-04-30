import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginCourierTest {

    CourierClient courierClient;
    Courier courier;
    int courierId;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandom();
        courierClient.create(courier);
    }

    @After
    public void tearDown(){
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Курьер может авторизоваться, заполнив все обязательные поля")
    public void courierCanLoginWithValidCredentials(){
        ValidatableResponse loginResponse = courierClient.login(new CourierLogin(courier.getLogin(), courier.getPassword()));
        int statusCode = loginResponse.extract().statusCode();
        courierId = loginResponse.extract().path("id");

        assertThat("Courier cannot login", statusCode, equalTo(SC_OK));
        assertThat("Courier ID is incorrect", courierId, is(not(0)));
    }

    @Test
    @DisplayName("Курьер не может авторизоваться, не заполнив Логин")
    public void courierCantLoginWithoutLogin(){
        ValidatableResponse loginResponse = courierClient.login(new CourierLogin("", courier.getPassword()));
        int statusCode = loginResponse.extract().statusCode();
        String message = loginResponse.extract().path("message");

        assertThat("Login should be filled", statusCode, equalTo(SC_BAD_REQUEST));
        assertThat(message, equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Курьер не может авторизоваться, не заполнив Пароль")
    public void courierCantLoginWithoutPassword(){
        ValidatableResponse loginResponse = courierClient.login(new CourierLogin(courier.getLogin(), ""));
        int statusCode = loginResponse.extract().statusCode();
        String message = loginResponse.extract().path("message");

        assertThat("Password should be filled", statusCode, equalTo(SC_BAD_REQUEST));
        assertThat(message, equalTo("Недостаточно данных для входа"));

        ValidatableResponse loginResponseForDelete = courierClient.login(new CourierLogin(courier.getLogin(), courier.getPassword()));
        courierId = loginResponseForDelete.extract().path("id");
    }

    @Test
    @DisplayName("Курьер не может авторизоваться, введя некорретный пароль")
    public void courierCantLoginWithNonExistentPairLoginAndPassword(){
        ValidatableResponse loginResponse = courierClient.login(new CourierLogin(courier.getLogin() + 232, courier.getPassword()));
        int statusCode = loginResponse.extract().statusCode();
        String message = loginResponse.extract().path("message");

        assertThat("Non-existent login", statusCode, equalTo(SC_NOT_FOUND));
        assertThat(message, equalTo("Учетная запись не найдена"));
    }
}
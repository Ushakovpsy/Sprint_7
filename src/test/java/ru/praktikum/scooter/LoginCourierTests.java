package ru.praktikum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.client.CourierClient;
import ru.praktikum.scooter.model.CourierCredentials;
import ru.praktikum.scooter.util.CourierGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginCourierTests {

    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Курьер может авторизоваться")
    @Description("Запрос возвращает id и статус код 200")
    public void courierCanLogin() {
        //логинимся под зарегестированным курьером
        ValidatableResponse loginResponse = courierClient
                .loginCourier(CourierCredentials.from(CourierGenerator.getRegisteredCourier()));

        //записываем id курьера
        courierId = loginResponse.extract().path("id");
        //проверяем, что id что не равен нулю
        assertNotNull("Id is null", courierId);
    }

    @Test
    @DisplayName("Заполнены не все обязательные поля при авторизации")
    @Description("Запрос возвращает ошибку 400")
    public void loginCourierEmptyPassword() {
        //логинимся без пароля
        ValidatableResponse loginResponse = courierClient
                .loginCourier(CourierCredentials.from(CourierGenerator.getDefaultWithoutPassword()));

        int loginStatusCode = loginResponse.extract().statusCode();
        String message = loginResponse.extract().path("message");

        //проверка ответа на запрос
        assertEquals("Некорректный статус код", 400, loginStatusCode);
        assertEquals("Недостаточно данных для входа", message);
    }

    @Test
    @DisplayName("Авторизация под несуществующим пользователем")
    @Description("Запрос возвращает ошибку 404")
    public void wrongLoginCourier() {
        //логинимся под несуществующим пользователем
        ValidatableResponse loginResponse = courierClient
                .loginCourier(CourierCredentials.from(CourierGenerator.getWrong()));
        int loginStatusCode = loginResponse.extract().statusCode();

        String message = loginResponse.extract().path("message");
        //проверка ответа на запрос
        assertEquals("Некорректный статус код", 404, loginStatusCode);
        assertEquals("Учетная запись не найдена", message);
    }
}
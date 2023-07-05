package ru.praktikum.scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.client.OrderClient;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class GetOrdersTests {

    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient  = new OrderClient();
    }

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Проверяем, что в теле ответа возвращается список заказов")
    public void getListOrdersTest() {
        ValidatableResponse response = orderClient.allOrders();

        ArrayList<String> orderBody = response.extract().path("orders");
        boolean isNotEmpty = orderBody!=null && !orderBody.isEmpty();

        assertTrue("Orders is empty", isNotEmpty);
    }
}
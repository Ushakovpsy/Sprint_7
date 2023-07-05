package ru.praktikum.scooter.util;

import ru.praktikum.scooter.model.Order;

public class OrderGenerator {

    public static Order getDefault(String[] color) {
        return new Order("oleg",
                "dobry",
                "moscow",
                4,
                "+7 800 355 35 35",
                3,
                "2023-07-05",
                "test",
                color);
    }
}
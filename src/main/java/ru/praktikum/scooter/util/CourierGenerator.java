package ru.praktikum.scooter.util;

import ru.praktikum.scooter.model.Courier;

public class CourierGenerator {
    public static Courier getDefault() {
        return new Courier("sasha007", "ooo007", "sasha");
    }

    public static Courier getDefaultWithoutPassword() {
        return new Courier("sasha007", "", "den07");
    }

    public static Courier getRegisteredCourier() {
        return new Courier("002qwerty", "456098", "bob");
    }

    public static Courier getWrong() {
        return new Courier("1122polo", "00000", "haha");
    }
}

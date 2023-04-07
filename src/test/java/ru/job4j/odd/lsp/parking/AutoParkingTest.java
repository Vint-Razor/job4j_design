package ru.job4j.odd.lsp.parking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AutoParkingTest {
    private static final int CARS_CAPACITY = 10;
    private static final int TRUCKS_CAPACITY = 5;
    private static Parking autoParking = new AutoParking(CARS_CAPACITY, TRUCKS_CAPACITY);
    private static Auto car = new Car("123");
    private static Auto car2 = new Car("234");
    private static Auto truck = new Truck("2d3");
    private static Auto truck2 = new Truck("4d3");
    private static Auto truck3 = new Truck("5d3");

    @BeforeEach
    void before() {
        autoParking.addListAuto(car);
        autoParking.addListAuto(car2);
        autoParking.addListAuto(truck);
        autoParking.addListAuto(truck2);
    }

    @Test
    void whenAdd4AutoThenInList4() {
        Set<Auto> expected = autoParking.getListAuto();
        assertThat(expected).contains(car)
                .contains(car2)
                .contains(truck)
                .contains(truck2);
    }

    @Test
    void whenDeletedAutoThenNoAuto() {
        autoParking.deleteAuto(car);
        assertThat(autoParking.getListAuto()).doesNotContain(car)
                .contains(car2)
                .contains(truck)
                .contains(truck2);
    }

    @Test
    void whenDeletedExistAutoThenTrue() {
        assertThat(autoParking.deleteAuto(car)).isTrue();
    }

    @Test
    void whenAddMoreSize1ThenTruck() {
        autoParking.addListAuto(truck3);
        assertThat(autoParking.getListAuto()).contains(truck3);
    }

    @AfterEach
    void after() {
        autoParking.getListAuto().clear();
    }
}
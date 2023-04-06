package ru.job4j.odd.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AutoParkingTest {

    @Test
    void checkAutoParking() {
        Parking autoParking = new AutoParking(10, 5);
        Auto car = new Car();
        Auto car2 = new Car();
        Auto truck = new Truck();
        Auto truck2 = new Truck();
        autoParking.addListAuto(car);
        autoParking.addListAuto(car2);
        autoParking.addListAuto(truck);
        autoParking.addListAuto(truck2);
        int actual = car.getSize() + car2.getSize() + truck.getSize() + truck2.getSize();
        int expected = autoParking.getOccupiedPlaces();
        assertThat(expected).isEqualTo(actual);
    }
}
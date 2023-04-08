package ru.job4j.odd.lsp.parking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class AutoParkingTest {
    private final int carsCapacity = 10;
    private final int trucksCapacity = 5;
    private final Parking autoParking = new AutoParking(carsCapacity, trucksCapacity);
    private final Auto car = new Car("car1", 1);
    private final Auto car2 = new Car("car2", 1);
    private final Auto truck = new Truck("truck1", 2);
    private final Auto truck2 = new Truck("truck2", 3);

    @BeforeEach
    void before() {
        autoParking.addAuto(car);
        autoParking.addAuto(car2);
        autoParking.addAuto(truck);
        autoParking.addAuto(truck2);
    }

    @Test
    void whenAdd4AutoThenInList4() {
        List<Auto> expected = autoParking.getListAuto();
        assertThat(expected).containsExactlyInAnyOrder(car, car2, truck, truck2);
    }

    @Test
    void whenDeletedAutoThenNoAuto() {
        autoParking.deleteAuto(car);
        assertThat(autoParking.getListAuto()).containsExactlyInAnyOrder(car2, truck, truck2);
    }

    @Test
    void whenDeletedExistAutoThenTrue() {
        assertThat(autoParking.deleteAuto(car)).isTrue();
    }


    @Test
    void checkDeleteAllAutos() {
        autoParking.deleteAllAutos();
        assertThat(autoParking.getListAuto()).isEmpty();
    }

    @Test
    void whenAddInFullParkingThenFalse() {
        int carsCapacity = 2;
        int trucksCapacity = 0;
        Parking autoParking = new AutoParking(carsCapacity, trucksCapacity);
        Auto car = new Car("123");
        Auto car2 = new Car("234");
        Auto truck3 = new Truck("5d3");
        autoParking.addAuto(car);
        autoParking.addAuto(car2);
        assertThat(autoParking.addAuto(truck3)).isFalse();
    }

    @Test
    void whenAddTruckInFullButFreeCarThenAdded() {
        int carsCapacity = 4;
        int trucksCapacity = 0;
        Parking autoParking = new AutoParking(carsCapacity, trucksCapacity);
        Auto car = new Car("123");
        Auto truck3 = new Truck("5d3");
        autoParking.addAuto(car);
        assertThat(autoParking.addAuto(truck3)).isTrue();
    }

    @Test
    void whenAddTrackSizeLess2ThenExcept() {
        Auto wrongTruck = new Truck("wrong truck", 1);
        assertThat(autoParking.addAuto(wrongTruck)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenAutoIsParkedThenTrue() {
        boolean expected = car.isParked();
        assertThat(expected).isTrue();
    }

    @AfterEach
    void after() {
        autoParking.deleteAllAutos();
    }
}
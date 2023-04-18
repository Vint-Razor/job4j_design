package ru.job4j.odd.lsp.storage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {
    private static LocalDate now = LocalDate.now();
    private static Store trash;
    private static Store warehouse;
    private static Store shop;
    private static List<Store> storeList = new ArrayList<>();
    private static Milk milkExp75 = new Milk("Milk", LocalDate.now().plusDays(75),
            LocalDate.now().minusDays(25), 56.0d, 20);
    private static Milk milkExp25 = new Milk("Milk", LocalDate.now().plusDays(25),
            LocalDate.now().minusDays(75), 56.0d, 20);
    private static Milk milkExp80 = new Milk("Milk", LocalDate.now().plusDays(20),
            LocalDate.now().minusDays(80), 56.0d, 20);
    private static Bread breadExp24 = new Bread("Bread", LocalDate.now().plusDays(76),
            LocalDate.now().minusDays(24), 45.0d, 20);
    private static Juice juiceExpired = new Juice("Juice", LocalDate.now().minusDays(5),
            LocalDate.now().minusDays(35), 100.0d, 15);

    @BeforeAll
    static void before() {
        CalcExpiration calc = new CalcExpiration(now);
        trash = new Trash(calc);
        shop = new Shop(calc);
        warehouse = new Warehouse(calc);
        storeList = List.of(trash, shop, warehouse);
        ControlQuality quality = new ControlQuality(storeList);
        quality.checkFood(milkExp75);
        quality.checkFood(milkExp25);
        quality.checkFood(milkExp80);
        quality.checkFood(breadExp24);
        quality.checkFood(juiceExpired);
    }

    @Test
    void whereExpirationDateMore24PerThereShop() {
        List<Food> expected = shop.getFoodList();
        assertThat(expected).containsExactlyInAnyOrder(milkExp25, milkExp75, milkExp80);
    }

    @Test
    void whereExpirationDateMore75PerThereShopAndChangePrice() {
        double newPrice = 44.8d;
        assertThat(milkExp80.getPrice()).isEqualTo(newPrice);
    }

    @Test
    void whereExpirationLess25PerThereWarehouse() {
        assertThat(warehouse.getFoodList()).containsExactlyInAnyOrder(breadExp24);
    }

    @Test
    void wereExpirationDateOutThereTrash() {
        assertThat(trash.getFoodList()).containsExactlyInAnyOrder(juiceExpired);
    }

    @Test
    @Deprecated
    void chekResort() {
        LocalDate newNow = LocalDate.now().plusDays(70);
        CalcExpiration calc = new CalcExpiration(now);
        ControlQuality quality = new ControlQuality(storeList);
        quality.checkFood(milkExp75);
        calc.setNow(newNow);
        //quality.resort();
        List<Food> expected = shop.getFoodList();
        assertThat(expected).contains(milkExp80);
    }
}
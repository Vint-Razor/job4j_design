package ru.job4j.odd.lsp.storage;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {
    private CalcExpiration calc = new CalcExpiration(LocalDate.now());
    private Store trash;
    private Store warehouse;
    private Store shop;
    private List<Store> storeList = new ArrayList<>();
    private  Milk milkExp75 = new Milk("Milk75", LocalDate.now().minusDays(75),
            LocalDate.now().plusDays(25), 56.0d, 20);
    private  Milk milkExp25 = new Milk("Milk25", LocalDate.now().minusDays(25),
            LocalDate.now().plusDays(75), 56.0d, 20);
    private  Milk milkExp80 = new Milk("Milk80", LocalDate.now().minusDays(80),
            LocalDate.now().plusDays(20), 56.0d, 20);
    private  Bread breadExp24 = new Bread("Bread24", LocalDate.now().minusDays(24),
            LocalDate.now().plusDays(76), 45.0d, 20);
    private  Juice juiceExpired = new Juice("Juice", LocalDate.now().minusDays(5),
            LocalDate.now().minusDays(35), 100.0d, 15);

    @BeforeEach
    void before() {
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

    @AfterEach
    void after() {
        storeList.forEach(store -> store.getFoodList().clear());
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
    void checkResort() {
        LocalDate newNow = LocalDate.now().plusDays(70);
        ControlQuality quality = new ControlQuality(storeList);
        calc.setNow(newNow);
        quality.resort();
        assertThat(trash.getFoodList()).containsExactlyInAnyOrder(juiceExpired, milkExp80, milkExp75);
        assertThat(shop.getFoodList()).containsExactlyInAnyOrder(milkExp25, breadExp24);
    }
}
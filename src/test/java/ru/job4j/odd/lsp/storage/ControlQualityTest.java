package ru.job4j.odd.lsp.storage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {
    private static Store trash = new Trash();
    private static Store warehouse = new Warehouse();
    private static Store shop = new Shop();
    private static Map<String, Store> storeMap = new HashMap<>();
    private static ControlQuality quality = new ControlQuality(storeMap);
    private static Milk milkExp75 = new Milk("Milk", LocalDate.now().plusDays(75)
            , LocalDate.now().minusDays(25), 56.0d, 20);
    private static Milk milkExp25 = new Milk("Milk", LocalDate.now().plusDays(25)
            , LocalDate.now().minusDays(75), 56.0d, 20);
    private static Milk milkExp80 = new Milk("Milk", LocalDate.now().plusDays(20)
            , LocalDate.now().minusDays(80), 56.0d, 20);
    private static Bread breadExp24 = new Bread("Bread", LocalDate.now().plusDays(76)
            , LocalDate.now().minusDays(24), 45.0d, 20);
    private static Juice juiceExpired = new Juice("Juice", LocalDate.now().minusDays(5)
            , LocalDate.now().minusDays(35), 100.0d, 15);

    @BeforeAll
    static void before() {
        storeMap.put(trash.getName(), trash);
        storeMap.put(shop.getName(), shop);
        storeMap.put(warehouse.getName(), warehouse);
        quality.checkFood(milkExp75);
        quality.checkFood(milkExp25);
        quality.checkFood(milkExp80);
        quality.checkFood(breadExp24);
        quality.checkFood(juiceExpired);
    }

    @Test
    void whereExpirationDateMore24PerThereShop() {
        assertThat(shop.getFoodList()).contains(milkExp25)
                .contains(milkExp75)
                .contains(milkExp80)
                .doesNotContain(breadExp24);
    }

    @Test
    void whereExpirationDateMore75PerThereShopAndChangePrice() {
        double newPrice = 44.8d;
        assertThat(milkExp80.getPrice()).isEqualTo(newPrice);
    }

    @Test
    void whereExpirationLess25PerThereWarehouse() {
        assertThat(warehouse.getFoodList()).contains(breadExp24)
                .doesNotContain(milkExp25)
                .doesNotContain(milkExp80);
    }

    @Test
    void wereExpirationDateOutThereTrash() {
        assertThat(trash.getFoodList()).contains(juiceExpired)
                .doesNotContain(milkExp25)
                .doesNotContain(milkExp80)
                .doesNotContain(breadExp24);
    }
}
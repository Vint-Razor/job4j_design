package ru.job4j.odd.lsp.storage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {
    private static Store trash = new Trash();
    private static Store warehouse = new Warehouse();
    private static Store shop = new Shop();
    private static Map<String, Store> storeMap = new HashMap<>();
    private final ControlQuality quality = new ControlQuality(storeMap);

    @BeforeAll
    static void before() {
        storeMap.put(trash.getName(), trash);
        storeMap.put(shop.getName(), shop);
        storeMap.put(warehouse.getName(), warehouse);
    }

    @Test
    void whereExpirationDate50PerThereShop() {
        Milk milk = new Milk("Milk", LocalDate.now().plusDays(5)
                , LocalDate.now().minusDays(5), 56.0d, 20);
        quality.checkFood(milk);
        assertThat(shop.getFoodList()).contains(milk);
    }

    @Test
    void whereExpirationDate75PerThereShop() {
        Milk milk = new Milk("Milk", LocalDate.now().plusDays(75)
                , LocalDate.now().minusDays(25), 56.0d, 20);
        quality.checkFood(milk);
        assertThat(shop.getFoodList()).contains(milk);
    }

    @Test
    void whereExpirationDate25PerThereShop() {
        Milk milk = new Milk("Milk", LocalDate.now().plusDays(25)
                , LocalDate.now().minusDays(75), 56.0d, 20);
        quality.checkFood(milk);
        assertThat(shop.getFoodList()).contains(milk);
    }

    @Test
    void whereExpirationDate80PerThereShop() {
        Milk milk = new Milk("Milk", LocalDate.now().plusDays(20)
                , LocalDate.now().minusDays(80), 56.0d, 20);
        quality.checkFood(milk);
        assertThat(shop.getFoodList()).contains(milk);
    }

    @Test
    void whereExpirationDate80PerThereChangePrice() {
        double newPrice = 44.8;
        Milk milk = new Milk("Milk", LocalDate.now().plusDays(20)
                , LocalDate.now().minusDays(80), 56.0d, 20);
        quality.checkFood(milk);
        assertThat(milk.getPrice()).isEqualTo(newPrice);
    }
}
package ru.job4j.odd.lsp.storage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TrashTest {

    @Test
    void wereExpirationDateOutThereTrash() {
        Trash trash = new Trash();
        Shop shop = new Shop();
        Warehouse warehouse = new Warehouse();
        Map<String, Store> storeMap = new HashMap<>();
        storeMap.put(trash.getName(), trash);
        storeMap.put(shop.getName(), shop);
        storeMap.put(warehouse.getName(), warehouse);
        Food milk = new Milk("Milk", LocalDate.of(2023, Calendar.MARCH, 10)
                , LocalDate.of(2023, Calendar.MARCH, 6), 56.0d, 20);
        ControlQuality controlQuality = new ControlQuality(storeMap);
        controlQuality.checkFood(milk);
        assertThat(trash.getFoodList()).contains(milk);
    }
}
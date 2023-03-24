package ru.job4j.odd.lsp.storage;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TrashTest {

    @Test
    void wereExpirationDateOutThereTrash() {
        Trash trash = new Trash();
        Shop shop = new Shop();
        Warehouse warehouse = new Warehouse();
        List<Store> listStore = List.of(trash, shop, warehouse);
        Food milk = new Milk("Milk", LocalDate.of(2023, Calendar.MARCH, 10)
                , LocalDate.of(2023, Calendar.MARCH, 6), 56.0d);
        ControlQuality controlQuality = new ControlQuality(listStore);
        controlQuality.checkFood(milk);
        assertThat(trash.getFoodList()).contains(milk);
    }
}
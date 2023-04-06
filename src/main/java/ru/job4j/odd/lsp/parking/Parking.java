package ru.job4j.odd.lsp.parking;

import java.util.List;

public interface Parking {

    List<Auto> getListAuto();

    void addListAuto(Auto auto);

    void deleteAuto(Auto auto);
}

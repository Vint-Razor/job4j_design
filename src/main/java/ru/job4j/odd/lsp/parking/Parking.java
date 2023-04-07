package ru.job4j.odd.lsp.parking;

import java.util.List;

public interface Parking {

    boolean addAuto(Auto auto);

    boolean deleteAuto(Auto auto);

    List<Auto> getListAuto();

    void deleteAllAutos();
}

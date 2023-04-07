package ru.job4j.odd.lsp.parking;

import java.util.List;
import java.util.Set;

public interface Parking {

    void addListAuto(Auto auto);

    boolean deleteAuto(Auto auto);

    Set<Auto> getListAuto();
}

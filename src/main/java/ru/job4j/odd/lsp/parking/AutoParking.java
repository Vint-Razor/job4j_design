package ru.job4j.odd.lsp.parking;

import java.util.LinkedList;
import java.util.List;

public class AutoParking implements Parking {

    private final int cars;
    private final int tracks;
    private List<Auto> autoList = new LinkedList<>();

    public AutoParking(int cars, int tracks) {
        this.cars = cars;
        this.tracks = tracks;
    }

    @Override
    public List<Auto> getListAuto() {
        return autoList;
    }

    @Override
    public void addListAuto(Auto auto) {
        autoList.add(auto);
    }

    @Override
    public void deleteAuto(Auto auto) {
        autoList.remove(auto);
    }
}

package ru.job4j.odd.lsp.parking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class AutoParking implements Parking {
    private static final int CAR_SIZE = 1;
    private final int capacityCars;
    private final int capacityTracks;
    private final List<Auto> carList = new LinkedList<>();
    private final List<Auto> truckList = new LinkedList<>();
    private final Predicate<Auto> compareSize = a -> a.getSize() > CAR_SIZE;


    public AutoParking(int cars, int tracks) {
        validator(cars, tracks);
        this.capacityCars = cars;
        this.capacityTracks = tracks;
    }

    private void validator(int cars, int tracks) {
        if (cars < 0 || tracks < 0) {
            throw new IllegalArgumentException("аргумент не может быть меньше нуля");
        }
    }

    @Override
    public Set<Auto> getListAuto() {
        Set<Auto> set = new HashSet<>(carList);
        set.addAll(truckList);
        return set;
    }

    @Override
    public void addListAuto(Auto auto) {
        if (isTruck(auto)) {
            truckList.add(auto);
        } else {
            carList.add(auto);
        }
    }

    @Override
    public boolean deleteAuto(Auto auto) {
        return getListAuto().remove(auto);
    }

    private boolean isTruck(Auto auto) {
        return compareSize.test(auto);
    }
}

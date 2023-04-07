package ru.job4j.odd.lsp.parking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class AutoParking implements Parking {
    private static final int CAR_SIZE = 1;
    private int freeSpaceCar;
    private int freeSpaceTruck;
    private final Set<Auto> carSet = new HashSet<>();
    private final Set<Auto> truckSet = new HashSet<>();
    private final List<Set<Auto>> allAutoSets = List.of(carSet, truckSet);
    private final Predicate<Auto> compareSize = a -> a.getSize() > CAR_SIZE;


    public AutoParking(int cars, int tracks) {
        validator(cars, tracks);
        this.freeSpaceCar = cars;
        this.freeSpaceTruck = tracks;
    }

    private void validator(int cars, int tracks) {
        if (cars < 0 || tracks < 0) {
            throw new IllegalArgumentException("аргумент не может быть меньше нуля");
        }
    }

    @Override
    public List<Auto> getListAuto() {
        List<Auto> list = new LinkedList<>();
        for (Set<Auto> autoSet : allAutoSets) {
            list.addAll(autoSet);
        }
        return list;
    }

    @Override
    public boolean addAuto(Auto auto) {
        boolean added = false;
        if (isTruck(auto) && freeSpaceTruck > 0) {
            truckSet.add(auto);
            freeSpaceTruck -= 1;
            added = true;
        } else if (freeSpaceCar > 0 && freeSpaceCar >= auto.getSize()) {
            carSet.add(auto);
            freeSpaceCar -= auto.getSize();
            added = true;
        }
        return added;
    }

    @Override
    public boolean deleteAuto(Auto auto) {
        boolean removed = false;
        for (Set<Auto> autoSet : allAutoSets) {
            if (autoSet.remove(auto)) {
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public void deleteAllAutos() {
        allAutoSets.forEach(Set::clear);
    }

    private boolean isTruck(Auto auto) {
        return compareSize.test(auto);
    }
}

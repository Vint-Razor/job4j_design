package ru.job4j.odd.lsp.parking;

import java.util.Objects;

public abstract class Auto {
    private boolean parked;
    private final int size;
    private final String number;

    public Auto(int size, String number) {
        this.size = size;
        this.number = number;
        this.parked = false;
    }

    public int getSize() {
         return size;
     }

    public boolean isParked() {
        return parked;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }

    @Override
    public String toString() {
        return "Auto{"
                + "size=" + size
                + ", number='" + number + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Auto auto = (Auto) o;
        return Objects.equals(number, auto.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}

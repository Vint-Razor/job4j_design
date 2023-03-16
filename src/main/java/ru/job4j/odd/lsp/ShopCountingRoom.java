package ru.job4j.odd.lsp;

public class ShopCountingRoom extends CountingRoom {

    public ShopCountingRoom(int normHours, int payPerHour) {
        super(normHours, payPerHour);
    }

    @Override
    public int pay(WorkDays workDays) {
        int factHours = 0;
        for (Integer hoursPerDay : workDays) {
            factHours += hoursPerDay;
        }
        return factHours * payPerHour;
    }
}

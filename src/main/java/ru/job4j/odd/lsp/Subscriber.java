package ru.job4j.odd.lsp;

public class Subscriber {
    protected PhoneNumber phoneNumber;

    public Subscriber(PhoneNumber phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    private void validate(PhoneNumber phoneNumber) {
        if (phoneNumber.getCountryCode() < 1 || phoneNumber.getCountryCode() > 999) {
            throw new IllegalArgumentException("неверный код страны");
        }
        if (phoneNumber.getCityCode() < 1 || phoneNumber.getCityCode() > 999) {
            throw new IllegalArgumentException("неверный код города");
        }
        if (phoneNumber.getNumber() < 1 || phoneNumber.getNumber() > 999_999_999) {
            throw new IllegalArgumentException("неверный номер телефона");
        }
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }
}

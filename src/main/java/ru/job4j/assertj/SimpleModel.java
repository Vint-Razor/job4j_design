package ru.job4j.assertj;

public class SimpleModel {

    private String name = "";

    public String getName() {
        if (name.length() == 0) {
            throw new IllegalArgumentException();
        }
        return name;
    }

    public void setName(String word, int number) {
        if (word.length() != number) {
            throw new IllegalArgumentException(
                    String.format("это слово %s по длине не равно : %d", word, number)
            );
        }
        this.name = word;
    }
}

package ru.job4j.gc.leak;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {
    public static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";

    private static final String SEPARATOR = System.lineSeparator();
    private static final List<Comment> COMMENTS = new ArrayList<>();
    private static final int COUNT = 50;
    private List<String> phrases;
    private final UserGenerator useGenerator;
    private final Random random;

    public CommentGenerator(Random random, UserGenerator useGenerator) {
        this.useGenerator = useGenerator;
        this.random = random;
        read();
    }

    private void read() {
        phrases = read(PATH_PHRASES);
    }

    public static List<Comment> getComments() {
        return COMMENTS;
    }

    @Override
    public void generate() {
        COMMENTS.clear();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < COUNT; i++) {
            builder.append(phrases.get(random.nextInt(phrases.size())));
            builder.append(SEPARATOR);
            builder.append(phrases.get(random.nextInt(phrases.size())));
            builder.append(SEPARATOR);
            builder.append(phrases.get(random.nextInt(phrases.size())));
            COMMENTS.add(new Comment(builder.toString(),
                    useGenerator.randomUser()
            ));
            builder.setLength(0);
        }
    }
}

package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswer;
    private final List<String> log;

    public ConsoleChat(String path, String botAnswer) {
        this.path = path;
        this.botAnswer = botAnswer;
        this.log = new LinkedList<>();
    }

    public void run() {
        validator(botAnswer);
        Random rnd = new Random();
        Scanner scanner = new Scanner(System.in);
        List<String> phrases = readPhrases();
        System.out.println("введите строку");
        String str = scanner.nextLine();
        while (!OUT.equals(str)) {
            if (STOP.equals(str)) {
                while (!CONTINUE.equals(str)) {
                    str = logScanUser(str, scanner);
                }
            }
            String strBot = phrases.get(rnd.nextInt(phrases.size()));
            log.add("Bot: " + strBot);
            System.out.println(strBot);
            str = logScanUser(str, scanner);
        }
        log.add(str);
        saveLog(log);
        System.exit(0);
    }

    private String logScanUser(String str, Scanner scanner) {
        log.add("User: " + str);
        str = scanner.nextLine();
        return str;
    }

    private static void validator(String botAnswer) {
        File valid = new File(botAnswer);
        if (!valid.exists()) {
            throw new IllegalArgumentException(String.format("Файл %s не найден", botAnswer));
        }
        if (!valid.isFile()) {
            throw new IllegalArgumentException(String.format(
                    "%s не является файлом, используйте файл с расширением .txt ", botAnswer));
        }
        if (!botAnswer.endsWith(".txt")) {
            throw new IllegalArgumentException(String.format(
                    "%s не является текстовым файлом, используйте файл с расширением .txt", botAnswer));
        }
        if (valid.length() == 0) {
            throw new IllegalArgumentException(String.format(
                    "%s файл не должен быть пустым, он должен содержать варианты ответа."
                            + " Каждый ответ в новой строке", botAnswer));
        }
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswer))) {
            String line = in.readLine();
            while (line != null) {
                phrases.add(line);
                line = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/ConsoleChat/log.txt",
                "./data/ConsoleChat/bot_answer.txt");
        cc.run();
    }
}

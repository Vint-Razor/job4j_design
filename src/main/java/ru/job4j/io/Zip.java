package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path sours : sources) {
                zip.putNextEntry(new ZipEntry(sours.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(sours.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validatorArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("One or more parameters are missing."
                    + " Using: java -jar pack.jar -d=c:\\dir -e=.exclude -o=project.zip");
        }
    }

    private static void validator(Path directory, String exclude) {
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("The file extension must start with \".\"");
        }
        if (!directory.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", directory.toAbsolutePath()));
        }
        if (!directory.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", directory.toAbsolutePath()));
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        validatorArgs(args);
        ArgsName zipArg = ArgsName.of(args);
        Path directory = Paths.get(zipArg.get("d"));
        String exclude = zipArg.get("e");
        File output = Paths.get(zipArg.get("o")).toFile();
        validator(directory, exclude);
        List<Path> pathList = Search.search(directory, path -> !path.toFile()
                .getName()
                .endsWith(exclude));
        zip.packFiles(pathList, output);
    }
}

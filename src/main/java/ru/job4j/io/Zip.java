package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File sours : sources) {
                zip.putNextEntry(new ZipEntry(sours.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(sours))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validatorArgs(Path directory, String exclude, File output) {
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
        ArgsName zipArg = ArgsName.of(args);
        Path directory = Paths.get(zipArg.get("d"));
        String exclude = zipArg.get("e");
        File output = Paths.get(zipArg.get("o")).toFile();
        validatorArgs(directory, exclude, output);
        List<Path> pathList = Search.search(directory, path -> !path.toFile()
                .getName()
                .endsWith(exclude));
        List<File> fileList = pathList.stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
        zip.packFiles(fileList, output);
    }
}

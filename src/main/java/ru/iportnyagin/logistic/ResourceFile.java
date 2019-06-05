package ru.iportnyagin.logistic;

import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Доступ к файлам-"ресурсам"
 */
@RequiredArgsConstructor
public class ResourceFile {

    private final String fileName;

    public String read() {
        StringBuilder sb = new StringBuilder();
        File file = new File(getClass().getClassLoader()
                                       .getResource(fileName)
                                       .getFile());

        try (BufferedReader br = Files.newBufferedReader(file.toPath())) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

}

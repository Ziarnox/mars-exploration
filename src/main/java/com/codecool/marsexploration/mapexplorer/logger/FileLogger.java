package com.codecool.marsexploration.mapexplorer.logger;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private final String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void log(String message) {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(message + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

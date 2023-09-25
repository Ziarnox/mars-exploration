package com.codecool.marsexploration.mapexplorer.logger;

public class ConsoleLogger implements Logger{
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}

package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.List;

public record SimulationConfiguration(
        String mapFilePath,
        Coordinate landingCoordinates,
        List<String> resourceSymbols,
        int timeoutSteps
) {
    @Override
    public String mapFilePath() {
        return mapFilePath;
    }

    @Override
    public Coordinate landingCoordinates() {
        return landingCoordinates;
    }

    @Override
    public List<String> resourceSymbols() {
        return resourceSymbols;
    }

    @Override
    public int timeoutSteps() {
        return timeoutSteps;
    }
}

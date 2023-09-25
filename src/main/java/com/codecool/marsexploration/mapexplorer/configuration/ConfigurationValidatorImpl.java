package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.logger.ConsoleLogger;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.util.List;

public class ConfigurationValidatorImpl implements ConfigurationValidator {
    private final Map map;
    private final Logger logger = new ConsoleLogger();

    public ConfigurationValidatorImpl(Map map) {
        this.map = map;
    }

    @Override
    public boolean isConfigurationValid(SimulationConfiguration simulationConfiguration) {
        boolean isLandingEmpty = isLandingSpotEmpty(simulationConfiguration.landingCoordinates());
        boolean hasEmptyAdjacent = hasEmptyAdjacentCoordinate(simulationConfiguration.landingCoordinates());
        boolean isPathNotEmpty = isPathNotEmpty(simulationConfiguration.mapFilePath());
        boolean areResourcesSpecified = areResourcesSpecified(simulationConfiguration.resourceSymbols());
        boolean isTimeoutNotZero = isTimeoutNotZero(simulationConfiguration.timeoutSteps());
        logger.log("Is landing spot empty: "+isLandingEmpty);
        logger.log("Has empty adjacent spots: "+hasEmptyAdjacent);
        logger.log("Is file path not empty: "+isPathNotEmpty);
        logger.log("Are resources specified: "+areResourcesSpecified);
        logger.log("Is timeout not zero: "+isTimeoutNotZero);
        return isLandingEmpty &&
                hasEmptyAdjacent &&
                isPathNotEmpty &&
                areResourcesSpecified &&
                isTimeoutNotZero;
    }

    private boolean isLandingSpotEmpty(Coordinate coordinate) {
        return map.isEmpty(coordinate);
    }

    private boolean isPathNotEmpty(String path) {
        return path.length() > 0;
    }

    private boolean areResourcesSpecified(List<String> resources) {
        return resources.size() > 0;
    }

    private boolean isTimeoutNotZero(int timeout) {
        return timeout > 0;
    }


    private boolean hasEmptyAdjacentCoordinate(Coordinate coordinate) {
        List<Coordinate> adjacentCoordinates = getAdjacentCoordinates(coordinate);
        for (Coordinate cord : adjacentCoordinates) {
            if (map.isEmpty(cord)) return true;
        }
        return false;
    }

    private List<Coordinate> getAdjacentCoordinates(Coordinate coordinate) {
        int x = coordinate.X();
        int y = coordinate.Y();
        List<Coordinate> adjacentCoordinates = List.of(
                new Coordinate(x - 1, y - 1),
                new Coordinate(x, y - 1),
                new Coordinate(x + 1, y - 1),
                new Coordinate(x + 1, y),
                new Coordinate(x + 1, y + 1),
                new Coordinate(x, y + 1),
                new Coordinate(x - 1, y + 1),
                new Coordinate(x - 1, y)
        );
        return adjacentCoordinates;
    }
}

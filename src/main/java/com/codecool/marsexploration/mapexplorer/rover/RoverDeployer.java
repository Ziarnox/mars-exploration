package com.codecool.marsexploration.mapexplorer.rover;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RoverDeployer {
    public void deployRover(Rover rover, Coordinate spaceshipCoordinate, Map map) {
        Coordinate deploymentCoordinate = findDeploymentCoordinate(spaceshipCoordinate, map);
        if (deploymentCoordinate == null) {
            System.out.println("Couldn't find an empty space around the spaceship...\n" + "Rover was not deployed");
        } else {
            System.out.println(deploymentCoordinate);
            deploymentCoordinate = findDeploymentCoordinate(spaceshipCoordinate, map);
            String[][] mapRepresentation = map.getRepresentation();
            mapRepresentation[deploymentCoordinate.Y()][deploymentCoordinate.X()] = "R";
            rover.setCurrentPosition(deploymentCoordinate);
            map.setRepresentation(mapRepresentation);
        }
    }

    public Coordinate findDeploymentCoordinate(Coordinate spaceshipCoordinate, Map map) {
        List<Coordinate> coordinates = getAdjacentCoordinates(spaceshipCoordinate, map);
        Random random = new Random();
        return coordinates.get(random.nextInt(coordinates.size()));
    }

    private boolean isOnBoard(Coordinate coordinate, double dimension) {
        return (coordinate.X() >= 0 && coordinate.Y() >= 0) && (coordinate.X() <= dimension && coordinate.Y() <= dimension);
    }

    private List<Coordinate> validateEdgeCases(Iterable<Coordinate> coordinates, Map map) {
        ArrayList<Coordinate> adjacentCoordinates = new ArrayList<>();
        for (Coordinate coordinate : coordinates) {
            if (isOnBoard(coordinate, Math.sqrt(map.toString().length()))) {
                adjacentCoordinates.add(coordinate);
            }
        }
        return adjacentCoordinates;
    }

    public List<Coordinate> getAdjacentCoordinates(Coordinate coordinate, Map map) {
        int x = coordinate.X();
        int y = coordinate.Y();
        List<Coordinate> adjacentCoordinates = Arrays.asList(
                new Coordinate(x - 1, y - 1),
                new Coordinate(x, y - 1),
                new Coordinate(x + 1, y - 1),
                new Coordinate(x + 1, y),
                new Coordinate(x + 1, y + 1),
                new Coordinate(x, y + 1),
                new Coordinate(x - 1, y + 1),
                new Coordinate(x - 1, y)
        );


        return validateEdgeCases(adjacentCoordinates, map);
    }
}

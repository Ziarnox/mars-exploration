package com.codecool.marsexploration.mapexplorer.rover;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.model.Resource;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

public class RoverCommandCenterBuilder {
    private Rover rover;
    private Map map;

    public RoverCommandCenterBuilder(Rover rover, Map map) {
        this.rover = rover;
        this.map = map;
    }

    private void buildCommandCenter() {

    }

    public Coordinate returnBestSpotForBase() {
        Coordinate coordinate = new Coordinate(0, 0);
        for (int i = 0; i < map.getRepresentation().length; i++) {
            for (int j = 0; j < map.getRepresentation()[0].length; j++) {
                coordinate = checkIfCoordinateIsSuitable(new Coordinate(i, j));
                if(coordinate!=null){
                    return coordinate;
                }
            }
        }
        return coordinate;
    }

    private Coordinate checkIfCoordinateIsSuitable(Coordinate coordinate) {
        int amountOfMinerals = 0;
        int x = coordinate.X();
        int y = coordinate.Y();
        for (int i = y - 2; i <= y + 2; i++) {
            for (int j = x - 2; j <= x + 2; j++) {
                if (i >= 0 && i <= map.returnSize() - 1 && j >= 0 && j <= map.returnSize() - 1) {
                    if (map.getByCoordinate(new Coordinate(j, i)).equals("%")) {
                        amountOfMinerals++;

                    }

                }
            }
        }

        if (amountOfMinerals >= 3 && map.getByCoordinate(coordinate).equals(" ")) {
            return coordinate;
        }

        return null;
    }
}

package com.codecool.marsexploration.mapexplorer.rover;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.model.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Rover { //Split for model and business logic
    private String roverID;
    private Coordinate currentPosition;
    private final int sightRange;
    private ArrayList<Resource> resources;
    private HashMap resourcesBag = new HashMap<String, Integer>();


    public Rover(String roverID, Coordinate currentPosition, int sightRange) {
        this.roverID = roverID;
        this.currentPosition = currentPosition;
        this.sightRange = sightRange;
        this.resources = new ArrayList<Resource>();
    }

    public void updateRoverPosition(Coordinate newCoordinate, Map map) {
        scanTerrain(map);
        setCurrentPosition(newCoordinate);

    }

    public void addToBag(String name, int amount) {
        resourcesBag.put(name, amount);
    }

    public void emptyBag() {
        this.resourcesBag = new HashMap<String, Integer>();
    }

    public HashMap getResourcesBag() {
        return resourcesBag;
    }

    private void scanTerrain(Map map) {
        int x = this.currentPosition.X();
        int y = this.currentPosition.Y();
        for (int i = y - sightRange; i <= y + sightRange; i++) {
            for (int j = x - sightRange; j <= x + sightRange; j++) {
                if (isInMapBorder(x, y, map, sightRange)) {
                    if (isResource(map.getRepresentation()[i][j])) {
                        Resource resource = new Resource(new Coordinate(j, i), map.getRepresentation()[i][j]);
                        if (!isResourceAlreadyAdded(resource)) {
                            this.resources.add(resource);
                        }

                    }
                }
            }
        }
    }

    private boolean isInMapBorder(int x, int y, Map map, int sightRange) {
        double mapSize = Math.sqrt(map.toString().length());
        return x - sightRange >= 0 && x + sightRange < mapSize - 1 && y - sightRange >= 0 && y + sightRange < mapSize - 1;
    }

    public String getResourcesString() {
        String output = "";
        int i = 0;
        for (Resource resource : resources) {
            output += i + ". Resource= " + resource.getCoordinate() + ", Symbol: '" + resource.getSymbol() + "'\n";
            i++;
        }
        return output;
    }

    private boolean isResourceAlreadyAdded(Resource resource) {
        for (Resource resource1 : resources) {
            if (resource1.getCoordinate().equals(resource.getCoordinate())) {
                return true;
            }
        }
        return false;
    }

    private boolean isResource(String field) {
        return field.equals("#") || field.equals("&") || field.equals("*") || field.equals("%");
    }

    public void setCurrentPosition(Coordinate currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    public List<Resource> getResources() {
        return resources;
    }


}



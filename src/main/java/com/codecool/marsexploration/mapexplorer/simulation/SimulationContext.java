package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.model.Resource;
import com.codecool.marsexploration.mapexplorer.rover.Rover;

import java.util.List;

public class SimulationContext {
    private int numberOfSteps;
    private int requiredNumberOfSteps;

    private Rover rover;
    private Coordinate spaceshipLocation;
    private Map map;
    private List<String> symbolsOfResources;
    private List<String> explorationOutcome;
    private Coordinate baseLocation;

    public SimulationContext(int numberOfSteps, int requiredNumberOfSteps, Rover rover, Coordinate spaceshipLocation, Map map, List<String> symbolsOfResources, List<String> explorationOutcome) {
        this.numberOfSteps = numberOfSteps;
        this.requiredNumberOfSteps = requiredNumberOfSteps;
        this.rover = rover;
        this.spaceshipLocation = spaceshipLocation;
        this.map = map;
        this.symbolsOfResources = symbolsOfResources;
        this.explorationOutcome = explorationOutcome;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public int getRequiredNumberOfSteps() {
        return requiredNumberOfSteps;
    }

    public Coordinate getSpaceshipLocation() {
        return spaceshipLocation;
    }
    public void setBaseLocation(Coordinate coordinate){
        this.baseLocation=coordinate;
    }

    public Coordinate getBaseLocation() {
        return baseLocation;
    }

    public Map getMap() {
        return map;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public List<String> getSymbolsOfResources() {
        return symbolsOfResources;
    }

    public List<String> getExplorationOutcome() {
        return explorationOutcome;
    }

    public Rover getRover() {
        return rover;
    }
}

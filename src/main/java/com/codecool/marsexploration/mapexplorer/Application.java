package com.codecool.marsexploration.mapexplorer;

import com.codecool.marsexploration.mapexplorer.simulation.ExplorationSimulator;


public class Application {
    public static void main(String[] args) {
        ExplorationSimulator explorationSimulator = new ExplorationSimulator();
        explorationSimulator.runSimulation();
    }
}


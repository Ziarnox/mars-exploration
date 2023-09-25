package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.Analysis.Analyzer;
import com.codecool.marsexploration.mapexplorer.Analysis.SuccessAnalyzer;
import com.codecool.marsexploration.mapexplorer.Analysis.TimeoutAnalyzer;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidator;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidatorImpl;
import com.codecool.marsexploration.mapexplorer.configuration.SimulationConfiguration;
import com.codecool.marsexploration.mapexplorer.logger.ConsoleLogger;
import com.codecool.marsexploration.mapexplorer.logger.FileLogger;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.impl.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.rover.Rover;
import com.codecool.marsexploration.mapexplorer.rover.RoverDeployer;
import com.codecool.marsexploration.mapexplorer.rover.RoverMovementRoutine;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ExplorationSimulator {
    private static final int TIMEOUT_STEPS = 999;

    Logger consoleLogger = new ConsoleLogger();
    SimulationConfiguration simulationConfiguration;
    Map map;
    SimulationContext simulationContext;
    ConfigurationValidator configurationValidator;
    RoverDeployer roverDeployer = new RoverDeployer();
    Analyzer successAnalyzer;
    Analyzer timeoutAnalyzer;
    RoverMovementRoutine roverMovementRoutine;
    private boolean movedToLeftUpperCorner = false;
    private boolean movedToRightBorder = false;
    private boolean movedToLeftBorder = false;

    public void runSimulation() {
        FileLogger fileLogger = new FileLogger("log.txt");
        prepareConfig();
        if (validateConfiguration()) {
            markLandingSpot();
            deployRover();

            while (!successAnalyzer.wereOutcomeConditionsMet() && !timeoutAnalyzer.wereOutcomeConditionsMet()) {
                exploreMap();
                System.out.println(simulationContext.getRover().getResourcesString());
                rerenderMap(simulationConfiguration.mapFilePath(), simulationContext, simulationConfiguration.landingCoordinates());
                printMap();
                consoleLogger.log("Step: " + simulationContext.getNumberOfSteps());
                simulationContext.setNumberOfSteps(simulationContext.getNumberOfSteps() + 1);
                fileLogger.log("STEP " + simulationContext.getNumberOfSteps() + "; EVENT position; UNIT rover-1; POSITION [" +
                        simulationContext.getRover().getCurrentPosition().X() + "," + simulationContext.getRover().getCurrentPosition().Y() + "]");
            }
            if (successAnalyzer.wereOutcomeConditionsMet()) {
                consoleLogger.log("Simulation succeed!");
                fileLogger.log("STEP " + simulationContext.getNumberOfSteps() + "; EVENT outcome; OUTCOME COLONIZABLE");
                System.exit(0);
            } else {
                consoleLogger.log("Timeout reached!");
                fileLogger.log("STEP " + simulationContext.getNumberOfSteps() + "; EVENT outcome; OUTCOME TIMEOUT");
                System.exit(0);
            }
        } else {
            consoleLogger.log("Configuration invalid!");
        }
    }

    private static void rerenderMap(String mapPath, SimulationContext simulationContext, Coordinate landingSpot) {
        MapLoader mapLoader = new MapLoaderImpl();
        Map map = mapLoader.load(mapPath);
        int x = simulationContext.getRover().getCurrentPosition().X();
        int y = simulationContext.getRover().getCurrentPosition().Y();
        map.getRepresentation()[y][x] = "R";
        map.getRepresentation()[landingSpot.Y()][landingSpot.X()] = "X";
        simulationContext.getMap().setRepresentation(map.getRepresentation());
    }

    private void createRoverMovementRoutine() {
        this.roverMovementRoutine = new RoverMovementRoutine(simulationContext.getRover(), simulationContext.getMap());
    }

    private void markLandingSpot() {
        simulationContext.getMap().getRepresentation()[simulationConfiguration.landingCoordinates().Y()][simulationConfiguration.landingCoordinates().X()] = "X";
    }

    private void exploreMap() {
        if (simulationContext.getRover().getCurrentPosition().X() != simulationContext.getMap().getRepresentation().length - 1
                && simulationContext.getRover().getCurrentPosition().Y() != simulationContext.getMap().getRepresentation()[0].length - 1) {

            if (!movedToLeftUpperCorner) {
                roverMovementRoutine.moveToLeftUpper();
                if (simulationContext.getRover().getCurrentPosition().X() == 0 &&
                        simulationContext.getRover().getCurrentPosition().Y() == 0) {
                    movedToLeftUpperCorner = true;
                }
            }

            if (!movedToRightBorder && movedToLeftUpperCorner) {
                roverMovementRoutine.moveRight();

                if (simulationContext.getRover().getCurrentPosition().X() == simulationContext.getMap().getRepresentation().length - 1) {
                    movedToRightBorder = true;
                    roverMovementRoutine.moveDown();

                }
            }
            if (movedToRightBorder && movedToLeftUpperCorner) {
                roverMovementRoutine.moveLeft();
                if (simulationContext.getRover().getCurrentPosition().X() == 0) {

                    roverMovementRoutine.moveDown();
                    movedToRightBorder = false;
                    movedToLeftBorder = true;
                }
            }
        }
    }

    private void printMap() {
        consoleLogger.log(simulationContext.getMap().toString());
    }

    private void deployRover() {
        roverDeployer.deployRover(simulationContext.getRover(), simulationConfiguration.landingCoordinates(), simulationContext.getMap());
    }

    private void prepareConfig() {
        createConfiguration();
        createNewMap();
        createSimulationContext();
        createConfigurationValidator();
        createAnalyzers();
        createRoverMovementRoutine();
    }

    private void createAnalyzers() {
        this.timeoutAnalyzer = new TimeoutAnalyzer(this.simulationContext);
        this.successAnalyzer = new SuccessAnalyzer(this.simulationContext);
    }

    private boolean validateConfiguration() {
        return configurationValidator.isConfigurationValid(simulationConfiguration);
    }


    private void createConfiguration() {
        this.simulationConfiguration = new SimulationConfiguration(
                "src/main/resources/exploration-0.map",
                new Coordinate(1, 2),
                List.of("#", "&", "%", "*"),
                TIMEOUT_STEPS);
    }

    private void createNewMap() {
        MapLoader mapLoader = new MapLoaderImpl();
        this.map = mapLoader.load(simulationConfiguration.mapFilePath());

    }

    private void createSimulationContext() {
        this.simulationContext = new SimulationContext(0,
                TIMEOUT_STEPS,
                new Rover("rover-1", simulationConfiguration.landingCoordinates(), 5),
                simulationConfiguration.landingCoordinates(),
                map,
                simulationConfiguration.resourceSymbols(),
                new ArrayList<String>());
    }

    private void createConfigurationValidator() {
        this.configurationValidator = new ConfigurationValidatorImpl(this.map);
    }
}

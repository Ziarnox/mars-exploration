package com.codecool.marsexploration.mapexplorer.rover;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RoverMovementRoutine {
    private static Rover rover;
    private static Map map;

    private ArrayList<Coordinate> alreadyVisited;

    public RoverMovementRoutine(Rover rover, Map map) {
        this.rover = rover;
        this.map = map;
        this.alreadyVisited = new ArrayList<Coordinate>();
    }

    public void moveUp() {
        Coordinate newCoordinates = new Coordinate(rover.getCurrentPosition().X(), rover.getCurrentPosition().Y() - 1);
        rover.updateRoverPosition(newCoordinates, map);
    }

    public void moveDown() {
        Coordinate newCoordinates = new Coordinate(rover.getCurrentPosition().X(), rover.getCurrentPosition().Y() + 1);
        rover.updateRoverPosition(newCoordinates, map);
    }

    public void moveLeft() {
        Coordinate newCoordinates = new Coordinate(rover.getCurrentPosition().X() - 1, rover.getCurrentPosition().Y());
        rover.updateRoverPosition(newCoordinates, map);
    }

    public void moveRight() {
        Coordinate newCoordinates = new Coordinate(rover.getCurrentPosition().X() + 1, rover.getCurrentPosition().Y());
        rover.updateRoverPosition(newCoordinates, map);
    }

    public void moveToLeftUpper(){
        if(rover.getCurrentPosition().X()>0){
            moveLeft();
        }
        else if(rover.getCurrentPosition().Y()>0){
            moveUp();
        }
    }


}

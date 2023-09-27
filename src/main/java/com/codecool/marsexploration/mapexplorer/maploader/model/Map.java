package com.codecool.marsexploration.mapexplorer.maploader.model;

import com.codecool.marsexploration.mapexplorer.model.Resource;
import com.codecool.marsexploration.mapexplorer.simulation.ExplorationSimulator;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private String[][] representation;
    private boolean successfullyGenerated;

    public Map(String[][] representation, boolean successfullyGenerated) {
        this.representation = representation;
        this.successfullyGenerated = successfullyGenerated;
    }

    public int getDimension() {
        return representation.length;
    }

    public double returnSize() {
        return Math.sqrt(this.toString().length());
    }


    private static String createStringRepresentation(String[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (String[] strings : arr) {
            StringBuilder s = new StringBuilder();
            for (String string : strings) {
                s.append(string == null ? " " : string);
            }
            sb.append(s).append("\n");
        }

        return sb.toString();
    }

    public String getByCoordinate(Coordinate coordinate) {
        return representation[coordinate.Y()][coordinate.X()];
    }

    public boolean isEmpty(Coordinate coordinate) {
        return representation[coordinate.X()][coordinate.Y()] == null
                || representation[coordinate.X()][coordinate.Y()].isEmpty()
                || representation[coordinate.X()][coordinate.Y()].equals(" ");
    }

    public String[][] getRepresentation() {
        return representation;
    }

    public void setRepresentation(String[][] representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return createStringRepresentation(representation);
    }
}

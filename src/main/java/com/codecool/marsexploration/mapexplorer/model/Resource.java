package com.codecool.marsexploration.mapexplorer.model;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

public class Resource {
    Coordinate coordinate;
    String symbol;

    public Resource(Coordinate coordinate, String symbol) {
        this.coordinate = coordinate;
        this.symbol = symbol;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "Resource: " +
                "coordinate: " + coordinate +
                ", symbol: '" + symbol + "'\n";
    }
}

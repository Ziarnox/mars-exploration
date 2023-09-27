package com.codecool.marsexploration.mapexplorer.commandCenter;

import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.rover.Rover;

public class CommandCenterStockServiceImpl implements CommandCenterStockService{


    @Override
    public void addResource(CommandCenter center, char symbol, int amount) {
        String resourceName;
        switch (symbol){
            case '*':
                resourceName = "Water";
                break;
            case '%':
                resourceName = "Minerals";
                break;
            default:
                throw new IllegalArgumentException("Invalid resource symbol: " + symbol);
        }
        center.getResourcesOnStock().put(resourceName, center.getResourcesOnStock().getOrDefault(resourceName, 0)+amount);
    }
}

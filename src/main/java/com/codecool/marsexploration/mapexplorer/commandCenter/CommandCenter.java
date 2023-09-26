package com.codecool.marsexploration.mapexplorer.commandCenter;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.Map;

public class CommandCenter {
private int ID;
private Coordinate location;
private CommandCenterStatus status;
private Map<String,Integer> resourcesOnStock;

    public CommandCenter(int id, Coordinate location, CommandCenterStatus status, Map<String, Integer> resourcesOnStock) {
        this.ID = id;
        this.location = location;
        this.status = status;
        this.resourcesOnStock = resourcesOnStock;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Coordinate getLocation() {
        return location;
    }

    public void setLocation(Coordinate location) {
        this.location = location;
    }

    public CommandCenterStatus getStatus() {
        return status;
    }

    public void setStatus(CommandCenterStatus status) {
        this.status = status;
    }

    public Map<String, Integer> getResourcesOnStock() {
        return resourcesOnStock;
    }

    public void setResourcesOnStock(Map<String, Integer> resourcesOnStock) {
        this.resourcesOnStock = resourcesOnStock;
    }

    @Override
    public String toString() {
        return "CommandCenter{" +
                "id=" + ID +
                ", location=" + location +
                ", status=" + status +
                ", resourcesOnStock=" + resourcesOnStock +
                '}';
    }
}

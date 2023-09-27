package com.codecool.marsexploration.mapexplorer.rover;

import com.codecool.marsexploration.mapexplorer.model.Resource;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.util.HashMap;
//import java.util.Map;

public class RoverResourcesManager {
    private Rover rover;
    private Map map;


    public RoverResourcesManager(Rover rover, Map map) {
        this.rover = rover;
        this.map = map;
    }

    public void collectResource(Resource resource) {
        HashMap<String, Integer> asd = rover.getResourcesBag();
        if (resource.getCoordinate().equals(rover.getCurrentPosition())) {
            if(rover.getResourcesBag().containsKey("%")){
                int currentValue = asd.get("%")+1;
                rover.getResourcesBag().put("%",currentValue);
            }else{
                rover.addToBag(resource.getSymbol(), 1);
            }

        }

    }




    private void deliverToBase() {
        HashMap<String, Integer> bag = rover.getResourcesBag();
        for (java.util.Map.Entry<String, Integer> entry : bag.entrySet()) {
            String resource = entry.getKey();
            Integer value = entry.getValue();
            //TODO Add to base ?
        }
        rover.emptyBag();
    }


}

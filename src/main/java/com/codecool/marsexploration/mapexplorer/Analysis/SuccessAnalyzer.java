package com.codecool.marsexploration.mapexplorer.Analysis;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.model.Resource;
import com.codecool.marsexploration.mapexplorer.rover.Rover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import java.util.List;

public class SuccessAnalyzer implements Analyzer{
    private SimulationContext simulationContext;
    private Rover rover;

    public SuccessAnalyzer(SimulationContext simulationContext) {
        this.simulationContext = simulationContext;
        this.rover = simulationContext.getRover();
    }

    @Override
    public boolean wereOutcomeConditionsMet() {
        return wereEnoughMaterialsFound();
    }

    private  boolean wereEnoughMaterialsFound(){
        List<Resource> materialsCoordinates = rover.getResources();
        int minerals=0;
        int waters=0;
        for(Resource resource : materialsCoordinates){
            if(resource.getSymbol().equals("*")) waters++;
            if(resource.getSymbol().equals("%")) minerals++;

        }
        return minerals>=4 && waters>=3;
    }

}

package com.codecool.marsexploration.mapexplorer.Analysis;

import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;

public class TimeoutAnalyzer implements Analyzer {
    SimulationContext simulationContext;
//    private final int requiredNumberOfSteps;

    @Override
    public boolean wereOutcomeConditionsMet( ) {
        return didCurrentStepReachTimeout();
    }

    public TimeoutAnalyzer(SimulationContext simulationContext) {
        this.simulationContext = simulationContext;
//        this.requiredNumberOfSteps = simulationContext.getRequiredNumberOfSteps();
    }

    private boolean didCurrentStepReachTimeout( ) {
        return this.simulationContext.getNumberOfSteps() == this.simulationContext.getRequiredNumberOfSteps();
    }

}

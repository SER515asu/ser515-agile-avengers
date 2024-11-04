package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;

public class SolutionStore {
    private static SolutionStore solutionStore;
    private static String currentSimulationId;

    public static SolutionStore getInstance(String simulationId) {
        if (solutionStore == null || !simulationId.equals(currentSimulationId)) {
            currentSimulationId = simulationId;
            solutionStore = new SolutionStore();
        }
        return solutionStore;
    }


    private ArrayList<Solution> solutions;

    public SolutionStore(){
        solutions = new ArrayList<>();
        fetchAndStoreSolutionsForSimulation();
    }

    public List<Solution> getSolutions(){
        return new ArrayList<>(solutions);
    }

    public void addSolution(Solution solution){
        solutions.add(solution);
        SimulationStateManager.storeSolutionInSimulation(currentSimulationId, solution);
    }

    private void fetchAndStoreSolutionsForSimulation(){
        solutions.addAll(SimulationStateManager.getSolutionsForSimulation(currentSimulationId));
    }

    public void removeSolution(Solution solution){
        solutions.remove(solution);
    }

    public Solution getSolutionByTitle(String title) {
        for (Solution solution : solutions) {
            if (solution.getTitle().equals(title)) {
                return solution;
            }
        }
        return null;
    }
}

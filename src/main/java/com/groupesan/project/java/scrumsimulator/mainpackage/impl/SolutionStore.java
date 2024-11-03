package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;

/**
 * SolutionStore manages a list of solutions and provides methods to add, remove, and retrieve solutions.
 * This class follows the Singleton design pattern to ensure only one instance of SolutionStore exists.
 */
public class SolutionStore {
    private static SolutionStore solutionStore;  // Singleton instance of SolutionStore
    private static String currentSimulationId;

    private List<Solution> solutions;  // List to hold all solutions

    /**
     * Returns the singleton instance of SolutionStore, creating it if it does not already exist
     * or if the simulationId is different.
     *
     * @param simulationId The ID of the simulation to retrieve or create the SolutionStore for.
     * @return The singleton instance of SolutionStore.
     */
    public static SolutionStore getInstance(String simulationId) {
        if (solutionStore == null || !simulationId.equals(currentSimulationId)) {
            currentSimulationId = simulationId;
            solutionStore = new SolutionStore();
        }
        return solutionStore;
    }

    /**
     * Private constructor to prevent instantiation from other classes.
     */
    private SolutionStore() {
        solutions = new ArrayList<>();
        fetchAndStoreSolutionsForSimulation();
    }

    /**
     * Returns a copy of the list of solutions.
     *
     * @return A new list containing all solutions.
     */
    public List<Solution> getSolutions() {
        return new ArrayList<>(solutions);
    }

    /**
     * Adds a solution to the store.
     *
     * @param solution The solution to add.
     */
    public void addSolution(Solution solution) {
        solutions.add(solution);
        SimulationStateManager.storeSolutionInSimulation(currentSimulationId, solution);
    }

    /**
     * Removes a solution from the store.
     *
     * @param solution The solution to remove.
     */
    public void removeSolution(Solution solution) {
        solutions.remove(solution);
    }

    /**
     * Retrieves a solution by its title. Returns null if no solution with the given title is found.
     *
     * @param title The title of the solution to retrieve.
     * @return The solution with the specified title, or null if not found.
     */
    public Solution getSolutionByTitle(String title) {
        for (Solution solution : solutions) {
            if (solution.getTitle().equals(title)) {
                return solution;
            }
        }
        return null;
    }

    /**
     * Clears all solutions from the store. Useful for testing or resetting the application state.
     */
    public void clearSolutions() {
        solutions.clear();
    }

    /**
     * Fetches and stores solutions for the current simulation.
     */
    private void fetchAndStoreSolutionsForSimulation() {
        solutions.addAll(SimulationStateManager.getSolutionsForSimulation(currentSimulationId));
    }
}

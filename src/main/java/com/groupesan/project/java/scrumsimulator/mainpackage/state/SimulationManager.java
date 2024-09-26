package com.groupesan.project.java.scrumsimulator.mainpackage.state;

/**
 * SimulationManager acts as an intermediary between the UI and SimulationStateManager. It handles
 * the creation and updating of simulations.
 */
public class SimulationManager {

    public SimulationManager() {
        // empty for now as methods in 'SimulationStateManager' are static
    }

    /**
     * Creates a simulation with the provided simulation ID, name and sprint count.
     *
     * @param simId The simulation ID.
     * @param simName The simulation name.
     * @param numberOfSprints The total sprint count.
     */
    public void createSimulation(String simId, String simName, String numberOfSprints) {
        SimulationStateManager.saveNewSimulationDetails(simId, simName, numberOfSprints);
    }
}

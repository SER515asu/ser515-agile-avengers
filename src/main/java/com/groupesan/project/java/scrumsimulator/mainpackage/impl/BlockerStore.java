package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BlockerStore {

    // Singleton instance of BlockerStore
    private static BlockerStore instance;

    // List to store the blockers
    private List<Blocker> blockers;
    private static String currentSimulationId;

    // Private constructor to prevent instantiation
    private BlockerStore() {
        blockers = new ArrayList<>();
        fetchAndStoreBlockersForSimulation();
    }

    // Method to get the singleton instance of BlockerStore
    public static BlockerStore getInstance(String simulationId) {
        if (instance == null || !currentSimulationId.equals(simulationId)) {
            currentSimulationId = simulationId;
            instance = new BlockerStore();
        }
        return instance;
    }

    // Method to add a blocker to the store
    public void addBlocker(Blocker blocker) {
        blockers.add(blocker);
        SimulationStateManager.storeBlockerInSimulation(currentSimulationId, blocker);
    }

    // Method to remove a blocker by its UUID
    public void removeBlocker(UUID id) {
        blockers.removeIf(blocker -> blocker.getId().equals(id));
    }

    // Method to retrieve all blockers
    public List<Blocker> getAllBlockers() {
        return new ArrayList<>(blockers);
    }

    // Method to find a blocker by its UUID
    public Blocker getBlockerById(UUID id) {
        return blockers.stream()
                .filter(blocker -> blocker.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Method to resolve a blocker by its UUID
    public boolean resolveBlocker(UUID id) {
        Blocker blocker = getBlockerById(id);
        if (blocker != null && !blocker.isResolved()) {
            blocker.resolve();
            return true;
        }
        return false;
    }

    private void fetchAndStoreBlockersForSimulation(){
        blockers.addAll(SimulationStateManager.getBlockersForSimulation(currentSimulationId));
    }
}

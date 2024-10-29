package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import java.util.ArrayList;
import java.util.List;

public class SprintStore {
    private static SprintStore sprintStore;
    private List<Sprint> sprints;
    private String simulationID;

    private SprintStore(String simulationID) {
        sprints = new ArrayList<>();
        this.simulationID = simulationID;
    }

    public static SprintStore getInstance(String simulationID) {
        if (sprintStore == null) {
            sprintStore = new SprintStore(simulationID);
        }
        return sprintStore;
    }

    public void addSprint(Sprint sprint) {
        if (!sprints.contains(sprint)) {
            sprints.add(sprint);
            SimulationStateManager.addSprintToSimulation(simulationID, sprint);
        }
    }

    public List<Sprint> getSprints() {
        return new ArrayList<>(sprints);
    }

    public void removeSprint(Sprint sprint) {
        sprints.remove(sprint);
        SimulationStateManager.removeSprintFromSimulation(simulationID, sprint.getId());
    }


    /**
     * Loads sprints from the JSON file for a specific simulation ID and populates the SprintStore.
     */
    public void loadSprintsFromJson(String simulationID) {
        sprints.clear();  // Clear existing sprints
        List<Sprint> loadedSprints = SimulationStateManager.getSprintsForSimulation(simulationID);
        sprints.addAll(loadedSprints);
    }
}

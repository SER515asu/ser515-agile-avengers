package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import java.util.ArrayList;
import java.util.List;

public class UserStoryStore {
    private static UserStoryStore userStoryStore;
    private List<UserStory> userStories;
    private String simulationID;

    private UserStoryStore(String simulationID) {
        this.simulationID = simulationID;
        userStories = new ArrayList<>();
        loadUserStoriesFromJson();
    }

    public static UserStoryStore getInstance(String simulationID) {
        if (userStoryStore == null) {
            userStoryStore = new UserStoryStore(simulationID);
        }
        return userStoryStore;
    }

    public void addUserStory(UserStory userStory) {
        userStories.add(userStory);
        SimulationStateManager.addUserStoryToSimulation(simulationID, userStory);
    }

    public List<UserStory> getUserStories() {
        return new ArrayList<>(userStories);
    }

    public void removeUserStory(UserStory userStory) {
        userStories.remove(userStory);
        SimulationStateManager.removeUserStoryFromSimulation(simulationID, userStory.getName());
    }

    /**
     * Loads user stories from JSON for a specific simulation ID and populates the UserStoryStore.
     */
    public void loadUserStoriesFromJson() {
        userStories.clear();  // Clear existing user stories
        userStories.addAll(SimulationStateManager.getUserStoriesForSimulation(simulationID));
    }
}

package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStoryStore {
    private static UserStoryStore userStoryStore;
    private List<UserStory> backlogStories; // Stories in the backlog
    private String simulationID;

    private UserStoryStore(String simulationID) {
        this.simulationID = simulationID;
        backlogStories = new ArrayList<>();
        loadProductBacklogFromJson();
    }

    public static UserStoryStore getInstance(String simulationID) {
        if (userStoryStore == null || !userStoryStore.simulationID.equals(simulationID)) {
            userStoryStore = new UserStoryStore(simulationID);
        }
        return userStoryStore;
    }

    /**
     * Adds a user story to the product backlog and updates the simulation state.
     *
     * @param userStory The user story to add.
     */
    public void addUserStoryToBacklog(UserStory userStory) {
        if (!backlogStories.contains(userStory)) {
            backlogStories.add(userStory);
            SimulationStateManager.addUserStoryToSimulation(simulationID, userStory);
        }
    }

    /**
     * Returns a list of user stories in the product backlog.
     *
     * @return List of user stories in the backlog.
     */
    public List<UserStory> getBacklogStories() {
        return new ArrayList<>(backlogStories);
    }

    /**
     * Removes a user story from the product backlog and updates the simulation state.
     *
     * @param userStory The user story to remove.
     */
    public void removeUserStoryFromBacklog(UserStory userStory) {
        if (backlogStories.remove(userStory)) {
            SimulationStateManager.removeUserStoryFromSimulation(simulationID, userStory.getName());
            System.out.println("User Story removed from backlog: " + userStory.getName()); // For debugging
        } else {
            System.out.println("User Story not found in backlog for removal: " + userStory.getName()); // For debugging
        }
    }

    public List<UserStory> getUserStoriesFromJson() {
        loadProductBacklogFromJson();
        return new ArrayList<>(backlogStories);
    }

    public void removeUserStory(UserStory userStory) {
        backlogStories.remove(userStory);
        SimulationStateManager.removeUserStoryFromSimulation(simulationID, userStory.getName());
    }

    /**
     * Loads user stories from JSON for a specific simulation ID and populates the UserStoryStore.
     */
    public void loadProductBacklogFromJson() {
        backlogStories.clear();
        backlogStories.addAll(SimulationStateManager.getUserStoriesForSimulation(simulationID,true)); // switch to false
    }

    public List<UserStory> getAllUserStories(){
        List<UserStory> userStories = new ArrayList<>(backlogStories);
        userStories.addAll(getUserStoriesFromAllSprints());
        return userStories;
    }

    public List<UserStory> getUserStoriesFromAllSprints(){
        List<UserStory> userStories = new ArrayList<>();
        for(Sprint sprint: SprintStore.getInstance(simulationID).getSprints()){
            userStories.addAll(sprint.getUserStories());
        }
        return userStories;
    }
}

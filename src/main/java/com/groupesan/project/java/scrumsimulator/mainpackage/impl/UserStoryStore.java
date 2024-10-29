package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import java.util.ArrayList;
import java.util.List;

public class UserStoryStore {
    private static UserStoryStore userStoryStore;
    private List<UserStory> backlogStories; // Stories in the backlog
    private List<UserStory> sprintStories;  // Stories assigned to sprints
    private String simulationID;

    private UserStoryStore(String simulationID) {
        this.simulationID = simulationID;
        backlogStories = new ArrayList<>();
        sprintStories = new ArrayList<>();
        loadUserStoriesFromJson();
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

    /**
     * Adds a user story to the sprint list, removing it from the backlog if present.
     *
     * @param userStory The user story to add to the sprint.
     */
    public void addUserStoryToSprint(UserStory userStory) {
        if (backlogStories.contains(userStory)) {
            backlogStories.remove(userStory); // Remove from backlog if present
        }
        if (!sprintStories.contains(userStory)) {
            sprintStories.add(userStory); // Add to sprint stories
        }
    }

    /**
     * Removes a user story from the sprint list and restores it to the backlog.
     *
     * @param userStory The user story to remove from the sprint.
     */
    public void removeUserStoryFromSprint(UserStory userStory) {
        if (sprintStories.remove(userStory)) { // Remove from sprint if present
            backlogStories.add(userStory); // Restore to backlog
        }
    }

    /**
     * Returns a list of user stories currently in sprints.
     *
     * @return List of user stories in the sprint.
     */
    public List<UserStory> getUserStoriesInSprint() {
        return new ArrayList<>(sprintStories);
    }

    /**
     * Loads user stories from JSON for a specific simulation ID and populates the UserStoryStore.
     */
    public void loadUserStoriesFromJson() {
        backlogStories.clear();
        sprintStories.clear();
        backlogStories.addAll(SimulationStateManager.getUserStoriesForSimulation(simulationID));
    }
}

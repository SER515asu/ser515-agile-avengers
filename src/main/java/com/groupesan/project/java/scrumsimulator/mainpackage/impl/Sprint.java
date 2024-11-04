package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Sprint {
    private ArrayList<UserStory> userStories = new ArrayList<>();
    private String name;

    private String description;

    private int length;

    private int remainingDays;

    private String id;

    public Sprint(String name, String description, int length, String id) {
        this.name = name;
        this.description = description;
        this.length = length;
        this.remainingDays = length;
        this.id = id;
        this.userStories = new ArrayList<>();
    }

    public void addUserStory(String simulationId, UserStory us) {
        userStories.add(us);
        SimulationStateManager.addUserStoryToSprintBacklog(simulationId, getId(), us);
        UserStoryStore.getInstance(simulationId).removeUserStoryFromBacklog(us);
    }

    public void removeUserStory(String simulationId, UserStory us) {
        userStories.remove(us);
        SimulationStateManager.removeUserStoryFromSprintBacklog(simulationId, getId(), us);
        UserStoryStore.getInstance(simulationId).addUserStoryToBacklog(us);
    }

    public List<UserStory> getUserStories() {
        return new ArrayList<>(userStories);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLength() {
        return length;
    }

    public int getDaysRemaining() {
        return remainingDays;
    }

    public void decrementRemainingDays() {
        if (remainingDays > 0) remainingDays--;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        String header = "Sprint " + this.id + ": " + this.name + "\n";
        StringBuilder USes = new StringBuilder();

        for (UserStory us : userStories) {
            USes.append(us.toString()).append("\n");
        }
        return header + USes;
    }
}

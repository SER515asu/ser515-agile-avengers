package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Blocker {
    private UUID id;
    private String name;
    private String description;
    private boolean resolved;
    private Solution solution; // Added Solution to hold the linked solution

    // List to store linked user stories
    private List<UserStory> linkedUserStories = new ArrayList<>();

    public Blocker(UUID blockerId, String name, String description) {
        this.id = blockerId;
        this.name = name;
        this.description = description;
        this.resolved = false;
        this.solution = null; // Initialize solution as null
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isResolved() {
        return resolved;
    }

    /**
     * Adds a user story to the list of linked user stories if it’s not already linked.
     * @param userStory the user story to link
     */
    public void addLinkedUserStory(UserStory userStory) {
        if (!linkedUserStories.contains(userStory)) {
            linkedUserStories.add(userStory);
        }
    }

    /**
     * Removes a user story from the list of linked user stories.
     * @param userStory the user story to unlink
     */
    public void removeLinkedUserStory(UserStory userStory) {
        linkedUserStories.remove(userStory);
    }

     /**
     * Resolves the blocker by linking a solution to it.
     * @param solution The solution to link with the blocker.
     */
    public void resolve(Solution solution) {
        this.resolved = true;
        this.solution = solution;
    }

    /**
     * Returns the title of the linked solution if available.
     * @return the solution title, or null if no solution is linked
     */
    public String getSolutionTitle() {
        return solution != null ? solution.getTitle() : "No Solution Linked";
    }

    // Method to register the blocker in the BlockerStore
//    public void doRegister() {
//        BlockerStore.getInstance().addBlocker(this); // Register this blocker in the store
//    }

    @Override
    public String toString() {
        return name;
    }
}

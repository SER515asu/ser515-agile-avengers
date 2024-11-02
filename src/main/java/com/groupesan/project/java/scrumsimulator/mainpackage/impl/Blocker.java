package com.groupesan.project.java.scrumsimulator.mainpackage.impl;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Blocker {
    private UUID id;
    private String name;
    private String description;
    private boolean resolved;

    // List to store linked user stories
    private List<UserStory> linkedUserStories = new ArrayList<>();

    public Blocker(UUID blockerId, String name, String description) {
        this.id = blockerId;
        this.name = name;
        this.description = description;
        this.resolved = false;
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
     * Resolves the blocker.
     */
    public void resolve() {
        this.resolved = true;
    }

    // Method to register the blocker in the BlockerStore
    public void doRegister() {
        BlockerStore.getInstance().addBlocker(this);
    }

    @Override
    public String toString() {
        return name + " (ID: " + name + ")";
    }

}

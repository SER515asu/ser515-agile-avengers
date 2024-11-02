package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Spike {
    @Setter
    @Getter
    private UUID id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String description;
    private boolean resolved;

    public Spike(UUID spikeId, String name, String description) {
        this.id = spikeId;
        this.name = name;
        this.description = description;
        this.resolved = false;
    }

    private List<UserStory> linkedUserStories = new ArrayList<>();

    public void addLinkedUserStory(UserStory userStory) {
        if (!linkedUserStories.contains(userStory)) {
            linkedUserStories.add(userStory);
        }
    }

    public void removeLinkedUserStory(UserStory userStory) {
        linkedUserStories.remove(userStory);
    }


    public boolean isResolved() {
        return resolved;
    }

    public void resolve() {
        this.resolved = true;
    }

    public void doRegister() {
        SpikeStore.getInstance().addSpike(this);
    }

    @Override
    public String toString() {
        return name + " (ID: " + name + ")";
    }
}

package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

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
    @Getter
    private boolean resolved;
    @Getter
    @Setter
    private boolean failed;

    @Getter
    @Setter
    private List<UserStory> linkedUserStories = new ArrayList<>();

    public Spike(UUID spikeId, String name, String description) {
        this.id = spikeId;
        this.name = name;
        this.description = description;
        this.resolved = false;
        this.failed = false;
    }

    public void addLinkedUserStory(UserStory userStory) {
        if (!linkedUserStories.contains(userStory)) {
            linkedUserStories.add(userStory);
        }
    }

    public void removeLinkedUserStory(UserStory userStory) {
        linkedUserStories.remove(userStory);
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

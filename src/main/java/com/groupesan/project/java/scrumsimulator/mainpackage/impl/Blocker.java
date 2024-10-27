package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import lombok.Setter;

import java.util.UUID;

public class Blocker {
    @Setter
    private UUID id;
    @Setter
    private String name;
    @Setter
    private String description;
    private boolean resolved;

    public Blocker(UUID blockerId, String name, String description) {
        this.id = blockerId;
        this.name = name;
        this.description = description;
        this.resolved = false;
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

    public void resolve() {
        this.resolved = true;
    }


    // Method to register the blocker in the BlockerStore
    public void doRegister() {
        BlockerStore.getInstance().addBlocker(this); // Register this blocker in the store
    }
}

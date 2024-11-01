package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import lombok.Getter;
import lombok.Setter;

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


    public boolean isResolved() {
        return resolved;
    }

    public void resolve() {
        this.resolved = true;
    }

    public void doRegister() {
        SpikeStore.getInstance().addSpike(this);
    }
}

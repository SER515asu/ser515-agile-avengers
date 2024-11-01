package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpikeStore {

    private static SpikeStore instance;

    private List<Spike> spikes;

    private SpikeStore() {
        spikes = new ArrayList<>();
    }

    public static SpikeStore getInstance() {
        if (instance == null) {
            instance = new SpikeStore();
        }
        return instance;
    }

    public void addSpike(Spike spike) {
        spikes.add(spike);
    }

    public void removeSpike(UUID id) {
        spikes.removeIf(spike -> spike.getId().equals(id));
    }

    public List<Spike> getAllSpikes() {
        return new ArrayList<>(spikes);
    }

    public Spike getSpikeById(UUID id) {
        return spikes.stream()
                .filter(spike -> spike.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean resolveSpike(UUID id) {
        Spike spike = getSpikeById(id);
        if (spike != null && !spike.isResolved()) {
            spike.resolve();
            return true;
        }
        return false;
    }
}

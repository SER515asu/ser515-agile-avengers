package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SpikeFactory {

    private static SpikeFactory instance = null;

    private Map<UUID, Spike> spikeMap;

    private SpikeFactory() {
        spikeMap = new HashMap<>();
    }

    public static SpikeFactory getInstance() {
        if (instance == null) {
            instance = new SpikeFactory();
        }
        return instance;
    }

    public Spike createNewSpike(String name, String description) {
        UUID spikeId = UUID.randomUUID();
        Spike newSpike = new Spike(spikeId, name, description);
        spikeMap.put(spikeId, newSpike);
        return newSpike;
    }

    public Spike getSpike(UUID spikeId) {
        return spikeMap.get(spikeId);
    }

    public void removeSpike(UUID spikdId) {
        spikeMap.remove(spikdId);
    }

    public Map<UUID, Spike> getAllSpikes() {
        return new HashMap<>(spikeMap);
    }
}

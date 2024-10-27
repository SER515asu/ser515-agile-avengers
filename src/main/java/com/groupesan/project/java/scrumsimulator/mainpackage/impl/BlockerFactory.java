package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

public class BlockerFactory {

    // Singleton instance
    private static BlockerFactory instance = null;

    // Stores all created blockers
    private Map<UUID, Blocker> blockerMap;

    // Private constructor to enforce singleton pattern
    private BlockerFactory() {
        blockerMap = new HashMap<>();
    }

    // Singleton getInstance method
    public static BlockerFactory getInstance() {
        if (instance == null) {
            instance = new BlockerFactory();
        }
        return instance;
    }

    // Method to create a new Blocker object
    public Blocker createNewBlocker(String name, String description) {
        UUID blockerId = UUID.randomUUID();  // Unique ID for each blocker
        Blocker newBlocker = new Blocker(blockerId, name, description);
        blockerMap.put(blockerId, newBlocker);  // Store it in the map
        return newBlocker;
    }

    // Method to retrieve a Blocker by ID
    public Blocker getBlocker(UUID blockerId) {
        return blockerMap.get(blockerId);
    }

    // Method to remove a blocker
    public void removeBlocker(UUID blockerId) {
        blockerMap.remove(blockerId);
    }

    // Method to get all blockers
    public Map<UUID, Blocker> getAllBlockers() {
        return new HashMap<>(blockerMap);  // Return a copy of the map to avoid external modification
    }
}

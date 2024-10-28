package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.UUID;

public class SprintFactory {
    private static SprintFactory sprintFactory;

    public static SprintFactory getSprintFactory() {
        if (sprintFactory == null) {
            sprintFactory = new SprintFactory();
        }

        return sprintFactory;
    }

    private int numSprints;

    private SprintFactory() {
        numSprints = 0;
    }

    public Sprint createNewSprint(String name, String description, int length) {
        Sprint newSprint = new Sprint(name, description, length, UUID.randomUUID().toString());
        return newSprint;
    }
}

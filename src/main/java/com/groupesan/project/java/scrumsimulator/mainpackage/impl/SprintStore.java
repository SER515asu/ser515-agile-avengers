package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

public class SprintStore {
    private static SprintStore sprintStore;

    public static SprintStore getInstance() {
        if (sprintStore == null) {
            sprintStore = new SprintStore();
        }
        return sprintStore;
    }

    private List<Sprint> sprints;

    private SprintStore() {
        sprints = new ArrayList<>();
    }

    public void addSprint(Sprint sprint) {
        sprints.add(sprint);
    }

    public List<Sprint> getSprints() {
        return new ArrayList<>(sprints);
    }
}

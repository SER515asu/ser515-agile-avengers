package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;

public class ScrumMaster extends ScrumRole {

    /**
     * Constructor that creates a scum master role for use in a simulation.
     *
     * @param player
     * @param sprint
     */
    public ScrumMaster() {
        super("Scrum Master");
    }

    @Override
    public String toString() {
        return "[Scrum Master]" + super.toString();
    }
}

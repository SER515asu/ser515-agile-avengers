package com.groupesan.project.java.scrumsimulator.mainpackage.core;

public class Player extends User {

    private Simulation simulation;

    public Player(String username, ScrumRole scrumRole) {
        super(username, scrumRole);
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public String toString() {
        return "[Player] " + super.toString();
    }
}

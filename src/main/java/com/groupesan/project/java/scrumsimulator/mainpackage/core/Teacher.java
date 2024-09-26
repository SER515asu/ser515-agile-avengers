package com.groupesan.project.java.scrumsimulator.mainpackage.core;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {

    private final List<Simulation> simulations = new ArrayList<Simulation>();

    public Teacher(String username, ScrumRole scrumRole) {
        super(username, scrumRole);
    }

    public List<Simulation> getSimulations() {
        return simulations;
    }

    public void addSimulation(Simulation simulation) {
        simulations.add(simulation);
    }

    public boolean removeSimulation(Simulation simulation) {
        return simulations.remove(simulation);
    }

    public String toString() {
        return "[Teacher] " + super.toString();
    }
}

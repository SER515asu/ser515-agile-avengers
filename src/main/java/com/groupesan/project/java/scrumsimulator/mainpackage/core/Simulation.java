package com.groupesan.project.java.scrumsimulator.mainpackage.core;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Simulation {

    private String simulationName;
    private Teacher teacher;
    private final List<Player> players = new ArrayList<>();
    private int sprintCount;

    public Simulation(String simulationName, Teacher teacher, int sprintCount) {
        this.simulationName = simulationName;
        this.teacher = teacher;
        this.sprintCount = sprintCount;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean removePlayer(Player player) {
        return players.remove(player);
    }
    
    @Override
    public String toString() {
        String result = "[Simulation] " + getSimulationName() + "\n";
        result += "Sprints: " + sprintCount + "\n";
        for (Player player : players) {
            result += player + "\n";
        }
        return result;
    }
}

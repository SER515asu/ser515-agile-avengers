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
    private int sprintDuration;

    public Simulation(String simulationName, Teacher teacher, int sprintCount, int sprintDuraion) {
        this.simulationName = simulationName;
        this.teacher = teacher;
        this.sprintCount = sprintCount;
        this.sprintDuration = sprintDuraion;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean removePlayer(Player player) {
        return players.remove(player);
    }
    
    @Override
    public String toString() {
        String result = "Simulation Name: " + getSimulationName() + "\n";
        result += "Total Sprints: " + sprintCount + "\n";
        result += "Each Sprint Duration: " + sprintDuration + "\n";
        for (Player player : players) {
            result += player + "\n";
        }
        return result;
    }
}

package com.groupesan.project.java.scrumsimulator.mainpackage.core;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends User {

    private Simulation simulation;

    public Player(String username, ScrumRole scrumRole) {
        super(username, scrumRole);
    }

    public String toString() {
        return "[Player] " + super.toString();
    }
}

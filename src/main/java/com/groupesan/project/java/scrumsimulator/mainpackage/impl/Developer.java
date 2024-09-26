package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;

public class Developer extends ScrumRole {

    // Task implementation goes here once done

    public Developer(Player player) {
        super("Developer");
    }

    @Override
    public String toString() {
        return "[Developer]" + super.toString();
    }
}

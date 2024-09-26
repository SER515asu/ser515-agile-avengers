package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;

public class UserStoryIdentifier extends ScrumIdentifier {
    public UserStoryIdentifier(int value) {
        super(value);
    }

    public String toString() {
        return "US #" + this.id;
    }
}

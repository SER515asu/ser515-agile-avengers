package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;

public class UserIdentifier extends ScrumIdentifier {

    public UserIdentifier(int value) {
        super(value);
    }

    @Override
    public String toString() {
        return "User #" + this.id;
    }
}

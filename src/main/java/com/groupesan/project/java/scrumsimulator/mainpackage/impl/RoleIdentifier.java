package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;

public class RoleIdentifier extends ScrumIdentifier {

    public RoleIdentifier(int value) {
        super(value);
    }

    @Override
    public String toString() {
        return "Role #" + this.id;
    }
}

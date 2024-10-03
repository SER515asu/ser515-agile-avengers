package com.groupesan.project.java.scrumsimulator.mainpackage.core;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.ScrumIdentifierStoreSingleton;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ScrumIdentifier {
    protected int id;

    private ScrumObject thisObject;

    public ScrumIdentifier(int value) {
        id = value;
        ScrumIdentifierStoreSingleton.get().registerIdentifier(this);
    }

    public int getValue() {
        return id;
    }

    public abstract String toString();
}

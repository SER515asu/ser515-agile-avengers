package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;
import java.util.ArrayList;

public class ScrumIdentifierStoreSingleton {
    private static ScrumIdentifierStoreSingleton scrumIdentifierStoreSingleton;

    public static ScrumIdentifierStoreSingleton get() {
        if (scrumIdentifierStoreSingleton == null) {
            scrumIdentifierStoreSingleton = new ScrumIdentifierStoreSingleton();
        }
        return scrumIdentifierStoreSingleton;
    }

    private final ArrayList<ScrumIdentifier> objectList = new ArrayList<>();

    public ScrumIdentifierStoreSingleton() {}

    // The next Id will always increment even if previous Ids are deleted, etc

    /**
     * gets a new Id which is guaranteed to be unique across the entire system, even if previous IDs
     * were deleted, etc
     *
     * @return a unique integer
     */
    public int getNextId() {
        int nextIdValue = objectList.size();
        objectList.add(nextIdValue, null);
        return nextIdValue;
    }

    public void clear() {
        objectList.clear();
    }

    public void registerIdentifier(ScrumIdentifier identifier) {
        int index = identifier.getValue();
        while (objectList.size() <= index) {
            objectList.add(null);  // Expand the list to avoid nulll exception
        }
        assert objectList.get(index) == null;
        objectList.set(index, identifier);
    }

}

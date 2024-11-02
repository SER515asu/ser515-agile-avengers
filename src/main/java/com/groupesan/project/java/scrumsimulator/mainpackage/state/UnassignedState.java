package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;

public class UnassignedState extends UserStoryState {
    public UnassignedState(UserStory userStory) {
        super(userStory);
    }

    @Override
    public void editStoryStateFunction() {
        // Implement transition logic if needed
        System.out.println("Story is now Unassigned.");
    }
}


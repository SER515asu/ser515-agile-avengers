package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;

public class InProgressState extends UserStoryState {
    public InProgressState(UserStory userStory) {
        super(userStory);
    }

    @Override
    public void editStoryStateFunction() {
        System.out.println("Story is now In Progress.");
    }
}

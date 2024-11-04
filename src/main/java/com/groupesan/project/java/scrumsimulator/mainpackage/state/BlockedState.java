package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;

public class BlockedState extends UserStoryState {
    public BlockedState(UserStory userStory) {
        super(userStory);
    }

    @Override
    public void editStoryStateFunction() {
        System.out.println("Story is now blocked");
    }
}

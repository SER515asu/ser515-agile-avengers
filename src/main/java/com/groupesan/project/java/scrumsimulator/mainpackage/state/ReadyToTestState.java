package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;

public class ReadyToTestState extends UserStoryState {
    public ReadyToTestState(UserStory userStory) {
        super(userStory);
    }

    @Override
    public void editStoryStateFunction() {
        System.out.println("Story is Ready to Test.");
    }
}

package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;

public abstract class UserStoryState {
    protected UserStory userStory;

    UserStoryState(UserStory userStory) {
        this.userStory = userStory;
    }

    public abstract void editStoryStateFunction();

    public static String[] getStatusOptions() {
        return new String[]{"Unassigned", "New", "InProgress", "ReadyToTest", "Complete"};
    }
}




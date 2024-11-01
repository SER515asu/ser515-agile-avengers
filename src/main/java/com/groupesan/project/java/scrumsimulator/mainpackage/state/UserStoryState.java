package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;

public abstract class UserStoryState {
    protected UserStory userStory;

    UserStoryState(UserStory userStory) {
        this.userStory = userStory;
    }

    // Methods to change state, return a string of the state after change
    public abstract String onSelect();

    public abstract String onComplete();

    public abstract String onDelete();
}

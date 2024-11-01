package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;

public class UserStoryDeletedState extends UserStoryState {

    public UserStoryDeletedState(UserStory userStory) {
        super(userStory);
    }

    @Override
    public String onSelect() {
        return "Deleted";
    }

    @Override
    public String onComplete() {
        return "Deleted";
    }

    @Override
    public String onDelete() {
        return "Deleted";
    }
}

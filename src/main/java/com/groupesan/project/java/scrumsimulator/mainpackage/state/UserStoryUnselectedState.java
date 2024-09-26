package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;

public class UserStoryUnselectedState extends UserStoryState {

    public UserStoryUnselectedState(UserStory userStory) {
        super(userStory);
    }

    @Override
    public String onSelect() {
        userStory.changeState(new UserStorySelectedState(userStory));
        return "Selected";
    }

    @Override
    public String onComplete() {
        return "Unselected";
    }

    @Override
    public String onDelete() {
        userStory.changeState(new UserStoryDeletedState(userStory));
        return "Deleted";
    }
}

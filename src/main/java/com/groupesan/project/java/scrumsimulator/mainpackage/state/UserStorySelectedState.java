package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;

public class UserStorySelectedState extends UserStoryState {

    public UserStorySelectedState(UserStory userStory) {
        super(userStory);
    }

    @Override
    public String onSelect() {
        userStory.changeState(new UserStoryUnselectedState(userStory));
        return "Unselected";
    }

    @Override
    public String onComplete() {
        userStory.changeState(new UserStoryCompletedState(userStory));
        return "Completed";
    }

    @Override
    public String onDelete() {
        userStory.changeState(new UserStoryDeletedState(userStory));
        return "Deleted";
    }
}

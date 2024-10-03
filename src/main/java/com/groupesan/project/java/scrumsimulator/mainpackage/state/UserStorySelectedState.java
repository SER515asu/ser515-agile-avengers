package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;

public class UserStorySelectedState extends UserStoryState {

    public UserStorySelectedState(UserStory userStory) {
        super(userStory);
    }

    @Override
    public String onSelect() {
        userStory.setUserStoryState(new UserStoryUnselectedState(userStory));
        return "Unselected";
    }

    @Override
    public String onComplete() {
        userStory.setUserStoryState(new UserStoryCompletedState(userStory));
        return "Completed";
    }

    @Override
    public String onDelete() {
        userStory.setUserStoryState(new UserStoryDeletedState(userStory));
        return "Deleted";
    }
}

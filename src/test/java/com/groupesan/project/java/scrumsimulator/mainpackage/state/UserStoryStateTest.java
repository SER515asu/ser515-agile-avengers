package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import org.junit.jupiter.api.Test;

public class UserStoryStateTest {

    UserStory testUserStory = new UserStory("test", 1.0);

    @Test
    public void testUserStoryUnselectedState() {
        UserStoryUnselectedState testState = new UserStoryUnselectedState(testUserStory);
        assertAll(
                "Unselected State",
                () -> assertEquals("Selected", testState.onSelect()),
                () -> assertEquals("Unselected", testState.onComplete()),
                () -> assertEquals("Deleted", testState.onDelete()));
    }

    @Test
    public void testUserStorySelectedState() {
        UserStorySelectedState testState = new UserStorySelectedState(testUserStory);
        assertAll(
                "Selected State",
                () -> assertEquals("Unselected", testState.onSelect()),
                () -> assertEquals("Completed", testState.onComplete()),
                () -> assertEquals("Deleted", testState.onDelete()));
    }

    @Test
    public void testUserStoryCompletedState() {
        UserStoryCompletedState testState = new UserStoryCompletedState(testUserStory);
        assertAll(
                "Completed State",
                () -> assertEquals("Completed", testState.onSelect()),
                () -> assertEquals("Completed", testState.onComplete()),
                () -> assertEquals("Deleted", testState.onDelete()));
    }

    @Test
    public void testUserDeletedState() {
        UserStoryDeletedState testState = new UserStoryDeletedState(testUserStory);
        assertAll(
                "Deleted State",
                () -> assertEquals("Deleted", testState.onSelect()),
                () -> assertEquals("Deleted", testState.onComplete()),
                () -> assertEquals("Deleted", testState.onDelete()));
    }
}

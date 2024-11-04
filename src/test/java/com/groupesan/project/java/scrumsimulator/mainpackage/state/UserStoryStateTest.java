package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserStoryStateTest {
    private UserStory mockUserStory;

    @BeforeEach
    void setUp() {
        mockUserStory = new UserStory("testStory","this is a test story", 2.0, 3.0);  // Assuming UserStory has a no-args constructor
    }

    @Test
    void testGetStatusOptions() {
        String[] expectedStatuses = {"Unassigned", "New", "InProgress", "ReadyToTest", "Complete", "Blocked"};
        assertArrayEquals(expectedStatuses, UserStoryState.getStatusOptions(), "Status options should match expected array.");
    }

    @Test
    void testUnassignedStateInitialization() {
        UnassignedState unassignedState = new UnassignedState(mockUserStory);
        assertNotNull(unassignedState, "UnassignedState should be initialized.");
    }

    @Test
    void testNewStateInitialization() {
        NewState newState = new NewState(mockUserStory);
        assertNotNull(newState, "NewState should be initialized.");
    }

    @Test
    void testInProgressStateInitialization() {
        InProgressState inProgressState = new InProgressState(mockUserStory);
        assertNotNull(inProgressState, "InProgressState should be initialized.");
    }

    @Test
    void testReadyToTestStateInitialization() {
        ReadyToTestState readyToTestState = new ReadyToTestState(mockUserStory);
        assertNotNull(readyToTestState, "ReadyToTestState should be initialized.");
    }

    @Test
    void testCompleteStateInitialization() {
        CompleteState completeState = new CompleteState(mockUserStory);
        assertNotNull(completeState, "CompleteState should be initialized.");
    }
}

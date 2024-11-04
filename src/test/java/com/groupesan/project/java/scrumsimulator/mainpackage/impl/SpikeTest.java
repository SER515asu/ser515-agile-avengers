package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class USTest {

    private UserStory userStory;
    private String name;
    private String description;
    private double pointValue;
    private double businessValue;

    @BeforeEach
    void setUp() {
        name = "User Story 1";
        description = "This is a test user story.";
        pointValue = 5.0;
        businessValue = 10.0;
        userStory = new UserStory(name, description, pointValue, businessValue);
    }

    @Test
    void testUserStoryInitialization() {
        assertEquals(name, userStory.getName(), "UserStory name should be initialized correctly.");
        assertEquals(description, userStory.getDescription(), "UserStory description should be initialized correctly.");
        assertEquals(pointValue, userStory.getPointValue(), "UserStory point value should be initialized correctly.");
        assertEquals(businessValue, userStory.getBusinessValue(), "UserStory business value should be initialized correctly.");
        assertEquals("Unassigned", userStory.getState(), "UserStory should be in Unassigned state initially.");
    }

    @Test
    void testAddSpike() {
        Spike spike = new Spike(UUID.randomUUID(), "Spike 1", "Spike description");
        userStory.addSpike(spike);

        assertTrue(userStory.getLinkedSpikes().contains(spike), "Spike should be added to linkedSpikes.");
        assertTrue(spike.getLinkedUserStories().contains(userStory), "UserStory should be added to Spike's linkedUserStories.");
    }

    @Test
    void testAddSpike_Duplicate() {
        Spike spike = new Spike(UUID.randomUUID(), "Spike 1", "Spike description");

        userStory.addSpike(spike);
        userStory.addSpike(spike); // Add the same spike again

        assertEquals(1, userStory.getLinkedSpikes().size(), "Duplicate Spike should not be added.");
    }

    @Test
    void testRemoveSpike() {
        Spike spike = new Spike(UUID.randomUUID(), "Spike 1", "Spike description");
        userStory.addSpike(spike);

        userStory.removeSpike(spike);

        assertFalse(userStory.getLinkedSpikes().contains(spike), "Spike should be removed from linkedSpikes.");
        assertFalse(spike.getLinkedUserStories().contains(userStory), "UserStory should be removed from Spike's linkedUserStories.");
    }


    @Test
    void testStateTransition() {
        userStory.setState(new NewState(userStory));
        assertEquals("New", userStory.getState(), "State should transition to New.");

        userStory.setState(new InProgressState(userStory));
        assertEquals("InProgress", userStory.getState(), "State should transition to InProgress.");

        userStory.setState(new ReadyToTestState(userStory));
        assertEquals("ReadyToTest", userStory.getState(), "State should transition to ReadyToTest.");

        userStory.setState(new CompleteState(userStory));
        assertEquals("Complete", userStory.getState(), "State should transition to Complete.");
    }

    @Test
    void testBlockStory() {
        Spike spike = new Spike(UUID.randomUUID(), "Spike for Blocking", "This spike will block the story");
        String simulationId = "Sim-001";

        userStory.blockStory(spike, simulationId);

        assertTrue(userStory.isSpiked(), "UserStory should be marked as spiked.");
        assertTrue(userStory.getLinkedSpikes().contains(spike), "Spike should be added to linkedSpikes when blocking.");
        assertEquals("Blocked", userStory.getState(), "UserStory state should transition to Blocked when blocked.");
    }
}

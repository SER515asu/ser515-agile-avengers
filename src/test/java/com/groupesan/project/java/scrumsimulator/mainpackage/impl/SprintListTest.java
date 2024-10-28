// Creating an issue for it

//package com.groupesan.project.java.scrumsimulator.mainpackage.impl;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
//import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
//import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.SprintListPane;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import javax.swing.*;
//import java.awt.event.WindowEvent;
//
//public class SprintListTest {
//    private Sprint mySprint;
//    private UserStory myUserStory;
//    private String simulationId="test";
//    private String sprintId = "sdadsad";
//
//    @BeforeEach
//    public void setup() {
//        // Create a new sprint for testing
//        mySprint = new Sprint("Test Sprint", "A sprint for testing", 14, sprintId);
//        SprintStore.getInstance(simulationId).getSprints().clear();  // Clear existing sprints for a clean state
//        SprintStore.getInstance(simulationId).addSprint(mySprint);
//
//        // Create a user story for testing
//        myUserStory = UserStoryFactory.getInstance().createNewUserStory("Test User Story", "Testing description", 5.0, 4);
//        myUserStory.doRegister();  // Register to assign an ID
//    }
//
//    @Test
//    public void testAddSprint() {
//        Sprint newSprint = new Sprint("New Sprint", "Description", 7, sprintId);
//        SprintStore.getInstance(simulationId).addSprint(newSprint);
//
//        assertTrue(SprintStore.getInstance(simulationId).getSprints().contains(newSprint), "New sprint should be added.");
//    }
//
//    @Test
//    public void testRemoveSprint() {
//        SprintStore.getInstance(simulationId).removeSprint(mySprint);
//        assertFalse(SprintStore.getInstance(simulationId).getSprints().contains(mySprint), "Sprint should be removed.");
//    }
//
//    @Test
//    public void testAddUserStoryToSprint() {
//        mySprint.addUserStory(myUserStory);
//        assertTrue(mySprint.getUserStories().contains(myUserStory), "User story should be added to the sprint.");
//    }
//
//    @Test
//    public void testRemoveUserStoryFromSprint() {
//        mySprint.addUserStory(myUserStory);
//        assertTrue(mySprint.getUserStories().contains(myUserStory), "User story should be initially added to the sprint.");
//
//        mySprint.removeUserStory(myUserStory);
//        assertFalse(mySprint.getUserStories().contains(myUserStory), "User story should be removed from the sprint.");
//    }
//
//    @Test
//    public void testSprintListPaneUI() {
//        SwingUtilities.invokeLater(() -> {
//            SprintListPane sprintListPane = new SprintListPane(new Player("test", new ScrumRole("Developer")),simulationId);
//            sprintListPane.setVisible(true);
//
//            // Simulate closing the window after adding a sprint
//            WindowEvent windowClosing = new WindowEvent(sprintListPane, WindowEvent.WINDOW_CLOSING);
//            sprintListPane.dispatchEvent(windowClosing);
//
//            // Check if the frame closed successfully
//            assertFalse(sprintListPane.isVisible(), "SprintListPane should be closed.");
//        });
//    }
//
//    @Test
//    public void testAddUserStoryToSprintUI() {
//        SwingUtilities.invokeLater(() -> {
//            SprintListPane sprintListPane = new SprintListPane(new Player("test", new ScrumRole("Developer")),simulationId);
//            sprintListPane.setVisible(true);
//
//            // Simulate adding a user story to the sprint
//            mySprint.addUserStory(myUserStory);
//
//            assertTrue(mySprint.getUserStories().contains(myUserStory), "User story should be present in the sprint.");
//
//            // Simulate closing the window
//            WindowEvent windowClosing = new WindowEvent(sprintListPane, WindowEvent.WINDOW_CLOSING);
//            sprintListPane.dispatchEvent(windowClosing);
//            assertFalse(sprintListPane.isVisible(), "SprintListPane should be closed.");
//        });
//    }
//
//    @Test
//    public void testRemoveUserStoryFromSprintUI() {
//        SwingUtilities.invokeLater(() -> {
//            SprintListPane sprintListPane = new SprintListPane(new Player("test", new ScrumRole("Developer")),simulationId);
//            sprintListPane.setVisible(true);
//
//            // Add and then remove a user story from the sprint
//            mySprint.addUserStory(myUserStory);
//            assertTrue(mySprint.getUserStories().contains(myUserStory), "User story should be present before removal.");
//
//            mySprint.getUserStories().remove(myUserStory);
//            assertFalse(mySprint.getUserStories().contains(myUserStory), "User story should be removed from the sprint.");
//
//            // Simulate closing the window
//            WindowEvent windowClosing = new WindowEvent(sprintListPane, WindowEvent.WINDOW_CLOSING);
//            sprintListPane.dispatchEvent(windowClosing);
//            assertFalse(sprintListPane.isVisible(), "SprintListPane should be closed.");
//        });
//    }
//}

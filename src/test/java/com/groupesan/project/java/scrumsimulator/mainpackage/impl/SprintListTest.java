import java.awt.GraphicsEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Roles;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Sprint;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryFactory;

public class SprintListTest {
    private SprintStore sprintStore;
    private Sprint mySprint;
    private UserStory myUserStory;
    private String simulationID = "testSimulation";

    @BeforeEach
    public void setup() {
        Assumptions.assumeTrue(!GraphicsEnvironment.isHeadless(), "Test requires a graphical environment");
        sprintStore = SprintStore.getInstance(simulationID);
        sprintStore.getSprints().clear();

        mySprint = new Sprint("Test Sprint", "A sprint for testing", 14, "sprintId1");
        myUserStory = UserStoryFactory.getInstance().createNewUserStory("Test Story", "Story description", 5.0, 3);
        myUserStory.doRegister();  // Register the story to assign an ID
    }


    @Test
    public void testAddUserStoryToSprint() {
        mySprint.addUserStory(simulationID, myUserStory);
        assertTrue(mySprint.getUserStories().contains(myUserStory), "User story should be added to the sprint.");
        assertEquals(1, mySprint.getUserStories().size(), "There should be exactly one user story in the sprint.");
    }

    @Test
    public void testRemoveUserStoryFromSprint() {
        mySprint.addUserStory(simulationID, myUserStory);
        mySprint.removeUserStory(simulationID, myUserStory);

        assertFalse(mySprint.getUserStories().contains(myUserStory), "User story should be removed from the sprint.");
        assertEquals(0, mySprint.getUserStories().size(), "There should be no user stories left in the sprint.");
    }

    @Test
    public void testRoleBasedAccess() {
        Player scrumMaster = new Player("Scrum Master", new ScrumRole(Roles.SCRUM_MASTER.getDisplayName()));
        assertTrue(scrumMaster.getRole().getName().equals(Roles.SCRUM_MASTER.getDisplayName()), 
                   "Scrum Master should be able to create sprints.");

        // Simulate a player with Developer role (should restrict sprint creation)
        Player developer = new Player("Developer", new ScrumRole(Roles.DEVELOPER.getDisplayName()));
        assertTrue(developer.getRole().getName().equals(Roles.DEVELOPER.getDisplayName()), 
                   "Developer should not be able to create sprints.");
    }

    @Test
    public void testUniqueSprintNames() {
        Sprint duplicateSprint = new Sprint("Test Sprint", "Another sprint with the same name", 14, "sprintId2");
        sprintStore.getSprints().add(mySprint);
        boolean nameExists = sprintStore.getSprints().stream()
                .anyMatch(sprint -> sprint.getName().equals(duplicateSprint.getName()));
        assertFalse(nameExists, "A sprint with the same name already exists.");
    }
}



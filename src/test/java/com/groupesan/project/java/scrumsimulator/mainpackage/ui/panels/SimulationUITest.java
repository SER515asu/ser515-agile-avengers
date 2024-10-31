package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.GraphicsEnvironment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Roles;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;

public class SimulationUITest {

    private SimulationUI simulationUI;

    @BeforeEach
    public void setUp() {
        Assumptions.assumeTrue(!GraphicsEnvironment.isHeadless(), "Test requires a graphical environment");
        simulationUI = new SimulationUI();
    }

    @Test
    public void testScrumMasterRoleAccess() {
        simulationUI.setUserRole(new ScrumRole(Roles.SCRUM_MASTER.getDisplayName()));
        simulationUI.updateUI();

        assertTrue(simulationUI.isButtonVisible("Sprints"));
        assertTrue(simulationUI.isButtonVisible("Product Backlog"));
        assertFalse(simulationUI.isButtonVisible("Update User Story Status"));
        assertTrue(simulationUI.isButtonVisible("Switch Role"));
        assertTrue(simulationUI.isButtonVisible("Blockers"));
    }

    @Test
    public void testProductOwnerRoleAccess() {
        simulationUI.setUserRole(new ScrumRole(Roles.PRODUCT_OWNER.getDisplayName()));
        simulationUI.updateUI();

        assertFalse(simulationUI.isButtonVisible("Sprints"));
        assertTrue(simulationUI.isButtonVisible("Product Backlog"));
        assertFalse(simulationUI.isButtonVisible("Update User Story Status"));
        assertTrue(simulationUI.isButtonVisible("Switch Role"));
        assertTrue(simulationUI.isButtonVisible("Blockers"));
    }

    @Test
    public void testDeveloperRoleAccess() {
        simulationUI.setUserRole(new ScrumRole(Roles.DEVELOPER.getDisplayName()));
        simulationUI.updateUI();

        assertTrue(simulationUI.isButtonVisible("Sprints"));
        assertFalse(simulationUI.isButtonVisible("Product Backlog"));
        assertTrue(simulationUI.isButtonVisible("Update User Story Status"));
        assertTrue(simulationUI.isButtonVisible("Switch Role"));
        assertFalse(simulationUI.isButtonVisible("Blockers"));
    }

    @Test
    public void testRoleSwitchingUpdatesUI() {
        simulationUI.setUserRole(new ScrumRole(Roles.DEVELOPER.getDisplayName()));
        simulationUI.updateUI();

        assertFalse(simulationUI.isButtonVisible("Blockers"));

        // Switch role to Scrum Master
        simulationUI.setUserRole(new ScrumRole(Roles.SCRUM_MASTER.getDisplayName()));
        simulationUI.updateUI();

        assertTrue(simulationUI.isButtonVisible("Blockers"));
    }
}

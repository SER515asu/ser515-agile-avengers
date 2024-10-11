package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DemoPaneTest {

    @Test
    public void testPaneExistence() {
        // nominal test to verify the existence of the DemoPane class
        try {
            Class.forName(
                    "com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.DemoPane");
            assertTrue(true, "DemoPane class exists");
        } catch (ClassNotFoundException e) {
            assertTrue(false, "DemoPane class does not exist");
        }
    }

    public void testProductOwnerDisablesSprintButton() {
        // Set the player's role to Product Owner
        demoPane.switchRole("Product Owner");

        // Verify that the Sprints button is disabled
        window.button("Sprints").requireDisabled();
}

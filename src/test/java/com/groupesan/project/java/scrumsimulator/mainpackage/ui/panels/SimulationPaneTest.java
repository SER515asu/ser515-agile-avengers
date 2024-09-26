package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SimulationPaneTest {

    @Test
    public void testPaneExistence() {
        // nominal test to verify the existence of the SimulationPane class
        try {
            Class.forName(
                    "com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.SimulationPane");
            assertTrue(true, "SimulationPane class exists");
        } catch (ClassNotFoundException e) {
            assertTrue(false, "SimulationPane class does not exist");
        }
    }
}

package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationManager;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ModifySimulationPaneTest {

    @Test
    public void testPaneExistence() {
        // nominal test to verify the existence of the ModifySimulationPane class
        try {
            Class.forName(
                    "com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.ModifySimulationPane");
            assertTrue(true, "ModifySimulationPane class exists");
        } catch (ClassNotFoundException e) {
            assertTrue(false, "ModifySimulationPane class does not exist");
        }
    }

    @Test
    public void testInitialization() {
        // Ignored if run in a non-GUI environment
        Assumptions.assumeTrue(!GraphicsEnvironment.isHeadless(), "Test requires a graphical environment");
        SimulationManager simulationManager = new SimulationManager();
        ModifySimulationPane modifySimulationPane = new ModifySimulationPane(simulationManager);

        assertEquals("Modify Simulation", modifySimulationPane.getTitle());
        assertEquals(600, modifySimulationPane.getWidth());
        assertEquals(400, modifySimulationPane.getHeight());
        assertFalse(modifySimulationPane.isVisible());

        JComboBox<String> simulationNameField = modifySimulationPane.getSimulationNameField();
        assertNotNull(simulationNameField);
        assertTrue(simulationNameField.getItemCount() > 0);

        JTextField numberOfSprintsField = modifySimulationPane.getNumberOfSprintsField();
        JTextField lengthOfSprintField = modifySimulationPane.getLengthOfSprintField();

        assertNotNull(numberOfSprintsField);
        assertNotNull(lengthOfSprintField);
    }

}

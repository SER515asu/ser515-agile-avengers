package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimulationStateTest {

    private SimulationStateManager simulationStateManager;

    @BeforeEach
    public void setUp() {
        simulationStateManager = new SimulationStateManager();
    }

    @AfterEach
    public void tearDown() {
        simulationStateManager = null;
    }

    @Test
    public void testInitialState() {
        assertFalse(simulationStateManager.isRunning());
    }

    @Test
    public void testStartSimulation() {
        simulationStateManager.startSimulation();
        assertTrue(simulationStateManager.isRunning());
    }

    @Test
    public void testStopSimulation() {
        simulationStateManager.stopSimulation();
        assertFalse(simulationStateManager.isRunning());
    }
}

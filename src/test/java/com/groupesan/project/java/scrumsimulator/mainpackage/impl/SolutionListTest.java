package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionListTest {
    private Solution mySolution;
    private String mySimulationId;

    @BeforeEach
    public void setup(){
        Assumptions.assumeTrue(!GraphicsEnvironment.isHeadless(), "Test requires a graphical environment");
        mySolution = SolutionFactory.getSolutionFactory().createNewSolution("Solution1","Solution1 description");
        mySimulationId = "random_test_id";
    }

    @Test
    public void testAddSolution(){
        // Create a new solution
        Solution newSolution = SolutionFactory.getSolutionFactory().createNewSolution("Test solution", "Test description");

        // Add solution to the store
        SolutionStore.getInstance(mySimulationId).addSolution(newSolution);
        assertTrue(SolutionStore.getInstance(mySimulationId).getSolutions().contains(newSolution));

    }

    @Test
    public void testRemoveSolution(){
        SolutionStore.getInstance(mySimulationId).removeSolution(mySolution);
        assertFalse(SolutionStore.getInstance(mySimulationId).getSolutions().contains(mySolution), "My solution should not be in solution store.");
    }

    @Test
    public void testSolutionDetails(){
        Solution newSolution = SolutionFactory.getSolutionFactory().createNewSolution("Test solution", "Test description");
        SolutionStore.getInstance(mySimulationId).addSolution(newSolution);
        Solution savedSolution = SolutionStore.getInstance(mySimulationId).getSolutions().getFirst();

        assertEquals(newSolution.getTitle(), savedSolution.getTitle());
        assertEquals(newSolution.getDescription(), savedSolution.getDescription());
        assertEquals(newSolution.getId(), savedSolution.getId());
    }

    @Test
    public void testProbabilityRangeValues() {
        mySolution.setProbabilityRangeMinimum((float) 0.2);
        mySolution.setProbabilityRangeMaximum((float) 0.7);
        assertEquals((float) 0.2, mySolution.getProbabilityRangeMinimum());
        assertEquals((float) 0.7, mySolution.getProbabilityRangeMaximum());
    }
}

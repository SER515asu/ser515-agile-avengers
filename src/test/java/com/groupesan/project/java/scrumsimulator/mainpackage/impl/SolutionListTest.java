package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionListTest {
    private Solution mySolution;

    @BeforeEach
    public void setup(){
        SolutionStore.getInstance().getSolutions().clear(); // Clear existing solutions
    }

    @Test
    public void testAddSolution(){
        // Create a new solution
        Solution newSolution = SolutionFactory.getSprintFactory().createNewSolution("Test solution", "Test description");

        // Add solution to the store
        SolutionStore.getInstance().addSolution(newSolution);

        mySolution = newSolution;

        assertTrue(SolutionStore.getInstance().getSolutions().contains(newSolution), "New solution should be added");
    }

    @Test
    public void testRemoveSolution(){
        SolutionStore.getInstance().removeSolution(mySolution);

        assertFalse(SolutionStore.getInstance().getSolutions().contains(mySolution), "My solution should not be in solution store.");
    }

    @Test
    public void testSolutionDetails(){
        Solution newSolution = SolutionFactory.getSprintFactory().createNewSolution("Test solution", "Test description");

        SolutionStore.getInstance().addSolution(newSolution);

        Solution savedSolution = SolutionStore.getInstance().getSolutions().getFirst();

        assertEquals(newSolution.getTitle(), savedSolution.getTitle());
        assertEquals(newSolution.getDescription(), savedSolution.getDescription());
        assertEquals(newSolution.getId(), savedSolution.getId());
    }

}

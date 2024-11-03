package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class BlockerListTest {
    private Blocker myBlocker;
    private String mySimulationId;

    @BeforeEach
    public void setup() {
        // Create a new blocker for testing
        myBlocker =
                BlockerFactory.getInstance()
                        .createNewBlocker("Blocker1","Blocker1description");
        mySimulationId = "random_test_id";
    }

    //Testing AddBlocker functionality
//    @Test
//    public void testAddBlocker(){
//        Blocker newBlocker = BlockerFactory.getInstance().createNewBlocker("Test Blocker", "Test description");
//        BlockerStore.getInstance(mySimulationId).addBlocker(newBlocker);
//        assertTrue(BlockerStore.getInstance(mySimulationId).getAllBlockers().contains(newBlocker));
//    }

    //Testing doRegister functionality on blocker creation
//    @Test
//    public void testBlockerRegistered() {
//        BlockerStore.getInstance(mySimulationId).addBlocker(myBlocker);
//        UUID id = myBlocker.getId();
//        assertNotNull(id);
//    }

    //Testing blocker details after creation
    @Test
    public void testBlockerDetails() {
        String blockerName = myBlocker.getName();
        String blockerDescription = myBlocker.getDescription();
        assertEquals("Blocker1", blockerName);
        assertEquals("Blocker1description", blockerDescription);
    }

    @Test
    public void testInitialProbabilityValues(){
        // Initially no probabilities are set, so the expected value is 0.0
        assertEquals(0.0, myBlocker.getProbabilityRangeStart());
        assertEquals(0.0, myBlocker.getProbabilityRangeEnd());
    }

    @Test
    public void testUpdatedProbabilityValues(){
        myBlocker.setProbabilityRangeStart((float) 0.3);
        myBlocker.setProbabilityRangeEnd((float) 0.5);

        assertEquals((float)0.3, myBlocker.getProbabilityRangeStart());
        assertEquals((float) 0.5, myBlocker.getProbabilityRangeEnd());
    }

}
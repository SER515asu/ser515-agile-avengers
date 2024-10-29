package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class BlockerListTest {
    private Blocker myBlocker;

    @BeforeEach
    public void setup() {
        // Create a new blocker for testing
        myBlocker =
                BlockerFactory.getInstance()
                        .createNewBlocker("Blocker1","Blocker1description");
    }

    //Testing AddBlocker functionality
    @Test
    public void testAddBlocker(){
        Blocker newBlocker = BlockerFactory.getInstance().createNewBlocker("Test Blocker", "Test description");
        BlockerStore.getInstance().addBlocker(newBlocker);
        assertTrue(BlockerStore.getInstance().getAllBlockers().contains(newBlocker));
    }

    //Testing doRegister functionality on blocker creation
    @Test
    public void testBlockerRegistered() {
        myBlocker.doRegister();
        UUID id = myBlocker.getId();
        assertNotNull(id);
    }

    //Testing blocker details after creation
    @Test
    public void testBlockerDetails() {
        String blockerName = myBlocker.getName();
        String blockerDescription = myBlocker.getDescription();
        assertEquals("Blocker1", blockerName);
        assertEquals("Blocker1description", blockerDescription);
    }

}
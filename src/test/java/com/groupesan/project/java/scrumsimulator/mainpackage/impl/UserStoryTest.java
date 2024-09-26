package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserStoryTest {
    private UserStory myUserStory;

    @BeforeEach
    public void setup() {
        myUserStory =
                UserStoryFactory.getInstance()
                        .createNewUserStory("predefinedUS1", "description1", 1.0);
    }

    @Test
    public void testUserStoryUnregistered1() {
        // modified from example code from Baeldung
        // https://www.baeldung.com/junit-assert-exception

        Exception exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> {
                            ScrumIdentifier id = myUserStory.getId();
                        });

        String actualMessage = exception.getMessage();

        assertEquals(
                "This UserStory has not been registered and does not have an ID yet!",
                actualMessage);
    }

    /** Test case to ensure that toString handles the unregistered state */
    @Test
    public void testUserStoryUnregistered2() {
        String string = myUserStory.toString();

        assertEquals("(unregistered) - predefinedUS1", string);
    }

    @Test
    public void testUserStoryRegistered() {
        myUserStory.doRegister();

        ScrumIdentifier id = myUserStory.getId();

        assertNotNull(id);
    }
}

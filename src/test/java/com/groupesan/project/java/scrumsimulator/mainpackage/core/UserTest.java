package com.groupesan.project.java.scrumsimulator.mainpackage.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User myUser;

    private static ScrumRole dev;

    @BeforeAll
    public static void preSetup() {
        dev = new ScrumRole("developer");
        dev.doRegister();
    }

    @BeforeEach
    public void setup() {
        myUser = new User("bob", dev);
    }

    @Test
    public void testUserUnregistered1() {
        // modified from example code from Baeldung
        // https://www.baeldung.com/junit-assert-exception

        Exception exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> {
                            ScrumIdentifier id = myUser.getId();
                        });

        String actualMessage = exception.getMessage();

        assertEquals(
                "This User has not been registered and does not have an ID yet!", actualMessage);
    }

    @Test
    public void testUserUnregistered2() {
        String string = myUser.toString();

        assertEquals("bob " + dev.toString() + " (unregistered)", string);
    }

    @Test
    public void testUserRegistered() {
        myUser.doRegister();

        ScrumIdentifier id = myUser.getId();

        assertNotNull(id);
    }
}

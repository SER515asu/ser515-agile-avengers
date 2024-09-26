package com.groupesan.project.java.scrumsimulator.mainpackage.core;

import static org.junit.jupiter.api.Assertions.*;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.ScrumIdentifierStoreSingleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScrumRoleTest {
    private ScrumRole myScrumRole;

    @BeforeEach
    public void setup() {
        myScrumRole = new ScrumRole("testRole");
        ScrumIdentifierStoreSingleton.get().clear();
    }

    @Test
    public void testRoleUnregistered1() {
        // modified from example code from Baeldung
        // https://www.baeldung.com/junit-assert-exception

        Exception exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> {
                            ScrumIdentifier id = myScrumRole.getId();
                        });

        String actualMessage = exception.getMessage();

        assertEquals(
                "This Role has not been registered and does not have an ID yet!", actualMessage);
    }

    @Test
    public void testRoleUnregistered2() {

        String string = myScrumRole.toString();

        assertEquals("testRole (unregistered)", string);
    }

    @Test
    public void testRoleRegistered() {
        myScrumRole.doRegister();

        ScrumIdentifier id = myScrumRole.getId();

        assertNotNull(id);
    }

    @Test
    public void testToString() {
        assertEquals("testRole (unregistered)", myScrumRole.toString());
        myScrumRole.doRegister();
        assertEquals("testRole (Role #0)", myScrumRole.toString());
    }

    @Test
    public void testEquals() {
        ScrumRole otherRole = new ScrumRole("testRole");
        assertEquals(otherRole, myScrumRole);
        myScrumRole.doRegister();
        assertNotEquals(otherRole, myScrumRole);
        otherRole.doRegister();
        assertNotEquals(otherRole, myScrumRole);
    }
}

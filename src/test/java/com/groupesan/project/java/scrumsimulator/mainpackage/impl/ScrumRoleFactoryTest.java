package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScrumRoleFactoryTest {
    private ScrumRole defaultRole;

    private static final ScrumRoleFactory factory = ScrumRoleFactory.getInstance();

    @BeforeEach
    public void setup() {
        defaultRole = new ScrumRole("default");
    }

    @Test
    public void testCreateNewDeveloper() {
        Player player = new Player("Player", defaultRole);
        assertTrue(factory.createNewDeveloper(player, "Developer") instanceof Developer);
    }

    @Test
    public void testCreateNewScrumMaster() {
        Player player = new Player("Player", defaultRole);
        Sprint sprint = new Sprint(null, null, 0, 0);
        assertTrue(
                factory.createNewScrumMaster(player, "Scrum Master", sprint)
                        instanceof ScrumMaster);
    }

    @Test
    public void testCreateNewProductOwner() {
        Teacher teacher = new Teacher("Teacher", defaultRole);
        assertTrue(factory.createNewProductOwner(teacher, "Product Owner") instanceof ProductOwner);
    }
}

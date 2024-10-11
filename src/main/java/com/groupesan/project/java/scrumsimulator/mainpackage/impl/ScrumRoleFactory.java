package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Teacher;

public class ScrumRoleFactory {

    private static ScrumRoleFactory scrumRoleFactory;

    /**
     * Static instance of factory
     *
     * @return ScrumRoleFactory
     */
    public static ScrumRoleFactory getInstance() {
        if (scrumRoleFactory == null) {
            scrumRoleFactory = new ScrumRoleFactory();
        }
        return scrumRoleFactory;
    }

    private ScrumRoleFactory() {}

    /*
     *Method producing a new developer role
     *
     * @return Developer
     */
    public ScrumRole createNewDeveloper(Player player, String selection) {
        ScrumRole newRole;
        if (selection.equals("Developer")) {
            newRole = new Developer(player);
        } else {
            newRole = null;
        }
        return newRole;
    }

    /*
     *Method producing a new scrum master role
     *
     * @return ScrumMaster
     */
    public ScrumRole createNewScrumMaster(Player player, String selection, Sprint sprint) {
        ScrumRole newRole;
        if (selection.equals("Scrum Master")) {
            newRole = new ScrumMaster();
        } else {
            newRole = null;
        }
        return newRole;
    }

    /*
     *Method producing a new product owner role
     *
     * @return Product Owner
     */
    public ScrumRole createNewProductOwner(Teacher teacher, String selection) {
        ScrumRole newRole;
        if (selection.equals("Product Owner")) {
            newRole = new ProductOwner();
        } else {
            newRole = null;
        }
        return newRole;
    }
}

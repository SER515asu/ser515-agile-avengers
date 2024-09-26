package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import java.util.ArrayList;
import java.util.List;

public class RoleStore {

    private static RoleStore roleStore;
    private final List<ScrumRole> scrumRoles;

    public static RoleStore get() {
        if (roleStore == null) {
            roleStore = new RoleStore();
        }
        return roleStore;
    }

    private RoleStore() {
        scrumRoles = new ArrayList<>();
    }

    public void addRole(ScrumRole scrumRole) {
        if (!scrumRole.isRegistered()) {
            scrumRole.doRegister();
        }
        scrumRoles.add(scrumRole);
    }

    public void removeRole(int index) {
        scrumRoles.remove(index);
    }

    public List<ScrumRole> getRoles() {
        return new ArrayList<>(scrumRoles);
    }
}

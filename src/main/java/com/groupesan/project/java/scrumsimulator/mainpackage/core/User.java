package com.groupesan.project.java.scrumsimulator.mainpackage.core;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.ScrumIdentifierStoreSingleton;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserIdentifier;

public class User extends ScrumObject {
    private String name;
    private ScrumRole scrumRole;
    private ScrumIdentifier id;

    public User(String username) {
        this.name = username;
    }

    public User(String username, ScrumRole scrumRole) {
        this(username);
        this.scrumRole = scrumRole;
    }

    public void register() {
        this.id = new UserIdentifier(ScrumIdentifierStoreSingleton.get().getNextId());
        this.scrumRole.doRegister();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScrumRole getRole() {
        return scrumRole;
    }

    public void setRole(ScrumRole scrumRole) {
        this.scrumRole = scrumRole;
    }

    public UserIdentifier getId() {
        if (!isRegistered()) {
            throw new IllegalStateException(
                    "This User has not been registered and does not have an ID yet!");
        }
        return (UserIdentifier) id;
    }

    public String toString() {
        if (isRegistered()) {
            return this.getId().toString();
        }
        return getName() + " " + getRole() + " (unregistered)";
    }
}

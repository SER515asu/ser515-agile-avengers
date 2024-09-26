package com.groupesan.project.java.scrumsimulator.mainpackage.core;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.RoleIdentifier;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.ScrumIdentifierStoreSingleton;

public class ScrumRole extends ScrumObject {
    private String name;

    private RoleIdentifier id;

    public ScrumRole(String name) {
        this.name = name;
    }

    protected void register() {
        this.id = new RoleIdentifier(ScrumIdentifierStoreSingleton.get().getNextId());
    }

    /**
     * Gets the name of the role.
     *
     * @return The name of the role
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the id of the role
     *
     * @return the {@link ScrumIdentifier} id
     */
    public ScrumIdentifier getId() {
        if (!isRegistered()) {
            throw new IllegalStateException(
                    "This Role has not been registered and does not have an ID yet!");
        }
        return id;
    }

    @Override
    public String toString() {
        if (isRegistered()) {
            return getName() + " (" + this.getId().toString() + ")";
        }
        return getName() + " (unregistered)";
    }

    public int hashCode() {
        assert false : "hashCode not designed";
        return 42;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj instanceof ScrumRole) {
            if (isRegistered() && ((ScrumRole) obj).isRegistered()) {
                return id.equals(((ScrumRole) obj).getId());
            }
            if ((!isRegistered() && ((ScrumRole) obj).isRegistered())
                    || (isRegistered() && !((ScrumRole) obj).isRegistered())) {
                return false;
            }
            return getName().equals(((ScrumRole) obj).getName());
        }
        return super.equals(obj);
    }
}

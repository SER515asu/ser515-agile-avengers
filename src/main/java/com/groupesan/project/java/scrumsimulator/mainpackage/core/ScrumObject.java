package com.groupesan.project.java.scrumsimulator.mainpackage.core;

public abstract class ScrumObject {

    private boolean registered = false;

    public boolean isRegistered() {
        return registered;
    }

    protected abstract void register();

    /**
     * Call this to register a ScrumObject. When this is called, the ScrumObject's ID will be
     * assigned, which is an irreversible action since ScrumObject IDs are unique throughout the
     * whole system. After this method is called, the object's isFinalized Method will return true;
     */
    public void doRegister() {
        if (!isRegistered()) {
            this.register();
            this.registered = true;
        }
    }

    public abstract ScrumIdentifier getId();

    public abstract String getName();

    @Override
    public abstract String toString();
}

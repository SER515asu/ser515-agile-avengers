package com.groupesan.project.java.scrumsimulator.mainpackage.core;

public enum Roles {
    SCRUM_MASTER("Scrum Master"),
    PRODUCT_OWNER("Product Owner"),
    DEVELOPER("Developer"),
    TESTER("Tester");
    
    private final String displayName;
    Roles(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}

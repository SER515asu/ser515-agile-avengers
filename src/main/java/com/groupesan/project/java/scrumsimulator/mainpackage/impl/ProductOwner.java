package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Roles;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;

public class ProductOwner extends ScrumRole {
    // Task implementation goes here once done

    public ProductOwner() {
        super(Roles.PRODUCT_OWNER.getDisplayName());
    }

    @Override
    public String toString() {
        return "[Teacher]" + super.toString();
    }
}

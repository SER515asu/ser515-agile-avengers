package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

public enum CreateEditMode {
    CREATE("Create"),
    EDIT("Edit");

    private final String text;

    CreateEditMode(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

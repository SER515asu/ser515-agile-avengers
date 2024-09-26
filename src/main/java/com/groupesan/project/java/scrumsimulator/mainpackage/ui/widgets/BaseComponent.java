package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

/**
 * This interface defines methods that should be implemented in all components. All ui components
 * should implement this interface to ensure consistency.
 */
public interface BaseComponent {
    /**
     * This function should be used for everything UI related for the component instance. This will
     * help keep ui and logic separate.
     */
    void init();
}

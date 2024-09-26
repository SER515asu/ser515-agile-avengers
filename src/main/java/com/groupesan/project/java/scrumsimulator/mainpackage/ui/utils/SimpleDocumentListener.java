package com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SimpleDocumentListener implements DocumentListener {
    public void changed(DocumentEvent event) {}

    @Override
    public void insertUpdate(DocumentEvent event) {
        changed(event);
    }

    @Override
    public void removeUpdate(DocumentEvent event) {
        changed(event);
    }

    @Override
    public void changedUpdate(DocumentEvent event) {
        changed(event);
    }
}

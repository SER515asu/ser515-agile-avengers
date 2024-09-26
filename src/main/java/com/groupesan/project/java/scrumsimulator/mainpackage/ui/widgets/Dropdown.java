package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.DataModel;
import javax.swing.*;

public class Dropdown<T> extends ScrumLabeledComponent<JComboBox<T>, DataModel<T>> {
    public Dropdown(String labelText, JComboBox<T> component, DataModel<T> model) {
        super(labelText, component, model);
    }

    public void init() {
        super.init();
        component.addActionListener(e -> model.setData((T) component.getSelectedItem()));
    }
}

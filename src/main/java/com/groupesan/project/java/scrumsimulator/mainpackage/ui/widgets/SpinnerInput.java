package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.DataModel;
import javax.swing.JSpinner;

/** Reusable JSpinner input */
public class SpinnerInput extends ScrumLabeledComponent<JSpinner, DataModel<Object>> {
    public SpinnerInput(String labelText, JSpinner component, DataModel<Object> model) {
        super(labelText, component, model);
    }

    public void init() {
        super.init();
        component.addChangeListener(
                event -> {
                    model.setData(component.getValue());
                    System.out.println(model.getData());
                });
    }
}

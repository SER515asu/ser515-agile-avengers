package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.DataModel;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.SimpleDocumentListener;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

/** Reusable JTextField component. */
public class TextInput extends ScrumLabeledComponent<JTextField, DataModel<String>> {
    public TextInput(String labelText, JTextField component, DataModel<String> model) {
        super(labelText, component, model);
    }

    public void init() {
        super.init();
        component
                .getDocument()
                .addDocumentListener(
                        new SimpleDocumentListener() {
                            public void changed(DocumentEvent event) {
                                model.setData(component.getText());
                            }
                        });
    }
}

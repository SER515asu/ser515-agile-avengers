package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.DataModel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Basic component that can be used to display components next to a label.
 *
 * @param <T> The component type to use
 * @param <U> The data model type to use
 */
public class ScrumLabeledComponent<T extends JComponent, U extends DataModel<?>> extends JPanel
        implements BaseComponent {

    protected final T component;
    protected final String labelText;
    protected final U model;

    public ScrumLabeledComponent(String labelText, T component, U model) {
        this.labelText = labelText;
        this.component = component;
        this.model = model;
        this.init();
    }

    @Override
    public void init() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(2, 2, 2, 2));
        add(new JLabel(this.labelText), BorderLayout.WEST);
        add(component, BorderLayout.CENTER);
    }
}

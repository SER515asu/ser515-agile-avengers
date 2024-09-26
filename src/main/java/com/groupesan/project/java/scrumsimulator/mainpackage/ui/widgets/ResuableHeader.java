package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Reusable header component with an optional subheader. */
public class ResuableHeader extends JPanel implements BaseComponent {
    private final String headerText;
    private String subheaderText = null;

    public ResuableHeader(String headerText) {
        this.headerText = headerText;
        this.init();
    }

    public ResuableHeader(String headerText, String subheaderText) {
        this.headerText = headerText;
        this.subheaderText = subheaderText;
        this.init();
    }

    @Override
    public void init() {
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        JLabel headerLabel = new JLabel(headerText);
        headerLabel.setFont(headerLabel.getFont().deriveFont(20.0f));
        add(headerLabel);

        if (subheaderText != null) {
            JLabel subheaderLabel = new JLabel(subheaderText);
            subheaderLabel.setFont(subheaderLabel.getFont().deriveFont(12.f));
            add(subheaderLabel);
        }
    }
}

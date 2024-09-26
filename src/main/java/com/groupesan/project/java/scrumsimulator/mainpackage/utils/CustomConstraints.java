package com.groupesan.project.java.scrumsimulator.mainpackage.utils;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CustomConstraints extends GridBagConstraints {
    public CustomConstraints(int gridx, int gridy, int anchor, int fill) {
        this.gridx = gridx;
        this.gridy = gridy;
        this.anchor = anchor;
        this.fill = fill;
        this.insets = new Insets(5, 5, 5, 5);
    }

    public CustomConstraints(
            int gridx, int gridy, int anchor, double weightx, double weighty, int fill) {
        this.gridx = gridx;
        this.gridy = gridy;
        this.anchor = anchor;
        this.weightx = weightx;
        this.weighty = weighty;
        this.fill = fill;
        this.insets = new Insets(5, 5, 5, 5);
    }
}

package com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils;

import java.awt.*;

public class GridBagConstraintsBuilder extends GridBagConstraints {
    public GridBagConstraintsBuilder setGridX(int gridx) {
        this.gridx = gridx;
        return this;
    }

    public GridBagConstraintsBuilder setGridY(int gridy) {
        this.gridy = gridy;
        return this;
    }

    public GridBagConstraintsBuilder setGridWidth(int gridwidth) {
        this.gridwidth = gridwidth;
        return this;
    }

    public GridBagConstraintsBuilder setGridHeight(int gridheight) {
        this.gridheight = gridheight;
        return this;
    }

    public GridBagConstraintsBuilder setWeightX(double weightx) {
        this.weightx = weightx;
        return this;
    }

    public GridBagConstraintsBuilder setWeightY(double weighty) {
        this.weighty = weighty;
        return this;
    }

    public GridBagConstraintsBuilder setAnchor(int anchor) {
        this.anchor = anchor;
        return this;
    }

    public GridBagConstraintsBuilder setFill(int fill) {
        this.fill = fill;
        return this;
    }

    public GridBagConstraintsBuilder setInsets(Insets insets) {
        this.insets = insets;
        return this;
    }

    public GridBagConstraintsBuilder setIPadX(int ipadx) {
        this.ipadx = ipadx;
        return this;
    }

    public GridBagConstraintsBuilder setIPadY(int ipady) {
        this.ipady = ipady;
        return this;
    }
}

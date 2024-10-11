package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Sprint;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class SprintWidget extends JPanel {

    private Sprint sprint;
    private boolean isSelected = false;
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel lengthLabel;

    public SprintWidget(Sprint sprint) {
        this.sprint = sprint;
        this.init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        nameLabel = new JLabel(sprint.getName());
        descriptionLabel = new JLabel(sprint.getDescription());
        lengthLabel = new JLabel("Length: " + sprint.getLength());

        add(
                nameLabel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                descriptionLabel,
                new CustomConstraints(
                        1, 0, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                lengthLabel,
                new CustomConstraints(
                        2, 0, GridBagConstraints.WEST, 0.5, 0.0, GridBagConstraints.HORIZONTAL));

        setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleSelection();
            }
        });
    }

    private void toggleSelection() {
        isSelected = !isSelected;
        setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
        repaint();
    }

    public boolean isSelected() {
        return isSelected;
    }

    public Sprint getSprint() {
        return sprint;
    }
}

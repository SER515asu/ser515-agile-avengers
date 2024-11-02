package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Solution;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import java.awt.*;

public class SolutionWidget extends JPanel implements BaseComponent {
    JLabel id;
    JLabel title;
    JLabel desc;
    JLabel blocker;

    private Solution mySolution;
    private String simulationId;

    public SolutionWidget(Solution solution, String simulationId){
        this.mySolution = solution;
        this.simulationId = simulationId;
        this.init();
    }

    public void init(){
        removeAll();

        id = new JLabel(String.valueOf(mySolution.getId()));// check this
        title = new JLabel(mySolution.getTitle());
        desc = new JLabel(mySolution.getDescription());
        blocker = new JLabel(""); // Change this when linked to blocker

        GridBagLayout myGridBagLayout = new GridBagLayout();
        setLayout(myGridBagLayout);

        add(
                id,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        add(
                title,
                new CustomConstraints(
                        1, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        add(
                desc,
                new CustomConstraints(
                        2, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        add(
                blocker,
                new CustomConstraints(
                        3, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        revalidate();
        repaint();
    }
}

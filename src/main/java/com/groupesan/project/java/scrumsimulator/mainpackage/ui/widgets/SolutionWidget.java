package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Solution;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import java.awt.*;

public class SolutionWidget extends JPanel implements BaseComponent {
    private JLabel id;
    private JLabel title;
    private JLabel desc;

    // Flag to ensure the headers are only added once
    private static boolean headersAdded = false;
    private Solution mySolution;
    private String simulationId;

    public SolutionWidget(Solution solution, String simulationId){
        this.mySolution = solution;
        this.simulationId = simulationId;
        this.init();
    }

    public void init(){
        // Initialize layout
        setLayout(new GridBagLayout());

        // Add headers only once (when the first SolutionWidget is created)
        if (!headersAdded) {
            addHeaders();
            headersAdded = true;
        }

        // Initialize labels for solution details
        id = new JLabel(String.valueOf(mySolution.getId()));
        title = new JLabel(mySolution.getTitle());
        desc = new JLabel(mySolution.getDescription());


        // Set alignment for each label
        id.setHorizontalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        desc.setHorizontalAlignment(SwingConstants.CENTER);

        // Add solution details to the panel
        add(id, new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(title, new CustomConstraints(1, 1, GridBagConstraints.WEST, 0.3, 0.0, GridBagConstraints.HORIZONTAL));
        add(desc, new CustomConstraints(2, 1, GridBagConstraints.WEST, 0.4, 0.0, GridBagConstraints.HORIZONTAL));

        revalidate();
        repaint();
    }

    // Method to add header labels
    private void addHeaders() {
        JLabel idHeader = new JLabel("ID");
        JLabel titleHeader = new JLabel("Title");
        JLabel descHeader = new JLabel("Description");


        // Center align the headers
        idHeader.setHorizontalAlignment(SwingConstants.CENTER);
        titleHeader.setHorizontalAlignment(SwingConstants.CENTER);
        descHeader.setHorizontalAlignment(SwingConstants.CENTER);
    }

    // Optional: Method to reset the headersAdded flag
    public static void resetHeadersAddedFlag() {
        headersAdded = false;
    }
}

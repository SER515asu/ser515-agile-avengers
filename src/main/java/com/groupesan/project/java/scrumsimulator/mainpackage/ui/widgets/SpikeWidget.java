package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Spike;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.SpikeListPanel;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.UserStorySelectionPanel;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.StartSpikePanel;
public class SpikeWidget extends JPanel implements BaseComponent {

    JLabel id;
    JLabel name;
    JLabel description;
    JButton linkUserStoryButton;
    JButton resolveButton;
    JLabel status;

    // Flag to ensure the headers are only added once
    private static boolean headersAdded = false;

    @Getter
    private Spike spike;
    private SpikeListPanel parentPane;
    private int sequenceNumber; // Sequence number for display as ID

    public SpikeWidget(Spike spike, int sequenceNumber, SpikeListPanel parentPane) {
        this.spike = spike;
        this.sequenceNumber = sequenceNumber;
        this.parentPane = parentPane;
        this.init();
    }

    public void init() {
        removeAll();

        if (parentPane.getWidgets().isEmpty()) {
            resetHeadersAddedFlag();
        }

        if (!headersAdded) {
            addHeaders();
            headersAdded = true;
        }

        id = new JLabel(String.valueOf(sequenceNumber)); // Display sequence number as ID
        name = new JLabel(spike.getName());
        description = new JLabel(spike.getDescription());

        status = new JLabel();
        if (spike.isResolved()) {
            status.setText("Resolved");
        } else if (spike.isFailed()) {
            status.setText("Failed");
        } else {
            status.setText("Active");
        }

        linkUserStoryButton = new JButton("Link User Story");
        linkUserStoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String simulationID = parentPane.getSimulationID(); // Assuming this method exists in SpikeListPanel
                UserStoryStore userStoryStore = UserStoryStore.getInstance(simulationID);
                List<UserStory> sprintUserStories = userStoryStore.getUserStoriesInSprint(); // Get the sprint stories

                UserStorySelectionPanel selectionPanel = new UserStorySelectionPanel((Frame) SwingUtilities.getWindowAncestor(SpikeWidget.this), spike, sprintUserStories, simulationID);
                selectionPanel.setVisible(true);
                parentPane.refreshSpikes(); // Refresh to reflect the updated state
            }
        });

        JButton startSpikeButton = new JButton("Start Spike");
        startSpikeButton.addActionListener(e -> {
            StartSpikePanel startSpikePanel = new StartSpikePanel(spike.getId(), parentPane);
            startSpikePanel.setVisible(true);
        });


        if (spike.isResolved() || spike.isFailed()) {
            linkUserStoryButton.setEnabled(false);
            startSpikeButton.setEnabled(false);
        }

        GridBagLayout myGridBagLayout = new GridBagLayout();
        setLayout(myGridBagLayout);

        add(id, new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(name, new CustomConstraints(1, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(description, new CustomConstraints(2, 1, GridBagConstraints.WEST, 0.4, 0.0, GridBagConstraints.HORIZONTAL));
        add(status, new CustomConstraints(3, 1, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(linkUserStoryButton, new CustomConstraints(4, 1, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(startSpikeButton, new CustomConstraints(5, 1, GridBagConstraints.WEST, 0.1, 0.1, GridBagConstraints.HORIZONTAL));

        revalidate();
        repaint();
    }

    private void addHeaders() {
        JLabel idHeader = new JLabel("ID");
        JLabel nameHeader = new JLabel("Name");
        JLabel descHeader = new JLabel("Description");
        JLabel statusHeader = new JLabel("Status");

        add(idHeader, new CustomConstraints(0, 0, GridBagConstraints.CENTER, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(nameHeader, new CustomConstraints(1, 0, GridBagConstraints.CENTER, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(descHeader, new CustomConstraints(2, 0, GridBagConstraints.CENTER, 0.4, 0.0, GridBagConstraints.HORIZONTAL));
        add(statusHeader, new CustomConstraints(3, 0, GridBagConstraints.CENTER, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
    }

    private void resolveSpike() {
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to resolve this Spike?",
                "Resolve Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            SpikeStore.getInstance().resolveSpike(spike.getId());
            parentPane.removeSpikeWidget(this);
        }
    }

    // Method to reset the headersAdded flag
    public static void resetHeadersAddedFlag() {
        headersAdded = false;
    }
}

package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Roles;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Sprint;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

public class SprintListPane extends JFrame implements BaseComponent {
    private List<Sprint> sprints = new ArrayList<>();
    private JPanel subPanel;
    private Player player;
    private String simulationID;

    public SprintListPane(Player player, String simulationID) {
        this.player = player;
        this.simulationID = simulationID;
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Sprints List");
        setSize(600, 400);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        // Load existing sprints from the store
        sprints.addAll(SprintStore.getInstance(simulationID).getSprints());

        subPanel = new JPanel(new GridBagLayout());
        updateSprintList();

        myJpanel.add(
                new JScrollPane(subPanel),
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.BOTH));

        JButton newSprintButton = new JButton("New Sprint");
        if (player.getRole().getName().equals(Roles.DEVELOPER.getDisplayName()) || player.getRole().getName().equals(Roles.PRODUCT_OWNER.getDisplayName())) {
            newSprintButton.setEnabled(false);
        }
        newSprintButton.addActionListener(e -> {
            NewSprintForm form = new NewSprintForm(simulationID);
            form.setVisible(true);

            form.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    Sprint newSprint = form.getSprintObject();
                    if (newSprint != null && !sprints.contains(newSprint)) {
                        SprintStore.getInstance(simulationID).addSprint(newSprint);
                        updateSprintList();
                    }
                }
            });
        });

        myJpanel.add(
                newSprintButton,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }

    private void updateSprintList() {
        subPanel.removeAll();
        sprints = new ArrayList<>(SprintStore.getInstance(simulationID).getSprints());

        int row = 0;
        for (Sprint sprint : sprints) {
            JPanel sprintPanel = new JPanel(new GridBagLayout());
            JButton sprintButton = new JButton("Sprint " + sprint.getName());
            sprintButton.addActionListener(e -> showSprintDetails(sprint));

            JButton removeSprintButton = new JButton("Remove");
            removeSprintButton.addActionListener(e -> {
                SprintStore.getInstance(simulationID).removeSprint(sprint);
                updateSprintList();
            });

            sprintPanel.add(
                    sprintButton,
                    new CustomConstraints(
                            0, 0, GridBagConstraints.WEST, 0.8, 0.0, GridBagConstraints.HORIZONTAL));
            sprintPanel.add(
                    removeSprintButton,
                    new CustomConstraints(
                            1, 0, GridBagConstraints.EAST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));

            subPanel.add(
                    sprintPanel,
                    new CustomConstraints(
                            0,
                            row++,
                            GridBagConstraints.WEST,
                            1.0,
                            0.1,
                            GridBagConstraints.HORIZONTAL));
        }
        subPanel.revalidate();
        subPanel.repaint();
    }

    private void showSprintDetails(Sprint sprint) {
        Sprint refreshedSprint = SprintStore.getInstance(simulationID).getSprint(sprint.getName());

        JFrame sprintDetailsFrame = new JFrame("Sprint: " + refreshedSprint.getName());
        sprintDetailsFrame.setSize(500, 400);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridBagLayout());

        JLabel sprintLabel = new JLabel("Sprint: " + refreshedSprint.getName() + " - " + refreshedSprint.getDescription());
        detailsPanel.add(
                sprintLabel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        // Show details of each user story in the sprint
        int row = 1;
        for (UserStory userStory : refreshedSprint.getUserStories()) {
            JLabel userStoryLabel = new JLabel("ID: " + userStory.getId() +
                    " | Name: " + userStory.getName() +
                    " | Points: " + userStory.getPointValue() +
                    " | Description: " + userStory.getDescription());
            detailsPanel.add(
                    userStoryLabel,
                    new CustomConstraints(
                            0, row++, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));
        }

        JButton addUserStoryButton = new JButton("Add User Story");
        addUserStoryButton.addActionListener(e -> {
            AddUserStoryToSprintForm form = new AddUserStoryToSprintForm(refreshedSprint, simulationID);
            form.setVisible(true);
            form.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    updateSprintList();
                    sprintDetailsFrame.dispose();
                    showSprintDetails(refreshedSprint);
                }
            });
        });

        JButton removeUserStoryButton = new JButton("Remove User Story");
        removeUserStoryButton.addActionListener(e -> {
            RemoveUserStoryFromSprintForm form = new RemoveUserStoryFromSprintForm(refreshedSprint, simulationID);
            form.setVisible(true);
            form.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    updateSprintList();
                    sprintDetailsFrame.dispose();
                    showSprintDetails(refreshedSprint);
                }
            });
        });

        detailsPanel.add(
                addUserStoryButton,
                new CustomConstraints(
                        0, row++, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        detailsPanel.add(
                removeUserStoryButton,
                new CustomConstraints(
                        0, row++, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        sprintDetailsFrame.add(new JScrollPane(detailsPanel));
        sprintDetailsFrame.setVisible(true);
    }
}

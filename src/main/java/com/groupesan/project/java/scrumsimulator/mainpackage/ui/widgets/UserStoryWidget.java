package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.StoryForm;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.UserStoryListPane;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

public class UserStoryWidget extends JPanel implements BaseComponent {

    JLabel id;
    JLabel points;
    JLabel bv;
    JLabel name;
    JLabel desc;
    JLabel linkedBlockersLabel;  // Display linked blockers
    JButton deleteButton;
    JButton linkBlockerButton;  // Button to link a blocker to this user story

    // Flag to ensure the headers are only added once
    private static boolean headersAdded = false;

    @Getter
    private UserStory userStory;

    private UserStoryListPane parentPane;

    MouseAdapter openEditDialog =
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    StoryForm form = new StoryForm(userStory);
                    form.setVisible(true);

                    form.addWindowListener(
                            new java.awt.event.WindowAdapter() {
                                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                    init();
                                }
                            });
                }
            };

    public UserStoryWidget(UserStory userStory) {
        this.userStory = userStory;
        this.init();
    }

    public UserStoryWidget(UserStory userStory, UserStoryListPane parentPane) {
        this.userStory = userStory;
        this.parentPane = parentPane;
        this.init();
    }

    public void init() {
        removeAll();

        // Reset headers if no widgets exist in the parentPane
        if (parentPane.getWidgets().isEmpty()) {
            resetHeadersAddedFlag();
        }

        // Add headers only once (when the first user story widget is created)
        if (!headersAdded) {
            addHeaders();
            headersAdded = true;
        }

        id = new JLabel(userStory.getId().toString());
        id.addMouseListener(openEditDialog);
        points = new JLabel(Double.toString(userStory.getPointValue()));
        points.addMouseListener(openEditDialog);
        bv = new JLabel(Double.toString(userStory.getBusinessValue()));
        bv.addMouseListener(openEditDialog);
        name = new JLabel(userStory.getName());
        name.addMouseListener(openEditDialog);
        desc = new JLabel(userStory.getDescription());
        desc.addMouseListener(openEditDialog);

        // Display linked blockers
        linkedBlockersLabel = new JLabel(getLinkedBlockersText());

        // Delete button setup
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteUserStory());

        // Link Blocker button setup
        linkBlockerButton = new JButton("Link Blocker");
        linkBlockerButton.addActionListener(e -> linkBlockerToUserStory());

        GridBagLayout myGridBagLayout = new GridBagLayout();
        setLayout(myGridBagLayout);

        // Add user story details and buttons below the header
        add(id, new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(points, new CustomConstraints(1, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(bv, new CustomConstraints(2, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(name, new CustomConstraints(3, 1, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(desc, new CustomConstraints(4, 1, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(linkedBlockersLabel, new CustomConstraints(5, 1, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(deleteButton, new CustomConstraints(6, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(linkBlockerButton, new CustomConstraints(7, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        revalidate();
        repaint();
    }

    private void addHeaders() {
        JLabel idHeader = new JLabel("ID");
        JLabel pointsHeader = new JLabel("Points");
        JLabel bvHeader = new JLabel("BV");
        JLabel nameHeader = new JLabel("Name");
        JLabel descHeader = new JLabel("Description");
        JLabel blockersHeader = new JLabel("Linked Blockers");
        JLabel actionHeader = new JLabel("Action");
        JLabel linkBlockerHeader = new JLabel("Link Blocker");

        add(idHeader, new CustomConstraints(0, 0, GridBagConstraints.CENTER, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(pointsHeader, new CustomConstraints(1, 0, GridBagConstraints.CENTER, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(bvHeader, new CustomConstraints(2, 0, GridBagConstraints.CENTER, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(nameHeader, new CustomConstraints(3, 0, GridBagConstraints.CENTER, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(descHeader, new CustomConstraints(4, 0, GridBagConstraints.CENTER, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(blockersHeader, new CustomConstraints(5, 0, GridBagConstraints.CENTER, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(actionHeader, new CustomConstraints(6, 0, GridBagConstraints.CENTER, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(linkBlockerHeader, new CustomConstraints(7, 0, GridBagConstraints.CENTER, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
    }

    private void deleteUserStory() {
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this user story?",
                "Delete Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            UserStoryStore.getInstance(parentPane.getSimulationID()).removeUserStoryFromSprint(userStory);
            parentPane.removeUserStoryWidget(this);
        }
    }

    private void linkBlockerToUserStory() {
        // Show a dialog with a list of blockers to choose from
        List<Blocker> blockers = BlockerStore.getInstance(parentPane.getSimulationID()).getAllBlockers();
        Blocker selectedBlocker = (Blocker) JOptionPane.showInputDialog(
                this,
                "Select a blocker to link:",
                "Link Blocker",
                JOptionPane.PLAIN_MESSAGE,
                null,
                blockers.toArray(),
                null);

        if (selectedBlocker != null) {
            userStory.addBlocker(selectedBlocker); // Link the selected blocker
            linkedBlockersLabel.setText(getLinkedBlockersText()); // Update the linked blockers display
            JOptionPane.showMessageDialog(this, "Blocker linked to User Story successfully!");
        }
    }

    private String getLinkedBlockersText() {
        List<Blocker> linkedBlockers = userStory.getLinkedBlockers();
        if (linkedBlockers.isEmpty()) {
            return "No blockers linked";
        } else {
            return linkedBlockers.stream()
                    .map(Blocker::getName)
                    .collect(Collectors.joining(", "));
        }
    }

    // Reset the static flag if needed (e.g., when refreshing the UI)
    public static void resetHeadersAddedFlag() {
        headersAdded = false;
    }
}

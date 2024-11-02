package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.BlockersListPane;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class BlockerWidget extends JPanel implements BaseComponent {

    JLabel id;
    JLabel name;
    JLabel description;
    JLabel linkedUserStoriesLabel; // Label to show linked user stories
    JButton linkUserStoryButton;
    JButton resolveButton;

    private static boolean headersAdded = false;

    @Getter
    private Blocker blocker;
    private BlockersListPane parentPane;
    private int sequenceNumber; // Sequence number for display as ID
    private String simulationId;

    public BlockerWidget(String simulationId, Blocker blocker, int sequenceNumber, BlockersListPane parentPane) {
        this.simulationId = simulationId;
        this.blocker = blocker;
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

        id = new JLabel(String.valueOf(sequenceNumber));
        name = new JLabel(blocker.getName());
        description = new JLabel(blocker.getDescription());

        // Create label to display linked user stories
        String linkedUserStoryNames = blocker.getLinkedUserStories().stream()
                .map(UserStory::getName)
                .collect(Collectors.joining(", "));
        linkedUserStoriesLabel = new JLabel(linkedUserStoryNames.isEmpty() ? "No Linked User Story" : linkedUserStoryNames);

        linkUserStoryButton = new JButton("Link User Story");
        linkUserStoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                linkUserStoryToBlocker();
            }
        });

        resolveButton = new JButton("Resolve");
        resolveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolveBlocker();
            }
        });

        setLayout(new GridBagLayout());

        add(id, new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(name, new CustomConstraints(1, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(description, new CustomConstraints(2, 1, GridBagConstraints.WEST, 0.4, 0.0, GridBagConstraints.HORIZONTAL));
        add(linkedUserStoriesLabel, new CustomConstraints(3, 1, GridBagConstraints.WEST, 0.3, 0.0, GridBagConstraints.HORIZONTAL));
        add(linkUserStoryButton, new CustomConstraints(4, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(resolveButton, new CustomConstraints(5, 1, GridBagConstraints.WEST, 0.1, 0.1, GridBagConstraints.HORIZONTAL));

        revalidate();
        repaint();
    }

    private void addHeaders() {
        JLabel idHeader = new JLabel("ID");
        JLabel nameHeader = new JLabel("Name");
        JLabel descHeader = new JLabel("Description");
        JLabel linkedUserStoryHeader = new JLabel("Linked User Stories");

        add(idHeader, new CustomConstraints(0, 0, GridBagConstraints.CENTER, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(nameHeader, new CustomConstraints(1, 0, GridBagConstraints.CENTER, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(descHeader, new CustomConstraints(2, 0, GridBagConstraints.CENTER, 0.4, 0.0, GridBagConstraints.HORIZONTAL));
        add(linkedUserStoryHeader, new CustomConstraints(3, 0, GridBagConstraints.CENTER, 0.3, 0.0, GridBagConstraints.HORIZONTAL));
    }

    private void linkUserStoryToBlocker() {
        List<UserStory> userStories = UserStoryStore.getInstance(simulationId).getBacklogStories();

        UserStory selectedUserStory = (UserStory) JOptionPane.showInputDialog(
                this,
                "Select a user story to link:",
                "Link User Story",
                JOptionPane.PLAIN_MESSAGE,
                null,
                userStories.toArray(),
                null);

        if (selectedUserStory != null) {
            blocker.addLinkedUserStory(selectedUserStory);
            selectedUserStory.addBlocker(blocker);

            String updatedLinkedUserStoryNames = blocker.getLinkedUserStories().stream()
                    .map(UserStory::getName)
                    .collect(Collectors.joining(", "));
            linkedUserStoriesLabel.setText(updatedLinkedUserStoryNames);

            JOptionPane.showMessageDialog(this, "User Story linked to Blocker successfully!");
        }
    }

    private void resolveBlocker() {
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to resolve this blocker?",
                "Resolve Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            BlockerStore.getInstance(simulationId).removeBlocker(blocker.getId());
            parentPane.removeBlockerWidget(this);
        }
    }

    public static void resetHeadersAddedFlag() {
        headersAdded = false;
    }
}

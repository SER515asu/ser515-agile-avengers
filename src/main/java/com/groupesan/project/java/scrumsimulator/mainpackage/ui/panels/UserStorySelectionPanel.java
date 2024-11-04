package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Spike;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserStorySelectionPanel extends JDialog {
    private Spike spike;
    private List<UserStory> userStories;
    private JPanel userStoryPanel;
    private JButton linkButton;
    private String simulationID;

    public UserStorySelectionPanel(Frame owner, Spike spike, List<UserStory> userStories, String simulationID) {
        super(owner, "Select User Stories to Link", true);
        this.spike = spike;
        this.userStories = userStories;
        this.simulationID = simulationID;

        userStoryPanel = new JPanel(new GridLayout(0, 1));
        for (UserStory userStory : userStories) {
            JCheckBox checkBox = new JCheckBox(userStory.getName());
            checkBox.putClientProperty("userStory", userStory);
            userStoryPanel.add(checkBox);
        }

        linkButton = new JButton("Link Selected Stories");
        linkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                linkSelectedUserStories();
            }
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(userStoryPanel), BorderLayout.CENTER);
        add(linkButton, BorderLayout.SOUTH);
        setSize(400, 300);
        setLocationRelativeTo(owner);
    }

    private void linkSelectedUserStories() {
        for (Component component : userStoryPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                    UserStory userStory = (UserStory) checkBox.getClientProperty("userStory");
                    spike.addLinkedUserStory(userStory);
    
                    userStory.blockStory(spike, simulationID);
                }
            }
        }
        dispose();
    }
}

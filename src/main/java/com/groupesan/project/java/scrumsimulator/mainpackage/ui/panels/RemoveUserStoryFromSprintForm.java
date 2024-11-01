package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Sprint;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveUserStoryFromSprintForm extends JFrame {

    private Sprint sprint;
    private JComboBox<UserStory> userStoryComboBox;
    private String simulationID;

    public RemoveUserStoryFromSprintForm(Sprint sprint, String simulationID) {
        this.sprint = sprint;
        this.simulationID = simulationID;
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Remove User Story from Sprint: " + sprint.getName());
        setSize(400, 200);

        JPanel panel = new JPanel(new GridBagLayout());

        JLabel instructionLabel = new JLabel("Select a User Story to Remove:");
        panel.add(
                instructionLabel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        userStoryComboBox = new JComboBox<>();
        for (UserStory userStory : sprint.getUserStories()) {
            userStoryComboBox.addItem(userStory);
        }

        panel.add(
                userStoryComboBox,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        UserStory selectedUserStory = (UserStory) userStoryComboBox.getSelectedItem();
                        if (selectedUserStory != null) {
                            sprint.removeUserStory(selectedUserStory);
                            UserStoryStore.getInstance(simulationID).removeUserStoryFromSprint(selectedUserStory); // Move back to backlog
                            JOptionPane.showMessageDialog(RemoveUserStoryFromSprintForm.this,
                                    "User story removed successfully.",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(RemoveUserStoryFromSprintForm.this,
                                    "No user story selected.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

        panel.add(
                removeButton,
                new CustomConstraints(
                        0, 2, GridBagConstraints.CENTER, 1.0, 0.0, GridBagConstraints.NONE));

        add(panel);
        setLocationRelativeTo(null); // Center the form on the screen
    }
}

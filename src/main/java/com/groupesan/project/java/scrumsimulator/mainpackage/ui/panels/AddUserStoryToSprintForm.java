package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Sprint;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserStoryToSprintForm extends JFrame {

    private Sprint sprint;
    private JComboBox<UserStory> userStoryComboBox;
    String simulationID;

    public AddUserStoryToSprintForm(Sprint sprint, String simulationID) {
        this.sprint = sprint;
        this.simulationID = simulationID;
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Add User Story to Sprint: " + sprint.getName());
        setSize(400, 200);

        JPanel panel = new JPanel(new GridBagLayout());

        JLabel instructionLabel = new JLabel("Select a User Story to Add:");
        panel.add(
                instructionLabel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        userStoryComboBox = new JComboBox<>();
        for (UserStory userStory : UserStoryStore.getInstance(simulationID).getUserStories()) {
            if (!sprint.getUserStories().contains(userStory)) {
                userStoryComboBox.addItem(userStory);
            }
        }

        panel.add(
                userStoryComboBox,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        UserStory selectedUserStory = (UserStory) userStoryComboBox.getSelectedItem();
                        if (selectedUserStory != null) {
                            sprint.addUserStory(selectedUserStory);
                            JOptionPane.showMessageDialog(AddUserStoryToSprintForm.this,
                                    "User story added successfully.",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(AddUserStoryToSprintForm.this,
                                    "No user story selected.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

        panel.add(
                addButton,
                new CustomConstraints(
                        0, 2, GridBagConstraints.CENTER, 1.0, 0.0, GridBagConstraints.NONE));

        add(panel);
        setLocationRelativeTo(null); // Center the form on the screen
    }
}

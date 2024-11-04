package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Sprint;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NewSprintForm extends JFrame implements BaseComponent {
    private JTextField nameField = new JTextField();
    private JTextArea descArea = new JTextArea();
    private JSpinner sprintDays = new JSpinner(new SpinnerNumberModel(2, 1, 4, 1));
    private String simulationID;

    private DefaultListModel<String> listModel;
    private JList<String> usList;

    // Flag to ensure sprint addition only happens once
    private boolean sprintAdded = false;

    public NewSprintForm(String simulationID) {
        this.simulationID = simulationID;
        this.init();
    }

    public void init() {
        setTitle("New Sprint");
        setSize(500, 300);

        JPanel myJpanel = new JPanel(new GridBagLayout());
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Layout configuration
        JLabel nameLabel = new JLabel("Name:");
        myJpanel.add(nameLabel, new CustomConstraints(0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(nameField, new CustomConstraints(1, 0, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel descLabel = new JLabel("Description:");
        myJpanel.add(descLabel, new CustomConstraints(0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(new JScrollPane(descArea), new CustomConstraints(1, 1, GridBagConstraints.EAST, 1.0, 0.3, GridBagConstraints.BOTH));

        JLabel pointsLabel = new JLabel("Length (Weeks):");
        myJpanel.add(pointsLabel, new CustomConstraints(0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(sprintDays, new CustomConstraints(1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.WEST));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this::handleSubmit);

        // Populate user story list
        listModel = new DefaultListModel<>();
        for (UserStory userStory : UserStoryStore.getInstance(simulationID).getBacklogStories()) {
            listModel.addElement(userStory.toString());
        }
        usList = new JList<>(listModel);
        usList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(usList);
        scrollPane.setPreferredSize(new Dimension(300, 100));

        JLabel userStoriesLabel = new JLabel("User Stories:");
        myJpanel.add(userStoriesLabel, new CustomConstraints(0, 3, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(scrollPane, new CustomConstraints(1, 3, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.BOTH));

        myJpanel.add(cancelButton, new CustomConstraints(0, 4, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(submitButton, new CustomConstraints(1, 4, GridBagConstraints.WEST, GridBagConstraints.NONE));

        add(myJpanel);
    }

    // Handles the submission logic with duplication prevention
    private void handleSubmit(ActionEvent e) {
        if (sprintAdded) {
            return; // Prevent duplicate submission
        }

        Sprint sprint = createSprintObject();
        if (sprint != null) {
            SprintStore.getInstance(simulationID).addSprint(sprint);
            sprintAdded = true; // Set flag after adding sprint
            JOptionPane.showMessageDialog(this, "Sprint added successfully!");
        }

        dispose(); // Close the form after submission
    }

    private Sprint createSprintObject() {
        String name = nameField.getText();
        String description = descArea.getText();
        Integer length = (Integer) sprintDays.getValue();

        SprintFactory sprintFactory = SprintFactory.getSprintFactory();
        Sprint mySprint = sprintFactory.createNewSprint(name, description, length);

        int[] selectedIdx = usList.getSelectedIndices();
        for (int idx : selectedIdx) {
            String stringIdentifier = listModel.getElementAt(idx);
            for (UserStory userStory : UserStoryStore.getInstance(simulationID).getBacklogStories()) {
                if (stringIdentifier.equals(userStory.toString())) {
                    mySprint.addUserStory(simulationID, userStory);
                    break;
                }
            }
        }

        return mySprint;
    }
}

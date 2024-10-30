package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Roles;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStoryState;

public class UpdateUserStoryPanel extends JFrame {
    private Player player;
    private String simulationID;
    private JComboBox<String> userStoryComboBox;
    private JComboBox<String> statusComboBox;

    public UpdateUserStoryPanel(Player player, String simulationID) {
        this.player = player;
        this.simulationID = simulationID;
        init();
    }

    private void init() {
        setTitle("Update User Story Status");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        placeComponents(panel);
        add(panel);

        setLocationRelativeTo(null);
        reloadData();
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userStoryLabel = new JLabel("Select User Story:");
        userStoryLabel.setBounds(10, 20, 120, 25);
        panel.add(userStoryLabel);

        userStoryComboBox = new JComboBox<>();
        userStoryComboBox.setBounds(150, 20, 200, 25);
        panel.add(userStoryComboBox);

        JLabel statusLabel = new JLabel("Select Status:");
        statusLabel.setBounds(10, 50, 120, 25);
        panel.add(statusLabel);

        statusComboBox = new JComboBox<>(UserStoryState.getStatusOptions());
        statusComboBox.setBounds(150, 50, 200, 25);
        panel.add(statusComboBox);

        userStoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStatusComboBox();
            }
        });

        JButton updateButton = new JButton("Update Status");
        updateButton.setBounds(150, 80, 150, 25);
        panel.add(updateButton);

        if ((player.getRole().getName().equals(Roles.PRODUCT_OWNER.getDisplayName()) ||
                player.getRole().getName().equals(Roles.SCRUM_MASTER.getDisplayName()))) {
            updateButton.setEnabled(false);
        }

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedUserStory = (String) userStoryComboBox.getSelectedItem();
                String selectedStatus = (String) statusComboBox.getSelectedItem();

                if (selectedUserStory != null && selectedStatus != null) {
                    SimulationStateManager.changeUserStoryState(simulationID, selectedUserStory, selectedStatus);
                    JOptionPane.showMessageDialog(null, "Status updated successfully!");
                    reloadData();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a User Story and Status");
                }
            }
        });
    }

    private void reloadData() {
        userStoryComboBox.removeAllItems();
        List<UserStory> userStories = UserStoryStore.getInstance(simulationID).getUserStoriesFromJson();

        for (UserStory userStory : userStories) {
            userStoryComboBox.addItem(userStory.getName());
        }

        if (userStoryComboBox.getItemCount() > 0) {
            userStoryComboBox.setSelectedIndex(0);
        }
    }

    private void updateStatusComboBox() {
        String selectedUserStoryName = (String) userStoryComboBox.getSelectedItem();
        if (selectedUserStoryName != null) {
            UserStory selectedUserStory = UserStoryStore.getInstance(simulationID).getUserStoriesFromJson().stream()
                    .filter(userStory -> userStory.getName().equals(selectedUserStoryName))
                    .findFirst()
                    .orElse(null);

            if (selectedUserStory != null) {
                statusComboBox.setSelectedItem(selectedUserStory.getState());
            }
        }
    }
}

package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStorySelectedState;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStoryUnselectedState;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.UserStoryWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 * A pane for displaying sprint actions and functions during a sprint. For now only consists of
 * functionality to add sprints to in progress for aplayer, but will be expanded as more functions
 * are added.
 */
public class SprintUIPane extends JFrame implements BaseComponent {

    private static final int MAX_IN_PROGRESS = 2;

    public SprintUIPane(Player player) {
        this.currentPlayer = player;
        this.init();
    }

    private List<UserStoryWidget> widgets = new ArrayList<>();

    private Player currentPlayer;

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Select UserStories");
        setSize(400, 300);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        JComboBox<String> selectComboBox = new JComboBox<>();
        myJpanel.add(
                selectComboBox,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            // only display unselected states
            if (userStory.getUserStoryState() instanceof UserStoryUnselectedState) {
                selectComboBox.addItem(userStory.toString());
                widgets.add(new UserStoryWidget(userStory));
            }
        }

        JPanel availableSubPanel = new JPanel();
        availableSubPanel.setLayout(new GridBagLayout());
        int i = 0;
        for (UserStoryWidget widget : widgets) {
            availableSubPanel.add(
                    widget,
                    new CustomConstraints(
                            0,
                            i++,
                            GridBagConstraints.WEST,
                            1.0,
                            0.1,
                            GridBagConstraints.HORIZONTAL));
        }
        myJpanel.add(
                new JScrollPane(availableSubPanel),
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));

        JPanel selectedSubPanel = new JPanel();
        selectedSubPanel.setLayout(new GridBagLayout());
        i = 0;

        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            // only display unselected states
            if (userStory.getUserStoryState() instanceof UserStorySelectedState
                    && currentPlayer.equals(userStory.getOwner())) {
                selectedSubPanel.add(
                        new UserStoryWidget(userStory),
                        new CustomConstraints(
                                0,
                                i++,
                                GridBagConstraints.WEST,
                                1.0,
                                0.1,
                                GridBagConstraints.HORIZONTAL));
            }
        }
        myJpanel.add(
                new JScrollPane(selectedSubPanel),
                new CustomConstraints(
                        0, 4, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));

        JLabel warningLabel = new JLabel("");

        myJpanel.add(
                warningLabel,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));

        JButton SelectUSButton = new JButton("Select");
        SelectUSButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int ownedUS = 0;
                        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
                            if (currentPlayer.equals(userStory.getOwner())) {
                                ownedUS++;
                            }
                        }

                        if (ownedUS >= MAX_IN_PROGRESS) {
                            warningLabel.setText("Only 2 US can be in progress at once!");
                            return;
                        }

                        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
                            if (userStory.toString().equals(selectComboBox.getSelectedItem())) {
                                userStory.setOwner(currentPlayer);
                                userStory.changeState(new UserStorySelectedState(userStory));
                            }
                        }
                        selectComboBox.removeAllItems();
                        widgets.clear();
                        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
                            // only display unselected states
                            if (userStory.getUserStoryState() instanceof UserStoryUnselectedState) {
                                selectComboBox.addItem(userStory.toString());
                                widgets.add(new UserStoryWidget(userStory));
                            }
                        }

                        availableSubPanel.removeAll();
                        int i = 0;
                        for (UserStoryWidget widget : widgets) {
                            availableSubPanel.add(
                                    widget,
                                    new CustomConstraints(
                                            0,
                                            i++,
                                            GridBagConstraints.WEST,
                                            1.0,
                                            0.1,
                                            GridBagConstraints.HORIZONTAL));
                        }

                        selectedSubPanel.removeAll();
                        i = 0;
                        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
                            // only display unselected states
                            if (userStory.getUserStoryState() instanceof UserStorySelectedState
                                    && currentPlayer.equals(userStory.getOwner())) {
                                selectedSubPanel.add(
                                        new UserStoryWidget(userStory),
                                        new CustomConstraints(
                                                0,
                                                i++,
                                                GridBagConstraints.WEST,
                                                1.0,
                                                0.1,
                                                GridBagConstraints.HORIZONTAL));
                            }
                        }
                    }
                });
        myJpanel.add(
                SelectUSButton,
                new CustomConstraints(
                        0, 3, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }
}

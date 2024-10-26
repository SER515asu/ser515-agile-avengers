package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Roles;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.UserStoryWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;


public class UserStoryListPane extends JFrame implements BaseComponent {
    private Player player;
    
    public UserStoryListPane(Player player) {
        this.player = player;
        this.init();
    }
    private List<UserStoryWidget> widgets = new ArrayList<>();
    private JPanel subPanel = new JPanel();

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("User Story list");
        setSize(500, 300);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        subPanel.setLayout(new GridBagLayout());
        refreshUserStories();

        myJpanel.add(
                new JScrollPane(subPanel),
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));

        JButton newSprintButton = new JButton("New User Story");
        if (player.getRole().getName().equals(Roles.PRODUCT_OWNER.getDisplayName())) {
            newSprintButton.setEnabled(false); 
        }
        newSprintButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        StoryForm form = new StoryForm();
                        form.setVisible(true);
                        form.addWindowListener(
                                new java.awt.event.WindowAdapter() {
                                    public void windowClosed(
                                            java.awt.event.WindowEvent windowEvent) {
                                        UserStory userStory = form.getUserStoryObject();
                                        UserStoryStore.getInstance().addUserStory(userStory);
                                        addUserStoryWidget(new UserStoryWidget(userStory, UserStoryListPane.this));
                                    }
                                });
                    }
                });
        myJpanel.add(
                newSprintButton,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }

    private void refreshUserStories() {
        subPanel.removeAll();
        widgets.clear();

        int i = 0;
        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            UserStoryWidget widget = new UserStoryWidget(userStory, this);
            widgets.add(widget);
            subPanel.add(
                    widget,
                    new CustomConstraints(
                            0,
                            i++,
                            GridBagConstraints.WEST,
                            1.0,
                            0.1,
                            GridBagConstraints.HORIZONTAL));
        }

        subPanel.revalidate();
        subPanel.repaint();
    }

    public void removeUserStoryWidget(UserStoryWidget widget) {
        widgets.remove(widget);
        refreshUserStories();
    }

    public void addUserStoryWidget(UserStoryWidget widget) {
        widgets.add(widget);
        refreshUserStories();
    }

    // Add this method to access the list of widgets
    public List<UserStoryWidget> getWidgets() {
        return widgets;
    }
}

package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.StoryForm;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.UserStoryListPane;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import lombok.Getter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class UserStoryWidget extends JPanel implements BaseComponent {

    JLabel id;
    JLabel points;
    JLabel bv;
    JLabel name;
    JLabel desc;
    JButton deleteButton;

    // TODO: This is a non transient field and this class is supposed to be serializable. this needs
    // to be dealt with before this object can be serialized
    @Getter
    private UserStory userStory;

    ActionListener actionListener = e -> {};
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
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUserStory();
            }
        });

        GridBagLayout myGridBagLayout = new GridBagLayout();

        setLayout(myGridBagLayout);

        add(
                id,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                points,
                new CustomConstraints(
                        1, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                bv,
                new CustomConstraints(
                        2, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                name,
                new CustomConstraints(
                        3, 0, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                desc,
                new CustomConstraints(
                        4, 0, GridBagConstraints.WEST, 0.4, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                deleteButton,
                new CustomConstraints(
                        5, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        revalidate();
        repaint();
    }

    private void deleteUserStory() {
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this user story?",
                "Delete Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            UserStoryStore.getInstance().removeUserStory(userStory);

            parentPane.removeUserStoryWidget(this);
        }
    }

}

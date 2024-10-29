package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.BlockersListPane;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import lombok.Getter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import static com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.UserStoryWidget.resetHeadersAddedFlag;

public class BlockerWidget extends JPanel implements BaseComponent {

    JLabel id;
    JLabel name;
    JLabel description;
    JButton linkUserStoryButton;
    JButton resolveButton;

    // Flag to ensure the headers are only added once
    private static boolean headersAdded = false;

    @Getter
    private Blocker blocker;
    private BlockersListPane parentPane;


    public BlockerWidget(Blocker blocker) {
        this.blocker = blocker;
        this.init();
    }


    public BlockerWidget(Blocker blocker, BlockersListPane parentPane) {
        this.blocker = blocker;
        this.parentPane = parentPane;
        this.init();
    }

    public void init() {
        removeAll();

        // Reset headers if no widgets exist in the parentPane
        if (parentPane.getWidgets().isEmpty()) {
            resetHeadersAddedFlag();
        }

        // Add headers only once (when the first blocker widget is created)
        if (!headersAdded) {
            addHeaders();
            headersAdded = true;
        }

        id = new JLabel(blocker.getId().toString());
        name = new JLabel(blocker.getName());
        description = new JLabel(blocker.getDescription());

        linkUserStoryButton = new JButton("Link User Story");
        linkUserStoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic for linking a user story
            }
        });

        resolveButton = new JButton("Resolve");
        resolveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resolveBlocker();
            }
        });

        GridBagLayout myGridBagLayout = new GridBagLayout();
        setLayout(myGridBagLayout);

        // Add blocker details
        add(id, new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(name, new CustomConstraints(1, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(description, new CustomConstraints(2, 1, GridBagConstraints.WEST, 0.4, 0.0, GridBagConstraints.HORIZONTAL));
        add(linkUserStoryButton, new CustomConstraints(3, 1, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(resolveButton, new CustomConstraints(4, 1, GridBagConstraints.WEST, 0.1, 0.1, GridBagConstraints.HORIZONTAL));

        revalidate();
        repaint();
    }

    private void addHeaders() {
        JLabel idHeader = new JLabel("ID");
        JLabel nameHeader = new JLabel("Name");
        JLabel descHeader = new JLabel("Description");

        add(idHeader, new CustomConstraints(0, 0, GridBagConstraints.CENTER, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(nameHeader, new CustomConstraints(1, 0, GridBagConstraints.CENTER, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(descHeader, new CustomConstraints(2, 0, GridBagConstraints.CENTER, 0.4, 0.0, GridBagConstraints.HORIZONTAL));
    }

    private void resolveBlocker() {
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to resolve this blocker?",
                "Resolve Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            BlockerStore.getInstance().removeBlocker(blocker.getId());
            parentPane.removeBlockerWidget(this);
        }
    }
}

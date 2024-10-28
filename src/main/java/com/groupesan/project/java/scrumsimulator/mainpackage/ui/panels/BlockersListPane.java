package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Roles;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BlockerWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

public class BlockersListPane extends JFrame implements BaseComponent {
    private Player player;
    private List<BlockerWidget> widgets = new ArrayList<>();
    private JPanel subPanel = new JPanel();

    public BlockersListPane(Player player) {
        this.player = player;
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Blocker List");
        setSize(500, 300);

        JPanel myJpanel = new JPanel(new GridBagLayout());
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        subPanel.setLayout(new GridBagLayout());

        // Wrap subPanel in a JScrollPane for vertical scrolling
        JScrollPane scrollPane = new JScrollPane(subPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        refreshBlockers();  // Populate initial list of blockers

        myJpanel.add(
                scrollPane,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.BOTH)); // Use BOTH to expand

        JButton newBlockerButton = new JButton("New Blocker");
        if (player.getRole().getName().equals(Roles.PRODUCT_OWNER.getDisplayName())) {
            newBlockerButton.setEnabled(false);
        }
        newBlockerButton.addActionListener(e -> {
            BlockerForm form = new BlockerForm();
            form.setVisible(true);
            form.addWindowListener(
                    new java.awt.event.WindowAdapter() {
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            Blocker blocker = form.getBlockerObject();
                            if (blocker != null) {  // Ensure blocker is valid
                                addBlockerWidget(new BlockerWidget(blocker, BlockersListPane.this));
                            }
                        }
                    });
        });

        myJpanel.add(
                newBlockerButton,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }

    private void refreshBlockers() {
        subPanel.removeAll();
        widgets.clear();

        int i = 0;
        for (Blocker blocker : BlockerStore.getInstance().getAllBlockers()) {
            BlockerWidget widget = new BlockerWidget(blocker, this);
            widgets.add(widget);
            subPanel.add(
                    widget,
                    new CustomConstraints(
                            0, i++, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));
        }

        subPanel.revalidate();
        subPanel.repaint();
    }

    public void removeBlockerWidget(BlockerWidget widget) {
        widgets.remove(widget);
        refreshBlockers();
    }

    public void addBlockerWidget(BlockerWidget widget) {
        widgets.add(widget);
        refreshBlockers();  // Ensure UI is refreshed after adding new blocker
    }

    // Optional: method to access the list of widgets if needed for other UI updates or testing
    public List<BlockerWidget> getWidgets() {
        return widgets;
    }
}
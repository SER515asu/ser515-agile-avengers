package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Roles;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Spike;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BlockerWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.SpikeWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SpikeListPanel extends JFrame implements BaseComponent {
    private Player player;
    @Getter
    private String simulationID;

    @Getter
    private List<SpikeWidget> widgets = new ArrayList<>();
    private JPanel subPanel = new JPanel();

    public SpikeListPanel(Player player, String simulationID) {
        this.player = player;
        this.simulationID = simulationID;
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Spike List");
        setSize(500, 300);

        JPanel myJpanel = new JPanel(new GridBagLayout());
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        subPanel.setLayout(new GridBagLayout());

        JScrollPane scrollPane = new JScrollPane(subPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        refreshSpikes();

        myJpanel.add(
                scrollPane,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.BOTH)); // Use BOTH to expand

        JButton newSpikeButton = new JButton("New Spike");
        if (player.getRole().getName().equals(Roles.PRODUCT_OWNER.getDisplayName())) {
            newSpikeButton.setEnabled(false);
        }
        newSpikeButton.addActionListener(e -> {
            SpikeForm form = new SpikeForm();
            form.setVisible(true);
            form.addWindowListener(
                    new java.awt.event.WindowAdapter() {
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            Spike spike = form.getSpikeObject();
                            if (spike != null) {
                                addSpikeWidget(spike);
                            }
                        }
                    });
        });

        myJpanel.add(
                newSpikeButton,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }

    public void refreshSpikes() {
        subPanel.removeAll();
        widgets.clear();

        int i = 1; // Start sequence numbering from 1
        for (Spike spike : SpikeStore.getInstance().getAllSpikes()) {
                SpikeWidget widget = new SpikeWidget(spike, i++, this); // Pass sequence number i
                widgets.add(widget);
                subPanel.add(
                        widget,
                        new CustomConstraints(
                                0, i - 1, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));

        }

        subPanel.revalidate();
        subPanel.repaint();
    }

    public void removeSpikeWidget(SpikeWidget widget) {
        widgets.remove(widget);
        refreshSpikes();
    }

    public void addSpikeWidget(Spike spike) {
        int sequenceNumber = widgets.size() + 1;
        SpikeWidget widget = new SpikeWidget(spike, sequenceNumber, this);
        widgets.add(widget);
        refreshSpikes();
    }

}

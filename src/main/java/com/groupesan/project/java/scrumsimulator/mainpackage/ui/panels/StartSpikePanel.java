package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeStore;

public class StartSpikePanel extends JFrame {
    private JTextField resourceCounter;
    private JTextArea resolutionSteps;
    private final UUID spikeId;

    public StartSpikePanel(UUID spikeID) {
        this.spikeId = spikeID;
        setTitle("Start Spike");
        setSize(400, 300);
        setLayout(new GridBagLayout());

        JLabel resourceLabel = new JLabel("Resources:");
        JButton addResourceButton = new JButton("+");
        resourceCounter = new JTextField("0", 5);
        JButton subtractResourceButton = new JButton("-");

        addResourceButton.addActionListener(e -> incrementResource());
        subtractResourceButton.addActionListener(e -> decrementResource());

        JLabel resolutionLabel = new JLabel("Resolution Steps:");
        resolutionSteps = new JTextArea(5, 20);
        JScrollPane resolutionScroll = new JScrollPane(resolutionSteps);

        JButton spikeFailedButton = new JButton("Spike Failed");
        JButton resolveButton = new JButton("Resolve");

        spikeFailedButton.addActionListener(e -> handleSpikeFailed());
        resolveButton.addActionListener(e -> handleSpikeResolved());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(resourceLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(resourceCounter, gbc);

        gbc.gridx = 2;
        add(addResourceButton, gbc);

        gbc.gridx = 3;
        add(subtractResourceButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(resolutionLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add(resolutionScroll, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(spikeFailedButton, gbc);

        gbc.gridx = 1;
        add(resolveButton, gbc);
    }

    private void incrementResource() {
        int count = Integer.parseInt(resourceCounter.getText());
        resourceCounter.setText(String.valueOf(count + 1));
    }

    private void decrementResource() {
        int count = Integer.parseInt(resourceCounter.getText());
        if (count > 0) resourceCounter.setText(String.valueOf(count - 1));
    }

    private void handleSpikeFailed() {
        JOptionPane.showMessageDialog(this, "The spike has failed.", "Spike Failure", JOptionPane.WARNING_MESSAGE);
        dispose(); 
    }

    private void handleSpikeResolved() {
        resolveSpike();
        dispose();
    }

    private void resolveSpike() {
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to resolve this Spike?",
                "Resolve Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            SpikeStore.getInstance().resolveSpike(spikeId);
        }
    }
}

package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Spike;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SpikeFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpikeForm extends JFrame implements BaseComponent {

    private JTextField nameField;
    private JTextArea descArea;
    private Spike spike;
    private boolean isNewSpike;

    public SpikeForm(Spike spike) {
        this.spike = spike;
        this.isNewSpike = false;
        this.init();
    }

    public SpikeForm() {
        this.isNewSpike = true;
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(this.isNewSpike ? "New Spike" : ("Edit Spike " + spike.getId().toString()));
        setSize(400, 300);
        nameField = new JTextField();
        descArea = new JTextArea();

        if (!this.isNewSpike) {
            nameField = new JTextField(spike.getName());
            descArea = new JTextArea(spike.getDescription());
        } else {
            nameField = new JTextField();
            descArea = new JTextArea();
        }

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        BorderLayout myBorderLayout = new BorderLayout();
        setLayout(myBorderLayout);

        JLabel nameLabel = new JLabel("Name:");
        myJpanel.add(
                nameLabel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                nameField,
                new CustomConstraints(
                        1, 0, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel descLabel = new JLabel("Description:");
        myJpanel.add(
                descLabel,
                new CustomConstraints(
                        0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                new JScrollPane(descArea),
                new CustomConstraints(
                        1, 1, GridBagConstraints.EAST, 1.0, 0.3, GridBagConstraints.BOTH));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

        JButton submitButton = new JButton("Submit");
        if (this.isNewSpike) {
            submitButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
        } else {
            submitButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            spike.setName(nameField.getText());
                            spike.setDescription(descArea.getText());
                            dispose();
                        }
                    });
        }

        myJpanel.add(
                cancelButton,
                new CustomConstraints(0, 2, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(
                submitButton,
                new CustomConstraints(1, 2, GridBagConstraints.WEST, GridBagConstraints.NONE));

        add(myJpanel);
    }

    public Spike getSpikeObject() {
        String name = nameField.getText();
        String description = descArea.getText();

        SpikeFactory spikeFactory = SpikeFactory.getInstance();
        Spike spike = spikeFactory.createNewSpike(name, description);
        spike.doRegister();

        return spike;
    }
}

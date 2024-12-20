package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlockerForm extends JFrame implements BaseComponent {

    private JTextField nameField;
    private JTextArea descArea;
    private Blocker blocker;
    private boolean isNewBlocker;
    private String simulationId;

    public BlockerForm(Blocker blocker) {
        this.blocker = blocker;
        this.isNewBlocker = false;
        this.init();
    }

    public BlockerForm(String simulationId) {
        this.isNewBlocker = true;
        this.simulationId = simulationId;
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(this.isNewBlocker ? "New Blocker" : ("Edit Blocker " + blocker.getId().toString()));
        setSize(400, 300);
        nameField = new JTextField();
        descArea = new JTextArea();

        if (!this.isNewBlocker) {
            nameField = new JTextField(blocker.getName());
            descArea = new JTextArea(blocker.getDescription());
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
        if (this.isNewBlocker) {
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
                            blocker.setName(nameField.getText());
                            blocker.setDescription(descArea.getText());
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

    public Blocker getBlockerObject() {
        String name = nameField.getText();
        String description = descArea.getText();

        BlockerFactory blockerFactory = BlockerFactory.getInstance();
        Blocker blocker = blockerFactory.createNewBlocker(name, description);
        BlockerStore.getInstance(simulationId).addBlocker(blocker);

        return blocker;
    }
}

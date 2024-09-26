package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.AddUser;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * SimulationPane is a part of the UI in the scrum simulator.
 *
 * <p>Todo: logic/controller portions of original FeedbackPanel.java
 *
 * @version 0.1
 * @since 2023-11-8
 */
public class SimulationPane extends JFrame {
    private JButton joinButton;
    private JTextField usernameField;
    private JRadioButton playerRadioButton;
    private JRadioButton teacherRadioButton;
    private ButtonGroup typeButtonGroup;
    private JComboBox<String> roleComboBox;

    private static final List<String> allowedRoleNames =
            Arrays.asList("pig", "chicken", "product owner", "scrum master");

    /** The simulation Pane for adding new users. */
    public SimulationPane() {
        setTitle("Simulation Status");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        panel.add(usernameLabel);
        panel.add(usernameField);

        JLabel typeLabel = new JLabel("Type:");
        panel.add(typeLabel);

        typeButtonGroup = new ButtonGroup();
        playerRadioButton = new JRadioButton("Player");
        teacherRadioButton = new JRadioButton("Teacher");
        typeButtonGroup.add(playerRadioButton);
        typeButtonGroup.add(teacherRadioButton);

        panel.add(playerRadioButton);
        panel.add(new JLabel(""));
        panel.add(teacherRadioButton);

        JLabel roleNameLabel = new JLabel("Role Name:");
        roleComboBox = new JComboBox<>(allowedRoleNames.toArray(new String[0]));
        panel.add(roleNameLabel);
        panel.add(roleComboBox);

        joinButton = new JButton("Join Simulation");
        joinButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Logic for join button
                        onJoinButtonClicked();
                    }
                });

        setLayout(new BorderLayout());
        add(joinButton, BorderLayout.SOUTH);
        add(panel);
    }

    private void onJoinButtonClicked() {
        String username = usernameField.getText();
        String type = playerRadioButton.isSelected() ? "player" : "teacher";
        String roleName = roleComboBox.getSelectedItem().toString();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null, "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        AddUser.addUser(username, type, roleName);
        clearFields();
    }

    private void clearFields() {
        usernameField.setText("");
        typeButtonGroup.clearSelection();
        roleComboBox.setSelectedIndex(0);
    }
}

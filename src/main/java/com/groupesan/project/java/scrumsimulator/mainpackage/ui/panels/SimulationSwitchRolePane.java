package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.function.Consumer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;

public class SimulationSwitchRolePane extends JFrame {
    private JRadioButton developerRadioButton;
    private JRadioButton scrumMasterRadioButton;
    private JRadioButton productOwnerRadioButton;
    private ButtonGroup roleButtonGroup;
    private JButton switchButton;
    private final Consumer<ScrumRole> roleSwitchCallback;

    public SimulationSwitchRolePane(Consumer<ScrumRole> roleSwitchCallback) {
        this.roleSwitchCallback = roleSwitchCallback;

        setTitle("Switch Role");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(4, 1));

        JLabel label = new JLabel("Select Role:");
        panel.add(label);

        developerRadioButton = new JRadioButton("Developer");
        scrumMasterRadioButton = new JRadioButton("Scrum Master");
        productOwnerRadioButton = new JRadioButton("Product Owner");

        roleButtonGroup = new ButtonGroup();
        roleButtonGroup.add(developerRadioButton);
        roleButtonGroup.add(scrumMasterRadioButton);
        roleButtonGroup.add(productOwnerRadioButton);

        panel.add(developerRadioButton);
        panel.add(scrumMasterRadioButton);
        panel.add(productOwnerRadioButton);

        switchButton = new JButton("Switch Role");
        switchButton.addActionListener(e -> onSwitchButtonClicked());

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(switchButton, BorderLayout.SOUTH);
    }

    private void onSwitchButtonClicked() {
        ScrumRole selectedRole = null;

        if (developerRadioButton.isSelected()) {
            selectedRole = new ScrumRole("Developer");
        } else if (scrumMasterRadioButton.isSelected()) {
            selectedRole = new ScrumRole("Scrum Master");
        } else if (productOwnerRadioButton.isSelected()) {
            selectedRole = new ScrumRole("Product Owner");
        }

        if (selectedRole != null) {
            roleSwitchCallback.accept(selectedRole);
            JOptionPane.showMessageDialog(this, "Switched to " + selectedRole.getName(),
                                          "Role Switching", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a role.",
                                          "Role Switching Error", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }
}

package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.FlowLayout;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;

/**
 * RoleSelectionPane provides a user interface for users to select their role in the simulation. It
 * supports the selection of roles like 'Developer', 'Product Owner', and 'Scrum Master'.
 */
public class RoleSelectionPane extends JFrame implements BaseComponent {
    private Consumer<String> onRoleSelected;

    /**
     * Constructor for RoleSelectionPane.
     *
     * @param onRoleSelected A Consumer functional interface to handle the selected role.
     */
    public RoleSelectionPane(Consumer<String> onRoleSelected) {
        this.onRoleSelected = onRoleSelected;
        setTitle("Select Role");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        init();
    }

    /**
     * Initializes the UI components for role selection. Adds buttons for each role and sets up
     * action listeners.
     */
    @Override
    public void init() {
        setLayout(new FlowLayout());

        JButton pigButton = new JButton("Developer");
        JButton productOwnerButton = new JButton("Product Owner");
        JButton scrumMasterButton = new JButton("Scrum Master");
        JButton scrumAdminButton = new JButton("Scrum Administrator");

        pigButton.addActionListener(e -> selectRole("Developer"));
        productOwnerButton.addActionListener(e -> selectRole("Product Owner"));
        scrumMasterButton.addActionListener(e -> selectRole("Scrum Master"));
        scrumAdminButton.addActionListener(e -> selectRole("Scrum Administrator"));

        add(pigButton);
        add(productOwnerButton);
        add(scrumMasterButton);
        add(scrumAdminButton);
    }

    /**
     * Handles the role selection and notifies the SimulationUI.
     *
     * @param role The role selected by the user.
     */
    private void selectRole(String role) {
        if (onRoleSelected != null) {
            onRoleSelected.accept(role);
        }
        this.dispose(); // Close the role selection window
    }
}

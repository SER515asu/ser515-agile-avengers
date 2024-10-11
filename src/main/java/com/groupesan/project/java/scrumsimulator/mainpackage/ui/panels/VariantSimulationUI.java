package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class VariantSimulationUI extends JFrame implements BaseComponent {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public VariantSimulationUI() {
        init();
    }

    @Override
    public void init() {
        setTitle("Variant Simulation UI");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // Buttons for role selection
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createRoleButton("Product Owner"));
        buttonPanel.add(createRoleButton("Scrum Master"));
        buttonPanel.add(createRoleButton("Chicken"));
        buttonPanel.add(createRoleButton("Pig"));

        // Adding role-specific panels
        mainPanel.add(createRolePanel("Product Owner"), "Product Owner");
        mainPanel.add(createRolePanel("Scrum Master"), "Scrum Master");
        mainPanel.add(createRolePanel("Chicken"), "Chicken");
        mainPanel.add(createRolePanel("Pig"), "Pig");

        // Default view
        mainPanel.add(new JLabel("Select a role to view its UI"), "Default");

        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        cardLayout.show(mainPanel, "Default");
    }

    private JButton createRoleButton(String role) {
        JButton button = new JButton(role);
        button.addActionListener((ActionEvent e) -> cardLayout.show(mainPanel, role));
        return button;
    }

    private JPanel createRolePanel(String role) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome to the " + role + " UI"));
        // Here, you can add more components that are specific to each role.
        // For example, for the Product Owner, you might add components related to backlog
        // management.
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> {
                    VariantSimulationUI variantSimulationUI = new VariantSimulationUI();
                    variantSimulationUI.setVisible(true);
                });
    }
}

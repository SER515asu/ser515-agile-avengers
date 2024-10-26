package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Roles;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.dialogs.simulation.SimulationWizard;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

public class DemoPane extends JFrame implements BaseComponent {
    private Player player = new Player("bob", new ScrumRole(Roles.SCRUM_MASTER.getDisplayName()));

    public DemoPane() {
        this.init();
        player.doRegister();
    }

    /**
     * Initialization of Demo Pane. Demonstrates creation of User stories, Sprints, and Simulation
     * start.
     */
    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Scrum Simulator");
        setSize(1200, 300);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        SimulationStateManager simulationStateManager = new SimulationStateManager();
        SimulationPanel simulationPanel = new SimulationPanel(simulationStateManager);

        JButton updateStoryStatusButton = new JButton("Update User Story Status");

        // Simulation button for Demo
        JButton simulationButton = new JButton("Add User");
        simulationButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SimulationPane simulationPane = new SimulationPane();
                        simulationPane.setVisible(true);
                    }
                });

        myJpanel.add(
                simulationButton,
                new CustomConstraints(
                        7, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Modify Simulation button
        JButton modifySimulationButton = new JButton("Modify Simulation");

        modifySimulationButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SimulationManager simulationManager = new SimulationManager();
                        ModifySimulationPane modifySimulationPane =
                                new ModifySimulationPane(simulationManager);
                        modifySimulationPane.setVisible(true);
                    }
                });

        // Add the button to the panel
        myJpanel.add(
                modifySimulationButton,
                new CustomConstraints(
                        5, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Create Simulation button
        JButton newSimulationButton = new JButton("New Simulation");
        newSimulationButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SimulationWizard wizard = new SimulationWizard(result->{
                            System.out.println(result.toString());
                        });
                        wizard.setVisible(true);
                    }
                });

        //Add the button to the panel
        myJpanel.add(
                newSimulationButton,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Join Simulation button
        JButton joinSimulationButton = new JButton("Join Simulation");
        joinSimulationButton.addActionListener(
                e -> {
                    SimulationUI simulationUserInterface = new SimulationUI();
                    simulationUserInterface.setVisible(true);
                });

        myJpanel.add(
                joinSimulationButton,
                new CustomConstraints(
                        6, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Simulation button for Demo
        JButton simulationSwitchRoleButton = new JButton("Switch Role");
        simulationSwitchRoleButton.addActionListener(e -> {
            SimulationSwitchRolePane roleSwitchPane = new SimulationSwitchRolePane(newRole -> {
                player.setRole(newRole); 
                refreshUIComponentsBasedOnRole();
            });
            roleSwitchPane.setVisible(true);
        });
        // New button for Variant Simulation UI
        JButton variantSimulationUIButton = new JButton("Variant Simulation UI");
        variantSimulationUIButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        VariantSimulationUI variantSimulationUI = new VariantSimulationUI();
                        variantSimulationUI.setVisible(true);
                    }
                });

        JButton SprintUIButton = new JButton("US Selection UI");
        SprintUIButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Load SprintUIPane
                        SprintUIPane sprintUIPane = new SprintUIPane(player);
                        sprintUIPane.setVisible(true);
                    }
                });

        // Adding the button to the panel
        myJpanel.add(
                SprintUIButton,
                new CustomConstraints(
                        8, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }

    private void refreshUIComponentsBasedOnRole() {
        System.err.println("Still to be implemented");
    }
}

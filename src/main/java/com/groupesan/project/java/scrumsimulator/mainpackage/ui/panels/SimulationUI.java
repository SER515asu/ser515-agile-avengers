package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Roles;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;

/**
 * SimulationUI is the main user interface for the simulation. It displays different UI elements
 * based on the user's selected role.
 */
public class SimulationUI extends JFrame implements BaseComponent {
    private ScrumRole userRole;
    private String selectedSimulationId;
    private JPanel panel;
    private Player player = new Player("bob", new ScrumRole("Scrum Master"));

    /** Constructor for SimulationUI. It initializes the role selection process. */
    public SimulationUI() {
        init();
    }

    /**
     * Initializes the user interface components. This method is called after the user role has been
     * set.
     */
    @Override
    public void init() {
        setTitle("Simulation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel(new GridBagLayout());
        setContentPane(panel);

        selectSimulation(); // Start the simulation selection process
    }

    private void selectSimulation() {
        JSONArray simulations = getSimulations();
        if (simulations != null) {
            String[] simulationNames = new String[simulations.length()];
            for (int i = 0; i < simulations.length(); i++) {
                JSONObject simulation = simulations.getJSONObject(i);
                simulationNames[i] = simulation.getString("Name") + " - " + simulation.getString("ID");
            }
            String selectedSimulation =
                    (String)
                            JOptionPane.showInputDialog(
                                    this,
                                    "Select a Simulation:",
                                    "Simulation Selection",
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    simulationNames,
                                    simulationNames[0]);

            // Store the selected simulation ID (extract from selectedSimulation)
            if (selectedSimulation != null) {
                this.selectedSimulationId = selectedSimulation.split(" - ")[1];
                selectUserRole();
            }
            else{
                dispose();
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "No simulations available.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
    }
}

    // Method to read simulations from JSON file
    private JSONArray getSimulations() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/simulation.json")) {
            JSONTokener tokener = new JSONTokener(fis);
            JSONObject obj = new JSONObject(tokener);
            return obj.getJSONArray("Simulations");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /** Opens the RoleSelectionPane for the user to select their role. */
    private void selectUserRole() {
        RoleSelectionPane roleSelectionPane = new RoleSelectionPane(roleName -> {
            if (roleName != null) {
                setUserRole(new ScrumRole(roleName));
            }
        });
    
        roleSelectionPane.setLocationRelativeTo(this);
        roleSelectionPane.setAlwaysOnTop(true);
    
        roleSelectionPane.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (userRole == null) {
                    dispose(); 
                }
            }
        });
    
        roleSelectionPane.setVisible(true);
    }
    

    /**
     * Sets the user role for the simulation and initializes the UI accordingly.
     *
     * @param role The role selected by the user.
     */
    void setUserRole(ScrumRole role) {
        this.userRole = role;
        this.player = new Player(player.getName(), role);
        updateUI();
    }

    public void updateUI() {
        panel.removeAll();
        GridBagConstraints constraints = new GridBagConstraints();

        // First row: Role and Simulation ID
        String roleText = (userRole != null) ? "Your Role: " + userRole : "Role not selected";
        String simulationText = (selectedSimulationId != null) ? "Simulation ID: " + selectedSimulationId : "Simulation ID not selected";

        JLabel roleLabel = new JLabel(roleText + " | " + simulationText);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(roleLabel, constraints);

        // Second row: Button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        Dimension buttonSize = new Dimension(150, 30);

        // Add buttons to the button panel
        // addButton(buttonPanel, "Sprints", buttonSize, e -> new SprintListPane(player,selectedSimulationId).setVisible(true));
        // addButton(buttonPanel, "Product Backlog", buttonSize, e -> new UserStoryListPane(player,selectedSimulationId).setVisible(true));
        // addButton(buttonPanel, "Update User Story Status", buttonSize, e -> new UpdateUserStoryPanel(player,selectedSimulationId).setVisible(true));
        // addButton(buttonPanel, "Switch Role", buttonSize, e -> selectUserRole());
        // addButton(buttonPanel,"List of Blockers",buttonSize,e -> new BlockersListPane(player).setVisible(true));
        
        JButton sprintsButton = new JButton("Sprints");
        sprintsButton.setPreferredSize(buttonSize);
        sprintsButton.addActionListener(e -> new SprintListPane(player, selectedSimulationId).setVisible(true));
        if (player.getRole().getName().equals(Roles.PRODUCT_OWNER.getDisplayName()) || player.getRole().getName().equals(Roles.SCRUM_ADMINISTRATOR.getDisplayName())) {
            sprintsButton.setVisible(false);
        }
        buttonPanel.add(sprintsButton);

        JButton backlogButton = new JButton("Product Backlog");
        backlogButton.setPreferredSize(buttonSize);
        backlogButton.addActionListener(e -> new UserStoryListPane(player, selectedSimulationId).setVisible(true));
        if (player.getRole().getName().equals(Roles.SCRUM_ADMINISTRATOR.getDisplayName())) {
            backlogButton.setVisible(false);
        }
        buttonPanel.add(backlogButton);

        JButton updateStoryButton = new JButton("Update User Story Status");
        updateStoryButton.setPreferredSize(buttonSize);
        updateStoryButton.addActionListener(e -> new UpdateUserStoryPanel(player, selectedSimulationId).setVisible(true));
        if (player.getRole().getName().equals(Roles.SCRUM_MASTER.getDisplayName())  || player.getRole().getName().equals(Roles.PRODUCT_OWNER.getDisplayName()) || player.getRole().getName().equals(Roles.SCRUM_ADMINISTRATOR.getDisplayName())) {
            updateStoryButton.setVisible(false);
        }
        buttonPanel.add(updateStoryButton);

        // Add the "List of Blockers" button with access only for Developer
        JButton blockersButton = new JButton("List of Blockers");
        blockersButton.setPreferredSize(buttonSize);
        blockersButton.addActionListener(e -> new BlockersListPane(player, selectedSimulationId).setVisible(true));
        buttonPanel.add(blockersButton);

        JButton spikeButton = new JButton("List of Spikes");
        spikeButton.setPreferredSize(buttonSize);
        spikeButton.addActionListener(e -> new SpikeListPanel(player,selectedSimulationId).setVisible(true));
        if (player.getRole().getName().equals(Roles.DEVELOPER.getDisplayName())  || player.getRole().getName().equals(Roles.PRODUCT_OWNER.getDisplayName())) {
            spikeButton.setVisible(false);
        }
        buttonPanel.add(spikeButton);

        // Add the "List of Solutions" button with access only for Developer
        JButton solutionsButton = new JButton("List of Solutions");
        solutionsButton.setPreferredSize(buttonSize);
        solutionsButton.addActionListener(e -> new SolutionListPane(player,selectedSimulationId).setVisible(true));
        buttonPanel.add(solutionsButton);

        // Add the "Switch Role" button, which is always visible
        JButton switchRoleButton = new JButton("Switch Role");
        switchRoleButton.setPreferredSize(buttonSize);
        switchRoleButton.addActionListener(e -> selectUserRole());
        buttonPanel.add(switchRoleButton);

        // Add the button panel to the main panel
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(10, 0, 0, 0);
        panel.add(buttonPanel, constraints);

        revalidate();
        repaint();
    }

    private void addButton(JPanel panel, String text, Dimension size, ActionListener action) {
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.addActionListener(action);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 5, 0, 5);
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(button, constraints);
    }

    public boolean isButtonVisible(String buttonText) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JButton && ((JButton) component).getText().equals(buttonText)) {
                return component.isVisible();
            }
        }
        return false;
    }
}

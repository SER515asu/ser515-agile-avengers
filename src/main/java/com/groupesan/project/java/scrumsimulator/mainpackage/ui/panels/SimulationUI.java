package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

/**
 * SimulationUI is the main user interface for the simulation. It displays different UI elements
 * based on the user's selected role.
 */
public class SimulationUI extends JFrame implements BaseComponent {
    private String userRole;
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
                                    null,
                                    "Select a Simulation:",
                                    "Simulation Selection",
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    simulationNames,
                                    simulationNames[0]);

            // Store the selected simulation ID (extract from selectedSimulation)
            if (selectedSimulation != null) {
                this.selectedSimulationId = selectedSimulation.split(" - ")[1];
            }
            selectUserRole();
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
        RoleSelectionPane roleSelectionPane = new RoleSelectionPane(this::setUserRole);

        // Set the SimulationUI as the parent of RoleSelectionPane
        roleSelectionPane.setLocationRelativeTo(this);

        // Make the RoleSelectionPane stay on top
        roleSelectionPane.setAlwaysOnTop(true);

        roleSelectionPane.setVisible(true);
    }

    /**
     * Sets the user role for the simulation and initializes the UI accordingly.
     *
     * @param role The role selected by the user.
     */
    void setUserRole(String role) {
        this.userRole = role;
        updateUI();
    }

    private void updateUI() {
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
        addButton(buttonPanel, "Sprints", buttonSize, e -> new SprintListPane(player,selectedSimulationId).setVisible(true));
        addButton(buttonPanel, "Product Backlog", buttonSize, e -> new UserStoryListPane(player,selectedSimulationId).setVisible(true));
        addButton(buttonPanel, "Update User Story Status", buttonSize, e -> new UpdateUserStoryPanel(player,selectedSimulationId).setVisible(true));
        addButton(buttonPanel, "Switch Role", buttonSize, e -> selectUserRole());
        addButton(buttonPanel,"List of Blockers",buttonSize,e -> new BlockersListPane(player).setVisible(true));
        addButton(buttonPanel,"List of Solutions",buttonSize,e -> new SolutionListPane(player).setVisible(true));

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
}

package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.SimulationHelper;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JComboBox;

/**
 * ModifySimulationPane is a UI component used by teachers to create or modify simulations. It
 * allows the generation of a new simulation ID and displays it on the UI.
 */
public class ModifySimulationPane extends JFrame implements BaseComponent {

    private SimulationManager simulationManager;

    @Getter
    @Setter
    private JComboBox<String> simulationNameField;
    @Getter
    @Setter
    private JTextField numberOfSprintsField;
    @Getter
    @Setter
    private JTextField lengthOfSprintField;

    private JTextArea simulationIdDisplay;
    private JSONObject selectedSimulation;

    public ModifySimulationPane(SimulationManager manager) {
        this.simulationManager = manager;
        this.init();
    }

    /** Initializes the UI components of the ModifySimulationPane. */
    @Override
    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Modify Simulation");
        setSize(600, 400);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        simulationIdDisplay = new JTextArea(2, 20);
        simulationIdDisplay.setEditable(false);

        simulationNameField = new JComboBox<>();
        numberOfSprintsField = new JTextField(20);
        lengthOfSprintField = new JTextField(4);

        JSONArray simulations = SimulationHelper.getSimulations();

        for (int i = 0; i < simulations.length(); i++) {
                JSONObject simulation = simulations.getJSONObject(i);
                simulationNameField.addItem(simulation.getString("Name") + " - " + simulation.getString("ID"));
                if(i == 0){
                        numberOfSprintsField.setText(simulation.getString("NumberOfSprints"));
                        lengthOfSprintField.setText(simulation.getString("LengthOfSprint"));
                }
        }
        simulationNameField.setSelectedIndex(0);
        if(!simulations.isEmpty()){
            selectedSimulation = SimulationHelper.getSprintParamsFromSimulation(simulations, simulationNameField.getSelectedItem().toString());
        }

        simulationNameField.addActionListener(
                new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                                String selectedSimulationName = (String) simulationNameField.getSelectedItem().toString();
                                selectedSimulation = SimulationHelper.getSprintParamsFromSimulation(simulations, selectedSimulationName);
                                if(selectedSimulation != null){
                                        numberOfSprintsField.setText(selectedSimulation.getString("NumberOfSprints"));
                                        lengthOfSprintField.setText(selectedSimulation.getString("LengthOfSprint"));
                                }
                        }
                }
        );

        JLabel nameLabel = new JLabel("Simulation Name:");
        JLabel sprintsLabel = new JLabel("Number of Sprints:");
        JLabel sprintDurationLabel = new JLabel("SprintDuration (weeks):");

        panel.add(
                nameLabel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        panel.add(
                simulationNameField,
                new CustomConstraints(
                        1, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        panel.add(
                sprintsLabel,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        panel.add(
                numberOfSprintsField,
                new CustomConstraints(
                        1, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        panel.add(
                sprintDurationLabel,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        panel.add(
                lengthOfSprintField,
                new CustomConstraints(
                        1, 2, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        JButton submitButton = new JButton("Submit");
        submitButton.setEnabled(!simulations.isEmpty());
        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String numberOfSprints = numberOfSprintsField.getText();

                        String lengthOfSprint = lengthOfSprintField.getText();

                        try {
                            int numSprints = Integer.parseInt(numberOfSprints);
                            int sprintDuration = Integer.parseInt(lengthOfSprint);

                            if (numSprints > 20 || numSprints < 1 || sprintDuration < 1 || sprintDuration > 4) {
                                Object[] message = {
                                        "Invalid sprint attributes. Number of sprints must be in the range 1-20, and sprint duration must be in the range 1-4 weeks"
                                };

                                // Show a dialog with the JTextField containing the Simulation ID
                                JOptionPane.showMessageDialog(
                                        ModifySimulationPane.this,
                                        message,
                                        "Error",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                selectedSimulation.put("NumberOfSprints", numberOfSprints);
                                selectedSimulation.put("LengthOfSprint", lengthOfSprint);
                                simulationManager.modifySimulation(selectedSimulation);


                                Object[] message = {
                                        "Simulation modified successfully - ", selectedSimulation.getString("Name") + " - " + selectedSimulation.getString("ID")
                                };

                                // Show a dialog with the JTextField containing the Simulation ID
                                JOptionPane.showMessageDialog(
                                        ModifySimulationPane.this,
                                        message,
                                        "Simulation Modified",
                                        JOptionPane.INFORMATION_MESSAGE);

                                // Reset fields and simulation ID display to blank
                                numberOfSprintsField.setText(selectedSimulation.getString("NumberOfSprints"));
                                lengthOfSprintField.setText(selectedSimulation.getString("LengthOfSprint"));

                                simulationIdDisplay.setText("");
                            }
                        }
                        catch (Exception err){
                            Object[] message = {
                                    "Invalid attributes!"
                            };

                            // Show a dialog with the JTextField containing the Simulation ID
                            JOptionPane.showMessageDialog(
                                    ModifySimulationPane.this,
                                    message,
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

        panel.add(
                cancelButton,
                new CustomConstraints(0, 3, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));

        panel.add(
                submitButton,
                new CustomConstraints(
                        1, 3, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        panel.add(
                simulationIdDisplay,
                new CustomConstraints(
                        2, 2, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));


        add(panel);
    }
}

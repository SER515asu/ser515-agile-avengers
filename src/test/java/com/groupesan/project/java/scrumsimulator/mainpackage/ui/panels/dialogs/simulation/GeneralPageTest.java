package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.dialogs.simulation;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.dialogs.simulation.GeneralPage;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.DataModel;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.SpinnerInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.awt.*;


public class GeneralPageTest {


    private DataModel<String> simulationModel;
    private DataModel<Object> sprintModel;
    private DataModel<Object> sprintDurationModel;
    private GeneralPage generalPage;

    @BeforeEach
    public void setUp() {
        simulationModel = new DataModel<>("Test Simulation");
        sprintModel = new DataModel<>(1);
        sprintDurationModel = new DataModel<>(1);
        generalPage = new GeneralPage(simulationModel, sprintModel, sprintDurationModel);
    }

    @Test
    public void testSprintDurationUpdatesCorrectly() {
        JPanel panel = generalPage.render();
        JPanel inputsPanel = (JPanel) ((BorderLayout) panel.getLayout()).getLayoutComponent(BorderLayout.NORTH);

        SpinnerInput sprintDurationInput = null;
        for (Component component : inputsPanel.getComponents()) {
            if (component instanceof SpinnerInput) {
                JSpinner spinner = findSpinnerInSpinnerInput((SpinnerInput) component);
                SpinnerNumberModel model = (SpinnerNumberModel) spinner.getModel();

                // Check for sprint duration input (assume max of 4 weeks)
                if (model.getMaximum().equals(4)) {
                    sprintDurationInput = (SpinnerInput) component;
                    break;
                }
            }
        }

        assertNotNull(sprintDurationInput);
        JSpinner sprintDurationSpinner = findSpinnerInSpinnerInput(sprintDurationInput);
        sprintDurationSpinner.setValue(3);
        assertEquals(3, sprintDurationSpinner.getValue());
    }

    // Helper method to find the JSpinner inside a SpinnerInput
    private JSpinner findSpinnerInSpinnerInput(SpinnerInput spinnerInput) {
        for (Component component : spinnerInput.getComponents()) {
            if (component instanceof JSpinner) {
                return (JSpinner) component;
            }
        }
        throw new IllegalStateException("No JSpinner found in SpinnerInput");
    }
}
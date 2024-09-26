package com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.dialogs.simulation.SimulationWizard;

public class WizardManager {
    private static WizardManager instance = null;

    private WizardManager() {}

    public static WizardManager get() {
        if (instance == null) {
            instance = new WizardManager();
        }
        return instance;
    }

    public void showSimulationWizard() {
        new SimulationWizard(
                        simulation -> {
                            System.out.println(simulation);
                        })
                .setVisible(true);
    }
}

package com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils;

import java.io.FileInputStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SimulationHelper {
    // Method to read simulations from JSON file
    public static JSONArray getSimulations() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/simulation.json")) {
            JSONTokener tokener = new JSONTokener(fis);
            JSONObject obj = new JSONObject(tokener);
            return obj.getJSONArray("Simulations");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getSprintParamsFromSimulation(JSONArray simulations, String selectedSimulation){
        String simulationId = selectedSimulation.split(" - ", 2)[1];
        for (int i = 0; i < simulations.length(); i++) {
            JSONObject simulation = simulations.getJSONObject(i);
            if(simulation.getString("ID").equals(simulationId)){
                return simulation;
            }
        }
        return null;
    }
}

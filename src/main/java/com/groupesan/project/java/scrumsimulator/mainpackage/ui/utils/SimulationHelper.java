package com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils;

import java.io.FileInputStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SimulationHelper {
    // Method to read simulations from JSON file
    public static JSONArray getSimulations() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/simulation.JSON")) {
            JSONTokener tokener = new JSONTokener(fis);
            JSONObject obj = new JSONObject(tokener);
            return obj.getJSONArray("Simulations");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

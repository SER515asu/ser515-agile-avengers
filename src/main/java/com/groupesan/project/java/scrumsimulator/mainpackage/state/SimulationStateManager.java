package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Sprint;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryIdentifier;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * SimulationStateManager manages the state of a simulation, including whether it is running and
 * saving its ID.
 */
public class SimulationStateManager {
    private boolean running;
    private static final String JSON_FILE_PATH = "src/main/resources/simulation.json";

    /** Simulation State manager. Not running by default. */
    public SimulationStateManager() {
        this.running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void startSimulation() {
        setRunning(true);
    }

    public void stopSimulation() {
        setRunning(false);
    }

    public static void saveNewSimulationDetails(String simId, String simName, String numberOfSprints, String lengthOfSprint) {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) {
            simulationData = new JSONObject();
        }

        JSONObject newSimulation = new JSONObject();
        newSimulation.put("ID", simId);
        newSimulation.put("Name", simName);
        newSimulation.put("Status", "New");
        newSimulation.put("NumberOfSprints", numberOfSprints);
        newSimulation.put("LengthOfSprint", lengthOfSprint);
        newSimulation.put("Sprints", new JSONArray());
        newSimulation.put("Events", new JSONArray());
        newSimulation.put("Users", new JSONArray());
        newSimulation.put("UserStories", new JSONArray());

        JSONArray simulations = simulationData.optJSONArray("Simulations");
        if (simulations == null) {
            simulations = new JSONArray();
            simulationData.put("Simulations", simulations);
        }
        simulations.put(newSimulation);

        updateSimulationData(simulationData);
    }

    public static void modifySimulationDetails(JSONObject updatedSimulationDetails){
        JSONObject simulationData = getSimulationData();
        if(simulationData == null){
            return;
        }

        JSONArray simulations = simulationData.optJSONArray("Simulations");

        for (int i = 0; i < simulations.length(); i++) {
            JSONObject simulation = simulations.getJSONObject(i);
            if(updatedSimulationDetails.getString("ID").equals(simulation.getString("ID"))){
                simulations.put(i, updatedSimulationDetails);
            }
        }

        updateSimulationData(simulationData);
    }

    /**
     * Adds a Sprint to a specific simulation based on the provided simulation ID.
     */
    public static void addSprintToSimulation(String simulationID, Sprint sprint) {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) return;

        JSONArray simulations = simulationData.getJSONArray("Simulations");

        for (int i = 0; i < simulations.length(); i++) {
            JSONObject simulation = simulations.getJSONObject(i);
            if (simulation.getString("ID").equals(simulationID)) {
                JSONArray sprints = simulation.optJSONArray("Sprints");
                if (sprints == null) {
                    sprints = new JSONArray();
                    simulation.put("Sprints", sprints);
                }

                // Create JSON representation of Sprint
                JSONObject newSprint = new JSONObject();
                newSprint.put("Name", sprint.getName());
                newSprint.put("Description", sprint.getDescription());
                newSprint.put("Length", sprint.getLength());
                newSprint.put("RemainingDays", sprint.getDaysRemaining());
                newSprint.put("ID", UUID.randomUUID().toString());

                sprints.put(newSprint);  // Add sprint to the array

                updateSimulationData(simulationData);
                JOptionPane.showMessageDialog(null, "Sprint added to simulation with ID: " + simulationID);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Simulation with ID " + simulationID + " not found.");
    }

    public static List<Sprint> getSprintsForSimulation(String simulationID) {
        JSONObject simulationData = getSimulationData();
        List<Sprint> sprintsList = new ArrayList<>();

        if (simulationData == null) return sprintsList;

        JSONArray simulations = simulationData.optJSONArray("Simulations");

        for (int i = 0; i < simulations.length(); i++) {
            JSONObject simulation = simulations.getJSONObject(i);
            if (simulation.getString("ID").equals(simulationID)) {
                JSONArray sprints = simulation.optJSONArray("Sprints");

                for (int j = 0; j < sprints.length(); j++) {
                    JSONObject sprintJson = sprints.getJSONObject(j);
                    String id = sprintJson.getString("ID");
                    String name = sprintJson.optString("Name");
                    String description = sprintJson.optString("Description", "No description");
                    int length = Integer.parseInt(simulation.optString("LengthOfSprint", "1"));

                    Sprint sprint = new Sprint(name, description, length, id);
                    sprintsList.add(sprint);
                }
                break;
            }
        }

        return sprintsList;
    }

    public static void removeSprintFromSimulation(String simulationID, String sprintID) {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) return;

        JSONArray simulations = simulationData.optJSONArray("Simulations");
        System.out.println("Hitting");

        for (int i = 0; i < simulations.length(); i++) {
            JSONObject simulation = simulations.getJSONObject(i);
            if (simulation.getString("ID").equals(simulationID)) {
                JSONArray sprints = simulation.optJSONArray("Sprints");

                for (int j = 0; j < sprints.length(); j++) {
                    JSONObject sprint = sprints.getJSONObject(j);
                    if (sprint.getString("ID").equals(sprintID)) {
                        sprints.remove(j);  // Remove the sprint from JSON array
                        updateSimulationData(simulationData);  // Save the updated JSON data
                        JOptionPane.showMessageDialog(null, "Sprint removed from simulation with ID: " + simulationID);
                        return;
                    }
                }
            }
        }
    }

    private static JSONObject getSimulationData() {
        try (FileInputStream fis = new FileInputStream(JSON_FILE_PATH)) {
            JSONTokener tokener = new JSONTokener(fis);
            return new JSONObject(tokener);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading from simulation.json");
            return null;
        }
    }

    private static void updateSimulationData(JSONObject updatedData) {
        try (OutputStreamWriter writer =
                     new OutputStreamWriter(
                             new FileOutputStream(JSON_FILE_PATH), StandardCharsets.UTF_8)) {
            writer.write(updatedData.toString(4));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to simulation.json");
        }
    }

    public static void addUserStoryToSimulation(String simulationID, UserStory userStory) {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) return;

        JSONArray simulations = simulationData.optJSONArray("Simulations");

        for (int i = 0; i < simulations.length(); i++) {
            JSONObject simulation = simulations.getJSONObject(i);
            if (simulation.getString("ID").equals(simulationID)) {
                JSONArray userStories = simulation.optJSONArray("UserStories");
                if (userStories == null) {
                    userStories = new JSONArray();
                    simulation.put("UserStories", userStories);
                }

                JSONObject newUserStory = new JSONObject();
                newUserStory.put("Name", userStory.getName());
                newUserStory.put("Description", userStory.getDescription());
                newUserStory.put("PointValue", userStory.getPointValue());
                newUserStory.put("BusinessValue", userStory.getBusinessValue());
                newUserStory.put("Id", userStory.getId());

                userStories.put(newUserStory);  // Add user story to JSON array
                updateSimulationData(simulationData);
                return;
            }
        }
    }

    public static void removeUserStoryFromSimulation(String simulationID, String userStoryName) {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) return;

        JSONArray simulations = simulationData.optJSONArray("Simulations");

        for (int i = 0; i < simulations.length(); i++) {
            JSONObject simulation = simulations.getJSONObject(i);
            if (simulation.getString("ID").equals(simulationID)) {
                JSONArray userStories = simulation.optJSONArray("UserStories");

                for (int j = 0; j < userStories.length(); j++) {
                    JSONObject userStory = userStories.getJSONObject(j);
                    if (userStory.getString("Name").equals(userStoryName)) {
                        userStories.remove(j);  // Remove the user story
                        updateSimulationData(simulationData);
                        return;
                    }
                }
            }
        }
    }

    public static List<UserStory> getUserStoriesForSimulation(String simulationID) {
        JSONObject simulationData = getSimulationData();
        List<UserStory> userStoryList = new ArrayList<>();

        if (simulationData == null) return userStoryList;

        JSONArray simulations = simulationData.optJSONArray("Simulations");

        for (int i = 0; i < simulations.length(); i++) {
            JSONObject simulation = simulations.getJSONObject(i);
            if (simulation.getString("ID").equals(simulationID)) {
                JSONArray userStories = simulation.optJSONArray("UserStories");

                for (int j = 0; j < userStories.length(); j++) {
                    JSONObject userStoryJson = userStories.getJSONObject(j);
                    String name = userStoryJson.getString("Name");
                    String description = userStoryJson.optString("Description", "No description");
                    double pointValue = userStoryJson.optDouble("PointValue", 0.0);
                    double businessValue = userStoryJson.optDouble("BusinessValue", 0.0);
                    System.out.println("value is "+userStoryJson.optString("Id","US #0").split("#")[1]);
                    UserStoryIdentifier id = new UserStoryIdentifier(Integer.parseInt(userStoryJson.optString("Id","US #0").split("#")[1]));

                    UserStory userStory = new UserStory(name, description, pointValue, businessValue);
                    userStoryList.add(userStory);
                }
                break;
            }
        }

        return userStoryList;
    }

}
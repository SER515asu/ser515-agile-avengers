package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class AddUser {
    private static final String JSON_FILE_PATH = "src/main/resources/simulation.json";

    /**
     * Implements the functionality of add user for the SimulationPane. Adds a user with a username,
     * type, and role.
     *
     * @param username name of the user: String
     * @param type type of user; player or teacher: String
     * @param roleName name of role: String
     */
    public static void addUser(String username, String type, String roleName) {
        FileInputStream fis = null;
        FileWriter fileWriter = null;

        try {
            File file = new File(JSON_FILE_PATH);

            if (!file.exists() && !file.createNewFile()) {
                System.err.println("Failed to create the JSON file.");
            }

            fis = new FileInputStream(JSON_FILE_PATH);

            JSONObject newUser = new JSONObject();
            String userId = "u" + System.currentTimeMillis();
            newUser.put("ID", userId);
            newUser.put("Username", username);
            newUser.put("Type", type);

            JSONObject role = new JSONObject();
            role.put("Name", roleName);
            newUser.put("Role", role);

            JSONTokener tokener = new JSONTokener(fis);
            JSONObject root = new JSONObject(tokener);
            JSONObject simulation = root.getJSONObject("Simulation");
            JSONArray users = simulation.getJSONArray("Users");

            users.put(newUser);

            fileWriter = new FileWriter(file, StandardCharsets.UTF_8);
            fileWriter.write(root.toString(4));

            System.out.println("User added successfully");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

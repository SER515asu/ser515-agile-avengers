package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserStoryStateManager {

    private static final String FILE_PATH = "src/main/resources/Simulation.json";

    /**
     * Method to get all user stories in a simulation.
     *
     * @return List Returns a list of all user stories in the simulation
     */
    public static List<String> getUserStories() {
        List<String> userStories = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(new File(FILE_PATH));

            JsonNode sprints = root.path("Simulation").path("Sprints");
            for (JsonNode sprint : sprints) {
                JsonNode userStoriesInSprint = sprint.path("User Stories");
                for (JsonNode userStory : userStoriesInSprint) {
                    userStories.add(userStory.path("Description").asText());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userStories;
    }

    /**
     * Method to update the status of a selected User Story.
     *
     * @param userStoryDescription The description of the User Story: String
     * @param newStatus The new status the User Story will be given : String
     */
    public static void updateUserStoryStatus(String userStoryDescription, String newStatus) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(new File(FILE_PATH));

            JsonNode sprints = root.path("Simulation").path("Sprints");
            for (JsonNode sprint : sprints) {
                JsonNode userStoriesInSprint = sprint.path("User Stories");
                for (JsonNode userStory : userStoriesInSprint) {
                    if (userStory.path("Description").asText().equals(userStoryDescription)) {
                        ((com.fasterxml.jackson.databind.node.ObjectNode) userStory)
                                .put("Status", newStatus);
                        break;
                    }
                }
            }

            objectMapper.writeValue(new File(FILE_PATH), root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

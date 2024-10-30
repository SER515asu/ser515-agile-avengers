package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumObject;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStory extends ScrumObject {
    private UserStoryIdentifier id;

    private String name;

    private String description;

    private double pointValue;

    private UserStoryState state;

    private Player owner;

    private double businessValue;

    @Setter
    @Getter
    private String sprint;


    // private ArrayList<Task> tasks;  TODO: implement tasks

    /**
     * Creates a user story. Leaves the description as an empty string.
     *
     * @param name the name for the user story
     * @param pointValue the point value for the story as a way of estimating required effort.
     */
    public UserStory(String name, double pointValue) {
        this.name = name;
        this.description = "";
        this.pointValue = pointValue;
        this.state = new UnassignedState(this);
    }

    /**
     * Creates a user story.
     *
     * @param name the name for the user story
     * @param description the description for the user story for better understanding of the
     *     requirements.
     * @param pointValue the point value for the story as a way of estimating required effort.
     */
    public UserStory(String name, String description, double pointValue, double businessValue) {
        this.name = name;
        this.description = description;
        this.pointValue = pointValue;
        this.businessValue = businessValue;
        this.state = new UnassignedState(this);
        this.register();
    }

    public UserStory(String name, String description, double pointValue, double businessValue, UserStoryIdentifier id) {
        this.name = name;
        this.description = description;
        this.pointValue = pointValue;
        this.businessValue = businessValue;
        this.state = new UnassignedState(this);
        this.id = id;
        this.register();
    }

    protected void register() {
        this.id = new UserStoryIdentifier(ScrumIdentifierStoreSingleton.get().getNextId());
    }

    /**
     * Gets the identifier object for this UserStory. **This will throw an exception if register()
     * has not been called yet.**
     *
     * @return The ScrumIdentifier for this user story
     */
    public ScrumIdentifier getId() {
        // To be uncommented again, will correct in bug ticket
//        if (!isRegistered()) {
//            throw new IllegalStateException(
//                    "This UserStory has not been registered and does not have an ID yet!");
//        }
        return id;
    }

    /**
     * [NOT IMPLEMENTED] return all child scrum objects of this object. Usually this would be tasks.
     *
     * @return a List containing all child ScrumObjects of this UserStory
     */
    public List<ScrumObject> getChildren() {
        return new ArrayList<>(); // TODO: implement tasks
    }

    /**
     * returns this user story's ID and name as text in the following format: US #3 - foo
     *
     * @return a string of the following format: "US #3 - foo"
     */
    @Override
    public String toString() {
        if (isRegistered()) {
            return this.getId().toString() + " - " + name;
        }
        return "(unregistered) - " + getName();
    }

    // State Management, need Player class to implement final selection logic
    public void editStoryStateFunction() {
        state.editStoryStateFunction();
    }

    public String getState() {
        if (state instanceof UnassignedState) return "Unassigned";
        if (state instanceof NewState) return "New";
        if (state instanceof InProgressState) return "InProgress";
        if (state instanceof ReadyToTestState) return "ReadyToTest";
        if (state instanceof CompleteState) return "Complete";
        return "Unknown";
    }

}

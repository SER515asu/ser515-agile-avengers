package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumObject;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStoryState;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStoryUnselectedState;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStory extends ScrumObject {
    private UserStoryIdentifier id;

    private String name;

    private String description;

    private double pointValue;

    private UserStoryState userStoryState;

    private Player owner;

    private double businessValue;

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
        this.userStoryState = new UserStoryUnselectedState(this);
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
        this.userStoryState = new UserStoryUnselectedState(this);
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
        if (!isRegistered()) {
            throw new IllegalStateException(
                    "This UserStory has not been registered and does not have an ID yet!");
        }
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


}

package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumObject;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private String sprint;

    // List to store linked blockers
    private List<Blocker> linkedBlockers = new ArrayList<>();

    // private ArrayList<Task> tasks;  TODO: implement tasks

    /**
     * Creates a user story. Leaves the description as an empty string.
     *
     * @param name       the name for the user story
     * @param pointValue the point value for the story as a way of estimating required effort
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
     * @param name         the name for the user story
     * @param description  the description for the user story for better understanding of the
     *                     requirements
     * @param pointValue   the point value for the story as a way of estimating required effort
     * @param businessValue the business value for prioritization
     */
    public UserStory(String name, String description, double pointValue, double businessValue) {
        this.name = name;
        this.description = description;
        this.pointValue = pointValue;
        this.businessValue = businessValue;
        this.state = new UnassignedState(this);
        this.doRegister();
    }

    public UserStory(String name, String description, double pointValue, double businessValue, UserStoryIdentifier id) {
        this.name = name;
        this.description = description;
        this.pointValue = pointValue;
        this.businessValue = businessValue;
        this.state = new UnassignedState(this);
        this.id = id;
        this.doRegister();
    }

    protected void register() {
        if(this.id == null){
            this.id = new UserStoryIdentifier(ScrumIdentifierStoreSingleton.get().getNextId());
        }
    }

    /**
     * Gets the identifier object for this UserStory. **This will throw an exception if register()
     * has not been called yet.**
     *
     * @return The ScrumIdentifier for this user story
     */
    public ScrumIdentifier getId() {
        // To be uncommented again, will correct in bug ticket
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
     * Returns this user story's ID and name as text in the following format: US #3 - foo
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

    /**
     * Adds a blocker to this user story if it’s not already linked and also links this user story to the blocker.
     *
     * @param blocker the blocker to add
     */
    public void addBlocker(Blocker blocker) {
        if (!linkedBlockers.contains(blocker)) {
            linkedBlockers.add(blocker);
            blocker.addLinkedUserStory(this); // Link this user story to the blocker as well
        }
    }

    /**
     * Removes a blocker from this user story and also removes this user story from the blocker.
     *
     * @param blocker the blocker to remove
     */
    public void removeBlocker(Blocker blocker) {
        if (linkedBlockers.remove(blocker)) {
            blocker.removeLinkedUserStory(this); // Remove this user story from the blocker's linked stories
        }
    }

    /**
     * Returns a list of linked blockers for this user story.
     *
     * @return List of linked blockers
     */
    public List<Blocker> getLinkedBlockers() {
        return new ArrayList<>(linkedBlockers);
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

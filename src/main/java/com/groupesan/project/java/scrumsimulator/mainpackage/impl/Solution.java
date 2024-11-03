package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Solution {
    private int id;

    private String title;

    private String description;

    public Solution(String title, String description, int id){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return title; // Only show the title in the dropdown
    }
}

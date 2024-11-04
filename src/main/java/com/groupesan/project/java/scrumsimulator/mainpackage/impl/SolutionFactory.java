package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import lombok.Setter;

public class SolutionFactory {
    private static SolutionFactory solutionFactory;

    public static SolutionFactory getSolutionFactory() {
        if (solutionFactory == null) {
            solutionFactory = new SolutionFactory();
        }
        return solutionFactory;
    }

    @Setter
    private int numSolutions;

    private SolutionFactory() {
        numSolutions = 0;
    }

    public Solution createNewSolution(String title, String description) {
        return new Solution(title, description, ++numSolutions);
    }
}

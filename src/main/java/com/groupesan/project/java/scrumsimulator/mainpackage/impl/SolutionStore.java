package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

public class SolutionStore {
    private static SolutionStore solutionStore;

    public static SolutionStore getInstance() {
        if (solutionStore == null) {
            solutionStore = new SolutionStore();
        }
        return solutionStore;
    }

    private ArrayList<Solution> solutions;

    public SolutionStore(){
        solutions = new ArrayList<>();
    }

    public List<Solution> getSolutions(){
        return new ArrayList<>(solutions);
    }

    public void addSolution(Solution solution){
        solutions.add(solution);
    }

    public void removeSolution(Solution solution){
        solutions.remove(solution);
    }
}

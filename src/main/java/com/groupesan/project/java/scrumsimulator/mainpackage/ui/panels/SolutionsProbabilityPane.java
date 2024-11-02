package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Solution;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SolutionStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class SolutionsProbabilityPane extends JFrame implements BaseComponent {
    private List<Solution> availableSolutions = new ArrayList<>();
    private List<Solution> selectedSolutions = new ArrayList<>();
    private JComboBox<String> solutionJComboBox = new JComboBox<>();
    JPanel subPanel = new JPanel();
    private List<Float> probabilities = new ArrayList<>();

    public SolutionsProbabilityPane(){
        this.availableSolutions = SolutionStore.getInstance().getSolutions();
        for(int i=0;i<=10;i++){
            probabilities.add((float) i/10);
        }
        this.init();
    }

    public void init(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Fine-tune Solutions Probabilities");
        setSize(600, 600);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        refreshSolutions();

        JLabel solutionSelectionLabel = new JLabel("Select Solution:");
        myJpanel.add(
                solutionSelectionLabel,
                new CustomConstraints(0, 0, GridBagConstraints.WEST, 0.0, 0.0, GridBagConstraints.HORIZONTAL));

        myJpanel.add(
                solutionJComboBox,
                new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectCurrentSolution();
                    }
                }
        );

        myJpanel.add(
                selectButton,
                new CustomConstraints(0, 2, GridBagConstraints.LINE_END, 0.2, 0.0, GridBagConstraints.NONE));

        JLabel selectedSolutionsLabel = new JLabel("Selected Solutions:");
        myJpanel.add(
                selectedSolutionsLabel,
                new CustomConstraints(0, 3, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        // Sub panel to show the list of selected solutions
        subPanel.setLayout(new GridBagLayout());

        myJpanel.add(
                new JScrollPane(subPanel),
                new CustomConstraints(0, 4, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.BOTH));

        //Adding UI for probabilitySelection
        JLabel probabilitySelectionLabel = new JLabel("Select Probability Range:");
        myJpanel.add(
                probabilitySelectionLabel,
                new CustomConstraints(0, 5, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        // Sub panel for probability range
        JPanel probabilitySubPanel = new JPanel();
        probabilitySubPanel.setLayout(new GridBagLayout());

        JLabel minLabel = new JLabel("Minimum:");
        JLabel maxLabel = new JLabel("Maximum:");
        probabilitySubPanel.add(
                minLabel,
                new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        probabilitySubPanel.add(
                maxLabel,
                new CustomConstraints(1, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        // Range selection dropdown
        JComboBox<String> minValue = new JComboBox<>();
        for(int i=0; i<probabilities.size()-1; i++){
            minValue.addItem(String.valueOf(probabilities.get(i)));
        }
        minValue.setSelectedIndex(0);

        JComboBox<String> maxValue = new JComboBox<>();
        for(int i=1; i<probabilities.size(); i++){
            maxValue.addItem(String.valueOf(probabilities.get(i)));
        }
        maxValue.setSelectedIndex(0);

        minValue.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedIndex = minValue.getSelectedIndex();
                        maxValue.removeAllItems();
                        for(int i=selectedIndex+1; i<probabilities.size(); i++){
                            maxValue.addItem(String.valueOf(probabilities.get(i)));
                        }
                    }
                }
        );

        JCheckBox randomizeProbability = new JCheckBox("Randomize range");
        randomizeProbability.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        int state = e.getStateChange();
                        minValue.setEnabled(state==2);
                        maxValue.setEnabled(state==2);
                    }
                }
        );

        probabilitySubPanel.add(
                minValue,
                new CustomConstraints(0, 2, GridBagConstraints.WEST, 0.2, 0.1, GridBagConstraints.HORIZONTAL));

        probabilitySubPanel.add(
                maxValue,
                new CustomConstraints(1, 2, GridBagConstraints.WEST, 0.2, 0.1, GridBagConstraints.HORIZONTAL));

        probabilitySubPanel.add(
                randomizeProbability,
                new CustomConstraints(0, 0, GridBagConstraints.WEST, 0.2, 0.1, GridBagConstraints.NONE));

        myJpanel.add(
                probabilitySubPanel,
                new CustomConstraints(0, 6, GridBagConstraints.WEST, 0.0, 0.0, GridBagConstraints.BOTH));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }
        );

        myJpanel.add(
                cancelButton,
                new CustomConstraints(0, 7, GridBagConstraints.WEST, GridBagConstraints.NONE));

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Add logic to store probabilities of solution in the backend
                    }
                }
        );

        myJpanel.add(
                submitButton,
                new CustomConstraints(0, 7, GridBagConstraints.EAST, GridBagConstraints.NONE));
        add(myJpanel);
    }

    private void refreshSolutions(){
        solutionJComboBox.removeAllItems();
        for(Solution solution: availableSolutions){
            solutionJComboBox.addItem(solution.getTitle());
        }
    }

    private void selectCurrentSolution(){
        int selectedSolutionIndex = solutionJComboBox.getSelectedIndex();
        Solution selectedSolution = availableSolutions.get(selectedSolutionIndex);
        selectedSolutions.add(selectedSolution);
        subPanel.add(
                new JLabel(selectedSolution.getTitle()),
                new CustomConstraints(0, selectedSolutions.size()-1, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.NONE));
        availableSolutions.remove(selectedSolutionIndex);
        refreshSolutions();
    }
}
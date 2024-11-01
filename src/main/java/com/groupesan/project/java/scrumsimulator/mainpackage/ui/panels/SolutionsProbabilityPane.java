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
import java.util.ArrayList;
import java.util.List;

public class SolutionsProbabilityPane extends JFrame implements BaseComponent {
    private List<Solution> availableSolutions = new ArrayList<>();
    private List<Solution> selectedSolutions = new ArrayList<>();
    private JComboBox<String> solutionJComboBox = new JComboBox<>();
    JPanel subPanel = new JPanel();

    public SolutionsProbabilityPane(){
        this.availableSolutions = SolutionStore.getInstance().getSolutions();
        this.init();
    }

    public void init(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Fine-tune Solutions Probabilities");
        setSize(400, 300);

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
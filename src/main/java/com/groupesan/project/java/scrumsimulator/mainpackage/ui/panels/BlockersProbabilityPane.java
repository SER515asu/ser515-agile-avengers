package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
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

public class BlockersProbabilityPane extends JFrame implements BaseComponent {
    private List<Blocker> availableBlockers = new ArrayList<>();
    private List<Blocker> selectedBlockers = new ArrayList<>();
    private JComboBox<String> blockerJComboBox = new JComboBox<>();
    JPanel subPanel = new JPanel();
    private List<Float> probabilities = new ArrayList<>();

    public BlockersProbabilityPane(){
        this.availableBlockers = BlockerStore.getInstance().getAllBlockers();
        for(int i=0;i<=10;i++){
            probabilities.add((float) i/10);
        }
        this.init();
    }

    public void init(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Fine-tune Blockers Probabilities");
        setSize(500, 500);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        refreshBlockers();

        JLabel blockerSelectionLabel = new JLabel("Select Blocker:");
        myJpanel.add(
                blockerSelectionLabel,
                new CustomConstraints(0, 0, GridBagConstraints.WEST, 0.0, 0.0, GridBagConstraints.HORIZONTAL));

        myJpanel.add(
                blockerJComboBox,
                new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectCurrentBlocker();
                    }
                }
        );

        myJpanel.add(
                selectButton,
                new CustomConstraints(0, 2, GridBagConstraints.LINE_END, 0.2, 0.0, GridBagConstraints.NONE));

        JLabel selectedBlockersLabel = new JLabel("Selected Blockers:");
        myJpanel.add(
                selectedBlockersLabel,
                new CustomConstraints(0, 3, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        // Sub panel to show the list of selected blockers
        subPanel.setLayout(new GridBagLayout());

        myJpanel.add(
                new JScrollPane(subPanel),
                new CustomConstraints(0, 4, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.BOTH));

        // Probability range selection
        JLabel probabilitySelectionLabel = new JLabel("Select Probability Range:");
        myJpanel.add(
                probabilitySelectionLabel,
                new CustomConstraints(0, 5, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        // Sub panel for probability range
        JPanel probabilitySubPanel = new JPanel();
        probabilitySubPanel.setLayout(new GridBagLayout());

        JLabel startLabel = new JLabel("Start:");
        JLabel endLabel = new JLabel("End:");
        probabilitySubPanel.add(
                startLabel,
                new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        probabilitySubPanel.add(
                endLabel,
                new CustomConstraints(1, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        // Dropdowns for range selection
        JComboBox<String> startValue = new JComboBox<>();
        for(int i=0; i<probabilities.size()-1; i++){
            startValue.addItem(String.valueOf(probabilities.get(i)));
        }
        startValue.setSelectedIndex(0);

        JComboBox<String> endValue = new JComboBox<>();
        for(int i=1; i<probabilities.size(); i++){
            endValue.addItem(String.valueOf(probabilities.get(i)));
        }
        endValue.setSelectedIndex(0);

        startValue.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedIndex = startValue.getSelectedIndex();
                        endValue.removeAllItems();
                        for(int i=selectedIndex+1; i<probabilities.size(); i++){
                            endValue.addItem(String.valueOf(probabilities.get(i)));
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
                        startValue.setEnabled(state==2);
                        endValue.setEnabled(state==2);
                    }
                }
        );

        probabilitySubPanel.add(
                startValue,
                new CustomConstraints(0, 2, GridBagConstraints.WEST, 0.2, 0.1, GridBagConstraints.HORIZONTAL));

        probabilitySubPanel.add(
                endValue,
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
                        // Add logic to store probabilities in the backend
                    }
                }
        );

        myJpanel.add(
                submitButton,
                new CustomConstraints(0, 7, GridBagConstraints.EAST, GridBagConstraints.NONE));

        add(myJpanel);
    }

    private void refreshBlockers(){
        blockerJComboBox.removeAllItems();
        for(Blocker blocker: availableBlockers){
            blockerJComboBox.addItem(blocker.getName());
        }
    }

    private void selectCurrentBlocker(){
        int selectedBlockerIndex = blockerJComboBox.getSelectedIndex();
        Blocker selectedBlocker = availableBlockers.get(selectedBlockerIndex);
        selectedBlockers.add(selectedBlocker);
        subPanel.add(
                new JLabel(selectedBlocker.getName()),
                new CustomConstraints(0, selectedBlockers.size()-1, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.NONE));
        availableBlockers.remove(selectedBlockerIndex);
        refreshBlockers();
    }
}
